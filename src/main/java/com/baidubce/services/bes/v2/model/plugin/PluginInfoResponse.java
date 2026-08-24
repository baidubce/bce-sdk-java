package com.baidubce.services.bes.v2.model.plugin;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

/**
 * Response for default and custom plugin list.
 */
@Data
public class PluginInfoResponse extends AbstractBceResponse {
    private Boolean success;
    private Integer status;
    private Result result;

    @Data
    public static class Result {
        private List<Plugin> plugins;
        private List<DefaultPlugin> defaultPlugins;
    }

    @Data
    public static class Plugin {
        private String moduleType;
        private String pluginDesc;
        private String pluginName;
        private String pluginStatus;
    }

    @Data
    public static class DefaultPlugin {
        private String moduleType;
        private String pluginDesc;
        private String pluginName;
        private String pluginStatus;
        private PluginOperation pluginOperation;
    }

    @Data
    public static class PluginOperation {
        private Boolean enable;
        private String operation;
        private String text;
    }
}
