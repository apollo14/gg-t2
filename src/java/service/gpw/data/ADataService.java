package service.gpw.data;

import model.EBroker;
import model.EOperationType;
import model.gpw.Operation;
import service.gpw.data.IDataService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by apollo14@o2.pl on 24-02-2017.
 */
public abstract class ADataService implements IDataService {

    protected Operation readLine(String line){
        String[] fields;
        fields = line.split(SEPARATOR);

        LocalDateTime dateCreated = LocalDateTime.parse(fields[0], df);
        String registerId = fields[1];
        EOperationType operationType = KUPNO.equals(fields[2]) ? EOperationType.BUY : EOperationType.SELL;
        Integer volume = new Integer(fields[3].replace(',', '.'));
        BigDecimal price = new BigDecimal(fields[4].replace(',', '.'));
        EBroker broker = EBroker.valueOf(fields[5]);
        Operation operation = new Operation(registerId, dateCreated, price, volume, operationType, broker);

        return operation;
    }

    protected String writeLine(Operation operation) throws Exception{
        StringBuilder sb = new StringBuilder();
        sb.append(operation.getDateCreated().format(df)).append(SEPARATOR);
        sb.append(operation.getRegisterId()).append(SEPARATOR);
        sb.append(operation.getType().name().equals(EOperationType.BUY.name())? KUPNO: SPRZEDAZ).append(SEPARATOR);
        sb.append(operation.getVolume().toString()).append(SEPARATOR);
        sb.append(operation.getPrice().toString()).append(SEPARATOR);
        sb.append(operation.getBroker().toString()).append(SEPARATOR);
        sb.append("\n");
        return sb.toString();
    }

}
