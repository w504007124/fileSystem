package com.wh.file.domain.web.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.wh.file.controller.BasePageController.BaseQueryParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 什么？我是真滴帅
 *
 * @author WuHao on 11:09 2022/6/24
 */
@Data
@ApiModel("查询文件列表list实体类")
public class GetFilesReq extends BaseQueryParam {
    /**
     * 文件名称
     */
    @ApiModelProperty("文件名称")
    private String fileName;
    /**
     * 文件类型
     */
    @ApiModelProperty("文件类型")
    private String fileType;
    /**
     * 业务类型(0:文件,1:图片)
     */
    @ApiModelProperty("业务类型(0:文件,1:图片)")
    @NotNull(message = "文件类型不能为空")
    private Integer businessType;
    /**
     * 创建者
     */
    @JsonSerialize(using= ToStringSerializer.class)
    @ApiModelProperty("创建者")
    private Long createBy;
}
