package com.baidubce.services.eni.model;

/**
 * The action for operating the Elastic Network Interface Card.
 */
public enum EniAction {
    /**
     * update specified ENI name or description
     */
    modifyAttribute,
    attach,
    detach,
    bind,
    unbind,
    bindSg,
    bindEsg,
}
