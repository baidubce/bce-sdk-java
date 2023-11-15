package com.baidubce.services.cdn.model.certificate;

import com.baidubce.services.cdn.model.JsonObject;

public class Cert extends JsonObject {

    /**
     * 证书的名称。长度限制为1-65个字符，
     * 以字母开头，只允许包含字母、数字、’-‘、’/’、’.’、’_’、’*’，
     * Java正则表达式 ^[a-zA-Z][a-zA-Z0-9\-/_\.\*]{0,64}$
     * 必须
     */
    private String certName;

    /**
     * 服务器证书的数据内容 (Base64编码)
     * 必须
     */
    private String certServerData;

    /**
     * 证书的私钥数据内容 (Base64编码)
     * 必须
     */
    private String certPrivateData;

    /**
     * 证书链数据内容 (Base64编码)
     * 可选
     */
    private String certLinkData;

    /**
     * 证书类型,只能取1。1表示的是服务端证书，如果不传此项那么默认的取值也是1。
     * 可选
     */
    private Integer certType;

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }

    public String getCertServerData() {
        return certServerData;
    }

    public void setCertServerData(String certServerData) {
        this.certServerData = certServerData;
    }

    public String getCertPrivateData() {
        return certPrivateData;
    }

    public void setCertPrivateData(String certPrivateData) {
        this.certPrivateData = certPrivateData;
    }

    public String getCertLinkData() {
        return certLinkData;
    }

    public void setCertLinkData(String certLinkData) {
        this.certLinkData = certLinkData;
    }

    public Integer getCertType() {
        return certType;
    }

    public void setCertType(Integer certType) {
        this.certType = certType;
    }

    public Cert withCertName(String certName) {
        setCertName(certName);
        return this;
    }

    public Cert withCertServerData(String certServerData) {
        setCertServerData(certServerData);
        return this;
    }

    public Cert withCertPrivateData(String certPrivateData) {
        setCertPrivateData(certPrivateData);
        return this;
    }

    public Cert withCertLinkData(String certLinkData) {
        setCertLinkData(certLinkData);
        return this;
    }

    public Cert withCertType(Integer certType) {
        setCertType(certType);
        return this;
    }
}
