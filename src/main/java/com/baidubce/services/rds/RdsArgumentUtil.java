package com.baidubce.services.rds;

import com.baidubce.BceClientException;
import com.baidubce.services.rds.model.RdsCreateInstanceRequest;
import com.baidubce.services.rds.model.RdsEngine;
import com.baidubce.services.rds.model.RdsRenewTimeUnit;
import com.baidubce.util.Validate;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Rds argument util
 */
public class RdsArgumentUtil {

    static final String REQUEST_STRING_EMPTY_ERROR_MESSAGE = "request %s should not be null or empty string";
    static final String OBJECT_NULL_ERROR_MESSAGE = "%s should not be null";

    private static final String[] SUPPORTED_CHARACTER_SET = {"utf8mb4", "latin1", "gbk", "utf8"};
    private static final List<Integer> NODE_AMOUNT_LIST = Collections.unmodifiableList(Arrays.asList(2, 4, 6, 8, 16));

    static void checkNull(Object value, String keyword) {
        Validate.checkNotNull(value, String.format(OBJECT_NULL_ERROR_MESSAGE, keyword));
    }

    static void checkMaxKeys(Integer maxKeys) {
        if (maxKeys != null && maxKeys > 1000) {
            throw new IllegalArgumentException("The maxKeys should less than 1000");
        }
    }

    static void checkString(String value, String keyword) {
        Validate.checkStringNotEmpty(value, String.format(REQUEST_STRING_EMPTY_ERROR_MESSAGE, keyword));
    }

    static void checkVolumeCapacity(Integer volumeCapacity) {
        if (volumeCapacity != null && volumeCapacity > 0 && volumeCapacity % 5 != 0) {
            String msg = "volumeCapacity is invalid,it has to be multiple of 5";
            throw new BceClientException(msg);
        }
    }

    static void checkPurchaseCount(int purchaseCount) {
        if (purchaseCount <= 0 || purchaseCount > 10) {
            String msg = "purchaseCount is invalid, value must range in (0,10]";
            throw new BceClientException(msg);
        }
    }

    static void checkEngine(RdsEngine engine) {
        if (engine == null) {
            throw new BceClientException("Please set rdsEngine");
        }
    }

    static void checkEngineVersion(String engineVersion) {
        if (StringUtils.isEmpty(engineVersion)) {
            throw new BceClientException("Please set engineVersion");
        }
    }

    static void checkCpuCount(Integer cpuCount) {
        if (cpuCount == null || cpuCount <= 0) {
            throw new BceClientException("cpuCount must greater than 0");
        }
    }

    static void checkMemoryCapacity(Integer memoryCapacity) {
        if (memoryCapacity == null || memoryCapacity <= 0) {
            throw new BceClientException("memoryCapacity must greater than 0");
        }
    }

    static void checkCharacterSetName(String characterSetName) {
        if (StringUtils.isNotEmpty(characterSetName)) {
            List<String> characterSet = Arrays.asList(SUPPORTED_CHARACTER_SET);
            if (!characterSet.contains(characterSetName)) {
                throw new BceClientException("Unsupported characterSetName");
            }
        }
    }

    static void checkAutoRenew(RdsCreateInstanceRequest request) {
        RdsRenewTimeUnit renewTimeUnit = request.getAutoRenewTimeUnit();
        Integer autoRenewTime = request.getAutoRenewTime();
        if (renewTimeUnit != null) {
            checkAutoRenew(renewTimeUnit, autoRenewTime);
        } else {
            request.setAutoRenewTime(null);
        }
    }

    static void checkAutoRenew(RdsRenewTimeUnit renewTimeUnit, Integer autoRenewTime) {
        if (renewTimeUnit == null) {
            throw new BceClientException("Please set autoRenewTimeUnit");
        }
        if (autoRenewTime == null) {
            throw new BceClientException("Please set autoRenewTime with renewTimeUnit together");
        }
        if (RdsRenewTimeUnit.YEAR == renewTimeUnit) {
            if (autoRenewTime <= 0 || autoRenewTime > 3) {
                throw new BceClientException("When renewTimeUnit is year,autoRenewTime is range in (0,3]");
            }
        } else {
            if (autoRenewTime <= 0 || autoRenewTime > 9) {
                throw new BceClientException("When renewTimeUnit is month,autoRenewTime is range in (0,9]");
            }
        }
    }

    static void checkPage(Integer pageNo, Integer pageSize) {
        if (pageNo != null) {
            if (pageNo <= 0) {
                throw new BceClientException("pageNo is invalid");
            }
        }
        if (pageSize != null) {
            if (pageSize > 2000) {
                throw new BceClientException("pageSize is invalid");
            }
        }
    }

    static void checkNodeAmount(Integer nodeAmount) {
        if (nodeAmount == null) {
            throw new BceClientException("nodeAmount can not be null");
        } else if (!NODE_AMOUNT_LIST.contains(nodeAmount)) {
            throw new BceClientException("nodeAmount value can only be 2,4,6,8,16");
        }
    }
}
