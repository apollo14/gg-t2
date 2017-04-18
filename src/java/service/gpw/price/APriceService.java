package service.gpw.price;

import model.gpw.Operation;
import model.gpw.Price;
import service.gpw.file.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by apollo14@o2.pl on 28-02-2017.
 */
public abstract class APriceService {

    private List<Price> loadFile(File file){
        List<Price> result = new ArrayList<Price>();
        for(String line: FileUtils.loadTextFile(file)) {
            result.add(readLine(line));
        }
        //Collections.reverse(result);
        return result;
    }

    protected abstract Price readLine(String line);
}
