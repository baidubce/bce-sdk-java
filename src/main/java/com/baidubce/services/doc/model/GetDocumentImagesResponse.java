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

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * GetDocumentImagesResponse class used to receive GetDocumentImage response
 * from API server.
 * Created by guofan on 2017/3/20.
 */
public class GetDocumentImagesResponse extends AbstractBceResponse {
    /**
     * images is a list to store the document's images
     */
    private List<DocumentImage> images = null;

    /**
     * get document images list
     *
     * @return the document image list
     */
    public List<DocumentImage> getImages() {
        return images;
    }

    /**
     * set document image list
     *
     * @param images the document image list
     */
    public void setImages(List<DocumentImage> images) {
        this.images = images;
    }

    /**
     * to string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DocuementImages {\n");
        sb.append("    images: ").append(images).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
