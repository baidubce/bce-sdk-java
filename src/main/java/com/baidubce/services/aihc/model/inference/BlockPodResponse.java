package com.baidubce.services.aihc.model.inference;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BlockPodResponse extends AbstractBceResponse {
    /*
     * 错误码
     */
    private int errno;
    /*
     * 访问id
     */
    @JsonProperty("request_id")
    private String requestId;

    @Override
    public String toString() {
        return "BlockPodResponse{" + "\n" +
                "\t errno = " + errno + "," + "\n" +
                "\t request_id = " + requestId + "," + "\n" +
                '}';
    }
}
