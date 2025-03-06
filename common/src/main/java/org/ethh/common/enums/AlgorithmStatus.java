package org.ethh.common.enums;

/**
 * @author wangyifei
 * 算法状态
 */
public enum AlgorithmStatus {
    // 暂停(未开始)
    Init,

    // 运行中-库存变更
    DeployedPosition,

    // 运行中-做市
    Deployed,

    // 运行中-休市
    DeployedCloesed,

    // 暂停(手动)
    Paused,

    // 运行中-清仓
    Undeploying,

    // 已下线
    Undeployed
}
