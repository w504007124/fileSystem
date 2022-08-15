package com.wh.file.service.impl;

import cn.hutool.log.StaticLog;
import com.wh.file.domain.web.req.BatchDownloadReq;
import com.wh.file.domain.web.req.FilesAddReq;
import com.wh.file.domain.web.req.GetFilesReq;
import com.wh.file.domain.web.req.GetRecycleBinReq;
import com.wh.file.domain.web.resp.FilesResp;
import com.wh.file.domain.web.resp.UploadResp;
import com.wh.file.mapper.FilesMapper;
import com.wh.file.service.FilesService;
import com.wh.file.utils.FileUtil;
import com.wh.file.utils.ResponseResult;
import com.wh.file.utils.SecurityUtils;
import com.wh.file.utils.fdfs.FastDFSClient;
import com.wh.file.utils.threadpool.FilesCallable;
import com.wh.file.utils.threadpool.GenerateThreadExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.zip.ZipOutputStream;

/**
 * 什么？我是真滴帅
 *
 * @author WuHao on 15:48 2022/6/23
 */
@Service
@Slf4j
public class FilesServiceImpl implements FilesService {


    @Autowired
    ThreadPoolTaskExecutor slowExecutor;

    @Autowired
    private FilesMapper filesMapper;

    @Autowired
    GenerateThreadExecutor threadPoolExecutor;

    @Autowired
    private FastDFSClient fastDFSClient;

    @Value("${prefixUrl}")
    private String prefixUrl;

