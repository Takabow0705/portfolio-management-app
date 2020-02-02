package app.component.controller.product.stock;

import app.commons.entities.products.StockMaster;
import app.component.service.products.stockMaster.StockMasterManagementService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StockMasterManagementControllerTest {

    @MockBean
    @Qualifier("stockMasterManagementService")
    private StockMasterManagementService stockMasterManagementService;

    @InjectMocks
    private StockMasterManagementController target;

    @Autowired
    private MockMvc mockMvc;

    /**
     * トップページを正常に取得できること
     * currencyList attributeが存在すること
     *
     * @throws Exception
     */
    @Test
    @WithMockUser
    public void getIndexPageWithStatus200() throws Exception{

        //given
        StockMaster stockMaster = new StockMaster();
        stockMaster.setStockCode(Long.valueOf(1000));
        stockMaster.setStockName("test stock");
        stockMaster.setMarket("test1");
        stockMaster.setSectorCode17(Long.valueOf(99));
        stockMaster.setSectorDetail17("test detail");
        stockMaster.setSectorCode33(Long.valueOf(10));
        stockMaster.setSectorDetail33("hoge");
        stockMaster.setMarketDevisionCode(Long.valueOf(1000));
        stockMaster.setUnit(new BigDecimal(("100")));

        List<StockMaster> stockMasterList = Arrays.asList(stockMaster);

        given(stockMasterManagementService.findAll()).willReturn(stockMasterList);

        mockMvc.perform(get("/management/products/stock/ja/"))
                .andExpect(status().isOk())
                .andExpect(view().name("management/products/stock/ja/index"))
                .andExpect((model().attribute("stocklist", stockMasterList)));
    }

    /**
     * トップページを正常に取得できること
     * currencyList attributeが存在すること
     *
     * @throws Exception
     */
    @Test
    @WithMockUser
    public void getIndexPageWithStatus200AndNoResult() throws Exception{

        //given
        List<StockMaster> stockMasterList = Collections.emptyList();

        given(stockMasterManagementService.findAll()).willReturn(stockMasterList);

        mockMvc.perform(get("/management/products/stock/ja/"))
                .andExpect(status().isOk())
                .andExpect(view().name("management/products/stock/ja/index"))
                .andExpect((model().attribute("stocklist", stockMasterList)));
    }
}