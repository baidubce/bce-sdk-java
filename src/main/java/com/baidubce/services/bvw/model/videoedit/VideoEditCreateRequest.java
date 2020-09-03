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

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import org.json.JSONObject;

public class VideoEditCreateRequest extends AbstractBceRequest {
    private static final long DefaultTaskId = 1;
    private static final String BucketKey = "bucket";
    private static final String TitleKey = "title";
    private static final String TaskIdKey = "taskId";
    private static final String RefererKey = "referer";
    private static final String NotificationKey = "notification";
    private static final String CmdKey = "cmd";

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
    private JSONObject cmd;

    public VideoEditCreateRequest() {
    }

    public VideoEditCreateRequest(JSONObject jsonObject) {
        this.cmd = jsonObject.has(CmdKey) ? jsonObject.getJSONObject(CmdKey) : null;
        this.bucket = jsonObject.has(BucketKey) ? jsonObject.getString(BucketKey) : null;
        this.title = jsonObject.has(TitleKey) ? jsonObject.getString(TitleKey) : null;
        this.taskId = jsonObject.has(TaskIdKey) ? jsonObject.getLong(TaskIdKey) : DefaultTaskId;
        this.referer = RefererType.baidu.name();
    }

    public VideoEditCreateRequest(JSONObject jsonObject, RefererType refererType) {
        this.cmd = jsonObject.has(CmdKey) ? jsonObject.getJSONObject(CmdKey) : null;
        this.bucket = jsonObject.has(BucketKey) ? jsonObject.getString(BucketKey) : null;
        this.title = jsonObject.has(TitleKey) ? jsonObject.getString(TitleKey) : null;
        this.taskId = jsonObject.has(TaskIdKey) ? jsonObject.getLong(TaskIdKey) : DefaultTaskId;
        this.referer = refererType.name();
    }

    public VideoEditCreateRequest(JSONObject jsonObject, RefererType refererType, String notification) {
        this.cmd = jsonObject.has(CmdKey) ? jsonObject.getJSONObject(CmdKey) : null;
        this.bucket = jsonObject.has(BucketKey) ? jsonObject.getString(BucketKey) : null;
        this.title = jsonObject.has(TitleKey) ? jsonObject.getString(TitleKey) : null;
        this.taskId = jsonObject.has(TaskIdKey) ? jsonObject.getLong(TaskIdKey) : DefaultTaskId;
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

    public JSONObject getCmd() {
        return cmd;
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

    public void setCmd(JSONObject cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return "VideoEditCreateRequest{" +
                "bucket='" + bucket + '\'' +
                ", title='" + title + '\'' +
                ", taskId='" + taskId + '\'' +
                ", referer='" + referer + '\'' +
                ", notification='" + notification + '\'' +
                ", cmd='" + cmd.toString() + '\'' +
                '}';
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
