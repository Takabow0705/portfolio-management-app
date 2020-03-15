create table discount_factor_master(
	term long NOT NULL,
    discount_factor decimal(20,16) NOT NULL,
    payment_type char(100) NOT NULL,
    version long ,
    update_time datetime DEFAULT NOW(),
    update_user varchar(100) ,
    PRIMARY KEY (term(255), payment_type, version(255))
)