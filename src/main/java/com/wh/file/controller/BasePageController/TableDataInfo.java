package com.wh.file.controller.BasePageController;

import java.io.Serializable;
import java.util.List;

/**
 * 分页
 *
 * @author WuHao on 15:09 2022/2/16
 */
public class TableDataInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private long total;
    private List<?> data;
    private int code;
    private String msg;

    public TableDataInfo() {
    }

    public TableDataInfo(List<?> list, int total) {
        this.data = list;
        this.total = (long)total;
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getData() {
        return this.data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
