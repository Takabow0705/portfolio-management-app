CREATE TABLE IF NOT EXISTS `cash_account`(
    id BIGINT NOT NULL AUTO_INCREMENT,
    stock_portfolio_id BIGINT NOT NULL,
    detail CHAR(5) NOT NULL,
    created_at DATE NOT NULL,
    PRIMARY KEY (id,stock_portfolio_id),
    FOREIGN KEY cash_account_fkey1 (stock_portfolio_id) references stock_portfolio(id)
);

CREATE TABLE IF NOT EXISTS `cash_balance`(
    cash_account_id BIGINT NOT NULL,
    base_date   DATE NOT NULL,
    currency_code CHAR(5) NOT NULL,
    amount DECIMAL(20,10) NOT NULL,
    update_timestamp DATETIME NOT NULL,
    update_user varchar(500) NOT NULL,
    create_timestamp DATETIME NOT NULL,
    create_user varchar(500) NOT NULL,
    PRIMARY KEY (cash_account_id,base_date,currency_code),
    FOREIGN KEY cash_balance_fkey1 (currency_code) references currency_master(currency_code),
    FOREIGN KEY cash_balance_fkey2 (cash_account_id) references cash_account(id)
);

CREATE TABLE IF NOT EXISTS  `unrealized_cashflow`(
    id BIGINT NOT NULL AUTO_INCREMENT,
    cash_account_id BIGINT NOT NULL,
    base_date   DATE NOT NULL,
    currency_code CHAR(5) NOT NULL,
    amount DECIMAL(20,10) NOT NULL,
    settlement_date DATE NOT NULL,
    execution_date DATE NOT NULL,
    pay_or_receive CHAR(15) NOT NULL,
    deleted boolean NOT NULL default False,
    update_timestamp DATETIME NOT NULL,
    update_user varchar(500) NOT NULL,
    create_timestamp DATETIME NOT NULL,
    create_user varchar(500) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY unrealized_cashflow_fkey1 (currency_code) references currency_master(currency_code),
    FOREIGN KEY unrealized_cashflow_fkey2 (cash_account_id) references cash_account(id)
);

CREATE TABLE IF NOT EXISTS  `unrealized_cashflow_attr`(
    id BIGINT NOT NULL AUTO_INCREMENT,
    unrealized_cashflow_id BIGINT NOT NULL,
    purpose varchar(500),
    detail  varchar(500),
    deleted boolean NOT NULL default False,
    update_timestamp DATETIME NOT NULL,
    update_user varchar(500) NOT NULL,
    create_timestamp DATETIME NOT NULL,
    create_user varchar(500) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY unrealized_cashflow_attr_fkey1 (id) references unrealized_cashflow(id)
);

CREATE TABLE IF NOT EXISTS  `cashflow_history`(
    id BIGINT NOT NULL AUTO_INCREMENT,
    cash_account_id BIGINT NOT NULL,
    base_date   DATE NOT NULL,
    currency_code CHAR(5) NOT NULL,
    amount DECIMAL(20,10) NOT NULL,
    pay_or_receive CHAR(15) NOT NULL,
    settlement_date DATE NOT NULL,
    execution_date DATE NOT NULL,
    deleted boolean NOT NULL default False,
    update_timestamp DATETIME NOT NULL,
    update_user varchar(500) NOT NULL,
    create_timestamp DATETIME NOT NULL,
    create_user varchar(500) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY cashflow_history_fkey1 (currency_code) references currency_master(currency_code),
    FOREIGN KEY cashflow_history_fkey2 (cash_account_id) references cash_account(id)
);

CREATE TABLE IF NOT EXISTS  `cashflow_attr_history`(
    id BIGINT NOT NULL AUTO_INCREMENT,
    cashflow_history_id BIGINT NOT NULL,
    purpose varchar(500),
    detail  varchar(500),
    deleted boolean NOT NULL default False,
    update_timestamp DATETIME NOT NULL,
    update_user varchar(500) NOT NULL,
    create_timestamp DATETIME NOT NULL,
    create_user varchar(500) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY cashflow_history_attr_fkey1 (id) references cashflow_history(id)
);