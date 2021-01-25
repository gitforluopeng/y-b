package com.lp.base;

import java.io.Serializable;
import java.util.Map;

public class PageParam implements Serializable {

    private static final long serialVersionUID = 5040562247837223988L;

    /**
     * 当前页数
     */
    private Integer pageNum;
    /**
     * 分页大小
     */
    private Integer pageSize;
    /**
     * 开始行
     */
    private Integer startNum;
    /**
     * oracle才用这个
     */
    private Integer endNum;

    public PageParam() {
        this.pageNum = 1;
        this.pageSize = 10;
    }

    public PageParam(Integer pageNum, Integer pageSize) {
        super();
        this.pageNum = pageNum < 1 ? 1 : pageNum;
        this.pageSize = pageSize < 1 ? 10 : pageSize;
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

    public Integer getStartNum() {
        return (pageNum - 1) > 0 ? (pageNum - 1) * pageSize : 0;
    }

    public void setStartNum(Integer startNum) {
        this.startNum = startNum;
    }

    public Integer getEndNum() {
        return pageSize * pageSize;
    }

    public void setEndNum(Integer endNum) {
        this.endNum = endNum;
    }

    public Map<String, Object> toMap() {
        return cn.hutool.core.bean.BeanUtil.beanToMap(this);
    }
}
