package com.baidubce.services.vod.model;

public class PlaybackCode {

    /*
     * The type of code snippet Possible values are: html, flash, url.
     */
    private String codeType;

    /*
     * The code snippet
     */
    private String sourceCode;

    public PlaybackCode() {
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PlaybackCode { \n");
        sb.append("   codeType = ").append(codeType).append("\n");
        sb.append("   sourceCode = ").append(sourceCode).append("\n");
        sb.append("  }\n");
        return sb.toString();
    }

}
