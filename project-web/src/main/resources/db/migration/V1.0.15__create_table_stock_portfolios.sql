CREATE TABLE stock_portfolios (
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_mail_address NOT NULL varchar(100),
	portofolio_name varchar,
	details varchar,
    is_deleted boolean NOT NULL,
    update_timestamp timestamp without time zone NOT NULL,
    update_user character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT stock_portfolios_pkey PRIMARY KEY (id)
    FOREIGN KEY (user_mail_address) references user_master(MAIL_ADDRESS);
)