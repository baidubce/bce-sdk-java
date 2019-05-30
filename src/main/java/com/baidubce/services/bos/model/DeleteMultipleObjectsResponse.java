package com.baidubce.services.bos.model;

import java.util.List;

/**
 * Delete Multiple Objects Response
 */
public class DeleteMultipleObjectsResponse extends BosResponse {

    /**
     * the errors message during DeleteMultipleObjects.
     */
    private  List<Errors> errors;

    /**
     * Gets the errors message during DeleteMultipleObjects.
     * @return the errors message for delete multiple objects.
     */
    public List<Errors> getErrors() {
        return errors;
    }

    /**
     * Sets the errors message during DeleteMultipleObjects.
     * @param the errors for delete multiple objects.
     */
    public void setErrors(List<Errors> errors) {
        this.errors = errors;
    }

    /**
     * Constructs a void constructor for delete multiple objects.
     */
    public DeleteMultipleObjectsResponse() {
    }

    /**
     * Constructs a new DeleteMultipleObjectsResponse object for delete multiple objects.
     * @param errors
     */
    public DeleteMultipleObjectsResponse(List<Errors> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "DeleteMultipleObjectsResponse{"
                + "errors=" + errors
                + '}';
    }
}
