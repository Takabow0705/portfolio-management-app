package app.commons.entities.portfolio;

import app.commons.entities.portfolio.base.OwnedStockEvaluationTimeseriesBase;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Generation-Gap Patternによるクラス
 */
@Entity
@Table(name = "owned_stock_evaluation_timeseries")
public class OwnedStockEvaluationTimeseries extends OwnedStockEvaluationTimeseriesBase implements Serializable {
    private static final long serialVersionUID = 1l;
}
