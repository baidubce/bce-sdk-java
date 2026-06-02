package com.baidubce.examples.rds;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.rds.RdsClient;
import com.baidubce.services.rds.model.RdsUpdateWriteListResquest;
import com.baidubce.services.rds.model.RdsViewWriteListRequest;
import com.baidubce.services.rds.model.RdsViewWriteListResponse;

import java.util.Arrays;
import java.util.List;

import static com.baidubce.examples.rds.RdsUtil.createRdsClient;
import static com.baidubce.examples.rds.RdsUtil.print;

public class TestUpdateWriteList {
    public static void main(String[] args) {
        RdsClient rdsClient = createRdsClient();

        // get etag
        RdsViewWriteListRequest request = new RdsViewWriteListRequest();
        request.setInstanceId("rds-V5NF0bDg");
        RdsViewWriteListResponse response = rdsClient.viewWriteList(request);

        // update writeList
        RdsUpdateWriteListResquest request2 = new RdsUpdateWriteListResquest();
        request2.setInstanceId("rds-V5NF0bDg");
        request2.setEtag(response.getEtag());
        List<String> ips = Arrays.asList("%");
        request2.setSecurityIps(ips);

        AbstractBceResponse response2 = rdsClient.updateWriteList(request2);

        print("updateWriteList", response);
    }
}
