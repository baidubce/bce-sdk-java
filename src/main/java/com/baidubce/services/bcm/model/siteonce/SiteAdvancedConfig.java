package com.baidubce.services.bcm.model.siteonce;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SiteAdvancedConfig {
    /**HTTP/HTTPS请求头各字段*/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cookies;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userAgent;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String host;

    /**HTTP/HTTP响应Code*/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String responseCode = "";
    /**HTTP/HTTP响应内容匹配*/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String responseCheck = "";
    /**HTTP/HTTP验证用户名与密码*/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String username = "";
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password = "";

    /**TCP请求内容格式*/
    private int inputType;
    /**TCP请求内容*/
    private String input;
    /**TCP响应内容匹配格式*/
    private int outputType;
    /**TCP响应匹配内容*/
    private String expectedOutput;

}
