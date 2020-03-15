package project.calculator.domain.repository.master.discountFactor;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import project.calculator.data.enums.PaymentType;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
@Qualifier("prod")
public class DiscountFactorDataRepositoryJdbcImpl implements DiscountFactorDataRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<BigDecimal> loadAnnualDiscountFactor() {
        List<DiscountFactor> annualDiscountFactor = this.findByPaymentType(PaymentType.Annual);
        List<BigDecimal> result = annualDiscountFactor.stream().map(DiscountFactor::getDiscountFactor).collect(Collectors.toList());;
        return result;
    }

    @Override
    public List<BigDecimal> loadSemiAnnualDiscountFactor() {
        List<DiscountFactor> annualDiscountFactor = this.findByPaymentType(PaymentType.SemiAnnual);
        List<BigDecimal> result = annualDiscountFactor.stream().map(DiscountFactor::getDiscountFactor).collect(Collectors.toList());;
        return result;
    }

    private List<DiscountFactor> findByPaymentType(PaymentType paymentType){
        String sql = "select term, discount_factor from discount_factor_master where payment_type = ?";
        List<DiscountFactor> result = jdbcTemplate.queryForObject(sql,
                new Object[]{paymentType.getDbValue()},
                discountFactorRowMapper());
        Collections.sort(result);
        return result;
    }

    private RowMapper<List<DiscountFactor>> discountFactorRowMapper() {

        return new RowMapper<List<DiscountFactor>>() {

            @Override
            public List<DiscountFactor> mapRow(ResultSet rs, int rowNum) throws SQLException {

                if( rs == null ){
                    return null;
                }

                List<DiscountFactor> list = new ArrayList<>();
                DiscountFactor discountFactor = new DiscountFactor();
                discountFactor.setTerm(rs.getDouble("term"));
                discountFactor.setDiscountFactor(rs.getBigDecimal("discount_factor"));
                list.add(discountFactor);

                while (rs.next()){
                    discountFactor = new DiscountFactor();
                    discountFactor.setTerm(rs.getDouble("term"));
                    discountFactor.setDiscountFactor(rs.getBigDecimal("discount_factor"));
                    list.add(discountFactor);
                }
                return list;
            }
        };
    }
}
