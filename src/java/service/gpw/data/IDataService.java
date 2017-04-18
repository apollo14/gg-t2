package service.gpw.data;

import model.gpw.Operation;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by apollo14@o2.pl on 24-02-2017.
 */
public interface IDataService {
     DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
     String KUPNO = "KUPNO";
     String SPRZEDAZ = "SPRZEDAŻ";
     String SEPARATOR = ";";
     String FILE_EXT = ".csv";

    List<Operation> loadData();
    void saveData(String filename, List<Operation> operations);

}
