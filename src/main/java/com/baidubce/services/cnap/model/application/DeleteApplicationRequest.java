package com.baidubce.services.cnap.model.application;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The request for delete application.
 */
public class DeleteApplicationRequest extends AbstractBceRequest {


    /**
     * The id of workspace.
     */
    private String workspaceID;

    /**
     * The id of application.
     */
    private String applicationID;

    /**
     * The flag which indicates ignore under lay error.
     */
    private boolean ignoreUnderlayError;

    /**
     * The flag which indicates skip delete under lay.
     */
    private boolean skipDeleteUnderlay;

    public String getWorkspaceID() {
        return workspaceID;
    }

    public void setWorkspaceID(String workspaceID) {
        this.workspaceID = workspaceID;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    public void setIgnoreUnderlayError(boolean ignoreUnderlayError) {
        this.ignoreUnderlayError = ignoreUnderlayError;
    }

    public boolean getIgnoreUnderlayError() {
        return ignoreUnderlayError;
    }

    public void setSkipDeleteUnderlay(boolean skipDeleteUnderlay) {
        this.skipDeleteUnderlay = skipDeleteUnderlay;
    }

    public boolean getSkipDeleteUnderlay() {
        return skipDeleteUnderlay;
    }

    public DeleteApplicationRequest withWorkspaceID(String workspaceID) {
        this.setWorkspaceID(workspaceID);
        return this;
    }

    public DeleteApplicationRequest withApplicationID(String applicationID) {
        this.setApplicationID(applicationID);
        return this;

    }

    public DeleteApplicationRequest withIgnoreUnderlayError(boolean ignoreUnderlayError) {
        this.setIgnoreUnderlayError(ignoreUnderlayError);
        return this;
    }

    public DeleteApplicationRequest withSkipDeleteUnderlay(boolean skipDeleteUnderlay) {
        this.setSkipDeleteUnderlay(skipDeleteUnderlay);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return request with credentials.
     */
    @Override
    public DeleteApplicationRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
