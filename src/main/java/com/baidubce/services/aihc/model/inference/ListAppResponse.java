package com.baidubce.services.aihc.model.inference;

import com.baidubce.model.AbstractBceResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@Getter
@Setter
public class ListAppResponse extends AbstractBceResponse {

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
        /*
         * 应用个数
         */
        private int count;
        /*
         * 应用列表具体内容
         */
        private List<AppModel> list;
    }

    @Getter
    @Setter
    public static class AppModel {
        private String appId;
        private String appName;
        private String creator;
        private int ctime;
        private int mtime;
        private boolean publicAccess;
        private String region;
        private String resPoolId;
        private String resPoolName;
        private String resQueue;

        @Override
        public String toString() {
            return "AppModel{" + "\n" +
                    "\t\t appId = " + appId + "\n" +
                    "\t\t appName = " + appName  + "\n" +
                    "\t\t creator = " + creator + "\n" +
                    "\t\t ctime = " + ctime + "\n" +
                    "\t\t mtime = " + mtime + "\n" +
                    "\t\t publicAccess = " + publicAccess + "\n" +
                    "\t\t region = " + region + "\n" +
                    "\t\t resPoolId = " + resPoolId + "\n" +
                    "\t\t resPoolName = " + resPoolName + "\n" +
                    "\t\t resQueue = " + resQueue + "\n" +
                    "\t }";
        }
    }

    @Override
    public String toString() {
        return "LisAppResponse{" + "\n" +
                "\t errno = " + errno + "," + "\n" +
                "\t request_id = " + requestId + "," + "\n" +
                "\t data = " + data + "\n" +
                '}';
    }
}
