package testcase.testdata.entity;

import app.commons.entities.products.StockMaster;

import java.math.BigDecimal;

public class StockMasterDataFactory {

    public static StockMaster createStock(){
        StockMaster data = new StockMaster();
        data.setStockCode(Long.valueOf(1000));
        data.setStockName("aaaaa");
        data.setMarket("test1");
        data.setSectorCode17(Long.valueOf(99));
        data.setSectorDetail17("test detail");
        data.setSectorCode33(Long.valueOf(10));
        data.setSectorDetail33("hoge");
        data.setMarketDevisionCode(Long.valueOf(1000));
        data.setUnit(new BigDecimal(("100")));
        return data;
    }

    public static StockMaster createStockA(){
        StockMaster data = new StockMaster();
        data.setStockCode(Long.valueOf(1000));
        data.setStockName("aaaaa");
        data.setMarket("test1");
        data.setSectorCode17(Long.valueOf(99));
        data.setSectorDetail17("test detail");
        data.setSectorCode33(Long.valueOf(10));
        data.setSectorDetail33("hoge");
        data.setMarketDevisionCode(Long.valueOf(1000));
        data.setUnit(new BigDecimal(("100")));
        return data;
    }

    public static StockMaster createStockB(){
        StockMaster data = new StockMaster();
        data.setStockCode(Long.valueOf(2000));
        data.setStockName("bbbbb");
        data.setMarket("test1");
        data.setSectorCode17(Long.valueOf(99));
        data.setSectorDetail17("test detail");
        data.setSectorCode33(Long.valueOf(10));
        data.setSectorDetail33("hoge");
        data.setMarketDevisionCode(Long.valueOf(1000));
        data.setUnit(new BigDecimal(("100")));
        return data;
    }
}
