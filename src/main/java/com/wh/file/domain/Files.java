package com.wh.file.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 文件表(Files)实体类
 *
 * @author makejava
 * @since 2022-06-16 15:10:25
 */
@ApiModel(description = "文件表(Files)实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "files")
public class Files implements Serializable {
    private static final long serialVersionUID = 800219850864581641L;
    /**
    * id
    */
    @Id
    @JsonSerialize(using= ToStringSerializer.class)
    @ApiModelProperty("id")
    private Long id;
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
    * 文件大小(M)
    */
    @ApiModelProperty("文件大小(M)")
    private BigDecimal fileSize;
    /**
    * 文件地址
    */
    @ApiModelProperty("文件地址")
    private String fileUrl;
    /**
    * 业务类型(0:文件,1:图片)
    */
    @ApiModelProperty("业务类型(0:文件,1:图片)")
    private Integer businessType;
    /**
    * 是否删除(0:正常,1:已删除)
    */
    @ApiModelProperty("是否删除(0:正常,1:已删除)")
    private Integer isDelete;
    /**
    * 创建者
    */
    @JsonSerialize(using= ToStringSerializer.class)
    @ApiModelProperty("创建者")
    private Long createBy;
    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间")
    private Date createTime;

}
