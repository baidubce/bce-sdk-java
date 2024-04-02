package com.baidubce.services.as.model.asgroup;

import lombok.Data;

@Data
public class CmdConfig {
    // 是否配置缩容脚本
    private boolean hasDecreaseCmd;
    // 缩容策略，失败暂停缩容、失败继续缩容：Proceed、Pause
    private ExecCmdStrategyType decCmdStrategy = ExecCmdStrategyType.Proceed;
    // 缩容脚本
    private String decCmdData = "";
    // 超时时间s
    private int decCmdTimeout = 3600;
    // 手动移出是否执行
    private boolean decCmdManual = false;

    // 是否配置扩容脚本
    private boolean hasIncreaseCmd;
    // 扩容策略，失败暂停扩容、失败继续扩容：Proceed、Pause
    private ExecCmdStrategyType incCmdStrategy;
    // 扩容脚本
    private String incCmdData = "";
    // 超时时间s
    private int incCmdTimeout = 3600;
    // 手动移入是否执行
    private boolean incCmdManual = false;
}
