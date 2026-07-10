package com.baidubce.services.eo.model.rule;

import com.baidubce.services.eo.model.JsonObject;

import java.util.List;

/**
 * The origin request args configuration.
 */
public class OriginArg extends JsonObject {
    /**
     * true means ignore the args, false means keep the args.
     */
    private Boolean ignore;

    /**
     * The args to keep or ignore.
     */
    private List<String> args;

    /**
     * @param ignore
     * @return this object
     */
    public OriginArg withIgnore(Boolean ignore) {
        this.ignore = ignore;
        return this;
    }

    /**
     * @return ignore
     */
    public Boolean getIgnore() {
        return ignore;
    }

    /**
     * @param ignore
     */
    public void setIgnore(Boolean ignore) {
        this.ignore = ignore;
    }

    /**
     * @param args
     * @return this object
     */
    public OriginArg withArgs(List<String> args) {
        this.args = args;
        return this;
    }

    /**
     * @return args
     */
    public List<String> getArgs() {
        return args;
    }

    /**
     * @param args
     */
    public void setArgs(List<String> args) {
        this.args = args;
    }
}
