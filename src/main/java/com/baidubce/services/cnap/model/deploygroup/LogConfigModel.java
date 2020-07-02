/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.cnap.model.deploygroup;

import java.util.HashMap;
import java.util.Map;

/**
 * The model for log config.
 */
public class LogConfigModel {

    /**
     * The tags of log.
     */
    private Map<String, String> logsTags = new HashMap<String, String>();

    /**
     * The type of log output. eg: "Compnonent" or "ElasticSearch"
     *
     */
    private String logsOutputType;

    /**
     * The exclude path.
     */
    private String excludePath;

    /**
     * The data source of this log strategy.
     */
    private DataSourceModel logFileFilter;

    /**
     * The flag which indicates log parser.
     */
    private boolean isLogParserEnabled;

    /**
     * The type of parser.
     */
    private String parserType = "json";

    /**
     * The formate of parser log.
     */
    private String parserLogFormat;

    /**
     * The time key.
     */
    private String timeKey;

    /**
     * The time format.
     */
    private String timeFormat;

    /**
     * The id of plugin.
     */
    private String pluginInsID;

    /**
     * The host of Elasticsearch.
     */
    private String host;

    /**
     * The index of Elasticsearch.
     */
    private String index;

    /**
     * The flag which indicates log stash format.
     */
    private boolean logstashFormat;

    /**
     * The prefix of log stash.
     */
    private String logstashPrefix;

    /**
     * The data format of log stash.
     */
    private String logstashDateFormat;

    /**
     * The auth info.
     */
    private String auth;

    /**
     * The user name of Elasticsearch.
     */
    private String userName;

    /**
     * The password of Elasticsearch.
     */
    private String password;

    /**
     * The pid info.
     */
    private String pid;


    public Map<String, String> getLogsTags() {
        return logsTags;
    }

    public void setLogsTags(Map<String, String> logsTags) {
        this.logsTags = logsTags;
    }

    public String getLogsOutputType() {
        return logsOutputType;
    }

    public void setLogsOutputType(String logsOutputType) {
        this.logsOutputType = logsOutputType;
    }

    public String getExcludePath() {
        return excludePath;
    }

    public void setExcludePath(String excludePath) {
        this.excludePath = excludePath;
    }

    public DataSourceModel getLogFileFilter() {
        return logFileFilter;
    }

    public void setLogFileFilter(DataSourceModel logFileFilter) {
        this.logFileFilter = logFileFilter;
    }

    public void setIsLogParserEnabled(boolean isLogParserEnabled) {
        this.isLogParserEnabled = isLogParserEnabled;
    }

    public boolean getIsLogParserEnabled() {
        return this.isLogParserEnabled;
    }

    public String getParserType() {
        return parserType;
    }

    public void setParserType(String parserType) {
        this.parserType = parserType;
    }

    public String getParserLogFormat() {
        return parserLogFormat;
    }

    public void setParserLogFormat(String parserLogFormat) {
        this.parserLogFormat = parserLogFormat;
    }

    public String getTimeKey() {
        return timeKey;
    }

    public void setTimeKey(String timeKey) {
        this.timeKey = timeKey;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    public String getPluginInsID() {
        return pluginInsID;
    }

    public void setPluginInsID(String pluginInsID) {
        this.pluginInsID = pluginInsID;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public boolean getLogstashFormat() {
        return logstashFormat;
    }

    public void setLogstashFormat(boolean logstashFormat) {
        this.logstashFormat = logstashFormat;
    }

    public String getLogstashPrefix() {
        return logstashPrefix;
    }

    public void setLogstashPrefix(String logstashPrefix) {
        this.logstashPrefix = logstashPrefix;
    }

    public String getLogstashDateFormat() {
        return logstashDateFormat;
    }

    public void setLogstashDateFormat(String logstashDateFormat) {
        this.logstashDateFormat = logstashDateFormat;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public LogConfigModel withLogsOutputType(String logsOutputType) {
        this.setLogsOutputType(logsOutputType);
        return this;
    }

    public LogConfigModel withExcludePath(String excludePath) {
        this.setExcludePath(excludePath);
        return this;
    }

    public LogConfigModel withLogFileFilter(DataSourceModel logFileFilter) {
        this.setLogFileFilter(logFileFilter);
        return this;
    }

    public LogConfigModel withIsLogParserEnabled(boolean isLogParserEnabled) {
        this.setIsLogParserEnabled(isLogParserEnabled);
        return this;
    }

    public LogConfigModel withParserLogFormat(String  parserLogFormat) {
        this.setParserLogFormat(parserLogFormat);
        return this;
    }

    public LogConfigModel withTimeKey(String timeKey) {
        this.setTimeKey(timeKey);
        return this;
    }

    public LogConfigModel withTimeFormat(String timeFormat) {
        this.setTimeFormat(timeFormat);
        return this;
    }

    public LogConfigModel withPluginInsID(String pluginInsID) {
        this.setPluginInsID(pluginInsID);
        return this;
    }

    public LogConfigModel withHost(String host) {
        this.setHost(host);
        return this;
    }

    public LogConfigModel withIndex(String index) {
        this.setIndex(index);
        return this;
    }

    public LogConfigModel withLogstashFormat(boolean logstashFormat) {
        this.setLogstashFormat(logstashFormat);
        return this;
    }

    public LogConfigModel withLogstashPrefix(String logstashPrefix) {
        this.setLogstashPrefix(logstashPrefix);
        return this;
    }

    public LogConfigModel withLogstashDateFormat(String logstashDateFormat) {
        this.setLogstashFormat(logstashFormat);
        return this;
    }

    public LogConfigModel withAuth(String auth) {
        this.setAuth(auth);
        return this;
    }

    public LogConfigModel withUserName(String userName) {
        this.setUserName(userName);
        return this;
    }

    public LogConfigModel withPassword(String password) {
        this.setPassword(password);
        return this;
    }

    public LogConfigModel withPid(String pid) {
        this.setPid(pid);
        return this;
    }

}
