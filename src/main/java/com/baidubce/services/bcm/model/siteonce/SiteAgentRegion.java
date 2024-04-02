package com.baidubce.services.bcm.model.siteonce;



import java.util.HashMap;
import java.util.Map;

public enum SiteAgentRegion {
    NORTHEAST,
    NORTH,
    EAST,
    SOUTH,
    CENTRAL,
    NORTHWEST,
    SOUTHWEST;

    private static final Map<String, SiteAgentRegion> REGION_MAP_MAP = new HashMap<String, SiteAgentRegion>(){{
        put("beijing", NORTH);
        put("tianjin", NORTH);
        put("hebei", NORTH);
        put("shanxi", NORTH);
        put("neimenggu", NORTH);
        put("liaonin", NORTHEAST);
        put("jilin", NORTHEAST);
        put("heilongjiang", NORTHEAST);
        put("liaoning", NORTHEAST);
        put("shanghai", EAST);
        put("jiangsu", EAST);
        put("zhejiang", EAST);
        put("anhui", EAST);
        put("fujian", EAST);
        put("jiangxi", EAST);
        put("shandong", EAST);
        put("henan", SOUTH);
        put("hubei", SOUTH);
        put("hunan", SOUTH);
        put("guangdong", SOUTH);
        put("guangxi", SOUTH);
        put("hainan", SOUTH);
        put("chongqing", SOUTHWEST);
        put("sichuan", SOUTHWEST);
        put("guizhou", SOUTHWEST);
        put("yunnan", SOUTHWEST);
        put("xizang", SOUTHWEST);
        put("shaanxi", NORTHWEST);
        put("gansu", NORTHWEST);
        put("qinghai", NORTHWEST);
        put("ningxia", NORTHWEST);
        put("xinjiang", NORTHWEST);

    }};

    public static Map<String, SiteAgentRegion> getAgentRegion() {
        return REGION_MAP_MAP;
    }

}
