package com.baidubce.services.bes.v3.model.plugin;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

import java.util.List;

@Data
public class ListCustomPluginsResponse extends AbstractBceResponse {
    private List<CustomPluginInfo> customPlugins;

    @Data
    public static class CustomPluginInfo {
        private String pluginName;
        private List<CustomPluginVersionInfo> versionInfos;
    }

    @Data
    public static class CustomPluginVersionInfo {
        private String version;
        private String description;
        private String status;
        private Long uploadTime;
    }
}
