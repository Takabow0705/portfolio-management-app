package app.component.controller.calculator.pricer.bond;

import app.component.service.pricer.bond.BondPriceCalculationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
@SpringBootTest
class BondPriceCalculationControllerTest {

    @InjectMocks
    private BondPriceCalculationController target;

    @MockBean
    private BondPriceCalculationService service;

    @Autowired
    private MockMvc mockMvc;

    /**
     * トップページを正常に取得できること
     *
     * @throws Exception
     */
    @Test
    @WithMockUser
    public void getIndexPageWithStatus200() throws Exception{

        mockMvc.perform(get("/calc/price/bond"))
                .andExpect(status().isOk())
                .andExpect(view().name("calc/price/bond/index"));
    }
}