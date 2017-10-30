package com.baidubce.services.vodpro.model.request;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.vodpro.model.common.Path;

/**
 * Created on 17/8/24
 *
 * @author liumin08
 */
public class GetMediaRequest extends AbstractBceRequest {

    private String projectName;

    private String spaceName;
    
    private Path path;

    private String type;

    @Override
    public GetMediaRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "GetMediaRequest{"
                + "projectName='" + projectName + '\''
                + ", spaceName='" + spaceName + '\''
                + ", path=" + path
                + ", type=" + type
                + '}';
    }
}
