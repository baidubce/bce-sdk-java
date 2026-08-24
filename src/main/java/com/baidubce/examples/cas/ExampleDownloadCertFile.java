/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.cas;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.cas.CasClient;
import com.baidubce.services.cas.model.request.DownloadCertFileRequest;
import com.baidubce.services.cas.model.type.CertFileType;

public class ExampleDownloadCertFile {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "cas.baidubce.com"; // 证书服务(CAS)的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        CasClient casClient = new CasClient(config); // 初始化CasClient

        String certId = "Your Cert Id"; // 证书id

        DownloadCertFileRequest downloadCertFileRequest = new DownloadCertFileRequest();
        downloadCertFileRequest.setFormat(CertFileType.PEM.getValue()); // 下载的证书包格式
        downloadCertFileRequest.setFilePassword(""); // 压缩包密码，非必填

        try {
            byte[] content = casClient.downloadCertFile(certId, downloadCertFileRequest);
            System.out.println("cert file size: " + content.length);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
