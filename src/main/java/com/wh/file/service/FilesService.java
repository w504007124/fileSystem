package com.wh.file.service;

import com.wh.file.domain.web.req.BatchDownloadReq;
import com.wh.file.domain.web.req.FilesAddReq;
import com.wh.file.domain.web.req.GetFilesReq;
import com.wh.file.domain.web.req.GetRecycleBinReq;
import com.wh.file.domain.web.resp.FilesResp;
import com.wh.file.domain.web.resp.UploadResp;
import com.wh.file.utils.ResponseResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 什么？我是真滴帅
 *
 * @author WuHao on 15:48 2022/6/23
 */
public interface FilesService {

    // 批量上传附件
    ResponseResult<List<UploadResp>> batchDownload(MultipartFile[] file);

    // 下载附件
    void download(HttpServletResponse response, String fileUrl);

    // 打包下载文件zip
    HttpServletResponse downloadZip(BatchDownloadReq fileLists, HttpServletResponse response );

    // 获取文件列表
    List<FilesResp> getFileLists(GetFilesReq req);

    // 保存文件到数据库
    ResponseResult saveFiles(List<FilesAddReq> req);

    // 批量删除文件--放回回收站
    ResponseResult delFiles(Long[] ids);

    // 从回收站恢复
    ResponseResult recovery(Long id);

    // 从回收站删除
    ResponseResult delFile(Long id);

    // 获取文件列表
    ResponseResult getFileType();

    //获取回收站的list
    List<FilesResp> getRecycleBinList(GetRecycleBinReq req);

    // 定时任务，删除回收站数据
    void delData();
}
