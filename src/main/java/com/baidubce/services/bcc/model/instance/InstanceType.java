/*
 * Copyright (c) 2014-2019 Baidu.com, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
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
     * The third Enhanced instance of normal purpose using Intel Skylake.
     */
    N3,

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
    S1,

    /**
     * The gpu instance.
     * See more information on
     * <a href = " https://cloud.baidu.com/product/gpu.html" >
     *     introduction for gpu BCC
     * </a>
     * When creating the gpu instance, one or more gpu card info must be created.
     * More gpu specification see document on
     * <a href = "https://cloud.baidu.com/doc/BCC/API.html#.5E.9B.3F.DF.1D.60.51.F5.A2.B0.FC.3D.24.64.A0.8C">
     *     the specification for gpu instance
     *  </a>
     */
    G1,

    /**
     * The fpga instance.
     * See more information on
     * <a href = " https://cloud.baidu.com/product/fpga.html" >
     *     introduction for fpga BCC
     * </a>
     * When creating the fpga instance, one or more fpga card info must be created.
     * More fpga specification see document on
     * <a href = "https://cloud.baidu.com/doc/BCC/API.html#.84.A3.73.8E.D2.2E.66.28.54.10.BF.38.C3.94.F7.9A">
     *     the specification for fpga instance
     *  </a>
     */
    F1



}
