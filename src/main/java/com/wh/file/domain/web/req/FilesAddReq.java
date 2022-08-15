package com.wh.file.domain.web.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 新增文件表
 *
 * @author wuhao
 * @since 2022-06-24 15:10:25
 */
@Data
@ApiModel("新增文件表实体类")
public class FilesAddReq{
    /**
    * 文件名称
    */
    @ApiModelProperty("文件名称")
    @NotBlank(message = "文件名称不能为空")
    private String fileName;
    /**
    * 文件类型
    */
    @ApiModelProperty("文件类型")
//    @NotBlank(message = "文件类型不能为空")
    private String fileType;
    /**
    * 文件大小(M)
    */
    @ApiModelProperty("文件大小(M)")
    private BigDecimal fileSize;
    /**
    * 文件地址
    */
    @ApiModelProperty("文件地址")
    @NotBlank(message = "文件地址不能为空")
    private String fileUrl;
    /**
    * 业务类型(0:文件,1:图片)
    */
    @ApiModelProperty("业务类型(0:文件,1:图片)")
    @NotBlank(message = "业务类型(0:文件,1:图片)不能为空")
    private Integer businessType;
    /**
    * 创建者
    */
    @ApiModelProperty("创建者")
    private Long createBy;
    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间")
    private Date createTime;

}
