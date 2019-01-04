package com.huiyuan.web.model;

import java.io.Serializable;
import java.util.Map;

public class PageParam  implements Serializable{

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 每页数据量
     */
    private Integer pageSize;

    private Map<String,Integer> sortMap;

    public PageParam() {
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}

