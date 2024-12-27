package com.baidubce.services.vod.v2.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.List;

public class MediaUpdateRequest extends AbstractBceRequest {

    private String name;
    private String description;
    private String categoryId;
    private List<String> addTags;
    private List<String> deleteTags;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public List<String> getAddTags() {
        return addTags;
    }

    public void setAddTags(List<String> addTags) {
        this.addTags = addTags;
    }

    public List<String> getDeleteTags() {
        return deleteTags;
    }

    public void setDeleteTags(List<String> deleteTags) {
        this.deleteTags = deleteTags;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public MediaUpdateRequest withName(String name) {
        this.name = name;
        return this;
    }

    public MediaUpdateRequest withDescription(String description) {
        this.description = description;
        return this;
    }

    public MediaUpdateRequest withCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public MediaUpdateRequest withAddTags(List<String> addTags) {
        this.addTags = addTags;
        return this;
    }

    public MediaUpdateRequest withDeleteTags(List<String> deleteTags) {
        this.deleteTags = deleteTags;
        return this;
    }

}
