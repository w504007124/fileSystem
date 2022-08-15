package com.wh.file.controller;

import com.github.pagehelper.PageHelper;
import com.wh.file.controller.BasePageController.BasePageController;
import com.wh.file.controller.BasePageController.TableDataInfo;
import com.wh.file.domain.Files;
import com.wh.file.domain.web.req.*;
import com.wh.file.domain.web.resp.FilesResp;
import com.wh.file.domain.web.resp.UploadResp;
import com.wh.file.service.FilesService;
import com.wh.file.utils.ResponseResult;
import com.wh.file.utils.StringUtils;
import com.wh.file.utils.annotation.SysLogs;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 什么？我是真滴帅
 *
 * @author WuHao on 14:46 2022/6/23
 */

@RestController
@Api(tags = "文件上传")
@RequestMapping("/files")
public class FilesController extends BasePageController {

    @Autowired
    private FilesService filesService;

    @PostMapping("/batchUpload")
    @ApiOperation(value = "批量上传附件")
    @SysLogs(title = "批量上传附件")
    @PreAuthorize("@er.hasAuthority('system:common')")
    public ResponseResult<List<UploadResp>>uploadFormFile(@RequestParam("files")MultipartFile[] file) throws Exception {
        return filesService.batchDownload(file);
    }

    @GetMapping("/download")
    @ApiOperation(value = "下载附件")
    @SysLogs(title = "下载附件")
    public void download(HttpServletResponse response, String fileUrl) {
        filesService.download(response,fileUrl);
    }

    @PostMapping(value = "/downloadZip")
    @ApiOperation(value = "打包下载文件zip")
    @SysLogs(title = "打包下载文件zip")
    public HttpServletResponse downloadZip2(@RequestBody BatchDownloadReq fileLists, HttpServletResponse response)throws Exception {
        return filesService.downloadZip(fileLists, response) ;
    }

    @PostMapping("/getFileLists")
    @ApiOperation("获取文件列表")
    public TableDataInfo getFileLists(@Validated @RequestBody GetFilesReq req) {
        Integer pageNum = Math.toIntExact(req.getPageNum());
        Integer pageSize = Math.toIntExact(req.getPageSize());
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            PageHelper.startPage(pageNum, pageSize, "");
        }
        List<FilesResp> files = filesService.getFileLists(req);
        return getDataTable(files);
    }

    @PostMapping("/saveFiles")
    @ApiOperation(value = "保存文件到数据库")
    public ResponseResult saveFiles(@RequestBody List<FilesAddReq> req) {

        return filesService.saveFiles(req);
    }

    @DeleteMapping("/delFiles/{ids}")
    @ApiOperation(value = "批量删除文件--放回回收站")
    public ResponseResult delFiles(@PathVariable Long[] ids) {
        return filesService.delFiles(ids);
    }

    @PutMapping("/recovery/{id}")
    @ApiOperation("从回收站恢复")
    public ResponseResult recovery(@PathVariable("id") Long id) {
        return filesService.recovery(id);
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation("从回收站删除")
    public ResponseResult delFile(@PathVariable("id") Long id) {
        return filesService.delFile(id);
    }

    @GetMapping("/getFileType")
    @ApiOperation("查询文件类型list")
    public ResponseResult getFileType() {
        return filesService.getFileType();
    }

    @GetMapping("getReccleBinList")
    @ApiOperation("获取回收站的list")
    public TableDataInfo getReccleBinList(@RequestBody GetRecycleBinReq req) {
        Integer pageNum = Math.toIntExact(req.getPageNum());
        Integer pageSize = Math.toIntExact(req.getPageSize());
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            PageHelper.startPage(pageNum, pageSize, "desc");
        }
        List<FilesResp> files = filesService.getRecycleBinList(req);
        return getDataTable(files);
    }

    /**
     *  xxljob -定时任务删除回收站数据
     */
    @DeleteMapping("/delData")
    @ApiOperation("定时任务删除回收站数据")
    public void delData() {
        filesService.delData();
    }
}
