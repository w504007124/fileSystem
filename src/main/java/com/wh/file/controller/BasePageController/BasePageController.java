package com.wh.file.controller.BasePageController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wh.file.utils.ResponseResult;
import com.wh.file.utils.StringUtils;
import com.wh.file.utils.dateUtils.DateUtils;
import com.wh.file.utils.page.PageDomain;
import com.wh.file.utils.page.TableSupport;
import com.wh.file.utils.sql.SqlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

/**
 * 什么？我是真滴帅
 *
 * @author WuHao on 15:10 2022/2/16
 */
public class BasePageController {
    protected final Logger logger = LoggerFactory.getLogger(BasePageController.class);

    public BasePageController() {
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            public void setAsText(String text) {
                this.setValue(DateUtils.parseDate(text));
            }
        });
    }

    protected void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }

    }

    protected TableDataInfo getDataTable(List<?> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(200);
        rspData.setData(list);
        rspData.setMsg("查询成功");
        rspData.setTotal((new PageInfo(list)).getTotal());
        return rspData;
    }

    protected ResponseResult toAjax(int rows) {
        return rows > 0 ? ResponseResult.success() : ResponseResult.fail();
    }
}
