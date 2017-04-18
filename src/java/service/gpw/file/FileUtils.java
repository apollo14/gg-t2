package service.gpw.file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apollo14@o2.pl on 28-02-2017.
 */
public class FileUtils {
    public static List<String> loadTextFile(InputStream s){
        List<String> result = new ArrayList<String>();
        BufferedReader br = null;
        String line;
        String[] fields;
        try {
            br = new BufferedReader(new InputStreamReader(s));
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }
}
