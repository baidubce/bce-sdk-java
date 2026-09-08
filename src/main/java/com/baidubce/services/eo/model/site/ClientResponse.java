package com.baidubce.services.eo.model.site;

import com.baidubce.services.eo.model.JsonObject;

import java.util.Map;

/**
 * The node response header configuration of a site.
 *
 */
public class ClientResponse extends JsonObject {
    /**
     * The headers to add when responding to the client, keyed by the header name.
     */
    private Object addHeaders;

    /**
     * The headers to remove when responding to the client, keyed by the header name.
     */
    private Object removeHeaders;

    /**
     * @param addHeaders
     * @return this object
     */
    public ClientResponse withAddHeaders(Map<String, String> addHeaders) {
        this.addHeaders = addHeaders;
        return this;
    }

    /**
     * @return addHeaders
     */
    public Object getAddHeaders() {
        return addHeaders;
    }

    /**
     * @param addHeaders
     */
    public void setAddHeaders(Object addHeaders) {
        this.addHeaders = addHeaders;
    }

    /**
     * @param removeHeaders
     * @return this object
     */
    public ClientResponse withRemoveHeaders(Map<String, String> removeHeaders) {
        this.removeHeaders = removeHeaders;
        return this;
    }

    /**
     * @return removeHeaders
     */
    public Object getRemoveHeaders() {
        return removeHeaders;
    }

    /**
     * @param removeHeaders
     */
    public void setRemoveHeaders(Object removeHeaders) {
        this.removeHeaders = removeHeaders;
    }
}
