package com.projectjavasneaker.backendis216.payload.response;

import java.util.Optional;

public class PageResponse {
    private Optional<Integer> page;
    private Integer per_page;
    private long total;
    private Integer total_pages;

    private Object data;

    public PageResponse(){}

    public PageResponse(Optional<Integer> page, Integer per_page, long total, Integer total_pages, Object data) {
        this.page = page;
        this.per_page = per_page;
        this.total = total;
        this.total_pages = total_pages;
        this.data = data;
    }

    public Optional<Integer> getPage() {
        return page;
    }

    public void setPage(Optional<Integer> page) {
        this.page = page;
    }

    public Integer getPer_page() {
        return per_page;
    }

    public void setPer_page(Integer per_page) {
        this.per_page = per_page;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
