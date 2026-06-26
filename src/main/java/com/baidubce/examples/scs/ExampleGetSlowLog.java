/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.ScsListOrder;
import com.baidubce.services.scs.model.ScsSlowLogRequest;
import com.baidubce.services.scs.model.ScsSlowLogResponse;
import com.baidubce.services.scs.model.ScsSlowLogType;
import java.util.Date;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleGetSlowLog {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsSlowLogRequest request = new ScsSlowLogRequest();
        request.setInstanceId(INSTANCE_ID);
        request.setType(ScsSlowLogType.Redis);
        request.setStartTime(new Date(System.currentTimeMillis() - 3600L * 1000));
        request.setEndTime(new Date());
        request.setListOrder(ScsListOrder.Desc);
        request.setPageNow(1);
        request.setPageSize(10);
        ScsSlowLogResponse response = client.getSlowLog(request);
        System.out.println(response);
    }
}
