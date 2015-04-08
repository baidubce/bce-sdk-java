/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.ses.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The token model object. It was contained by @link{DkimAttrModelTokenModel}.
 */
public class TokenModel {
    /**
     * The id of token.
     */
    private String id;
    /**
     * The private key of token.
     */
    @JsonProperty("private_key")
    private String privateKey;
    /**
     * The public key of token.
     */
    @JsonProperty("public_key")
    private String publicKey;
    /**
     * The selector of token.
     */
    private String selector;
    /**
     * The CName of token.
     */
    private String cname;
    /**
     * The DNS record name of token.
     */
    @JsonProperty("dns_record_name")
    private String dnsRecordName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getDnsRecordName() {
        return dnsRecordName;
    }

    public void setDnsRecordName(String dnsRecordName) {
        this.dnsRecordName = dnsRecordName;
    }

    @Override
    public String toString() {
        return "TokenModel [id=" + id + ", privateKey=" + privateKey + ", publicKey=" + publicKey + ", selector="
                + selector + ", cname=" + cname + ", dnsRecordName=" + dnsRecordName + "]";
    }

}
