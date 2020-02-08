package app.component.controller.auth;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
@SpringBootTest
public class AuthenticationControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private AuthenticationController target;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(target).build();
    }

    /**
     * 認証を行ったときに正常に先頭ページにredirectすること
     * @throws Exception
     */
    @Test
    public void postLoginFormPageWithStatus200() throws Exception{
        mockMvc.perform(post("/login"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    /**
     * 認証されていない場合は400系エラーとなる
     * @throws Exception
     */
    @Test
    public void getIndexPageWithStatus400() throws Exception{
        mockMvc.perform(get("/")).andExpect(status().is4xxClientError());
    }

}