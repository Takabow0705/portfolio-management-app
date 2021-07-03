CREATE TABLE stock_price_timeseries (
    stock_code CHAR(50) NOT NULL,
    evaluation_date Date Not null,
	open_price DECIMAL(30, 10) NOT NULL,
    high_price DECIMAL(30, 10) NOT NULL,
    low_price DECIMAL(30, 10) NOT NULL,
    close_price DECIMAL(30, 10) NOT NULL,
    volume DECIMAL(30, 5) NOT NULL default 0,
    stock_splits boolean NOT NULL default False,
    is_deleted boolean NOT NULL default False,
    update_timestamp DATETIME NOT NULL,
    update_user varchar(500) NOT NULL,
    create_timestamp DATETIME NOT NULL,
    create_user varchar(500) NOT NULL,
    CONSTRAINT stock_price_timeseries_pkey PRIMARY KEY (stock_code, evaluation_date),
    FOREIGN KEY stock_price_timeseries_fkey1 (stock_code) references stock_master(stock_code)
)