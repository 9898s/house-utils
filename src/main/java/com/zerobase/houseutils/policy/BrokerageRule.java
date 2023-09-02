package com.zerobase.houseutils.policy;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

/**
 * 가격이 특정 범위일 때 상환 효율과 상환 금액을 가지는 클래스
 */

@AllArgsConstructor
@NoArgsConstructor
public class BrokerageRule {
    private Double brokeragePercent;

    @Nullable
    private Long limitAmount;

    public Long calcMaxBrokerage(Long price) {
        if (limitAmount == null) {
            return multiplyPercent(price);
        }
        return Math.min(multiplyPercent(price), limitAmount);
    }

    private Long multiplyPercent(Long price) {
        return Double.valueOf(Math.floor(brokeragePercent / 100 * price)).longValue();
    }
}
