package service.gpw.price;

import model.gpw.Price;

import java.util.List;

/**
 * Created by apollo14@o2.pl on 28-02-2017.
 */
public interface IPricesService {
    List<Price> loadPrices(String registerId);
}
