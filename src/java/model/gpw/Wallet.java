package model.gpw;

import service.gpw.RegisterService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Q1O1 on 21-02-2017.
 */
public class Wallet {
    private Map<String, Register> registers;
    private BigDecimal incomeTotal = BigDecimal.ZERO;

    private RegisterService registerService;

    private Wallet(RegisterService registerService) {
        this.registerService = registerService;
        registers = new HashMap<String, Register>();
        registerService.loadData(this);
    }

    public Map<String, Register> getRegisters() {
        return registers;
    }

    public void setRegisters(Map<String, Register> registers) {
        this.registers = registers;
    }

    public BigDecimal getIncomeTotal() {
        return incomeTotal;
    }

    public void setIncomeTotal(BigDecimal incomeTotal) {
        this.incomeTotal = incomeTotal;
    }

}
