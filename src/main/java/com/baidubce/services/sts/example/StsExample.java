/*
 * Copyright (c) 2016 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.sts.example;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.BceCredentials;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.auth.DefaultBceSessionCredentials;
import com.baidubce.services.sts.StsClient;
import com.baidubce.services.sts.model.GetSessionTokenRequest;
import com.baidubce.services.sts.model.GetSessionTokenResponse;

public class StsExample {
    private static final String ENDPOINT = "http://sts.bj.baidubce.com";
    private static final String ACCESS_KEY_ID = "your accesskey id";
    private static final String SECRET_ACCESS_KEY = "your secret accesskey";

    public static void main(String[] args) {
        BceCredentials credentials = new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY);
        StsClient client = new StsClient(
                new BceClientConfiguration().withEndpoint(ENDPOINT).withCredentials(credentials)
        );
        GetSessionTokenResponse response = client.getSessionToken(new GetSessionTokenRequest());
        // or simply call:
        // GetSessionTokenResponse response = client.getSessionToken();
        // or you can specify limited permissions with ACL:
        // GetSessionTokenResponse response = client.getSessionToken(new GetSessionTokenRequest().withAcl("blabla"));

        // build DefaultBceSessionCredentials object from response:
        BceCredentials tempCredentials = new DefaultBceSessionCredentials(
                response.getAccessKeyId(),
                response.getSecretAccessKey(),
                response.getSessionToken());
        System.out.println("==================================");
        System.out.println("GetSessionToken result:");
        System.out.println("    accessKeyId:  " + response.getAccessKeyId());
        System.out.println("    secretAccessKey:  " + response.getSecretAccessKey());
        System.out.println("    securityToken:  " + response.getSessionToken());
        System.out.println("    expiresAt:  " + response.getExpiration().toString());
        System.out.println("==================================");
    }
}
