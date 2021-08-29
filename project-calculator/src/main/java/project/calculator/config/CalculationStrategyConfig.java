package project.calculator.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import project.infra.rdb.discountfactor.DiscountFactorDataRepository;
import project.infra.rdb.discountfactor.DiscountFactorDataRepositoryJdbcImpl;
import project.infra.rdb.discountfactor.DiscountFactorDataRepositoryMockImpl;

@EntityScan
public class CalculationStrategyConfig {

    @Bean
    public DiscountFactorDataRepository discountFactorDataRepositoryJdbcImpl(JdbcTemplate jdbcTemplate){
        return new DiscountFactorDataRepositoryJdbcImpl(jdbcTemplate);
    }

    @Bean
    public DiscountFactorDataRepository discountFactorDataRepositoryMockImpl(){
        return new DiscountFactorDataRepositoryMockImpl();
    }
}
