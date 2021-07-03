CREATE TABLE stock_portfolio_evaluation (
    stock_code CHAR(50) NOT NULL,
    evaluation_date Date Not null,
	current_value DECIMAL(20, 10) NOT NULL,
	amount DECIMAL(20,10) NOT NULL,
	currency_code CHAR(5) NOT NULL,
    is_deleted boolean NOT NULL default False,
    update_timestamp DATETIME NOT NULL,
    update_user varchar(500) NOT NULL,
    create_timestamp DATETIME NOT NULL,
    create_user varchar(500) NOT NULL,
    CONSTRAINT stock_portfolio_evaluation_pkey1 PRIMARY KEY (stock_code, evaluation_date),
    FOREIGN KEY stock_portfolio_evaluation_fkey1 (stock_code) references stock_master(stock_code),
    FOREIGN KEY stock_execution_fkey2 (currency_code) references currency_master(currency_code)
)