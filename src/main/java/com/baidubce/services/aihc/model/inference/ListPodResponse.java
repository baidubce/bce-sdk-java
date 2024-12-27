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
public class ListPodResponse extends AbstractBceResponse {

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
//        private String resPoolId;
//        private String resPoolName;
//        private String queue;
        private List<InsInfo> list;
    }

    @Getter
    @Setter
    public static class InsInfo {
        private String insID;
        private List<ContainerInfo> containers;
        private InsStatus status;

        @Override
        public String toString() {
            return "InsInfo{" + "\n" +
                    "\t\t insID = " + insID + "\n" +
                    "\t\t containers = " + containers + "\n" +
                    "\t\t status = " + status + "\n" +
                    "\t }";
        }
    }

    @Getter
    @Setter
    public static class ContainerInfo {
        private String containerId;
        private ContainerConf container;
        private ContainerStatus status;

        @Override
        public String toString() {
            return "ContainerInfo{" + "\n" +
                    "\t\t\t containerId = " + containerId + "\n" +
                    "\t\t\t container = " + container + "\n" +
                    "\t\t\t status = " + status + "\n" +
                    "\t\t }";
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
        private LifecycleHandlerConf postStart;
        private LifecycleHandlerConf preStop;

        @Override
        public String toString() {
            return "ContainerConf{" + "\n" +
                    "\t\t\t\t name = " + name + "\n" +
                    "\t\t\t\t cpus = " + cpus + "\n" +
                    "\t\t\t\t memory = " + memory + "\n" +
                    "\t\t\t\t cards = " + cards + "\n" +
                    "\t\t\t\t runCmd = " + runCmd + "\n" +
                    "\t\t\t\t runArgs = " + runArgs + "\n" +
                    "\t\t\t\t ports = " + ports + "\n" +
                    "\t\t\t\t env = " + env + "\n" +
                    "\t\t\t\t image = " + image + "\n" +
                    "\t\t\t\t volumeMounts = " + volumeMounts + "\n" +
                    "\t\t\t\t startupsProbe = " + startupsProbe + "\n" +
                    "\t\t\t\t readinessProbe = " + readinessProbe + "\n" +
                    "\t\t\t\t volumeMounts = " + livenessProbe + "\n" +
                    "\t\t\t\t startupsProbe = " + postStart + "\n" +
                    "\t\t\t\t readinessProbe = " + preStop + "\n" +
                    "\t\t\t }";
        }
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
    public static class VolumnMountConf {
        private String volumnName;
        private String dstPath;
        private boolean readOnly;
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
    public static class ContainerStatus {
        private String containerStatus;
        private int ctime;
        private String reason;
    }

    @Getter
    @Setter
    @ToString
    public static class InsStatus {
        private boolean blocked;
        private String insStatus;
        private int ctime;
        private int availableContainers;
        private int totalContainers;
        private String podIP;
        private String nodeIP;
    }

    @Override
    public String toString() {
        return "ListPodResponse{" + "\n" +
                "\t errno = " + errno + "," + "\n" +
                "\t request_id = " + requestId + "," + "\n" +
                "\t data = " + data + "\n" +
                '}';
    }
}
