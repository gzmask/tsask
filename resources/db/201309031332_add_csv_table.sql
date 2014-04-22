CREATE TABLE `CSV_report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY(id),
  `app_name` varchar(255),
  `address` varchar(255),
  `phone` varchar(255),
  `email` varchar(255),
  `app_type` varchar(255),
  `app_detail` varchar(255),
  `invoice_id` int,
  `paid_by` varchar(255),
  `card_type` varchar(255),
  `payment_amt` float
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE Version (                                                              
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY(id),
  `timestamp` varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into Version (timestamp) values ("201309031332");
