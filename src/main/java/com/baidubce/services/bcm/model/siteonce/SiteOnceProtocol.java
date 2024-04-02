package com.baidubce.services.bcm.model.siteonce;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum SiteOnceProtocol {
    HTTP,
    HTTPS,
    PING,
    TCP,
    UDP,
    FTP,
    DNS;

    private static final Map<SiteOnceProtocol, List<String>> PROTOCOL_METRIC_MAP =
            new HashMap<SiteOnceProtocol, List<String>>(){{
        put(HTTP, new ArrayList<String>() {{
            add(SiteConstant.SITE_ONCE_TOTAL_TIME);
            add(SiteConstant.SITE_ONCE_DNS_TIME);
            add(SiteConstant.SITE_ONCE_CONNECT_TIME);
            add(SiteConstant.SITE_ONCE_REDIRECT_TIME);
            add(SiteConstant.SITE_ONCE_FIRST_BYTE_TIME);
        }});
        put(HTTPS, new ArrayList<String>() {{
            add(SiteConstant.SITE_ONCE_TOTAL_TIME);
            add(SiteConstant.SITE_ONCE_DNS_TIME);
            add(SiteConstant.SITE_ONCE_CONNECT_TIME);
            add(SiteConstant.SITE_ONCE_REDIRECT_TIME);
            add(SiteConstant.SITE_ONCE_FIRST_BYTE_TIME);
        }});
        put(PING, new ArrayList<String>() {{
            add(SiteConstant.SITE_ONCE_AVGRTT_TIME);
            add(SiteConstant.SITE_ONCE_PACKET_LOSS);
        }});
        put(TCP, new ArrayList<String>() {{
            add(SiteConstant.SITE_ONCE_TOTAL_TIME);
            add(SiteConstant.SITE_ONCE_CONNECT_TIME);
            add(SiteConstant.SITE_ONCE_DNS_TIME);
        }});
        put(UDP, new ArrayList<String>() {{
            add(SiteConstant.SITE_ONCE_TOTAL_TIME);
            add(SiteConstant.SITE_ONCE_DNS_TIME);
        }});
        put(FTP, new ArrayList<String>() {{
            add(SiteConstant.SITE_ONCE_TOTAL_TIME);
            add(SiteConstant.SITE_ONCE_CONNECT_TIME);
            add(SiteConstant.SITE_ONCE_DNS_TIME);
        }});
        put(DNS, new ArrayList<String>() {{
            add(SiteConstant.SITE_ONCE_TOTAL_TIME);
            add(SiteConstant.SITE_ONCE_DNS_TIME);
            add(SiteConstant.SITE_ONCE_DNS_KIDNAP);
        }});
    }};

    public static Map<SiteOnceProtocol, List<String>> getProtocolMetricMap() {
        return PROTOCOL_METRIC_MAP;
    }

}
