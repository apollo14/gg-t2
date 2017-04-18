package service.gpw;

import model.CalculationsFilter;
import model.EOperationType;
import model.gpw.Operation;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Q1O1 on 21-02-2017.
 */
public class OperationService {
    public void buy(List<Operation> operations, Operation operation) {
        operations.add(operation);
    }

    public void sell(List<Operation> operations, Operation operation) {
        //<=
        if (operation.getVolume() <= getVolumeBalance(operations)){
            operations.add(operation);
        } else {
            throw new RuntimeException("SELL: volume balance is less then sell volume - ${operation.registerId}");
        }
    }

     private Integer getVolumeBalance(List<Operation> operations){
         Integer balance = 0;
         for (Operation it: operations.stream().filter(p -> p.getType().name().equals(EOperationType.BUY.name())).collect(Collectors.toList())) {
             balance = balance + it.getVolume();
         }
         for (Operation it: operations.stream().filter(p -> p.getType().name().equals(EOperationType.SELL.name())).collect(Collectors.toList())) {
             balance = balance - it.getVolume();
         }
         return balance;
     }

     public List<Operation> filter(List<Operation> operations, CalculationsFilter filter){
         List<Operation> filtered  = operations.stream().collect(Collectors.toList());
         if (filter.getFromYear() != null){
            filtered = filtered.stream().filter(p-> p.getDateCreated().getYear() >= Integer.parseInt(filter.getFromYear())).collect(Collectors.toList());
         }
         if (filter.getToYear() != null) {
             filtered = filtered.stream().filter(p->p.getDateCreated().getYear() <= Integer.parseInt(filter.getToYear())).collect(Collectors.toList());
         }
         return filtered;
     }
}
