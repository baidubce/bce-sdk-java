package com.baidubce.services.bos.model;

import java.util.List;

public class ListBucketInventoryResponse extends BosResponse{
    private List<Inventory> inventoryRuleList;

    public List<Inventory> getInventoryRuleList() {
        return inventoryRuleList;
    }

    public void setInventoryRuleList(List<Inventory> inventoryRuleList) {
        this.inventoryRuleList = inventoryRuleList;
    }
}
