package com.baidubce.services.bvw.model.matlib;

import com.baidubce.model.AbstractBceResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

@Data
@Wither
@AllArgsConstructor
@NoArgsConstructor
public class VideoGenerationResponse extends AbstractBceResponse {

    /**
     * 任务Id
     */
    private Long editId;
    /**
     * 结果视频时长
     */
    private Double duration;

}