package com.wh.file.utils.threadpool;

import cn.hutool.http.HttpUtil;
import com.wh.file.domain.web.resp.UploadResp;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * 什么？我是真滴帅
 *
 * @author WuHao on 16:31 2022/6/23
 */
public class FilesCallable implements Callable<File> {


    private String prefixUrl;

    private UploadResp file;

    public FilesCallable(String prefixUrl, UploadResp file) {
        this.prefixUrl = prefixUrl;
        this.file = file;
    }

    @Override
    public File call() throws Exception {
        HttpUtil.downloadFile(prefixUrl+file.getFileUrl(), "/"+ file.getFileName());
        return new File("/"+file.getFileName());
    }
}
