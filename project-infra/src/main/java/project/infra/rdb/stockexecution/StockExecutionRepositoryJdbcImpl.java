package project.infra.rdb.stockexecution;

import org.springframework.jdbc.core.JdbcTemplate;

public class StockExecutionRepositoryJdbcImpl {
    private final JdbcTemplate jdbcTemplate;

    public StockExecutionRepositoryJdbcImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

}
