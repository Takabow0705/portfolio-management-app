package project.calculator.domain.batch.porfolio.impl;

import project.calculator.domain.batch.porfolio.DailyPosition;
import project.calculator.domain.batch.porfolio.UnitPriceCalculator;
import project.calculator.domain.calendar.BusinessDays;
import project.infra.rdb.stockexecution.entity.StockExecution;
import project.infra.rdb.stockportfolioevaluation.StockPortfolioEvaluation;
import project.infra.rdb.strockpricetimeseries.StockPrice;
import project.infra.rdb.strockpricetimeseries.StockPriceBase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 移動平均法によって平均取得単価を計算するクラス
 */
public class MovingAverageUnitPriceCalculator implements UnitPriceCalculator<List<StockPortfolioEvaluation>> {

    private final List<StockExecution> execution;
    private final List<StockPrice> stockPrice;
    private final BusinessDays businessDays;

    private MovingAverageUnitPriceCalculator(List<StockExecution> execution, List<StockPrice> stockPrice, BusinessDays businessDays) {
        this.execution = execution;
        this.stockPrice = stockPrice;
        this.businessDays = businessDays;
    }

    /**
     * 平均取得単価の計算を実行する計算機インスタンスを返す。
     * 計算対象の約定データは株式コード毎に設定される想定になっているため、内部でその確認を行う。
     * @param execution
     * @param stockPrice
     * @return
     */
    public static UnitPriceCalculator<List<StockPortfolioEvaluation>> generate(List<StockExecution> execution, List<StockPrice> stockPrice, BusinessDays businessDays){
        Set<String> stockCodes = execution.stream().map(StockExecution::getStockCode).collect(Collectors.toSet());
        Set<String> marketDateCodes = stockPrice.stream().map(StockPriceBase::getStockCode).collect(Collectors.toSet());
        if(stockCodes.isEmpty() || stockCodes.size() != 1){
            throw new IllegalArgumentException(String.format("Calculation target must have only 1 stock code. Actual Num -> %d", stockPrice.size()));
        }
        if(marketDateCodes.isEmpty() || marketDateCodes.size() != 1){
            throw new IllegalArgumentException(String.format("StockPrice must have only 1 stock code. Actual Num -> %d", marketDateCodes.size()));
        }
        return new MovingAverageUnitPriceCalculator(execution,stockPrice,businessDays);
    }

    @Override
    public List<StockPortfolioEvaluation> calculate() {

        // 初期値
        BigDecimal prevAmount = BigDecimal.ZERO;
        BigDecimal prevBookValue = BigDecimal.ZERO;
        BigDecimal prevTotalValue = BigDecimal.ZERO;
        // 対象基準日からDailyPositionを生成
        List<DailyPosition> dailyPositions = this.businessDays.getBusinessDays().stream()
                .map(baseDate -> DailyPosition.aggregateDailyExecution(baseDate, execution))
                .collect(Collectors.toList());

        List<StockPortfolioEvaluation> result = new ArrayList<>();
        for(LocalDate businessday : this.businessDays.getBusinessDays()){
            // businessdayのポジションを取得。
            DailyPosition tPosition = dailyPositions.stream().filter(p -> p.getBaseDate().equals(businessday)).findFirst().orElseThrow(IllegalArgumentException::new);

            // T時点までの買ポジションの合計を算出する。
            BigDecimal totalAmount = prevAmount.add(tPosition.getLongAmount());
            // ポジションが存在するならT-1 時点までの平均取得単価からT時点までの平均取得単価を計算する。
            if(!totalAmount.equals(BigDecimal.ZERO)){
                prevBookValue = prevTotalValue.add(tPosition.calculateLongValue()).divide(prevBookValue.add(tPosition.getLongAmount()), 10 , RoundingMode.DOWN);
            }
            // T-1 までの残高に T のポジション残高を加算する。
            prevAmount = prevAmount.add(tPosition.calculateTotalAmount());

            // マーケットデータを取得する。
            StockPrice stockPrice = this.retrieveStockPrice(businessday);

            BigDecimal currentValue = null;
            BigDecimal currentPl = null;
            boolean isLockOut = true;
            LocalDate evaluationDateBaseDate = null;

            if (stockPrice != null){
                currentValue = stockPrice.getClosePrice();
                currentPl = currentValue.subtract(prevBookValue);
                isLockOut = stockPrice.getBaseDate().equals(businessday);
                evaluationDateBaseDate = stockPrice.getBaseDate();
            }

            // 必要なエンティティを構築する。
            StockPortfolioEvaluation evaluation = new StockPortfolioEvaluation();

            evaluation.setStockPortfolioId(tPosition.getStockPortfolioId());
            evaluation.setStockCode(tPosition.getStockCode());
            evaluation.setBaseDate(businessday);
            evaluation.setCurrentValue(currentValue);
            evaluation.setBookValue(prevBookValue);
            evaluation.setAmount(prevAmount);
            evaluation.setCurrentPl(currentPl);
            evaluation.setLockOut(isLockOut);
            evaluation.setEvaluationDateBaseDate(evaluationDateBaseDate);
            evaluation.setCreateUser("Calculator_" + Thread.currentThread().getName());
            evaluation.setCreateTimestamp(Timestamp.valueOf(LocalDateTime.now()));
            evaluation.setUpdateUser("Calculator_" + Thread.currentThread().getName());
            evaluation.setUpdateTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        }
        return result;
    }

    /**
     * baseDateのStockPriceを取得する。
     * 該当のデータがない場合は、baseDate以前の最新のStockPriceで横置きする。
     * 横置きも不可の場合はnullを返す。
     * @param baseDate
     * @return
     */
    private StockPrice retrieveStockPrice(LocalDate baseDate){
        StockPrice stockPrice = this.stockPrice.stream()
                .filter(s -> baseDate.equals(s.getBaseDate()) || s.getBaseDate().isBefore(baseDate))
                .findFirst().orElse(null);
        return stockPrice;
    }
}
