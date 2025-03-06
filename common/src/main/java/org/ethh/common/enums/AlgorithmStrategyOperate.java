package org.ethh.common.enums;

/**
 * @author wangyifei
 */
public enum AlgorithmStrategyOperate {
    // 部署
    Deploy,

    // 暂停
    Pause,

    // 恢复
    Recover,

    // 下线
    Offline,

    // 强制下线
    ForcedOffline,
    ;

    public static AlgorithmStrategyOperate getByName(String name) {
        for (AlgorithmStrategyOperate operate : AlgorithmStrategyOperate.values()) {
            if (operate.name().equals(name)) {
                return operate;
            }
        }
        return null;
    }
}