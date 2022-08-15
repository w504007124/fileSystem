package com.wh.file.controller.BasePageController;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wh
 * @Description
 * @date 2021/10/14
 */
@Data
public class BaseQueryParam {
    @ApiModelProperty("")
    private Long pageSize;
    @ApiModelProperty("")
    private Long pageNum;
}
