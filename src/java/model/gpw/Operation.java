package model.gpw;

import model.EBroker;
import model.EOperationType;
import model.EPercentage;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;

/**
 * Created by apollo14@o2.pl on 21-02-2017.
 */
public class Operation {
    private String registerId;
    private LocalDateTime dateCreated = LocalDateTime.now();
    private BigDecimal price;
    private BigDecimal commission;
    private EBroker broker;
    private Integer volume;
    private EOperationType type;
    private boolean manual = false;

    public Operation(String registerId, LocalDateTime dateCreated, BigDecimal price, Integer volume, EOperationType operationType, EBroker broker){
        this.registerId = registerId;
        this.dateCreated = dateCreated;
        this.price = price;
        this.volume = volume;
        this.type = operationType;
        this.broker = broker;
        BigDecimal commissionTmp = price.multiply(new BigDecimal(volume)).multiply(EPercentage.getPercentage(broker));//.round(new MathContext(2, RoundingMode.HALF_UP));
        this.commission = commissionTmp.compareTo(EPercentage.getMinValue(broker))==1 ? commissionTmp: EPercentage.getMinValue(broker);
    };

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getVolume() {
        return volume;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public EBroker getBroker() {
        return broker;
    }

    public void setBroker(EBroker broker) {
        this.broker = broker;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public EOperationType getType() {
        return type;
    }

    public void setType(EOperationType type) {
        this.type = type;
    }

    public boolean isManual() {
        return manual;
    }

    public void setManual(boolean manual) {
        this.manual = manual;
    }
}
