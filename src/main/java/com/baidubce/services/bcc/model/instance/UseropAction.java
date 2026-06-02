package com.baidubce.services.bcc.model.instance;

public enum UseropAction {
    /**
     * Authorization event.
     */
    AuthorizeServerEvent,

    /**
     * Create authorize rule.
     */
    CreateAuthorizeRule,

    /**
     * Modify authorize rule attribute.
     */
    ModifyInstUserOpAuthorizeRuleAttribute,

    /**
     * Delete authorize rule.
     */
    DeleteInstUserOpAuthorizeRule,

    /**
     * Describe authorize rules.
     */
    DescribeAuthorizeRules,

    /**
     * Describe planned events.
     */
    DescribePlannedEvents,

    /**
     * Describe planned event records.
     */
    DescribePlannedEventRecords,

    /**
     * Check unplanned event.
     */
    CheckUnplannedEvent,

    /**
     * Describe unplanned events.
     */
    DescribeUnplannedEvents,

    /**
     * Describe unplanned event records.
     */
    DescribeUnplannedEventRecords;
}
