/**
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.vca.example;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.vca.VcaClient;
import com.baidubce.services.vca.model.HighlightAnalyzeRequest;
import com.baidubce.services.vca.model.ImageAnalyzeResponse;
import com.baidubce.services.vca.model.QueryResultResponse;
import com.baidubce.services.vca.model.QuerySubTaskResponse;
import com.baidubce.services.vca.model.StreamAnalyzeRequest;
import com.baidubce.services.vca.model.StreamAnalyzeResponse;

/**
 * vca example.
 *
 * @author chenzhangli01
 */
public class VcaExample {

    public static void main(String[] args) {
        String ak = "YourAk";
        String sk = "YourSk";
        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        VcaClient client = new VcaClient(config);

        // send media analyze request
        String source = "YourMediaSource";
        String title = "title";
        client.analyze(source, title);
        // get media full analyze result
        QueryResultResponse resultResponse = client.queryResult(source);
        System.out.println(resultResponse);
        // get media subtask result
        QuerySubTaskResponse subTaskResponse = client.querySubTask(source, "ocr");
        System.out.println(subTaskResponse);

        // send stream analyze request
        String streamSource = "YourStreamSource";
        String streamNotification = "YourNotification";
        String streamPreset = "YourPreset";
        StreamAnalyzeRequest streamAnalyzeRequest = new StreamAnalyzeRequest();
        streamAnalyzeRequest.setSource(streamSource);
        streamAnalyzeRequest.setPreset(streamPreset);
        streamAnalyzeRequest.setNotification(streamNotification);
        streamAnalyzeRequest.setIntervalInSecond(10);
        client.analyzeStream(streamAnalyzeRequest);
        // query stream basic info
        StreamAnalyzeResponse queryStreamInfo = client.queryStream(streamSource);
        System.out.println(queryStreamInfo);
        // stop stream
        client.stopStream(streamSource);

        // send image analyze request
        String imageSource = "YourImageSource";
        ImageAnalyzeResponse imageAnalyzeResponse = client.analyzeImage(imageSource);
        System.out.println(imageAnalyzeResponse);

        // send highlight analyze request
        HighlightAnalyzeRequest highlightAnalyzeRequest = new HighlightAnalyzeRequest();
        highlightAnalyzeRequest.setSource("YourMediaSource");
        highlightAnalyzeRequest.setMaxDurationTime(100);
        highlightAnalyzeRequest.setVideoType("news");
        client.analyzeHighlight(highlightAnalyzeRequest);
        // get media highlight analyze result
        resultResponse = client.queryHighlightResult("YourMediaSource");
        System.out.println(resultResponse);
        // cancel media highlight analyze
        client.cancelHighlightResult("YourMediaSource");
    }
}
