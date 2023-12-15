/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.peerconn.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * The request for creating a peer conn.
 */
@Getter
@Setter
public class GetPeerConnRequest extends AbstractBceRequest {

    /**
     * The id of peer conn .
     */
    private String peerConnId;

    /**
     * The local or peer interface of peer conn while getting detail.
     * "initiator" means local, "acceptor" means peer.
     * If this parameter is not set, the same region will return a random peer.
     */
    private String role;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * Configure peerConnId for the request.
     *
     * @param peerConnId The peerConnId of GetPeerConnRequest
     * @return GetPeerConnRequest with specific peerConnId
     */
    public GetPeerConnRequest withPeerConnId(String peerConnId) {
        this.peerConnId = peerConnId;
        return this;
    }

    /**
     * Configure role for the request.
     *
     * @param role The role of GetPeerConnRequest
     * @return GetPeerConnRequest with specific role
     */
    public GetPeerConnRequest withRole(String role) {
        this.role = role;
        return this;
    }
}
