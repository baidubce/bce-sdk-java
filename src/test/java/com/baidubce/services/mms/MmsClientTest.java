package com.baidubce.services.mms;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.mms.model.InsertVideoResultResponse;
import com.baidubce.services.mms.model.MmsBaseResponse;
import com.baidubce.services.mms.model.MmsListLibResponse;
import com.baidubce.services.mms.model.MmsListMediaResponse;
import com.baidubce.services.mms.model.SearchVideoResponse;
import com.baidubce.services.mms.model.SourceAndDescRequest;
import com.baidubce.services.mms.model.SourceRequest;
import com.baidubce.services.mms.model.CreateLibRequest;
import com.baidubce.services.mms.model.ListLibRequest;
import com.baidubce.services.mms.model.ListMediaRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class MmsClientTest {

    private MmsClient mmsClient;

    private final String AK = "AK";
    private final String SK = "SK";
    private final String Endpoint = "http://endpoint";
    private final String VideoLib = "test";
    private final String VideoLibId = "test";
    private final String ImageLib = "test";
    private final String ImageLibId = "test";
    private final String VideoUrl = "https://video.mp4";
    private final String ImageUrl = "https://image.jpg";
    private final String TaskId = "test";
    private final String VideoId = "test";
    private final String ImageId = "test";

    @Before
    public void setUp() {
        mmsClient = Mockito.mock(MmsClient.class);
    }

    private void newMmsClient() {
        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(AK, SK));
        config.setEndpoint(Endpoint);
        mmsClient = new MmsClient(config);
    }

    @Test
    public void createVideoLib() {
        CreateLibRequest request = new CreateLibRequest();
        request.setName(VideoLib);
        request.setDescription("description");

        MmsBaseResponse mockReturn = new MmsBaseResponse();
        mockReturn.setStatus("SUCCESS");
        mockReturn.setLibId(VideoLibId);
        mockReturn.setError(null);

        Mockito.when(mmsClient.createVideoLib(request)).thenReturn(mockReturn);
        MmsBaseResponse response = mmsClient.createVideoLib(request);

        Assert.assertEquals("SUCCESS", response.getStatus());
        Assert.assertEquals(VideoLibId, response.getLibId());
        Assert.assertNull(response.getError());
    }

    @Test
    public void deleteVideoLib() {

        MmsBaseResponse mockReturn = new MmsBaseResponse();
        mockReturn.setStatus("SUCCESS");
        mockReturn.setError(null);

        Mockito.when(mmsClient.deleteVideoLib(VideoLibId)).thenReturn(mockReturn);
        MmsBaseResponse response = mmsClient.deleteVideoLib(VideoLibId);

        Assert.assertEquals("SUCCESS", response.getStatus());
        Assert.assertNull(response.getError());
    }

    @Test
    public void createImageLib() {
        CreateLibRequest request = new CreateLibRequest();
        request.setName(ImageLib);
        request.setDescription("description");

        MmsBaseResponse mockReturn = new MmsBaseResponse();
        mockReturn.setStatus("SUCCESS");
        mockReturn.setLibId(ImageLibId);
        mockReturn.setError(null);

        Mockito.when(mmsClient.createImageLib(request)).thenReturn(mockReturn);
        MmsBaseResponse response = mmsClient.createImageLib(request);

        Assert.assertEquals("SUCCESS", response.getStatus());
        Assert.assertEquals(ImageLibId, response.getLibId());
        Assert.assertNull(response.getError());
    }

    @Test
    public void deleteImageLib() {

        MmsBaseResponse mockReturn = new MmsBaseResponse();
        mockReturn.setStatus("SUCCESS");
        mockReturn.setError(null);

        Mockito.when(mmsClient.deleteImageLib(ImageLibId)).thenReturn(mockReturn);
        MmsBaseResponse response = mmsClient.deleteImageLib(ImageLibId);

        Assert.assertEquals("SUCCESS", response.getStatus());
        Assert.assertNull(response.getError());
    }

    @Test
    public void listLib() {

        MmsListLibResponse mockReturn = new MmsListLibResponse();
        mockReturn.setTotalCount(0);

        ListLibRequest request = new ListLibRequest();
        request.setType("VIDEO");

        Mockito.when(mmsClient.listLib(request)).thenReturn(mockReturn);
        MmsListLibResponse response = mmsClient.listLib(request);

        Assert.assertEquals(Integer.valueOf(0), response.getTotalCount());
    }

    @Test
    public void listMedia() {

        MmsListMediaResponse mockReturn = new MmsListMediaResponse();
        mockReturn.setTotalCount(0);

        ListMediaRequest request = new ListMediaRequest();
        request.setType("VIDEO");

        Mockito.when(mmsClient.listMedia(request)).thenReturn(mockReturn);
        MmsListMediaResponse response = mmsClient.listMedia(request);

        Assert.assertEquals(Integer.valueOf(0), response.getTotalCount());
    }

    @Test
    public void insertVideo() {
        SourceAndDescRequest request = new SourceAndDescRequest();
        request.setSource(VideoUrl);
        request.setDescription("description");
        request.setNotification("notification");

        MmsBaseResponse mockReturn = new MmsBaseResponse();
        mockReturn.setStatus("SUCCESS");
        mockReturn.setTaskId("1234567890");
        mockReturn.setError(null);

        Mockito.when(mmsClient.insertVideo(VideoLib, request)).thenReturn(mockReturn);
        MmsBaseResponse response = mmsClient.insertVideo(VideoLib, request);

        Assert.assertEquals("SUCCESS", response.getStatus());
        Assert.assertEquals("1234567890", response.getTaskId());
        Assert.assertNull(response.getError());
    }

    @Test
    public void getInsertVideoResult() {
        SourceRequest request = new SourceRequest();
        request.setSource(VideoUrl);

        InsertVideoResultResponse mockReturn = new InsertVideoResultResponse();
        mockReturn.setTaskId("1234567890");
        mockReturn.setStatus("PROCESSING");

        Mockito.when(mmsClient.getInsertVideoResult(VideoLib, request)).thenReturn(mockReturn);
        InsertVideoResultResponse response = mmsClient.getInsertVideoResult(VideoLib, request);
        Assert.assertEquals("1234567890", response.getTaskId());
        Assert.assertEquals("PROCESSING", response.getStatus());
    }

    @Test
    public void getInsertVideoResultById() {

        InsertVideoResultResponse mockReturn = new InsertVideoResultResponse();
        mockReturn.setTaskId("1234567890");
        mockReturn.setStatus("PROCESSING");

        Mockito.when(mmsClient.getInsertVideoResultById(VideoLibId, VideoId)).thenReturn(mockReturn);
        InsertVideoResultResponse response = mmsClient.getInsertVideoResultById(VideoLibId, VideoId);
        Assert.assertEquals("1234567890", response.getTaskId());
        Assert.assertEquals("PROCESSING", response.getStatus());
    }

    @Test
    public void searchVideoByVideo() {
        SourceAndDescRequest request = new SourceAndDescRequest();
        request.setSource(VideoUrl);
        request.setDescription("Desc");
        request.setNotification("notification");

        MmsBaseResponse mockReturn = new MmsBaseResponse();
        mockReturn.setTaskId("1234567890");
        mockReturn.setStatus("PROCESSING");

        Mockito.when(mmsClient.searchVideoByVideo(VideoLib, request)).thenReturn(mockReturn);
        MmsBaseResponse response = mmsClient.searchVideoByVideo(VideoLib, request);
        Assert.assertEquals("1234567890", response.getTaskId());
        Assert.assertEquals("PROCESSING", response.getStatus());
    }

    @Test
    public void getSearchVideoByVideoResultById() {

        SearchVideoResponse mockReturn = new SearchVideoResponse();
        mockReturn.setTaskId("1234567890");
        mockReturn.setStatus("PROCESSING");

        Mockito.when(mmsClient.getSearchVideoByVideoResultById(VideoLib, TaskId)).thenReturn(mockReturn);
        SearchVideoResponse response = mmsClient.getSearchVideoByVideoResultById(VideoLib, TaskId);

        Assert.assertEquals("1234567890", response.getTaskId());
        Assert.assertEquals("PROCESSING", response.getStatus());
    }

    @Test
    public void deleteVideoById() {

        SearchVideoResponse mockReturn = new SearchVideoResponse();
        mockReturn.setTaskId("1234567890");
        mockReturn.setStatus("PROCESSING");

        Mockito.when(mmsClient.deleteVideoById(VideoLibId, VideoId)).thenReturn(mockReturn);
        MmsBaseResponse response = mmsClient.deleteVideoById(VideoLib, VideoId);

        Assert.assertEquals("1234567890", response.getTaskId());
        Assert.assertEquals("PROCESSING", response.getStatus());
    }

    @Test
    public void deleteImageById() {

        SearchVideoResponse mockReturn = new SearchVideoResponse();
        mockReturn.setTaskId("1234567890");
        mockReturn.setStatus("PROCESSING");

        Mockito.when(mmsClient.deleteVideoById(ImageLibId, ImageId)).thenReturn(mockReturn);
        MmsBaseResponse response = mmsClient.deleteVideoById(ImageLibId, ImageId);

        Assert.assertEquals("1234567890", response.getTaskId());
        Assert.assertEquals("PROCESSING", response.getStatus());
    }

    @Test
    public void getSearchVideoByVideoResult() {
        SourceRequest request = new SourceRequest();
        request.setSource(VideoUrl);

        SearchVideoResponse mockReturn = new SearchVideoResponse();
        mockReturn.setTaskId("1234567890");
        mockReturn.setStatus("PROCESSING");

        Mockito.when(mmsClient.getSearchVideoByVideoResult(VideoLib, request)).thenReturn(mockReturn);
        SearchVideoResponse response = mmsClient.getSearchVideoByVideoResult(VideoLib, request);

        Assert.assertEquals("1234567890", response.getTaskId());
        Assert.assertEquals("PROCESSING", response.getStatus());
    }

    @Test
    public void insertImage() {
        SourceAndDescRequest request = new SourceAndDescRequest();
        request.setSource(ImageUrl);
        request.setDescription("ImagesInsert");

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(AK, SK));
        config.setEndpoint(Endpoint);
        MmsClient client = new MmsClient(config);

        try {
            // client.insertImage(ImageLib, request);
        } catch (Exception e) {
            // ...
        }
    }
}
