package com.baidubce.services.bes.v3.model.plugin;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

import java.util.List;

@Data
public class ListSystemPluginsResponse extends AbstractBceResponse {
    private List<SystemPluginInfo> devPlugins;
    private List<SystemPluginInfo> prePlugins;

    @Data
    public static class SystemPluginInfo {
        private String pluginName;
        private String engine;
        private String description;
        private String descriptionUrl;
        private Integer order;
        private List<SystemPluginVersionInfo> versionInfos;
    }

    @Data
    public static class SystemPluginVersionInfo {
        private String version;
        private String changelog;
        private String changelogUrl;
        private String status;
    }
}
