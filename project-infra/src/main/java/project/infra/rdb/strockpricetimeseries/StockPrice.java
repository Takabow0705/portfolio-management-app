package project.infra.rdb.strockpricetimeseries;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Generation-Gap Patternによるクラス
 */
@Entity
@Table(name = "stock_price")
public class StockPrice extends StockPriceBase implements Serializable {
}
