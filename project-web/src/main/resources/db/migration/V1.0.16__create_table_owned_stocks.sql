CREATE TABLE owned_stock (
    id BIGINT NOT NULL AUTO_INCREMENT,
    stock_code CHAR(50) NOT NULL,
    stock_portfolios_id BIGINT NOT NULL,
	stock_name VARCHAR(500) NOT NULL,
	book_value DECIMAL(10, 10) NOT NULL,
	amount DECIMAL(10,10) NOT NULL,
	start_hold_date Date NOT NULL,
	end_hold_date Date,
    is_deleted boolean NOT NULL default False,
    update_timestamp DATETIME NOT NULL,
    update_user varchar(500) NOT NULL,
    create_timestamp DATETIME NOT NULL,
    create_user varchar(500) NOT NULL,
    CONSTRAINT owned_stocks PRIMARY KEY (id),
    FOREIGN KEY owned_stocks_fkey1 (stock_code) references stock_master(stock_code),
    FOREIGN KEY owned_stocks_fkey2 (stock_portfolios_id) references stock_portfolios(id)
)