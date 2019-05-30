package com.baidubce.services.bos.model;

/**
 * The Errors class for just resolve Delete Multiple Objects response Json
 */
public class Errors {

    /**
     * Object name for Delete Object wrong
     */
    private String key;

    /**
     * error code for Delete Object wrong
     */
    private String code;

    /**
     * error code for Delete Object wrong
     */
    private String message;

    /**
     * Gets key of Delete Multiple Objects response.
     * @return key of Delete Multiple Objects response.
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets key of Delete Multiple Objects response.
     * @param key The key of Delete Multiple Objects response.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Gets code of Delete Multiple Objects response.
     * @return code of Delete Multiple Objects response.
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets code of Delete Multiple Objects response.
     * @param code The code of Delete Multiple Objects response.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets message of Delete Multiple Objects response.
     * @return message of Delete Multiple Objects response.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message of Delete Multiple Objects response.
     * @param message The message of Delete Multiple Objects response.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Constructs a void constructor Errors for delete multiple objects errors.
     */
    public Errors() {
    }

    /**
     * Constructs a new Errors object for delete multiple objects errors.
     * @param key
     * @param code
     * @param message
     */
    public Errors(String key, String code, String message) {
        this.key = key;
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Errors{" + "key='" + key + '\'' + ", code='" + code + '\'' + ", message='" + message + '\'' + '}';
    }
}
