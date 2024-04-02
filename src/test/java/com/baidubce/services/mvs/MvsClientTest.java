/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
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

package com.baidubce.services.mvs;

import com.baidubce.services.mvs.model.InsertImageRequest;
import com.baidubce.services.mvs.model.InsertImageResponse;
import com.baidubce.services.mvs.model.InsertVideoRequest;
import com.baidubce.services.mvs.model.InsertVideoResponse;
import com.baidubce.services.mvs.model.MvsError;
import com.baidubce.services.mvs.model.SearchImageByImageRequest;
import com.baidubce.services.mvs.model.SearchImageByImageResponse;
import com.baidubce.services.mvs.model.SearchImageByImageResult;
import com.baidubce.services.mvs.model.SearchVideoByVideoRequest;
import com.baidubce.services.mvs.model.SearchVideoByVideoResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * MVS client test.
 */
public class MvsClientTest {
    private static final String FAILED = "failed";
    private static final String SUCCESS = "success";

    private MvsClient mvsClient;

    @Before
    public void setUp() {
        mvsClient = Mockito.mock(MvsClient.class);
    }

    @Test
    public void insertImage() {
        // Construct request.
        String lib = "baiduyu_test";
        String source = "http://baiduyun/test.jpg";
        String description = "test insert image";
        InsertImageRequest insertImageRequest = InsertImageRequest.builder()
                .withSource(source)
                .withDescription(description)
                .build();

        // Construct mock response.
        InsertImageResponse insertImageResponseMock = new InsertImageResponse();
        insertImageResponseMock.setStatus(FAILED);
        insertImageResponseMock.setDescription(description);
        insertImageResponseMock.setLib(lib);
        insertImageResponseMock.setSource(source);
        insertImageResponseMock.setError(new MvsError("InvalidImage", "invalid image"));

        // Set return result.
        Mockito.when(mvsClient.insertImage(lib, insertImageRequest)).thenReturn(insertImageResponseMock);

        // Run.
        InsertImageResponse insertImageResponse = mvsClient.insertImage(lib, insertImageRequest);

        // Validate result.
        assertThat(insertImageResponse, is(notNullValue()));
        assertThat(insertImageResponse.getStatus(), equalTo(insertImageResponseMock.getStatus()));
        assertThat(insertImageResponse.getSource(), equalTo(insertImageResponseMock.getSource()));
        assertThat(insertImageResponse.getDescription(), equalTo(insertImageResponseMock.getDescription()));
        assertThat(insertImageResponse.getLib(), equalTo(insertImageResponseMock.getLib()));
        assertThat(insertImageResponse.getError().toString(), equalTo(insertImageResponseMock.getError().toString()));
    }

    @Test
    public void searchImageByImage() {
        // Construct request.
        String lib = "baiduyu_test";
        String source = "http://baiduyun/test.jpg";
        String description = "test search image by image";
        SearchImageByImageRequest searchImageByImageRequest = SearchImageByImageRequest.builder()
                .withSource(source)
                .withDescription(description)
                .build();

        // Construct mock response.
        SearchImageByImageResponse searchImageByImageResponseMock = new SearchImageByImageResponse();
        searchImageByImageResponseMock.setStatus(SUCCESS);
        searchImageByImageResponseMock.setDescription(description);
        searchImageByImageResponseMock.setLib(lib);
        searchImageByImageResponseMock.setSource(source);

        SearchImageByImageResult searchImageByImageResult = new SearchImageByImageResult();
        searchImageByImageResult.setDescription("for test");
        searchImageByImageResult.setScore(99.99);
        searchImageByImageResult.setSource(source);
        List<SearchImageByImageResult> resultList = new ArrayList<SearchImageByImageResult>();
        resultList.add(searchImageByImageResult);
        searchImageByImageResponseMock.setResults(resultList);

        // Set return result.
        Mockito.when(mvsClient.searchImageByImage(lib, searchImageByImageRequest))
                .thenReturn(searchImageByImageResponseMock);

        // Run.
        SearchImageByImageResponse searchImageByImageResponse
                = mvsClient.searchImageByImage(lib, searchImageByImageRequest);

        // Validate result.
        assertThat(searchImageByImageResponse, is(notNullValue()));
        assertThat(searchImageByImageResponse.getStatus(), equalTo(searchImageByImageResponseMock.getStatus()));
        assertThat(searchImageByImageResponse.getSource(), equalTo(searchImageByImageResponseMock.getSource()));
        assertThat(searchImageByImageResponse.getDescription(),
                equalTo(searchImageByImageResponseMock.getDescription()));
        assertThat(searchImageByImageResponse.getLib(), equalTo(searchImageByImageResponseMock.getLib()));
        assertThat(searchImageByImageResponse.getError(), is(nullValue()));
        assertThat(searchImageByImageResponse.getResults().get(0).toString(),
                equalTo(searchImageByImageResponseMock.getResults().get(0).toString()));
    }

    @Test
    public void insertVideo() {
        // Construct request.
        String lib = "baiduyun_test";
        InsertVideoRequest insertVideoRequest = InsertVideoRequest.builder()
                .withSource("http://baiduyun/test.mp4")
                .withNotification("baiduyun_test_notification")
                .withDescription("test insert video")
                .build();

        // Construct mock response.
        InsertVideoResponse insertVideoResponseMock = new InsertVideoResponse();
        insertVideoResponseMock.setCode("VideoSearchExceptions.NoSuchLibException");
        insertVideoResponseMock.setMessage("No such lib=baiduyu_test, please create it first!");
        insertVideoResponseMock.setRequestId("7a95abcb-3cd5-4d77-8b30-b23a653695bd");

        // Set return result.
        Mockito.when(mvsClient.insertVideo(lib, insertVideoRequest)).thenReturn(insertVideoResponseMock);

        // Run.
        InsertVideoResponse insertVideoResponse = mvsClient.insertVideo(lib, insertVideoRequest);

        // Validate result.
        assertThat(insertVideoResponse, is(notNullValue()));
        assertThat(insertVideoResponse.toString(), equalTo(insertVideoResponseMock.toString()));
    }

    @Test
    public void searchVideoByVideo() {
        // Construct request.
        String lib = "baiduyun_test";
        SearchVideoByVideoRequest searchVideoByVideoRequest = SearchVideoByVideoRequest.builder()
                .withSource("http://baiduyun.com/test.mp4")
                .withNotification("baiduyun_test_notification")
                .withDescription("test search video by video")
                .build();

        // Construct mock response.
        SearchVideoByVideoResponse searchVideoByVideoResponseMock = new SearchVideoByVideoResponse();

        // Set return result.
        Mockito.when(mvsClient.searchVideoByVideo(lib, searchVideoByVideoRequest))
                .thenReturn(searchVideoByVideoResponseMock);

        // Run.
        SearchVideoByVideoResponse searchVideoByVideoResponse
                = mvsClient.searchVideoByVideo(lib, searchVideoByVideoRequest);

        // Validate result.
        assertThat(searchVideoByVideoResponse, is(notNullValue()));
        assertThat(searchVideoByVideoResponse.toString(), equalTo(searchVideoByVideoResponseMock.toString()));
    }
}
