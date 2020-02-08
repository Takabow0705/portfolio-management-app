package app.component.controller.product.currency;

import app.commons.entities.products.CurrencyMaster;
import app.component.service.products.currencyMaster.CurrencyMasterManagementService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import testcase.testdata.entity.CurrencyMasterDataDactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CurrencyMasterManagementControllerTest {

    @InjectMocks
    private CurrencyMasterManagementController target;

    @MockBean
    @Qualifier("currencyMasterManagementService")
    private CurrencyMasterManagementService currencyMasterManagementService;

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
        CurrencyMaster currencyMaster = CurrencyMasterDataDactory.createCurrency();

        List<CurrencyMaster> currencyList = Arrays.asList(currencyMaster);

        given(currencyMasterManagementService.findAll()).willReturn(currencyList);

        mockMvc.perform(get("/management/products/currency"))
                .andExpect(status().isOk())
                .andExpect(view().name("management/products/currency/index"))
                .andExpect((model().attribute("currencyList", currencyList)));
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
        List<CurrencyMaster> currencyList = Collections.emptyList();

        given(currencyMasterManagementService.findAll()).willReturn(currencyList);

        mockMvc.perform(get("/management/products/currency"))
                .andExpect(status().isOk())
                .andExpect(view().name("management/products/currency/index"))
                .andExpect((model().attribute("currencyList", currencyList)));
    }
}