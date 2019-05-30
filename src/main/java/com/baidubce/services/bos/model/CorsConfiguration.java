/*
 * Copyright 2014 Baidu, Inc.
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
package com.baidubce.services.bos.model;

import java.util.List;

/**
 * CorsConfiguration for Bucket CORS(Cross-Origin Resource Sharing).
 * Bucket's CORS rule container.
 * Allow up to 100 rules.
 * If there are multiple configurations, the order of execution is from top to bottom.
 * Required Parameters: allowedOrigins.
 * Required Parameters: allowedMethods.
 * Optional Parameters: allowedHeaders.
 * Optional Parameters: allowedExposeHeaders.
 * Optional Parameters: maxAgeSeconds.
 */
public class CorsConfiguration {

    /**
     * the allowedOrigins of Bucket CORS.
     */
    private List<String> allowedOrigins;

    /**
     * the allowedMethods of Bucket CORS.
     */
    private List<AllowedMethods> allowedMethods;

    /**
     * the allowedHeaders of Bucket CORS.
     */
    private List<String> allowedHeaders;

    /**
     * the allowedExposeHeaders of Bucket CORS.
     */
    private List<String> allowedExposeHeaders;

    /**
     * the maxAgeSeconds of Bucket CORS.
     */
    private int maxAgeSeconds;

    /**
     * Gets allowedOrigins of Bucket CORS.
     * @return allowedOrigins of Bucket CORS.
     */
    public List<String> getAllowedOrigins() {
        return allowedOrigins;
    }

    /**
     * Sets allowedOrigins of Bucket CORS.
     * @param allowedOrigins The allowedOrigins of Bucket CORS.
     */
    public void setAllowedOrigins(List<String> allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }

    /**
     * Sets allowedOrigins of Bucket CORS.
     * @param allowedOrigins The allowedOrigins of Bucket CORS.
     * @return this object.
     */
    public CorsConfiguration withAllowedOrigins(List<String> allowedOrigins) {
        this.setAllowedOrigins(allowedOrigins);
        return this;
    }

    /**
     * Gets allowedMethods of Bucket CORS.
     * @return allowedMethods of Bucket CORS.
     */
    public List<AllowedMethods> getAllowedMethods() {
        return allowedMethods;
    }

    /**
     * Sets allowedMethods of Bucket CORS.
     * @param allowedMethods The allowedMethods of Bucket CORS.
     */
    public void setAllowedMethods(List<AllowedMethods> allowedMethods) {
        this.allowedMethods = allowedMethods;
    }

    /**
     * Sets allowedMethods of Bucket CORS.
     * @param allowedMethods The allowedMethods of Bucket CORS.
     * @return this object.
     */
    public CorsConfiguration withAllowedMethods(List<AllowedMethods> allowedMethods) {
        this.setAllowedMethods(allowedMethods);
        return this;
    }

    /**
     * Gets allowedHeaders of Bucket CORS.
     * @return allowedHeaders of Bucket CORS.
     */
    public List<String> getAllowedHeaders() {
        return allowedHeaders;
    }

    /**
     * Sets allowedHeaders of Bucket CORS.
     * @param allowedHeaders The allowedHeaders of Bucket CORS.
     */
    public void setAllowedHeaders(List<String> allowedHeaders) {
        this.allowedHeaders = allowedHeaders;
    }

    /**
     * Sets allowedHeaders of Bucket CORS.
     * @param allowedHeaders The allowedHeaders of Bucket CORS.
     * @return this object.
     */
    public CorsConfiguration withAllowedHeaders(List<String> allowedHeaders) {
        this.setAllowedHeaders(allowedHeaders);
        return this;
    }

    /**
     * Gets allowedExposeHeaders of Bucket CORS.
     * @return allowedExposeHeaders of Bucket CORS.
     */
    public List<String> getAllowedExposeHeaders() {
        return allowedExposeHeaders;
    }

    /**
     * Sets allowedExposeHeaders of Bucket CORS.
     * @param allowedExposeHeaders The allowedExposeHeaders of Bucket CORS.
     */
    public void setAllowedExposeHeaders(List<String> allowedExposeHeaders) {
        this.allowedExposeHeaders = allowedExposeHeaders;
    }

    /**
     * Sets allowedExposeHeaders of Bucket CORS.
     * @param allowedExposeHeaders The allowedExposeHeaders of Bucket CORS.
     * @return this object.
     */
    public CorsConfiguration withAllowedExposeHeaders(List<String> allowedExposeHeaders) {
        this.setAllowedExposeHeaders(allowedExposeHeaders);
        return this;
    }

    /**
     * Gets maxAgeSeconds of Bucket CORS.
     * @return maxAgeSeconds of Bucket CORS.
     */
    public int getMaxAgeSeconds() {
        return maxAgeSeconds;
    }

    /**
     * Sets maxAgeSeconds of Bucket CORS.
     * @param maxAgeSeconds The maxAgeSeconds of Bucket CORS.
     */
    public void setMaxAgeSeconds(int maxAgeSeconds) {
        this.maxAgeSeconds = maxAgeSeconds;
    }

    /**
     * Sets maxAgeSeconds of Bucket CORS.
     * @param maxAgeSeconds The maxAgeSeconds of Bucket CORS.
     * @return this object.
     */
    public CorsConfiguration withMaxAgeSeconds(int maxAgeSeconds) {
        this.setMaxAgeSeconds(maxAgeSeconds);
        return this;
    }

    /**
     * The void Constructor of Bucket CORS.
     */
    public CorsConfiguration() {
    }

    /**
     * The Constructor of Bucket CORS.
     * @param allowedOrigins The allowedOrigins of Bucket CROS.
     * @param allowedMethods The allowedMethods of Bucket CROS.
     * @param allowedHeaders The allowedHeaders of Bucket CROS.
     * @param allowedExposeHeaders The allowedExposeHeaders of Bucket CROS.
     * @param maxAgeSeconds The maxAgeSeconds of Bucket CROS.
     */
    public CorsConfiguration(List<String> allowedOrigins, List<AllowedMethods> allowedMethods,
                             List<String> allowedHeaders, List<String> allowedExposeHeaders, int maxAgeSeconds) {
        this.allowedOrigins = allowedOrigins;
        this.allowedMethods = allowedMethods;
        this.allowedHeaders = allowedHeaders;
        this.allowedExposeHeaders = allowedExposeHeaders;
        this.maxAgeSeconds = maxAgeSeconds;
    }

    @Override
    public String toString() {
        return "CorsConfiguration{"
                + "allowedOrigins=" + allowedOrigins
                + ", allowedMethods=" + allowedMethods
                + ", allowedHeaders=" + allowedHeaders
                + ", allowedExposeHeaders=" + allowedExposeHeaders
                + ", maxAgeSeconds=" + maxAgeSeconds
                + '}';
    }
}
