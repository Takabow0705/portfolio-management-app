package app.component.service.products.currencyMaster;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import project.infra.rdb.currencymaster.CurrencyMaster;
import project.infra.rdb.currencymaster.CurrencyMasterRepository;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@SpringBootTest
class CurrencyMasterManagementServiceImplTest {

    @MockBean
    private CurrencyMasterRepository currencyMasterRepository;

    @InjectMocks
    private CurrencyMasterManagementService target = new CurrencyMasterManagementServiceImpl();

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
        when(currencyMasterRepository.findAll()).thenReturn(null);

        List<CurrencyMaster> returnValue = target.findAll();
        assertThat(returnValue, equalTo(Collections.emptyList()));
    }
}