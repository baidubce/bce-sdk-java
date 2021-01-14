/*
 * Copyright 2019-2020 Baidu, Inc.
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
package com.baidubce.services.bvw.model.videoedit;

import com.baidubce.BceClientException;
import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.Map;

public class VideoEditCreateRequest extends AbstractBceRequest {
    private static final long DefaultTaskId = 0;
    private static final String BucketKey = "bucket";
    private static final String TitleKey = "title";
    private static final String TaskIdKey = "taskId";
    private static final String RefererKey = "referer";
    private static final String NotificationKey = "notification";
    private static final String CmdKey = "cmd";
    private static final String ExtInfoKey = "extInfo";

    /**
     * user store bucket
     */
    private String bucket;
    /**
     * use edit title
     */
    private String title;
    /**
     * user edit taskId
     */
    private long taskId;
    /**
     * user client referer, default is baidu
     */
    private String referer;
    /**
     * notification name
     */
    private String notification;
    /**
     * request body
     */
    private Map<String, Object> cmd;
    /**
     * extInfo body
     */
    private Map<String, Object> extInfo;

    public String getString(Object object) {
        if (object instanceof String) {
            return (String) object;
        }
        throw new BceClientException("bucket/title not a string.");
    }

    public VideoEditCreateRequest() {
    }

    public VideoEditCreateRequest(Map<String, Object> jsonObject) {
        this.cmd = jsonObject.containsKey(CmdKey) ? (Map<String, Object>) jsonObject.get(CmdKey) : null;
        this.extInfo = jsonObject.containsKey(ExtInfoKey) ? (Map<String, Object>) jsonObject.get(ExtInfoKey) : null;
        this.bucket = jsonObject.containsKey(BucketKey) ? getString(jsonObject.get(BucketKey)) : null;
        this.title = jsonObject.containsKey(TitleKey) ? getString(jsonObject.get(TitleKey)) : null;
        this.taskId = jsonObject.containsKey(TaskIdKey) ?
                Long.parseLong(String.valueOf(jsonObject.get(TaskIdKey))) : DefaultTaskId;
        this.referer = RefererType.baidu.name();
    }

    public VideoEditCreateRequest(
            Map<String, Object> jsonObject, RefererType refererType) {
        this.cmd = jsonObject.containsKey(CmdKey) ? (Map<String, Object>) jsonObject.get(CmdKey) : null;
        this.extInfo = jsonObject.containsKey(ExtInfoKey) ? (Map<String, Object>) jsonObject.get(ExtInfoKey) : null;
        this.bucket = jsonObject.containsKey(BucketKey) ? getString(jsonObject.get(BucketKey)) : null;
        this.title = jsonObject.containsKey(TitleKey) ? getString(jsonObject.get(TitleKey)) : null;
        this.taskId = jsonObject.containsKey(TaskIdKey) ?
                Long.parseLong(String.valueOf(jsonObject.get(TaskIdKey))) : DefaultTaskId;
        this.referer = refererType.name();
    }

    public VideoEditCreateRequest(
            Map<String, Object> jsonObject, RefererType refererType, String notification) {
        this.cmd = jsonObject.containsKey(CmdKey) ? (Map<String, Object>) jsonObject.get(CmdKey) : null;
        this.extInfo = jsonObject.containsKey(ExtInfoKey) ? (Map<String, Object>) jsonObject.get(ExtInfoKey) : null;
        this.bucket = jsonObject.containsKey(BucketKey) ? getString(jsonObject.get(BucketKey)) : null;
        this.title = jsonObject.containsKey(TitleKey) ? getString(jsonObject.get(TitleKey)) : null;
        this.taskId = jsonObject.containsKey(TaskIdKey) ?
                Long.parseLong(String.valueOf(jsonObject.get(TaskIdKey))) : DefaultTaskId;
        this.referer = refererType.name();
        this.notification = notification;
    }

    public static String getCmdKey() {
        return CmdKey;
    }

    public String getBucket() {
        return bucket;
    }

    public String getTitle() {
        return title;
    }

    public long getTaskId() {
        return taskId;
    }

    public String getReferer() {
        return referer;
    }

    public String getNotification() {
        return notification;
    }

    public Map<String, Object> getCmd() {
        return cmd;
    }

    public Map<String, Object> getExtInfo() {
        return extInfo;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public void setReferer(RefererType referer) {
        this.referer = referer.name();
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public void setCmd(Map<String, Object> cmd) {
        this.cmd = cmd;
    }

    public void setExtInfo(Map<String, Object> extInfo) {
        this.extInfo = extInfo;
    }

    @Override
    public String toString() {
        return "VideoEditCreateRequest{" +
                "bucket='" + bucket + '\'' +
                ", title='" + title + '\'' +
                ", taskId='" + taskId + '\'' +
                ", referer='" + referer + '\'' +
                ", notification='" + notification + '\'' +
                ", extInfo='" + extInfo.toString() + '\'' +
                ", cmd='" + cmd.toString() + '\'' +
                '}';
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
