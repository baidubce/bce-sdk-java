package com.baidubce.services.media;

import com.baidubce.services.media.model.GetTranscodingJobResponse;
import com.baidubce.services.media.model.JobOutputInfo;
import com.baidubce.services.media.model.JobOutputInfoAudio;
import com.baidubce.services.media.model.JobOutputInfoVideo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class GetTranscodingJobResponseTest {

    @InjectMocks
    GetTranscodingJobResponse getTranscodingJobResponse = new GetTranscodingJobResponse();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void toStringTest() {
        JobOutputInfo jobOutputInfo = new JobOutputInfo();
        getTranscodingJobResponse.setOutput(jobOutputInfo);
        String responseInfo = getTranscodingJobResponse.toString();
        Assert.assertFalse(responseInfo.contains("output"));

        JobOutputInfoVideo jobOutputInfoVideo = new JobOutputInfoVideo();
        jobOutputInfoVideo.setDurationInSeconds(100);
        jobOutputInfo.setVideo(jobOutputInfoVideo);
        getTranscodingJobResponse.setOutput(jobOutputInfo);
        responseInfo = getTranscodingJobResponse.toString();
        Assert.assertTrue(responseInfo.contains("output"));

        JobOutputInfoAudio jobOutputInfoAudio = new JobOutputInfoAudio();
        jobOutputInfoAudio.setChannels(2);
        jobOutputInfo.setAudio(jobOutputInfoAudio);
        getTranscodingJobResponse.setOutput(jobOutputInfo);
        responseInfo = getTranscodingJobResponse.toString();
        Assert.assertTrue(responseInfo.contains("output"));
    }

}
