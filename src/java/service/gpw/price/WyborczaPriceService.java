package service.gpw.price;

import model.gpw.Price;
import service.gpw.file.FileUtils;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Q1O1 on 28-02-2017.
 */
public class WyborczaPriceService extends APriceService implements IPricesService {

    private static final String SEPARATOR = ";";
    private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static String urlString = "http://xml.wyborcza.biz/ArchivalProfileExportServlet.servlet";
    @Override
    public List<Price> loadPrices(String registerId) {
        List<Price> result = new ArrayList<Price>();
        try {
            URL url = new URL(urlString.concat(buildQS(registerId)));
            List<String> lines = FileUtils.loadTextFile(url.openStream());

            for(String line : lines.subList(1, lines.size()-1)){
                result.add(readLine(line));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    private String buildQS(String registerId){
        StringBuilder qs = new StringBuilder("?");
        qs.append("p5=").append(registerId).append("&");
        qs.append("p6=").append("1995-01-01").append("&");
        qs.append("p7=").append(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).append("&");
        qs.append("p3=").append("CSV").append("&");
        qs.append("type=SHARE");
        return qs.toString();
    }

    @Override
    protected final Price readLine(String line) {
        String[] fields = line.split(SEPARATOR);
        Price p = new Price();
        p.setOpen(new Integer(fields[0].replace(",", "").replace(".", "")));
        p.setMax(new Integer(fields[1].replace(",", "").replace(".", "")));
        p.setMin(new Integer(fields[2].replace(",", "").replace(".", "")));
        p.setClose(new Integer(fields[3].replace(",", "").replace(".", "")));
        p.setPercent(new Integer(fields[4].replace(",", "").replace(".", "").replace("%","")));
        p.setVolume(new Integer(fields[5].replace(",", "").replace(".", "")));
        p.setDate(LocalDate.parse(fields[6], DF));

        return p;
    }
}
