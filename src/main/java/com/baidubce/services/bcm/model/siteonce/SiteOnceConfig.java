package com.baidubce.services.bcm.model.siteonce;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteOnceConfig {

    /**HTTP/HTTPS请求方法*/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String method;
    /**HTTP/HTTPS请求方法为POST时的提交内容*/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String postContent;

    /**
     * RECURSION:递归解析 ITERATION:迭代解析（不设置，默认为RECURSION）
     */
    private ResolveTypeEnum resolveType = ResolveTypeEnum.RECURSION;
    /**
     * DNS服务器（可选，如无需求，不需要设置）
     */
    private String server;
    /**
     * 劫持白名单，形如:www.baidu.com:1.2.3.4|a.b.c.d|*
     * 每一个ip/cname用 "|" 分割，支持通配符
     * ipv4: 192.168.1.* 或者 192.168.1.1~254
     * ipv6: 2400:A480:aa:400:a1:b2:c3:*  或者 2400:A480:aa:400:a1:b2:C3:0~FFFF
     * cname: www.baidu.com 或者 *baidu.com 或者 www.*.com 或者 www.baidu.* 或者 *
     */
    private String kidnapWhite;

    /**PING发包数*/
    private int packetCount;

    /**TCP/UDP/FTP端口号*/
    private int port;
    /**UDP请求内容格式*/
    private int inputType;
    /**UDP请求内容*/
    private String input;
    /**UDP响应内容匹配格式*/
    private int outputType;
    /**UDP响应匹配内容*/
    private String expectedOutput;

    /**是否匿名登录*/
    private boolean anonymousLogin;
    /**FTP登陆用户名*/
    private String username;
    /**FTP登陆密码*/
    private String password;
}
