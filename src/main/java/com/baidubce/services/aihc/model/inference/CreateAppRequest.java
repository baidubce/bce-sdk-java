package com.baidubce.services.aihc.model.inference;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class CreateAppRequest extends AbstractBceRequest {
    private String appName;
    private String chipType;
    private int insCount;
    private ResPoolConf resPool;
    private StorageConf storage;
    private List<ContainerConf> containers;
    private AccessConf access;
    private LogConf log;
    private DeployConf deploy;
    private Misc misc;

    @Getter
    @Setter
    @ToString
    public static class ResPoolConf {
        private String resPoolId;
        private String queue;

        public ResPoolConf(String resPoolId, String queue) {
            this.resPoolId = resPoolId;
            this.queue = queue;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class StorageConf {
        private int shmSize;
        private List<VolumnConf> volumns;

        public StorageConf(int shmSize, List<VolumnConf> volumns) {
            this.shmSize = shmSize;
            this.volumns = volumns;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class VolumnConf {
        private String volumeType;
        private String volumnName;
        private PFSConfig pfs;
        private HostPathConfig hostpath;

        public VolumnConf(String volumeType, String volumnName, PFSConfig pfs, HostPathConfig hostpath) {
            this.volumeType = volumeType;
            this.volumnName = volumnName;
            this.pfs = pfs;
            this.hostpath = hostpath;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class PFSConfig {
        private String srcPath;

        public PFSConfig(String srcPath) {
            this.srcPath = srcPath;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class HostPathConfig {
        @JsonProperty("SrcPath")
        private String srcPath;

        public HostPathConfig(String srcPath) {
            this.srcPath = srcPath;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class AccessConf {
        private boolean publicAccess;
        private String eip;

        public AccessConf(boolean publicAccess, String eip) {
            this.publicAccess = publicAccess;
            this.eip = eip;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class LogConf {
        private boolean persistent;

        public LogConf(boolean persistent) {
            this.persistent = persistent;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class DeployConf {
        private CanaryStrategyConf canaryStrategy;

        public DeployConf(CanaryStrategyConf canaryStrategy) {
            this.canaryStrategy = canaryStrategy;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class CanaryStrategyConf {
        private int maxSurge;
        private int maxUnavailable;

        public CanaryStrategyConf(int maxSurge, int maxUnavailable) {
            this.maxSurge = maxSurge;
            this.maxUnavailable = maxUnavailable;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class Misc {
        private Map<String, String> podLabels;
        private Map<String, String> podAnnotations;

        public Misc(Map<String, String> podLabels, Map<String, String> podAnnotations) {
            this.podLabels = podLabels;
            this.podAnnotations = podAnnotations;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class ContainerConf {
        private String name;
        private int cpus;
        private int memory;
        private int cards;
        private List<String> runCmd;
        private List<String> runArgs;
        private List<PortConf> ports;
        private Map<String, String> env;
        private ImageConf image;
        private List<VolumnMountConf> volumeMounts;
        private ProbeConf startupsProbe;
        private ProbeConf readinessProbe;
        private ProbeConf livenessProbe;

        public ContainerConf(String name, int cpus, int memory, int cards, List<String> runCmd, List<String> runArgs, List<PortConf> ports
                , Map<String, String> env, ImageConf image, List<VolumnMountConf> volumeMounts, ProbeConf startupsProbe, ProbeConf readinessProbe
                , ProbeConf livenessProbe) {
            this.name = name;
            this.cpus = cpus;
            this.memory = memory;
            this.cards = cards;
            this.runCmd = runCmd;
            this.runArgs = runArgs;
            this.ports = ports;
            this.env = env;
            this.image = image;
            this.volumeMounts = volumeMounts;
            this.startupsProbe = startupsProbe;
            this.readinessProbe = readinessProbe;
            this.livenessProbe = livenessProbe;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class PortConf {
        private int port;

        public PortConf(int port) {
            this.port = port;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class ImageConf {
        private int imageType;
        private String imageAddr;
        private String imagePullUser;
        private String imagePullPass;

        public ImageConf(int imageType, String imageAddr, String imagePullUser, String imagePullPass) {
            this.imageType = imageType;
            this.imageAddr = imageAddr;
            this.imagePullUser = imagePullUser;
            this.imagePullPass = imagePullPass;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class VolumnMountConf {
        private String volumnName;
        private String dstPath;
        private boolean readOnly;

        public VolumnMountConf(String volumnName, String dstPath, boolean readOnly) {
            this.volumnName = volumnName;
            this.dstPath = dstPath;
            this.readOnly = readOnly;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class ProbeConf {
        private int initialDelaySeconds;
        private int timeoutSeconds;
        private int periodSeconds;
        private int successThreshold;
        private int failureThreshold;
        private ProbeHandlerConf handler;

        public ProbeConf(int initialDelaySeconds, int timeoutSeconds, int periodSeconds, int successThreshold, int failureThreshold, ProbeHandlerConf handler) {
            this.initialDelaySeconds = initialDelaySeconds;
            this.timeoutSeconds = timeoutSeconds;
            this.periodSeconds = periodSeconds;
            this.successThreshold = successThreshold;
            this.failureThreshold = failureThreshold;
            this.handler = handler;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class ProbeHandlerConf {
        private ExecAction exec;
        private HTTPGetAction httpGet;
        private TCPSocketAction tcpSocketAction;

        public ProbeHandlerConf(ExecAction exec, HTTPGetAction httpGet, TCPSocketAction tcpSocketAction) {
            this.exec = exec;
            this.httpGet = httpGet;
            this.tcpSocketAction = tcpSocketAction;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class ExecAction {
        private List<String> command;

        public ExecAction(List<String> command) {
            this.command = command;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class HTTPGetAction {
        private String path;
        private int port;

        public HTTPGetAction(String path, int port) {
            this.path = path;
            this.port = port;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class TCPSocketAction {
        private int port;

        public TCPSocketAction(int port) {
            this.port = port;
        }
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return CreateAppRequest with credentials.
     */
    public CreateAppRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
