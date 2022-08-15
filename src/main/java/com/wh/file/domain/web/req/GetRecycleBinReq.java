package com.wh.file.domain.web.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.wh.file.controller.BasePageController.BaseQueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 什么？我是真滴帅
 *
 * @author WuHao on 17:39 2022/6/25
 */
@Data
@ApiModel("查询回收站文件列表list实体类")
public class GetRecycleBinReq extends BaseQueryParam {
    /**
     * 创建者
     */
    @JsonSerialize(using= ToStringSerializer.class)
    @ApiModelProperty("创建者")
    private Long createBy;
}
