package com.baidubce.services.media.model;

import static com.baidubce.util.Validate.checkNotNull;
import static com.baidubce.util.Validate.checkStringNotEmpty;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class CreateTranscodingJobRequest extends AbstractBceRequest {

    private String pipelineName = null;
    private Source source       = null;
    private Target target       = null;

    /**
     * 任务所属的pipelineName
     **/
    public String getPipelineName() {
        return pipelineName;
    }

    public void setPipelineName(String pipelineName) {
        checkStringNotEmpty(pipelineName, "The parameter pipelineName should NOT be null or empty string.");
        this.pipelineName = pipelineName;
    }

    public CreateTranscodingJobRequest withPipelineName(String pipelineName) {
        checkStringNotEmpty(pipelineName, "The parameter pipelineName should NOT be null or empty string.");
        this.pipelineName = pipelineName;
        return this;
    }

    /**
     * 输入的原始信息的集合
     **/
    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        checkNotNull(source, "The parameter source should NOT be null.");
        this.source = source;
    }

    public CreateTranscodingJobRequest withSource(Source source) {
        checkNotNull(source, "The parameter source should NOT be null.");
        this.source = source;
        return this;
    }

    /**
     * 输出信息的结合
     **/
    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        checkNotNull(target, "The parameter target should NOT be null.");
        this.target = target;
    }

    public CreateTranscodingJobRequest withTarget(Target target) {
        checkNotNull(target, "The parameter target should NOT be null.");
        this.target = target;
        return this;
    }

    public CreateTranscodingJobRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateJobRequest {\n");

        sb.append("    pipelineName: ").append(pipelineName).append("\n");
        sb.append("    source: ").append(source).append("\n");
        sb.append("    target: ").append(target).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
