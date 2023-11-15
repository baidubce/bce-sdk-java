package com.baidubce.services.bcc.model.image;

import com.baidubce.model.ListResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @Author: lilu24
 * @Date: 2023-09-04
 */

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetAvailableImagesBySpecResponse extends ListResponse {



    private List<SystemImageModel> images;
}
