package com.baidubce.services.bvw.model.matlib;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class VideoGenerationRequest extends AbstractBceRequest {

    /**
     * 一组视频生成任务的track
     */
    @NotNull
    private List<VideoGenerationTrack> tracks;
    /**
     * 发音人类型
     */
    @NotNull
    private Per ttsPer;
    /**
     * 结果bucket
     */
    @NotNull
    private String resultBucket;
    /**
     * 片头bucket
     */
    private String beginBucket;
    /**
     * 片头key
     */
    private String beginKey;
    /**
     * 片尾bucket
     */
    private String endBucket;
    /**
     * 片尾key
     */
    private String endKey;
    /**
     * 水印bucket
     */
    private String logoBucket;
    /**
     * 水印key
     */
    private String logoKey;
    /**
     * 水印边缘距离，会同时指定上方和左右的距离
     */
    private Integer logoMargin = 0;
    /**
     * 水印位置
     */
    private String logoPos = "top-left";
    /**
     * 背景音乐bucket
     */
    private String bgMusicBucket;
    /**
     * 背景音乐key
     */
    private String bgMusicKey;

    /**
     * 回调通知名
     */
    private String notification;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }


    @Data
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private static class VideoGenerationTrack {

        /**
         * 文本对应的素材一组素材
         */
        private List<TrackMaterial> materials;
        /**
         * 素材对应的文本
         */
        private String txt;

    }

    @Data
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private static class TrackMaterial {

        /**
         * 素材bucket
         */
        private String bucket;
        /**
         * 素材key
         */
        private String key;
        /**
         * 素材类型
         */
        private Type type;

    }

    public enum Type {
        video,
        image
    }



}
