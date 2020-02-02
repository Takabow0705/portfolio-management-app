package app.component.service.products.stockMaster;

import app.commons.entities.products.StockMaster;
import app.component.repository.product.StockMasterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@SpringBootTest
class StockMasterManagementServiceImplTest {

    @MockBean
    private StockMasterRepository stockMasterRepository;

    @InjectMocks
    private StockMasterManagementService target = new StockMasterManagementServiceImpl();

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
        when(stockMasterRepository.findAll()).thenReturn(null);

        List<StockMaster> returnValue = target.findAll();
        assertThat(returnValue, equalTo(Collections.emptyList()));
    }
}