    @Override
    public ResponseResult<List<UploadResp>> batchDownload(MultipartFile[] file) {
        try {
            List<UploadResp> pathList = new ArrayList<>();
            for (MultipartFile f : file) {
                if (!f.isEmpty()) {
                    UploadResp uploadResp = new UploadResp();
                    String fileName = f.getOriginalFilename();
                    Long fileSize = f.getSize();
                    // 调用接口上传照片
                    String path = fastDFSClient.uploadFile(f.getBytes(), fileName);
                    uploadResp.setFileName(fileName);
                    uploadResp.setFileUrl(path);
                    uploadResp.setFileSize(fileSize);
                    pathList.add(uploadResp);
                }
            }
            return ResponseResult.success(pathList); // 返回图片路径
        } catch (Exception e) {
            StaticLog.error("文件存储异常：{}", e);
            return ResponseResult.fail(ResponseResult.SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public void download(HttpServletResponse response, String fileUrl) {
        try {
            String storeAddress = fileUrl;
            if (fastDFSClient.getFileInfo(storeAddress) == null) {
                throw new RuntimeException("文件不存在");
            }

            byte [] buffer = fastDFSClient.downloadFile(storeAddress);
            String fileName = java.net.URLEncoder.encode("template","UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
//            response.setHeader("Content-Disposition", "attachment;");
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);

            OutputStream output = response.getOutputStream();
            output.write(buffer);
            output.flush();
            output.close();
        } catch (Exception e) {
            throw new RuntimeException("文件下载失败");
        }
    }

    @Override
    public HttpServletResponse downloadZip(BatchDownloadReq fileLists, HttpServletResponse response) {
        List<File> files = new ArrayList<>();
        if (null == fileLists.getFileList()) {
            throw new RuntimeException("文件列表不能为空");
        }

        long startTime = System.currentTimeMillis();
        log.info("多线程开始时间{}",startTime);

        // 存储线程的返回值
        List<Future<File>> filesFuture = new LinkedList<>();

        for (UploadResp file : fileLists.getFileList()) {
            FilesCallable task = new FilesCallable(prefixUrl, file);
            Future<File> future = slowExecutor.submit(task);
            filesFuture.add(future);
        }
        log.info("多线程获取list返回值:{}",filesFuture);

        long end = System.currentTimeMillis();
        log.info("多线程耗时:{}",end - startTime);

        for (Future<File> futures:filesFuture){
            try {
                files.add(futures.get());
            } catch (Exception e) {
                StaticLog.error("文件异步下载异常：{}", e);
                log.error("文件异步下载异常：{}", e);
                throw new RuntimeException("文件异步下载异常");
            }
        }
        log.info("files:{}",files);
        //打包凭证.zip与EXCEL一起打包
        try {
            String zipFilenameA = "files.zip";

            File fileA = new File(zipFilenameA);
            if (!fileA.exists()){
                fileA.createNewFile();
            }
            //创建文件输出流
            FileOutputStream fousa = new FileOutputStream(fileA);
            ZipOutputStream zipOutA = new ZipOutputStream(fousa);
            FileUtil.zipFile(files, zipOutA);
            zipOutA.close();
            fousa.close();
            return FileUtil.downloadZip(fileA,response);
        }catch (Exception e) {
            e.printStackTrace();
            StaticLog.error("文件压缩zip异常:{}", e);
        }
        return response;
    }

    @Override
    public List<FilesResp> getFileLists(GetFilesReq req) {
        if (req.getBusinessType() == null) {
            throw new RuntimeException("业务类型(0:文件,1:图片),业务类型不能为空");
        }
        Long userId = SecurityUtils.getUserId();
        req.setCreateBy(userId);
        List<FilesResp> list = filesMapper.getFileLists(req);
        log.info("获取文件列表{}: {}",req.getBusinessType(), list);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult saveFiles(List<FilesAddReq> req) {
        Long userId = SecurityUtils.getUserId();
        req.forEach(item -> {
            item.setCreateBy(userId);
            threadPoolExecutor.getTaskExecutor().execute(()-> {
                filesMapper.saveFiles(item);
            });
        });
        return ResponseResult.success("保存成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult delFiles(Long[] ids) {
        if (ids == null) {
            throw new RuntimeException("请先选择删除的文件");
        }
        int num = filesMapper.delFiles(ids);
        if (num < 1) {
            throw new RuntimeException("删除失败");
        }
        return ResponseResult.success("删除成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult recovery(Long id) {
        int num = filesMapper.recover(id);
        if (num < 1) {
            throw new RuntimeException("恢复失败");
        }
        return ResponseResult.success("恢复成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult delFile(Long id) {
        int num = filesMapper.delFile(id);
        if (num < 1) {
            throw new RuntimeException("删除成功");
        }
        return ResponseResult.success("删除成功");
    }

    @Override
    public ResponseResult getFileType() {
        List<String> result = filesMapper.getFileType();
        return ResponseResult.success(result);
    }

    @Override
    public List<FilesResp> getRecycleBinList(GetRecycleBinReq req) {
        Long userId = SecurityUtils.getUserId();
        req.setCreateBy(userId);
        List<FilesResp> list = filesMapper.getRecycleBinList(req.getCreateBy());
        log.info("获取回收站文件列表: {}", list);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delData() {
        log.info("定时任务开始时间: {}", System.currentTimeMillis());


    }
}

//    // 存储线程的返回值
//    List<Future<List<File>>> filesFuture = new LinkedList<>();
//        if (null == fileLists) {
//                throw new RuntimeException("文件列表不能为空");
//                }
//                List<File> files = new ArrayList<>();
//        for (UploadResp file : fileLists.getFileList()) {
//        Future<List<File>> future = slowExecutor.submit(() -> {
//        try {
//        List<File> fileList = new ArrayList<>();
//        HttpUtil.downloadFile(prefixUrl+file.getFileUrl(), "/"+ file.getFileName());
//        fileList.add(new File("/"+file.getFileName()));
//        return fileList;
//        } catch (Exception e){
//        StaticLog.error("文件下载异常：{}", e);
//        throw new RuntimeException("文件下载失败");
//        }
//        });
//        filesFuture.add(future);
//        }
//        for (Future<List<File>> futures:filesFuture){
//        files.addAll(futures.get());
//        }
