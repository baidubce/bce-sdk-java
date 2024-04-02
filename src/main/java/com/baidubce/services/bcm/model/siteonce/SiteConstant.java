package com.baidubce.services.bcm.model.siteonce;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by pangyangyang on 2021/02/22
 */
public class SiteConstant {

    public static final long SIXTY_SECONDS = 60L;

    public static final String SITE_SERVICE_NAME = "BCM_SITE";
    public static final List<Integer> CYCLE_LIST = Arrays.asList(
            10, 30, 60, 120, 300, 600, 900, 1800, 3600, 7200, 10800);
    public static final int IDC_LIMIT = 5;
    public static final int DEFAULT_HTTP_RESPONSE_CODE_CHECK = 200;
    public static final int DEFAULT_PING_PACKET_COUNT = 1;
    public static final int DEFAULT_CYCLE = 60;
    public static final String PROV_DIMENTION = "prov";
    public static final String ISP_DIMENTION = "isp";
    public static final String TASK_ID = "taskId";
    public static final String FIRST_UPPERCASE_TASK_ID = "TaskId";
    public static final String SITE_CYCLE = "CYCLE";
    public static final String AGGR_SITE_CYCLE = "cycle";
    public static final String SITE_USERID = "UserId";
    public static final String SITE_REGION = "bj";
    public static final String PACKET_LOSS = "packetLoss";
    public static final String A = "A";
    public static final String AAAA = "AAAA";
    public static final String CNAME = "CNAME";
    public static final Integer SITE_IS_DELETE_FLAG = 1;
    public static final Integer SITE_NO_DELETE_FLAG = 0;
    public static final String REACH_EXPECT = "符合预期";
    public static final String NO_REACH_EXPECT = "解析结果不符合预期";
    public static final String NOT_MATCH = "-";
    public static final String SITE_AUTO_RENEW_REGION = "global";
    public static final String SITE_AUTO_RENEW_SERVICE = "BCM";

    public static final String SITE_ONCE_CONNECT_TIME = "connectTime";
    public static final String SITE_ONCE_DNS_TIME = "dnsTime";
    public static final String SITE_ONCE_TOTAL_TIME = "totalTime";
    public static final String SITE_ONCE_AVGRTT_TIME = "avgRTT";
    public static final String SITE_ONCE_REDIRECT_TIME = "redirectTime";
    public static final String SITE_ONCE_FIRST_BYTE_TIME = "firstByteTime";
    public static final String SITE_ONCE_PACKET_LOSS = "packetLoss";
    public static final String SITE_ONCE_DNS_KIDNAP = "dnsKidnap";

    public static final String SITE_IP_RUNNING = "RUNNING";
    public static final String SITE_IP_STOPPED = "STOPPED";

    public static final String IP_ERR_RATE = "errRate";
    public static final String IP_TOTAL_TIME = "totalTime";
    public static final String IP_PACKET_LOSS = "packetLoss";
    public static final String IP_RTT = "rtt";

    public static final String SITE_HTTPS_PROTOCOL = "https://";
    public static final String SITE_HTTP_PROTOCOL = "http://";

    public static final List<String> SITE_AGGR_METRIC_NAME = Arrays.asList(
            "connectTime", "dnsTime", "firstByteTime", "packetLoss", "totalTime", "success",
            "redirectTime", "tlsTime", "downloadTime");

    public static final Map<String, String> METRIC_NAME = new HashMap();

    public static final Map<String, String> AREA_MAP = new HashMap();
    public static final Map<String, String> ISP_MAP = new HashMap();
    public static final Map<String, String> AVERAGE_MAP = new HashMap();
    public static final Map<String, String> AGGR_GROUP_BY_MAP = new HashMap();
    public static final Map<String, String> PROV_ISP_MAP = new HashMap();

    static {
        AREA_MAP.put("anhui", "安徽");
        AREA_MAP.put("beijing", "北京");
        AREA_MAP.put("fujian", "福建");
        AREA_MAP.put("gansu", "甘肃");
        AREA_MAP.put("guangdong", "广东");
        AREA_MAP.put("guangxi", "广西");
        AREA_MAP.put("guizhou", "贵州");
        AREA_MAP.put("hainan", "海南");
        AREA_MAP.put("hebei", "河北");
        AREA_MAP.put("henan", "河南");
        AREA_MAP.put("heilongjiang", "黑龙江");
        AREA_MAP.put("hubei", "湖北");
        AREA_MAP.put("hunan", "湖南");
        AREA_MAP.put("jilin", "吉林");
        AREA_MAP.put("jiangsu", "江苏");
        AREA_MAP.put("jiangxi", "江西");
        AREA_MAP.put("liaoning", "辽宁");
        AREA_MAP.put("neimenggu", "内蒙古");
        AREA_MAP.put("ningxia", "宁夏");
        AREA_MAP.put("qinghai", "青海");
        AREA_MAP.put("shandong", "山东");
        AREA_MAP.put("shanxi", "山西");
        AREA_MAP.put("shaanxi", "陕西");
        AREA_MAP.put("shanghai", "上海");
        AREA_MAP.put("sichuan", "四川");
        AREA_MAP.put("tianjin", "天津");
        AREA_MAP.put("xizang", "西藏");
        AREA_MAP.put("xinjiang", "新疆");
        AREA_MAP.put("zhejiang", "浙江");
        AREA_MAP.put("chongqing", "重庆");
        AREA_MAP.put("hunnan", "云南");

        ISP_MAP.put("CHINANET", "电信");
        ISP_MAP.put("CMNET", "移动");
        ISP_MAP.put("UNICOM", "联通");

        AVERAGE_MAP.put("average", "全网均值");

        AGGR_GROUP_BY_MAP.put("isp", "userId&serviceName&taskId&isp");
        AGGR_GROUP_BY_MAP.put("all", "userId&serviceName&taskId");

        METRIC_NAME.put("connectTime", "建连时间");
        METRIC_NAME.put("dnsTime", "DNS解析时间");
        METRIC_NAME.put("firstByteTime", "首包时间");
        METRIC_NAME.put("packetLoss", "丢包率");
        METRIC_NAME.put("totalTime", "响应时间");
        METRIC_NAME.put("success", "可用率");
        METRIC_NAME.put("redirectTime", "重定向时间");
        METRIC_NAME.put("tlsTime", "TLS时间");
        METRIC_NAME.put("downloadTime", "下载时间");
        METRIC_NAME.put("dnsKidnap", "DNS劫持率");

        PROV_ISP_MAP.put("prov", "省份");
        PROV_ISP_MAP.put("isp", "运营商");
    }
}
