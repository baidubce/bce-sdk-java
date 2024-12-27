package com.baidubce.services.vca.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * @Author heke
 * @Email heke03@baidu.com
 * @Description 视频分析接口 - 取消任务请求
 * @Date created in 2024/05/29/4:11 PM
 */
public class AnalyzeCancelRequest extends AbstractBceRequest {

    private String source;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AnalyzeCancelRequest{");
        sb.append("source='").append(source).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
