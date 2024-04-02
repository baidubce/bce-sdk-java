/*
 * Copyright 2015 Baidu, Inc.
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

package com.baidubce.services.media;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.baidubce.services.bos.model.BosObjectSummary;
import com.baidubce.services.media.model.GetMediaInfoOfFileResponse;

public class MediaInfoClientTest extends AbstractMediaTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void listResource() {
        // List<BucketSummary> resp = bosClient.listBuckets().getBuckets();
        // for (BucketSummary s : resp){
        // System.out.println(s);
        // }
        List<BosObjectSummary> objs = bosClient.listObjects("jianbininput").getContents();
        for (BosObjectSummary obj : objs) {
            System.out.println(obj);
        }
    }

    @Test
    public void testGetMediaInfoOfFile() {

        String bucket = "jianbininput";
        List<BosObjectSummary> objs = bosClient.listObjects("jianbininput").getContents();
        for (BosObjectSummary obj : objs) {
            String key = obj.getKey();
            if (key.endsWith("/")) {
                continue;
            }
            System.out.println("\nkey = " + key);
            GetMediaInfoOfFileResponse mediaInfo = mediaClient.getMediaInfoOfFile(bucket, key);
            if (mediaInfo.getContainer() != null && (mediaInfo.getContainer().contains("mp4") ||
                    mediaInfo.getContainer().contains("flv"))) {
                assertNotNull(mediaInfo.getDurationInSecond());
                assertNotNull(mediaInfo.getDurationInMillisecond());
            }

            System.out.println("Bucket: " + mediaInfo.getBucket());
            System.out.println("Container: " + mediaInfo.getContainer());
            System.out.println("DurationInSecond: " + mediaInfo.getDurationInSecond());
            System.out.println("DurationInMillisecond: " + mediaInfo.getDurationInMillisecond());
            System.out.println("Key: " + mediaInfo.getKey());
            System.out.println("Type: " + mediaInfo.getType());
            System.out.println("Etag: " + mediaInfo.getEtag());
            System.out.println("fileSizInByte: " + mediaInfo.getFileSizeInByte());
            System.out.println("Audio: " + mediaInfo.getAudio());
            System.out.println("Video: " + mediaInfo.getVideo());
        }

    }

}
