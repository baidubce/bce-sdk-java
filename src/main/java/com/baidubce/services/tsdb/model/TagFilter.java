package com.baidubce.services.tsdb.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Tag filter.
 *
 * @author linpengxiang
 */
public class TagFilter {

    /**
     * The tag key.
     */
    private String tag;

    /**
     * The tag values in which the tag value of this tag key should be.
     */
    private List<String> in = new ArrayList<String>();

    /**
     * The tag values in which the tag value of this tag key should not be.
     */
    private List<String> notIn = new ArrayList<String>();

    /**
     * The like pattern that tag value should match.
     */
    private String like;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<String> getIn() {
        return in;
    }

    public void setIn(List<String> in) {
        this.in = in;
    }

    public List<String> getNotIn() {
        return notIn;
    }

    public void setNotIn(List<String> notIn) {
        this.notIn = notIn;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public TagFilter withTag(String tag) {
        this.tag = tag;
        return this;
    }

    public TagFilter withIn(List<String> in) {
        this.notIn = null;
        this.in = in;
        return this;
    }

    public TagFilter withNotIn(List<String> notIn) {
        this.in = null;
        this.notIn = notIn;
        return this;
    }

    public TagFilter addIn(String tagValue) {
        if (in == null) {
            this.notIn = null;
            in = new ArrayList<String>();
        }
        in.add(tagValue);
        return this;
    }

    public TagFilter addNotIn(String tagValue) {
        if (notIn == null) {
            this.in = null;
            notIn = new ArrayList<String>();
        }
        notIn.add(tagValue);
        return this;
    }

    public TagFilter like(String like) {
        this.like = like;
        return this;
    }

}
