package com.baidubce.services.bcm.model.siteonce;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @Author panzhiwei01@baidu.com
 * @Date 2022/7/11 5:19 下午
 * @Description
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class HttpResponseWrapper<T> extends AbstractResponse {
    private T result;
    private Integer code;
}
