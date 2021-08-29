CREATE TABLE IF NOT EXISTS  `stock_execution_seq` (
  `next_val` BIGINT DEFAULT NULL
);
INSERT INTO `stock_execution_seq` (`next_val`) VALUES(1);