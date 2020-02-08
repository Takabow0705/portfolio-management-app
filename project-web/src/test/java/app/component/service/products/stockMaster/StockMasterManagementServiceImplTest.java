package app.component.service.products.stockMaster;

import app.commons.dto.SearchCondition;
import app.commons.entities.products.StockMaster;
import app.component.repository.product.StockMasterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import testcase.testdata.dto.SearchConditionFactory;
import testcase.testdata.entity.StockMasterDataFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyLong;
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

    /**
     * aを銘柄名に含むデータのみが抽出されること。
     *
     * @throws Exception
     */
    @Test
    public void whenSearchConditonContainsPartialTextMatchesAfindByConditionReturnsStocka() throws Exception{
        //given
        StockMaster stocka = StockMasterDataFactory.createStockA();
        StockMaster stockb = StockMasterDataFactory.createStockB();
        SearchCondition searchCondition = SearchConditionFactory.createCondition();

        List<StockMaster> mockResult = Arrays.asList(stocka, stockb);
        List<StockMaster> expectedResult = Arrays.asList(stocka);

        when(stockMasterRepository.findAllBy(anyLong(), anyLong())).thenReturn(mockResult);

        List<StockMaster> returnValue = target.findByCondition(searchCondition);
        assertThat(returnValue, equalTo(expectedResult));
    }

    /**
     * 検索結果がすべて返されること。
     *
     * @throws Exception
     */
    @Test
    public void whenSearchConditonContainsNullfindByConditionReturnsAllStocks() throws Exception{
        //given
        StockMaster stocka = StockMasterDataFactory.createStockA();
        StockMaster stockb = StockMasterDataFactory.createStockB();
        SearchCondition searchCondition = SearchConditionFactory.createEmptyPartialTextCondition();

        List<StockMaster> mockResult = Arrays.asList(stocka, stockb);
        List<StockMaster> expectedResult = Arrays.asList(stocka,stockb);

        when(stockMasterRepository.findAllBy(anyLong(), anyLong())).thenReturn(mockResult);

        List<StockMaster> returnValue = target.findByCondition(searchCondition);
        assertThat(returnValue, equalTo(expectedResult));
    }
}