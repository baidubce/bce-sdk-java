package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.JsonObject;

/**
 * create by changxing01 on 19/8/28
 */
public class SeoSwitch {

    /**
     * ON 表示设置直接回源，OFF 则相反
     * 可选
     */
    private String diretlyOrigin;

    /**
     * ON 表示给百度搜索推送访问记录，OFF则相反
     * 可选
     */
    private String pushRecord;

    /**
     * @return diretlyOrigin
     */
    public String getDiretlyOrigin() {
        return diretlyOrigin;
    }

    /**
     * @param diretlyOrigin Whether to return directly to the source
     */
    public void setDiretlyOrigin(String diretlyOrigin) {
        this.diretlyOrigin = diretlyOrigin;
    }

    /**
     * @param diretlyOrigin Whether to return directly to the source
     * @return this object
     */
    public SeoSwitch withDiretlyOrigin(String diretlyOrigin) {
        this.diretlyOrigin = diretlyOrigin;
        return this;
    }

    /**
     * @return pushRecord
     */
    public String getPushRecord() {
        return pushRecord;
    }

    /**
     * @param pushRecord Push access to the big search
     */
    public void setPushRecord(String pushRecord) {
        this.pushRecord = pushRecord;
    }

    /**
     * @param pushRecord Push access to the big search
     * @return this object
     */
    public SeoSwitch withPushRecord(String pushRecord) {
        this.pushRecord = pushRecord;
        return this;
    }
}
