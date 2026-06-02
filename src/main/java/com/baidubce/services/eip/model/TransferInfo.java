package com.baidubce.services.eip.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransferInfo {
  /** 转移ID */
  private String transferId;

  /** 资源ID */
  private String instanceId;

  public TransferInfo setTransferId(String transferId) {
    this.transferId = transferId;
    return this;
  }

  public String getTransferId() {
    return this.transferId;
  }

  public TransferInfo setInstanceId(String instanceId) {
    this.instanceId = instanceId;
    return this;
  }

  public String getInstanceId() {
    return this.instanceId;
  }

  @Override
  public String toString() {
    return "TransferInfo{"
        + "transferId="
        + transferId
        + "\n"
        + "instanceId="
        + instanceId
        + "\n"
        + "}";
  }
}
