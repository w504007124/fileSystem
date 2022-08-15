package com.wh.file.domain.web.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 什么？我是真滴帅
 *
 * @author WuHao on 15:51 2022/6/23
 */
@Data
@ApiModel("多文件下载")
public class DownloadReq {
    @ApiModelProperty("文件路径")
    private String fileUrl;
}
