/**
 * Copyright (C) 2017-2020 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.vcr;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.vcr.model.CancelStreamRequest;
import com.baidubce.services.vcr.model.CancelStreamResponse;
import com.baidubce.services.vcr.model.CheckResult;
import com.baidubce.services.vcr.model.GetAudioResponse;
import com.baidubce.services.vcr.model.GetImageAsyncResponse;
import com.baidubce.services.vcr.model.GetMediaResponse;
import com.baidubce.services.vcr.model.GetStreamCheckTaskListRequest;
import com.baidubce.services.vcr.model.GetStreamCheckTaskListResponse;
import com.baidubce.services.vcr.model.GetStreamRequest;
import com.baidubce.services.vcr.model.GetStreamResponse;
import com.baidubce.services.vcr.model.PutAudioResponse;
import com.baidubce.services.vcr.model.PutImageAsyncRequest;
import com.baidubce.services.vcr.model.PutImageAsyncResponse;
import com.baidubce.services.vcr.model.PutImageResponse;
import com.baidubce.services.vcr.model.PutMediaResponse;
import com.baidubce.services.vcr.model.PutStreamRequest;
import com.baidubce.services.vcr.model.PutStreamResponse;
import com.baidubce.services.vcr.model.PutTextResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Vcr client test.
 */
public class VcrClientTest {

    // qa-sandbox
    private static final String AK = "6107180b80ae42c0a821a5fe53ba615e";
    private static final String SK = "a88ed698c0fd4774a4f1bdcebcae5922";
    private static final String ENDPOINT = "http://10.105.98.47";

    private VcrClient vcrClient;

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(AK, SK))
                .withEndpoint(ENDPOINT);
        vcrClient = new VcrClient(config);
    }

    @Test
    public void putMedia() {
        PutMediaResponse response = vcrClient.putMedia("vod://mda-hjsqp2vx2fdv8sx0");
        assertThat(response, is(notNullValue()));
    }

    @Test
    public void getMedia() {
        GetMediaResponse response = vcrClient.getMedia("vod://mda-hj4mjssh1e340y9v");
        assertThat(response, is(notNullValue()));
        assertThat(response.getMediaId(), equalTo("mda-hj4mjssh1e340y9v"));
        assertThat(response.getSource(), equalTo("vod://mda-hj4mjssh1e340y9v"));
        assertThat(response.getLabel(), equalTo("REJECT"));
        assertThat(response.getResults(), is(notNullValue()));
    }

    @Test
    public void putAudio() {
        PutAudioResponse response = vcrClient.putAudio("bos://sdingbucket/vcr-test/ad.wav");
        assertThat(response, is(notNullValue()));
    }

    @Test
    public void getAudio() {
        GetAudioResponse response = vcrClient.getAudio("bos://sdingbucket/vcr-test/ad.wav");
        assertThat(response, is(notNullValue()));
        assertThat(response.getSource(), equalTo("bos://sdingbucket/vcr-test/ad.wav"));
        assertThat(response.getLabel(), equalTo("REJECT"));
        assertThat(response.getResults(), is(notNullValue()));
    }

    @Test
    public void putImage() {
        PutImageResponse response = vcrClient.putImage("bos://sdingbucket/vcr-test/qrcode.jpeg");
        assertThat(response, is(notNullValue()));
        assertThat(response.getLabel(), equalTo("REJECT"));
        List<CheckResult> results = response.getResults();
        assertThat(results, is(notNullValue()));
        assertThat(results.size(), equalTo(1));
        assertThat(results.get(0).getType(), equalTo("ad_qrcode"));
    }

    @Test
    public void putImageAsync() {
        PutImageAsyncRequest request = new PutImageAsyncRequest();
        request.setSource("bos://sdingbucket/vcr-test/qrcode.jpeg");
        request.setDescription("description content");
        PutImageAsyncResponse response = vcrClient.putImageAsync(request);
        assertThat(response, is(notNullValue()));
    }

    @Test
    public void getImageAsync() {
        GetImageAsyncResponse response = vcrClient.getImageAsync("bos://sdingbucket/vcr-test/qrcode.jpeg", null);
        assertThat(response, is(notNullValue()));
        assertThat(response.getLabel(), equalTo("REJECT"));
        List<CheckResult> results = response.getResults();
        assertThat(results, is(notNullValue()));
        assertThat(results.size(), equalTo(1));
        assertThat(results.get(0).getType(), equalTo("ad_marketing"));
        assertThat(response.getDescription(), equalTo("description content"));
    }

    @Test
    public void putText() {
        PutTextResponse response = vcrClient.putText("看片加vx：wmw，令计划风流往事");
        assertThat(response, is(notNullValue()));
        assertThat(response.getLabel(), equalTo("REJECT"));
        List<CheckResult> results = response.getResults();
        assertThat(results, is(notNullValue()));
        assertThat(results.size(), equalTo(3));
    }

    @Test
    public void putStream() {
        PutStreamRequest request = new PutStreamRequest();
        request.setSource("http://baidu.com/stream");
        request.setNotification("notification");
        PutStreamResponse response = vcrClient.putStreamV2(request);
        assertThat(response, is(notNullValue()));
    }

    @Test
    public void cancelStream() {
        CancelStreamRequest request = new CancelStreamRequest();
        request.setSource("http://baidu.com/stream");
        request.setNotification("notification");
        CancelStreamResponse response = vcrClient.cancelStreamV2(request);
        assertThat(response, is(notNullValue()));
    }

    @Test
    public void getStream() {
        GetStreamRequest request = new GetStreamRequest();
        request.setSource("http://baidu.com/stream");
        GetStreamResponse response = vcrClient.getStreamV2(request);
        assertThat(response, is(notNullValue()));
    }

    @Test
    public void getStreamCheckTaskList() {
        GetStreamCheckTaskListRequest request = new GetStreamCheckTaskListRequest();
        request.setStatus("success");
        request.setMaxKeys(10);
        request.setMarker("nextMarker");
        GetStreamCheckTaskListResponse response = vcrClient.getStreamCheckTaskListV2(request);
        assertThat(response, is(notNullValue()));
    }
}
