package com.zerobase.houseutils.policy;

import com.zerobase.houseutils.exception.ErrorCode;
import com.zerobase.houseutils.exception.HouseUtilsException;

import java.util.List;

public interface BrokeragePolicy {

    List<BrokerageRule> getRules();

    default Long calculate(Long price) {
        BrokerageRule brokerageRule = getRules().stream()
                .filter(rule -> price < rule.getLessThan())
                .findFirst()
                .orElseThrow(() -> new HouseUtilsException(ErrorCode.INTERNAL_ERROR));

        return brokerageRule.calcMaxBrokerage(price);
    }
}
