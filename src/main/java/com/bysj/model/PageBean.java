package com.bysj.model;

import java.util.List;
/**
 * 
* <p>Title: PageBean</p>
* <p>Description: 分页类</p>
* <p>Copyright: Copyright (c) 2016</p>
* @author zerolu
* @date 2016年3月25日
* @version 1.0
 */
public class PageBean<T> {

    private int total;//总数
    private int size;//每页的数量
    private int pageNo;//第几页
    private List<T> list;//每页的内容
    
    public PageBean(int pageNo , int size){
        this.pageNo = pageNo;
        this.size = size;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public int getPageNo() {
        return pageNo;
    }
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
    public List<T> getList() {
        return list;
    }
    public void setList(List<T> list) {
        this.list = list;
    }
    
}
