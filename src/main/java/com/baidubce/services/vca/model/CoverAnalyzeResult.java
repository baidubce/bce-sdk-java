package com.baidubce.services.vca.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * mca cover result model.
 *
 * @author wangzheng38
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoverAnalyzeResult {
    private CoverResultCell staticCoverResults;
    private CoverResultCell clipCoverResults;
    private List<String> gifCoverResult;

    public CoverResultCell getStaticCoverResults() {
        return staticCoverResults;
    }

    public void setStaticCoverResults(CoverResultCell staticCoverResults) {
        this.staticCoverResults = staticCoverResults;
    }

    public CoverResultCell getClipCoverResults() {
        return clipCoverResults;
    }

    public void setClipCoverResults(CoverResultCell clipCoverResults) {
        this.clipCoverResults = clipCoverResults;
    }

    public List<String> getGifCoverResult() {
        return gifCoverResult;
    }

    public void setGifCoverResult(List<String> gifCoverResult) {
        this.gifCoverResult = gifCoverResult;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CoverAnalyzeResult{");
        sb.append("staticCoverResults=").append(staticCoverResults);
        sb.append(", clipCoverResults='").append(clipCoverResults).append('\'');
        sb.append(", gifCoverResult='").append(gifCoverResult).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CoverResultCell {
        private List<String> urls;
        private String zipUrl;

        public List<String> getUrls() {
            return urls;
        }

        public void setUrls(List<String> urls) {
            this.urls = urls;
        }

        public String getZipUrl() {
            return zipUrl;
        }

        public void setZipUrl(String zipUrl) {
            this.zipUrl = zipUrl;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("CoverResultCell{");
            sb.append("urls=").append(urls);
            sb.append(", zipUrl='").append(zipUrl).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}

