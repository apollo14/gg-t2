package model.gpw;

import java.math.BigDecimal;

/**
 * Created by apollo14@o2.pl on 21-02-2017.
 */
public class Calculation {
    private Operation buy;
    private Operation sell;
    private Integer volume = 0;
    private BigDecimal income = BigDecimal.ZERO;

    public Calculation(Operation buy){
        this.buy = buy;
        this.volume = buy.getVolume();
    }

    public Operation getBuy() {
        return buy;
    }

    public void setBuy(Operation buy) {
        this.buy = buy;
    }

    public Operation getSell() {
        return sell;
    }

    public void setSell(Operation sell) {
        this.sell = sell;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }
}
