CREATE TABLE owned_stocks_evaluation_timeseries (
    owned_stocks_id BIGINT NOT NULL AUTO_INCREMENT,
    evaluation_date Date Not null,
	current_value DECIMAL(10, 10) NOT NULL,
    is_deleted boolean NOT NULL default False,
    update_timestamp DATETIME NOT NULL,
    update_user varchar(500) NOT NULL,
    CONSTRAINT stock_portfolio_evaluation_timeseries_pkey PRIMARY KEY (owned_stocks_id, evaluation_date),
    FOREIGN KEY stock_portfolio_evaluation_timeseries_fkey2 (owned_stocks_id) references owned_stocks(id)
)