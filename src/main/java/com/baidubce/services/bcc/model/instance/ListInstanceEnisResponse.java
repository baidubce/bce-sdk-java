package com.baidubce.services.bcc.model.instance;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bcc.model.NicInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class ListInstanceEnisResponse extends AbstractBceResponse {
    private List<NicInfo> enis;
}
