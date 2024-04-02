package com.baidubce.services.bcm.model.site;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

public class PageData<T> extends AbstractBceResponse {

    private List<T> content;
    private String query;
    private String[] fields = new String[0];
    private String[] orderBy = new String[0];
    private int pageNumber;
    private int pageSize;
    private int pageElements;
    private boolean last;
    private boolean first;
    private int totalPages;
    private long totalElements;

    public List<T> getContent() {
        return content;
    }

    public PageData<T> setContent(List<T> content) {
        if (content != null) {
            this.content = content;
        }
        return this;
    }

    public String getQuery() {
        return query;
    }

    public PageData<T> setQuery(String query) {
        this.query = query;
        return this;
    }

    public String[] getFields() {
        return fields;
    }

    public PageData<T> setFields(String[] fields) {
        this.fields = fields;
        return this;
    }

    public String[] getOrderBy() {
        return orderBy;
    }

    public PageData<T> setOrderBy(String[] orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public PageData<T> setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public PageData<T> setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getPageElements() {
        return pageElements;
    }

    public PageData<T> setPageElements(int pageElements) {
        this.pageElements = pageElements;
        return this;
    }

    public boolean isLast() {
        return last;
    }

    public PageData<T> setLast(boolean last) {
        this.last = last;
        return this;
    }

    public boolean isFirst() {
        return first;
    }

    public PageData<T> setFirst(boolean first) {
        this.first = first;
        return this;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public PageData<T> setTotalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public PageData<T> setTotalElements(long totalElements) {
        this.totalElements = totalElements;
        return this;
    }
}
