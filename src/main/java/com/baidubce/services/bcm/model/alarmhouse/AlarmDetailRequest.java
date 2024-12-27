package com.baidubce.services.bcm.model.alarmhouse;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlarmDetailRequest extends AbstractBceRequest {
    private String alarmId;
    private String userId;

    /**
     * {@inheritDoc}
     *
     * 重写父类方法，实现AlarmDetailRequest接口。返回当前对象自身，以支持链式调用。
     *
     * @param credentials BceCredentials，包含请求的Access Key ID和Secret Access Key。可为null，表示使用默认的Access Key ID和Secret Access Key。
     * @return AlarmDetailRequest，当前对象自身，以支持链式调用。
     */
    @Override
    public AlarmDetailRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
