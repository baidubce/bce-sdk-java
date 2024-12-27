package com.baidubce.services.aihc.model.inference;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
public class ListChangeResponse extends AbstractBceResponse {
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
        private List<AppChangeRecord> list;
    }

    @Getter
    @Setter
    public static class AppChangeRecord {
        private String changeId;
        private String prev;
        private int changeType;
        private String shortDesc;
        private String creator;
        private int ctime;

        @Override
        public String toString() {
            return "AppChangeRecord{" + "\n" +
                    "\t\t changeId = " + changeId + "\n" +
                    "\t\t prev = " + prev + "\n" +
                    "\t\t changeType = " + changeType + "\n" +
                    "\t\t shortDesc = " + shortDesc + "\n" +
                    "\t\t creator = " + creator + "\n" +
                    "\t\t ctime = " + ctime + "\n" +
                    "\t }";
        }
    }

    @Override
    public String toString() {
        return "ListChangeResponse{" + "\n" +
                "\t errno = " + errno + "," + "\n" +
                "\t request_id = " + requestId + "," + "\n" +
                "\t data = " + data + "\n" +
                '}';
    }
}
