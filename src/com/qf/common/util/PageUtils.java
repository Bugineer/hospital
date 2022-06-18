package com.qf.common.util;

import java.util.List;

public class PageUtils<T>{
    //当前页
    private  Integer currentPageNo;
    //页量
    private  Integer pageSize;
    //总记录数
    private  Integer totalPageCount;
    //总页数
    private   Integer totalPageSize;

    //数据库分页开始索引
    private Integer index;
    //集合
    private List<T> eList;

    public PageUtils(Integer currentPageNo, Integer pageSize, Integer totalPageCount) {
        this.currentPageNo = currentPageNo;
        this.pageSize = pageSize;
        this.totalPageCount = totalPageCount;
        setTotalPageSize();
        setIndex();
    }

    public Integer getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(Integer currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(Integer totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public Integer getTotalPageSize() {
        return totalPageSize;
    }

    //设置 总页数 = 总记录数 / 每页显示多少条
    private void setTotalPageSize() {
        //是否 有余
        if(totalPageCount % pageSize == 0) {
            totalPageSize = totalPageCount / pageSize;
        }else {
            totalPageSize = (totalPageCount / pageSize) + 1;
        }
    }

    public List<T> geteList() {
        return eList;
    }

    public void seteList(List<T> eList) {
        this.eList = eList;
    }

    public Integer getIndex() {
        return index;
    }


    private void setIndex() {
        //index = (pageNo - 1) * pageSize
        index = (currentPageNo - 1) * pageSize;
    }

}
