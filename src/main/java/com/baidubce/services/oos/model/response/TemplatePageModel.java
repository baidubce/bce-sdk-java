package com.baidubce.services.oos.model.response;

import com.baidubce.services.oos.model.Template;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplatePageModel {
    private List<Template> templates;
    private String orderBy;
    private String order;
    private int pageNo;
    private int pageSize;
    private int totalCount;
}
