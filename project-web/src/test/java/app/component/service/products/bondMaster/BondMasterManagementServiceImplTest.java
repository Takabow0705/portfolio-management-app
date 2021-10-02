package app.component.service.products.bondMaster;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import project.infra.rdb.bondmaster.BondMaster;
import project.infra.rdb.bondmaster.BondMasterRepository;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@SpringBootTest
class BondMasterManagementServiceImplTest {

    @MockBean
    private BondMasterRepository bondMasterRepository;

    @InjectMocks
    private BondMasterManagementService target = new BondMasterManagementServiceImpl();

    @BeforeEach
    void setup() {
        // 各テストの実行前にモックオブジェクトを初期化する
        MockitoAnnotations.initMocks(this);
    }

    /**
     * サービスは空リストを返すこと。
     *
     * @throws Exception
     */
    @Test
    public void whenDataIsNothingfindAllReturnsEmptyList() throws Exception{
        //given
        when(bondMasterRepository.findAll()).thenReturn(null);

        List<BondMaster> returnValue = target.findAll();
        assertThat(returnValue, equalTo(Collections.emptyList()));
    }
}