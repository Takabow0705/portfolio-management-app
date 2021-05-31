CREATE TABLE stock_portfolios (
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_id int(11) unsigned NOT NULL,
	portofolio_name varchar(500),
	details varchar(500),
    is_deleted boolean NOT NULL default False,
    update_timestamp DATETIME NOT NULL,
    update_user varchar(500) NOT NULL,
    create_timestamp DATETIME NOT NULL,
    create_user varchar(500) NOT NULL,
    CONSTRAINT stock_portfolios_pkey PRIMARY KEY (id),
    FOREIGN KEY stock_portfolios_fkey (user_id) references user_master(USER_ID)
)