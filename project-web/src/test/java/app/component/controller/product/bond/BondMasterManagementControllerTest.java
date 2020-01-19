package app.component.controller.product.bond;

import app.commons.entities.products.BondMaster;
import app.component.service.products.bondMaster.BondMasterManagementService;
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
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BondMasterManagementControllerTest {

    @InjectMocks
    private BondMasterManagementController target;

    @MockBean
    @Qualifier("bondMasterManagementService")
    private BondMasterManagementService bondMasterManagementService;

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
        BondMaster bondMaster = new BondMaster();
        bondMaster.setBondCode("1000");
        bondMaster.setBondName("test bond");
        bondMaster.setCouponRate(BigDecimal.valueOf(0.00001));
        bondMaster.setFaceValue(BigDecimal.valueOf(100));
        bondMaster.setMaturity(new SimpleDateFormat("yyyy-MM-dd").parse("2039-12-31"));
        bondMaster.setIssueDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-12-31"));
        bondMaster.setDeleted(false);
        bondMaster.setVersion(Long.valueOf(0));


        List<BondMaster> bondMasterList = Arrays.asList(bondMaster);

        given(bondMasterManagementService.findAll()).willReturn(bondMasterList);

        mockMvc.perform(get("/management/products/currency"))
                .andExpect(status().isOk())
                .andExpect(view().name("management/products/currency/index"))
                .andExpect((model().attribute("bondList", bondMasterList)));
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
        List<BondMaster> bondMasterList = Collections.emptyList();

        given(bondMasterManagementService.findAll()).willReturn(bondMasterList);

        mockMvc.perform(get("/management/products/bond"))
                .andExpect(status().isOk())
                .andExpect(view().name("management/products/bond/index"))
                .andExpect((model().attribute("bondList", bondMasterList)));
    }
}