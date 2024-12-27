package com.baidubce.services.aihc.model.inference;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class AppDetailsResponse extends AbstractBceResponse {
    /*
     * 错误码
     */
    private int errno;
    /*
     * 访问id
     */
    @JsonProperty("request_id")
    private String requestId;
    /*
     * data数据
     */
    private Data data;

    @Getter
    @Setter
    @ToString
    public static class Data {
        private AppConf app;
        private AppStatus status;
        private String creator;
        private int ctime;
        private int mtime;
    }

    @Getter
    @Setter
    @ToString
    public static class AppConf {
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
    }

    @Getter
    @Setter
    @ToString
    public static class ResPoolConf {
        private String resPoolId;
        private String resPoolName;
        private String queue;
        private String subnetID;
        private String vpcID;
    }

    @Getter
    @Setter
    @ToString
    public static class StorageConf {
        private int shmSize;
        private List<VolumnConf> volumns;
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
        private LifecycleHandlerConf postStart;
        private LifecycleHandlerConf preStop;
    }

    @Getter
    @Setter
    @ToString
    public static class PortConf {
        private String protocol;
        private String name;
        private int port;
    }

    @Getter
    @Setter
    @ToString
    public static class ImageConf {
        private int imageType;
        private String imageAddr;
        private String imagePullUser;
        private String imagePullPass;
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
    }

    @Getter
    @Setter
    @ToString
    public static class ProbeHandlerConf {
        private ExecAction exec;
        private HTTPGetAction httpGet;
        private TCPSocketAction tcpSocketAction;
    }

    @Getter
    @Setter
    @ToString
    public static class LifecycleHandlerConf {
        private ExecAction exec;
        private HTTPGetAction httpGet;
        private TCPSocketAction tcpSocket;
        private int sleepSec;
    }

    @Getter
    @Setter
    @ToString
    public static class ExecAction {
        private List<String> command;
    }

    @Getter
    @Setter
    @ToString
    public static class HTTPGetAction {
        private String path;
        private int port;
    }

    @Getter
    @Setter
    @ToString
    public static class TCPSocketAction {
        private int port;
    }


    @Getter
    @Setter
    @ToString
    public static class VolumnMountConf {
        private String volumnName;
        private String dstPath;
        private boolean readOnly;
    }

    @Getter
    @Setter
    @ToString
    public static class VolumnConf {
        private String volumeType;
        private String volumnName;
        private PFSConfig pfs;
        private HostPathConfig hostpath;
    }

    @Getter
    @Setter
    @ToString
    public static class PFSConfig {
        private String instanceId;
        private String instanceType;
        private String hostMountPath;
        private String posixMountTargets;
        private String clusterIP;
        private String clientID;
        private String clusterPort;
        private String srcPath;
    }

    @Getter
    @Setter
    @ToString
    public static class HostPathConfig {
        @JsonProperty("SrcPath")
        private String srcPath;
    }

    @Getter
    @Setter
    @ToString
    public static class AppStatus {
        private AccessIPConf accessIPs;
        private List<AccessPortConf> accessPorts;
        private String blbShortId;
    }

    @Getter
    @Setter
    @ToString
    public static class AccessIPConf {
        private String internal;
        private String external;
    }

    @Getter
    @Setter
    @ToString
    public static class AccessPortConf {
        private String name;
        private long containerPort;
        private long servicePort;
    }

    @Getter
    @Setter
    @ToString
    public static class AccessConf {
        private boolean publicAccess;
        private String eip;
    }

    @Getter
    @Setter
    @ToString
    public static class LogConf {
        private boolean persistent;
    }

    @Getter
    @Setter
    @ToString
    public static class DeployConf {
        private CanaryStrategyConf canaryStrategy;
    }

    @Getter
    @Setter
    @ToString
    public static class CanaryStrategyConf {
        private int maxSurge;
        private int maxUnavailable;
    }

    @Getter
    @Setter
    @ToString
    public static class Misc {
        private Map<String, String> podLabels;
        private Map<String, String> podAnnotations;
        private long gracePeriodSec;
    }

    @Override
    public String toString() {
        return "AppDetailsResponse{" + "\n" +
                "\t errno = " + errno + "," + "\n" +
                "\t request_id = " + requestId + "," + "\n" +
                "\t data = " + data + "\n" +
                '}';
    }
}
