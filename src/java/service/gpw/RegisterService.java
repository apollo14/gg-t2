package service.gpw;

import model.CalculationsFilter;
import model.EOperationType;
import model.gpw.Calculation;
import model.gpw.Operation;
import model.gpw.Register;
import model.gpw.Wallet;
import service.gpw.data.FileDataService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Q1O1 on 21-02-2017.
 */
public class RegisterService {
    private CalculationService calculationService;
    private OperationService operationService;
    private FileDataService dataService;

    public RegisterService(CalculationService calculationService, OperationService operationService, FileDataService dataService) {
        this.calculationService = calculationService;
        this.operationService = operationService;
        this.dataService = dataService;
    }

    public void addRegister(Wallet wallet, Register register){
        wallet.getRegisters().put(register.getId(), register);
    }

    public void add(Wallet wallet, Operation operation){
        if (operation.getType() == EOperationType.BUY) {
            buy(wallet, operation);
        } else {
            sell(wallet, operation);
        }
    }

    private void buy(Wallet wallet, Operation operation){
        Register register = wallet.getRegisters().get(operation.getRegisterId());
        if(register == null){
            register = new Register();
            register.setId(operation.getRegisterId());
            wallet.getRegisters().put(operation.getRegisterId(), register);
        }
        operationService.buy(register.getOperations(), operation);
        calculationService.buy(register.getCalculations(), operation);
        register.setVolume(calculateVolume(register));
    }

    private void sell(Wallet wallet, Operation operation){
        Register register = wallet.getRegisters().get(operation.getRegisterId());
        if (register != null) {
            operationService.sell(register.getOperations(), operation);
            calculationService.sell(register.getCalculations(), operation);
            register.setVolume(calculateVolume(register));
        } else {
            throw new RuntimeException("SELL: register not exists - ${operation.registerId}");
        }
    }

    public void loadData(Wallet wallet){
        wallet.setRegisters(new HashMap<String, Register>());
        List<Operation> operations = dataService.loadData();
        for(Operation it: operations){
            add(wallet, it);
        }
        calculateIncome(wallet);
        calculateVolume(wallet);
    }

    public void saveData(Wallet wallet){
        for(Register register: wallet.getRegisters().values()){
            dataService.saveData(register.getId(), register.getOperations());
        }
    }

    public void calculateIncome(Wallet wallet){
        wallet.setIncomeTotal(BigDecimal.ZERO);
        for(Register register : wallet.getRegisters().values()){
            calculationService.calculateIncome(register.getCalculations());
            for(Calculation it : register.getCalculations()){
                register.setIncome(register.getIncome().add(it.getIncome()));
                wallet.setIncomeTotal(wallet.getIncomeTotal().add(it.getIncome()));
            }
        }
    }

    public BigDecimal calculateIncome(Register register, CalculationsFilter filter){
        BigDecimal income = BigDecimal.ZERO;
        List<Calculation> calculationsFiltered = register.getCalculations();
        if (filter != null) {
            calculationsFiltered = calculationService.filterSold(register.getCalculations(), filter);
        }
        calculationService.calculateIncome(calculationsFiltered);
        for(Calculation it : calculationsFiltered){
            income = income.add(it.getIncome());
        }
        return income;
    }

    public void calculateVolume(Wallet wallet){
        for(Register register : wallet.getRegisters().values()){
            register.setVolume(calculateVolume(register));
        }
    }

    public Integer calculateVolume(Register register){
        int volume = 0;
        List<Calculation> calculationsFiltered = calculationService.filterNotSold(register.getCalculations());

        for(Calculation it : calculationsFiltered){
            volume = volume + it.getVolume();
        }
        return volume;
    }


}
