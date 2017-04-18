package service.gpw;

import model.CalculationsFilter;
import model.gpw.Calculation;
import model.gpw.Operation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Q1O1 on 21-02-2017.
 */
public class CalculationService {

    public void buy(java.util.List<Calculation> calculations, Operation operation) {
        calculations.add(new Calculation(operation));
    }

    public void sell(List<Calculation> calculations, Operation operation){

        List<Calculation> notSold = calculations.stream().filter(p -> p.getSell() == null).collect(Collectors.toList());
        Integer volume = operation.getVolume();

        for(Calculation it : notSold){
            //>
            if (volume > 0){
                if (it.getVolume().compareTo(volume) != 1){
                    // buy <= sell
                    it.setSell(operation);
                    volume = volume - it.getVolume();
                } else {
                    // buy > sell
                    List<Calculation> newCalculations = divideBuyCalculation(it, operation, volume);
                    int index = calculations.indexOf(it);
                    calculations.remove(index);
                    calculations.addAll(index, newCalculations);
                    volume = 0;
                    return;
                }
            }
        }
    }

    private List<Calculation> divideBuyCalculation(Calculation calculation, Operation sell, Integer volume){
        List<model.gpw.Calculation> newCalculations = new ArrayList<Calculation>();
        Calculation first = new Calculation(calculation.getBuy());
        first.setSell(sell);
        first.setVolume(volume);
        newCalculations.add(first);
        Calculation second = new Calculation(calculation.getBuy());
        second.setVolume(calculation.getVolume() - volume);
        newCalculations.add(second);
        return newCalculations;
    }

    public void calculateIncome(List<Calculation> calculations){
        for(Calculation it: calculations){
            if (it.getSell() != null && it.getIncome() == BigDecimal.ZERO) {
                //(sell.price - buy.price)*volume
                it.setIncome((it.getSell().getPrice().add(it.getBuy().getPrice().negate())).multiply(BigDecimal.valueOf(it.getVolume())));
            }
        }
    }

    public List<Calculation> filterSold(List<Calculation> calculations, CalculationsFilter filter){
        List<Calculation> filtered = calculations.stream().filter(p -> p.getSell() != null).collect(Collectors.toList());
        if (filter.getFromYear() != null){
            filtered = filtered.stream().filter(p -> p.getSell().getDateCreated().getYear() >= Integer.parseInt(filter.getFromYear())).collect(Collectors.toList());
        }
        if (filter.getToYear() != null){
            filtered = filtered.stream().filter(p -> p.getSell().getDateCreated().getYear() <= Integer.parseInt(filter.getToYear())).collect(Collectors.toList());
        }
        return filtered;
    }

    public List<Calculation> filterNotSold(List<Calculation> calculations){
        List<Calculation> filtered = calculations.stream().filter(p -> p.getSell() == null).collect(Collectors.toList());
        return filtered;
    }

}
