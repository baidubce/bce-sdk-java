package com.baidubce.services.ipcollection.model.ipset;


import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Query IpSet Detail Request.
 */
@Getter
@Setter
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class QueryIpSetDetailRequest extends AbstractBceRequest {
    private String ipSetId;

    /**
     * {@inheritDoc}
     * 重写父类方法，实现AbstractBceRequest接口的withRequestCredentials方法。
     * 该方法用于设置请求凭证（BceCredentials），并返回当前对象自身。
     *
     * @param credentials BceCredentials类型的请求凭证
     * @return AbstractBceRequest类型，当前对象自身
     */
    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
