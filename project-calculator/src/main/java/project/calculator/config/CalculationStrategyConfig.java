package project.calculator.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import project.calculator.data.BondPricingData;
import project.calculator.domain.finance.bond.algo.CalculationStrategy;
import project.calculator.domain.finance.bond.algo.pricing.BondTheoreticalPricingStrategy;
import project.infra.rdb.discountfactor.DiscountFactorDataRepository;
import project.infra.rdb.discountfactor.DiscountFactorDataRepositoryJdbcImpl;
import project.infra.rdb.discountfactor.DiscountFactorDataRepositoryMockImpl;

@Configuration
@EnableAutoConfiguration
public class CalculationStrategyConfig {

    /**
     * 本番用DFマスターデータRepository
     * @param jdbcTemplate
     * @return
     */
    @Bean
    public DiscountFactorDataRepository discountFactorDataRepositoryJdbcImpl(JdbcTemplate jdbcTemplate){
        return new DiscountFactorDataRepositoryJdbcImpl(jdbcTemplate);
    }

    /**
     * テスト用Repository
     * @return
     */
    @Bean
    public DiscountFactorDataRepository discountFactorDataRepositoryMockImpl(){
        return new DiscountFactorDataRepositoryMockImpl();
    }

    /**
     * 債権理論価格計算用JavaBean
     * @param discountFactorDataRepository
     * @return
     */
    @Bean
    public CalculationStrategy<BondPricingData> bondTheoreticalPricingStrategy(@Qualifier("discountFactorDataRepositoryJdbcImpl") DiscountFactorDataRepository discountFactorDataRepository){
        return new BondTheoreticalPricingStrategy(discountFactorDataRepository);
    }
}
