package com.baidubce.services.ipcollection.emum;

/**
 * ip collection action
 */
public enum IpCollectionAction {
    /**
     * update specified ip collection name or description
     */
    modifyAttribute,
    attach,
    detach,
    bind,
    unBind,
    bindSg,
    bindEsg,
}