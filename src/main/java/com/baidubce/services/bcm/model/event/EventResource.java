package com.baidubce.services.bcm.model.event;

import com.baidubce.services.bcm.model.Dimension;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongjiawei on 2023/12/12.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventResource {

    List<Dimension> identifiers = new ArrayList<Dimension>();
}
