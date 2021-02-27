package com.lzc.crm.base;


/**
 * 乐字节：专注线上IT培训
 * 答疑老师微信：lezijie
 */
public class BaseQuery {
    private Integer page=1;
    private Integer limit=10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
