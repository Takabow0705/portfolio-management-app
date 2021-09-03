package app.component.controller.user;

import app.commons.dto.UserMasterDto;
import app.commons.entities.user.UserMaster;
import app.commons.enums.UserAuthentication;
import app.component.service.userMaster.UserMasterManagementService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import testcase.testdata.entity.UserMasterDataFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class UserMasterManagementControllerTest {

    @InjectMocks
    private UserMasterManagementController target;

    @MockBean
    @Qualifier("userMasterServiceImpl")
    private UserMasterManagementService userMasterManagementService;

    @Autowired
    private MockMvc mockMvc;

    /**
     * トップページを正常に取得できること
     * users attributeが存在すること
     *
     * @throws Exception
     */
    @Test
    @WithMockUser
    public void getIndexPageWithStatus200() throws Exception{

        //given
        UserMaster userMaster = UserMasterDataFactory.createActiveUser();
        List<UserMaster> userMasterList = Arrays.asList(userMaster);

        given(userMasterManagementService.findAll()).willReturn(userMasterList);

        mockMvc.perform(get("/management/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("management/users/index"))
                .andExpect((model().attribute("users", userMasterList)));
    }

    /**
     * トップページを正常に取得できること
     * users attributeが存在すること
     *
     * @throws Exception
     */
    @Test
    @WithMockUser
    public void getIndexPageWithStatus200AndNoResult() throws Exception{

        //given
        List<UserMaster> userMasterList = Collections.emptyList();

        given(userMasterManagementService.findAll()).willReturn(userMasterList);

        mockMvc.perform(get("/management/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("management/users/index"))
                .andExpect((model().attribute("users", userMasterList)));
    }

    /**
     * createページが取得できること
     * authアトリビュートが存在すること
     * userMasterFormが存在すること
     *
     * @throws Exception
     */
    @Test
    @WithMockUser
    public void getCreatePageWithStatus200() throws Exception{
        mockMvc.perform(get("/management/users/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("management/users/create"))
                .andExpect((model().attribute("auth", UserAuthentication.values())))
                .andExpect((model().attribute("userMasterForm", new UserMasterDto())));
    }

    /**
     *　POST実行時にserviceが呼ばれること。正常にリダイレクトされること
     */
    @Test
    @WithMockUser
    public void postCreatePageWithStatus200() throws Exception{
        //given UserMasterのFormを準備
        UserMasterDto form = new UserMasterDto();
        form.setUserName("test-user");
        form.setPassword("testtest1");
        form.setMailAddress("testuser01@example.com");
        form.setUserAuthentication("ADMIN");

        //when .paramを使って書き換える
        mockMvc.perform(
                post("/management/users")
                        .with(csrf())
                        .param("password", form.getPassword())
                        .param("userAuthentication", form.getUserAuthentication())
                        .param("userName", form.getUserName())
                        .param("mailAddress", form.getMailAddress())
                )
                .andExpect(model().hasNoErrors())
                .andExpect(view().name("redirect:/management/users/"));

        //then
        verify(userMasterManagementService, times(1)).saveNewUser(form);
    }

    /**
     *　更新ページが正常にロードされること。
     * 指定されたユーザ情報が表示されること。
     *
     */
    @Test
    @WithMockUser
    public void getUpdatePagesWith200() throws Exception{

        UserMaster userMaster = UserMasterDataFactory.createActiveUser();

        given(userMasterManagementService.findByUserId(Long.valueOf(1))).willReturn(userMaster);

        mockMvc.perform(get("/management/users/1/update"))
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors())
                .andExpect(model().attribute("userMaster", userMaster))
                .andExpect(model().attribute("auth", UserAuthentication.values()))
                .andExpect(view().name("management/users/update"));
    }

    /**
     * 削除処理が正常終了し、トップページにリダイレクトされること。
     *
     */
    @Test
    @WithMockUser
    public void deleteUserWithRedirect() throws Exception{

        //given
        UserMaster userMaster = UserMasterDataFactory.createActiveUser();
        List<UserMaster> userMasterList = Arrays.asList(userMaster);

        given(userMasterManagementService.findAll()).willReturn(userMasterList);

        mockMvc.perform(post("/management/users/1/delete").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().hasNoErrors())
                .andExpect(view().name("redirect:/management/users/"));

        verify(userMasterManagementService, times(1)).delete(Long.valueOf(1));
    }

}