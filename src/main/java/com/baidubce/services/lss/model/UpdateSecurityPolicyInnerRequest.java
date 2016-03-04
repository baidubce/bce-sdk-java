package com.baidubce.services.lss.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Created by shidaiting01 on 2016/1/14.
 */
public class UpdateSecurityPolicyInnerRequest extends AbstractBceRequest {
    private Auth auth = null;

    private AntiLeech antiLeech = null;

    private Encryption encryption = null;

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    public UpdateSecurityPolicyInnerRequest withAuth(Auth auth) {
        this.auth = auth;
        return this;
    }

    public AntiLeech getAntiLeech() {
        return antiLeech;
    }

    public void setAntiLeech(AntiLeech antiLeech) {
        this.antiLeech = antiLeech;
    }

    public UpdateSecurityPolicyInnerRequest withAntiLeech(AntiLeech antiLeech) {
        this.antiLeech = antiLeech;
        return this;
    }

    public Encryption getEncryption() {
        return encryption;
    }

    public void setEncryption(Encryption encryption) {
        this.encryption = encryption;
    }

    public UpdateSecurityPolicyInnerRequest withEncryption(Encryption encryption) {
        this.encryption = encryption;
        return this;
    }

    public UpdateSecurityPolicyInnerRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class UpdateSecurityPolicyInnerRequest {\n");
        sb.append("    auth: ").append(auth).append("\n");
        sb.append("    antiLeech: ").append(antiLeech).append("\n");
        sb.append("    encryption: ").append(encryption).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
