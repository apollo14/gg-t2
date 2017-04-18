package model;

import java.math.BigDecimal;

/**
 * Created by Q1O1 on 24-02-2017.
 */
public enum EPercentage {

    MBANK(new BigDecimal("0.0039"), new BigDecimal("3.0"));

    private BigDecimal procentage;
    private BigDecimal min;

    EPercentage(BigDecimal procentage, BigDecimal min){
        this.procentage = procentage;
        this.min = min;
    }

    public static BigDecimal getPercentage(EBroker broker){
        for(EPercentage value : EPercentage.values()){
            if (value.name().equals(broker.name())){
                return value.procentage;
            }
        }
        throw new RuntimeException("Broker nie jest obsługiwany: ".concat(broker.name()));
    }

    public static BigDecimal getMinValue(EBroker broker){
        for(EPercentage value : EPercentage.values()) {
            if (value.name().equals(broker.name())) {
                return value.min;
            }
        }
        throw new RuntimeException("Broker nie jest obsługiwany: ".concat(broker.name()));
    }

}
