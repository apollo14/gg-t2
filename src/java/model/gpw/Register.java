package model.gpw;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Q1O1 on 21-02-2017.
 */
public class Register {
    private String id;
    private List<Operation> operations = new ArrayList<Operation>();
    private List<model.gpw.Calculation> calculations = new ArrayList<model.gpw.Calculation>();

    private Integer volume = 0;
    private BigDecimal income = BigDecimal.ZERO;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public List<Calculation> getCalculations() {
        return calculations;
    }

    public void setCalculations(List<Calculation> calculations) {
        this.calculations = calculations;
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
