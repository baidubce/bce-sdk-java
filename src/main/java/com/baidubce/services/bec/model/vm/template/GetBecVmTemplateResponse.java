package com.baidubce.services.bec.model.vm.template;

import com.baidubce.services.bec.model.vo.BecResultVo;
import com.baidubce.services.bec.model.vo.VmTemplateVo;
import lombok.Data;

/**
 * The response for getting the details of the BEC virtual machine template.
 */
@Data
public class GetBecVmTemplateResponse extends BecResultVo<VmTemplateVo> {
}
