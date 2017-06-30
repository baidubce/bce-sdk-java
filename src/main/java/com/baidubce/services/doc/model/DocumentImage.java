/*
 * Copyright 2017 Baidu, Inc.
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

package com.baidubce.services.doc.model;

/**
 * DocumentImage class used to store image result.
 *
 * Created by guofan on 2017/3/20.
 */
public class DocumentImage {
    /**
     * pageIndex indicate which page
     */
    private int pageIndex = -1;
    /**
     * url indicate page's image url address
     */
    private String url = null;

    /**
     * get pageIndex
     */
    public int getPageIndex() {
        return pageIndex;
    }

    /**
     * set pageIndex
     *
     * @param pageIndex  The document page index.
     */
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    /**
     * get url
     *
     * @return the image url
     */
    public String getUrl() {
        return url;
    }

    /**
     * set url
     *
     * @param url  set the image url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * to string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DocumentImage {\n");
        sb.append("    pageIndex: ").append(pageIndex).append("\n");
        sb.append("    url: ").append(url).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
