/**
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.vca.example;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.vca.VcaClient;
import com.baidubce.services.vca.model.ImageAnalyzeResponse;
import com.baidubce.services.vca.model.QueryResultResponse;
import com.baidubce.services.vca.model.QuerySubTaskResponse;

/**
 * vca example.
 *
 * @author chenzhangli01
 */
public class VcaExample {

    public static void main(String[] args) {
        String ak = "YourAK";
        String sk = "YourSK";

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));

        String source = "YourMediaSource";
        String title = "title";
        String imageSource = "YourImageSource";
        VcaClient client = new VcaClient(config);
        // send media analyze request
        client.analyze(source, title);

        // send image analyze request
        ImageAnalyzeResponse imageAnalyzeResponse = client.analyzeImage(imageSource);
        System.out.println(imageAnalyzeResponse);

        // get media full analyze result
        QueryResultResponse resultResponse = client.queryResult(source);
        System.out.println(resultResponse);

        // get subtask result
        QuerySubTaskResponse subTaskResponse = client.querySubTask(source, "ocr");
        System.out.println(subTaskResponse);
    }
}
