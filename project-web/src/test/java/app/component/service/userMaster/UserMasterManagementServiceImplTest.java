package app.component.service.userMaster;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import project.infra.rdb.user.UserMaster;
import project.infra.rdb.user.UserMasterRepository;
import testcase.testdata.entity.UserMasterDataFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
class UserMasterManagementServiceImplTest {

    @MockBean
    private UserMasterRepository userMasterRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserMasterManagementService target;

    @BeforeEach
    void setup() {
        // 各テストの実行前にモックオブジェクトを初期化する
        MockitoAnnotations.initMocks(this);
        this.target = mock(UserMasterManagementService.class);
    }

    /**
     * サービスは空リストを返すこと。
     *
     * @throws Exception
     */
    @Test
    public void whenDataIsNothingfindAllReturnsEmptyList() throws Exception{
        //given
        when(userMasterRepository.findAll()).thenReturn(null);

        List<UserMaster> returnValue = target.findAll();
        assertThat(returnValue, equalTo(Collections.emptyList()));
    }

    /**
     * サービスは空リストを返すこと。
     *
     * @throws Exception
     */
    @Test
    public void whenDataIsNothingfindAllActiveUserReturnsEmptyList() throws Exception{
        //given
        when(userMasterRepository.findAll()).thenReturn(null);

        List<UserMaster> returnValue = target.findAllActiveUser();
        assertThat(returnValue, equalTo(Collections.emptyList()));
    }

    /**
     * すべて削除済みユーザのときは空リストを返すこと。
     *
     * @throws Exception
     */
    @Test
    public void whenUserMasterIsAllDeletedfindAllActiveUserReturnsEmptyList() throws Exception{

        //given
        UserMaster userMaster = UserMasterDataFactory.createDeletedUser();
        List<UserMaster> mockResult = Arrays.asList(userMaster);
        when(userMasterRepository.findAll()).thenReturn(mockResult);

        List<UserMaster> returnValue = target.findAllActiveUser();
        assertThat(returnValue, equalTo(Collections.emptyList()));
    }

    /**
     * 論理削除されていないユーザのみを返すこと。
     *
     * @throws Exception
     */
    @Test
    public void whenUserMasterIsNotDeletedfindAllActiveUserReturnsList() throws Exception{

        //given
        UserMaster deletedUser = UserMasterDataFactory.createDeletedUser();
        UserMaster userMaster = UserMasterDataFactory.createActiveUser();

        List<UserMaster> mockResult = Arrays.asList(userMaster, deletedUser);
        when(userMasterRepository.findAll()).thenReturn(mockResult);
        List<UserMaster> returnValue = target.findAllActiveUser();

        List<UserMaster> expectedResult = Arrays.asList(userMaster);
        assertThat(returnValue, equalTo(expectedResult));
        assertThat(1, equalTo(expectedResult.size()));
    }
}