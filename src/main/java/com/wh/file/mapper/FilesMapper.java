package com.wh.file.mapper;

import com.wh.file.domain.Files;
import com.wh.file.domain.web.req.FilesAddReq;
import com.wh.file.domain.web.req.GetFilesReq;
import com.wh.file.domain.web.resp.FilesResp;
import com.wh.file.utils.baseMapper.MyBaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 什么？我是真滴帅
 *
 * @author WuHao on 15:17 2022/6/25
 */
@Component
public interface FilesMapper  extends MyBaseMapper<Files> {

    /**
     * 查询文件list
     * @param param
     * @return
     */
    List<FilesResp> getFileLists(GetFilesReq param);

    /**
     * 插入
     * @param param
     * @return
     */
    int saveFiles(FilesAddReq param);

    /**
     * 批量删除文件--放回回收站
     * @param ids
     * @return
     */
    int delFiles(Long[] ids);

    /**
     * 恢复文件
     * @param id
     * @return
     */
    int recover(Long id);

    /**
     * 从回收站删除
     * @param id
     * @return
     */
    int delFile(Long id);

    /**
     * 获取文件类型 options
     * @return
     */
    List<String> getFileType();

    List<FilesResp> getRecycleBinList(@Param("createBy") Long createBy);

    int delData();
}
