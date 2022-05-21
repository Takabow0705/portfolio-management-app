CREATE OR REPLACE VIEW stock_execution_view as
select sp.id as stock_portfolio_id
     , se.id as stock_execution_id
     , portfolio_name
     , stock_name
     , currency_code
     , book_value
     , execution_date
     , value_date
     , buy_sell_type
     , amount
     , se.is_deleted as is_execution_deleted
     , sp.is_deleted as is_portfolio_deleted
from stock_execution se inner join stock_master sm
                        on se.stock_code = sm.stock_code
                        inner join stock_portfolio sp
                        on sp.id = se.stock_portfolio_id;

