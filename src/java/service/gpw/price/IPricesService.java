package service.gpw.price;

import model.gpw.Price;

import java.util.List;

/**
 * Created by Q1O1 on 28-02-2017.
 */
public interface IPricesService {
    List<Price> loadPrices(String registerId);
}
