package com.wh.file.domain.web.req;

import com.wh.file.domain.web.resp.UploadResp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 什么？我是真滴帅
 *
 * @author WuHao on 16:26 2022/5/11
 */
@Data
@ApiModel("下载zip文件参数实体req")
public class BatchDownloadReq {

    /** 文件路径和名称 */
    @ApiModelProperty(value = "文件路径和名称list不能为空", required = true)
    @NotNull(message = "文件路径和名称list不能为空")
    private List<UploadResp> fileList;
}
