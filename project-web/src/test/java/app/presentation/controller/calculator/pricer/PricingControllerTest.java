package app.presentation.controller.calculator.pricer;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
@SpringBootTest
class PricingControllerTest {

    @InjectMocks
    private PricingController target;

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
        mockMvc.perform(get("/calc/price/"))
                .andExpect(status().isOk())
                .andExpect(view().name("calc/price/index"));
    }
}