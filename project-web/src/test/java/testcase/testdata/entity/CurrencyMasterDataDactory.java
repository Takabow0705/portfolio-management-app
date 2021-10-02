package testcase.testdata.entity;


import project.infra.rdb.currencymaster.CurrencyMaster;

public class CurrencyMasterDataDactory {

    public static CurrencyMaster createCurrency(){
        CurrencyMaster data = new CurrencyMaster();
        data.setCountryCode("100");
        data.setCurrencyCode("001");
        data.setCurrencyId("1");
        data.setCurrencyName("test currency");
        return data;
    }
}
