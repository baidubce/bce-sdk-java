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
package com.baidubce.services.esg.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.model.ListRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * The request for listing the enterprise security group owned by the user.
 */
@Getter
@Setter
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ListEsgRequest extends ListRequest {

  /**
   * The id of instance.
   * The optional parameter to list the enterprise security group.
   * If it's specified,only the enterprise security group related to the specified instance will be listed.
   */
  private String instanceId;

  /**
   * Configure request credential for the request.
   *
   * @param credentials a valid instance of BceCredentials.
   * @return ListEsgRequest with credentials.
   */
  @Override
  public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
    this.setRequestCredentials(credentials);
    return this;
  }
}
