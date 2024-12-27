package com.baidubce.services.aihc.model.inference;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class AppChangeDetailResponse extends AbstractBceResponse {
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
        private String changeId;
        private String prev;
        private int changeType;
        private String shortDesc;
        private String creator;
        private int ctime;
        private String snapshot;
    }

    @Override
    public String toString() {
        return "AppChangeDetailResponse{" + "\n" +
                "\t errno = " + errno + "," + "\n" +
                "\t request_id = " + requestId + "," + "\n" +
                "\t data = " + data + "\n" +
                '}';
    }
}
