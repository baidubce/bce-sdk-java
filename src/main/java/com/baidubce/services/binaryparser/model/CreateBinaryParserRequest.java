package com.baidubce.services.binaryparser.model;

import com.baidubce.model.GenericAccountRequest;

/**
 * Created by yuanyoujun on 2017/9/2.
 */
public class CreateBinaryParserRequest extends GenericAccountRequest {
    private String name;
    private String endpoint;
    private String inputTopic;
    private String script;
    private String outputTopic;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getInputTopic() {
        return inputTopic;
    }

    public void setInputTopic(String inputTopic) {
        this.inputTopic = inputTopic;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getOutputTopic() {
        return outputTopic;
    }

    public void setOutputTopic(String outputTopic) {
        this.outputTopic = outputTopic;
    }
}
