package com.baidubce.services.eo.model.rule;

import com.baidubce.services.eo.model.JsonObject;

/**
 * A single match condition of a rule engine page rule.
 */
public class Rule extends JsonObject {
    /**
     * The match type, e.g. host, header, arg, suffix, path, fullUrl, basename, remoteAddrs,
     * method, cookie, remoteGeos, remoteIsp, directory.
     */
    private String matchFrom;

    /**
     * The operator, e.g. inValues, notInValues, exists, notExists, regex, notRegex.
     */
    private String operator;

    /**
     * The match key. Only valid when matchFrom is header, arg or cookie.
     */
    private String matchKey;

    /**
     * The value(s).
     */
    private Object values;

    /**
     * Whether to ignore case for the match value.
     */
    private Boolean ignoreCase;

    /**
     * @param matchFrom
     * @return this object
     */
    public Rule withMatchFrom(String matchFrom) {
        this.matchFrom = matchFrom;
        return this;
    }

    /**
     * @return matchFrom
     */
    public String getMatchFrom() {
        return matchFrom;
    }

    /**
     * @param matchFrom
     */
    public void setMatchFrom(String matchFrom) {
        this.matchFrom = matchFrom;
    }

    /**
     * @param operator
     * @return this object
     */
    public Rule withOperator(String operator) {
        this.operator = operator;
        return this;
    }

    /**
     * @return operator
     */
    public String getOperator() {
        return operator;
    }

    /**
     * @param operator
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * @param matchKey
     * @return this object
     */
    public Rule withMatchKey(String matchKey) {
        this.matchKey = matchKey;
        return this;
    }

    /**
     * @return matchKey
     */
    public String getMatchKey() {
        return matchKey;
    }

    /**
     * @param matchKey
     */
    public void setMatchKey(String matchKey) {
        this.matchKey = matchKey;
    }

    /**
     * @param values
     * @return this object
     */
    public Rule withValues(Object values) {
        this.values = values;
        return this;
    }

    /**
     * @return values
     */
    public Object getValues() {
        return values;
    }

    /**
     * @param values
     */
    public void setValues(Object values) {
        this.values = values;
    }

    /**
     * @param ignoreCase
     * @return this object
     */
    public Rule withIgnoreCase(Boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
        return this;
    }

    /**
     * @return ignoreCase
     */
    public Boolean getIgnoreCase() {
        return ignoreCase;
    }

    /**
     * @param ignoreCase
     */
    public void setIgnoreCase(Boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }
}
