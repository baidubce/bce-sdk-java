package com.baidubce.services.bcc.model.instance;

/**
 * The enum class to enumerate all the supported instance type in SDK.
 */
public enum InstanceType {
    /**
     * The instance of normal purpose.
     */
    N1,

    /**
     * The second Enhanced instance of normal purpose for {@link InstanceType#N1}.
     */
    N2,

    /**
     * The compute optimized instance.
     * See more information on
     * <a href = " https://cloud.baidu.com/publicity/bccplus.html#bccplus-introduction" >
     *     introduction for Compute optimized BCC
     * </a>
     */
    C1,

    /**
     * The storage optimized instance.
     * See more information on
     * <a href = " https://cloud.baidu.com/publicity/bccplus.html#bccplus-introduction" >
     *     introduction for Storage optimized BCC
     * </a>
     * When creating the storage optimized instance, one ephemeral disk must be created together.
     */
    S1
}
