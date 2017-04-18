package service.gpw.data;

import model.gpw.Operation;
import service.gpw.file.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by apollo14@o2.pl on 21-02-2017.
 */
public class FileDataService extends ADataService implements IDataService {

    private String filesLocation;

    public FileDataService(String filesLocation) {
        this.filesLocation = filesLocation;
    }

    public List<Operation> loadData() {
        List<Operation> result = new ArrayList<Operation>();
        File filesLocationDir = new File(filesLocation);
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.contains(FILE_EXT)){
                    return true;
                }
                return false;
            }
        };

        File[] files = filesLocationDir.listFiles(filter);
        if (files != null) {
            for (File file : files) {
                try {
                    FileInputStream fis = new FileInputStream(file);
                    result.addAll(loadFile(fis));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public void saveData(String filename, List<Operation> operations){
        File file = new File(filesLocation.concat("/").concat(filename.concat(FILE_EXT)));
        saveFile(file, operations);
    }

    private void saveFile(File file, List<Operation> operations){
        BufferedWriter bw = null;
        try{
            bw = new BufferedWriter(new FileWriter(file));
            List<Operation> operationsRO = operations.stream().collect(Collectors.toList());
            Collections.reverse(operationsRO);
            for(Operation operation: operationsRO){
                bw.append(writeLine(operation));
            }
            bw.flush();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (bw != null){
                try{
                    bw.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }


    private List<Operation> loadFile(FileInputStream fis){
        List<Operation> result = new ArrayList<Operation>();
        for(String line: FileUtils.loadTextFile(fis)) {
            result.add(readLine(line));
        }
        Collections.reverse(result);
        return result;
    }

}
