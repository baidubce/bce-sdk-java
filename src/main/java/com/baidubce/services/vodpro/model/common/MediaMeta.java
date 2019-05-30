package com.baidubce.services.vodpro.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created on 2017/8/7 13:39.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MediaMeta {
    private String type;
    private String format;
    private String formatLongName;
    private Double startTimeInSecond;
    private Double fileSizeInByte;
    private Double durationInSecond;
    private Double bitRateInBps;
    private Video video = new Video();
    private Audio audio = new Audio();
    private Tag tag = new Tag();
}
