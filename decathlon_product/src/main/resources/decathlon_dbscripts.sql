create database decathlon;


CREATE TABLE `product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_description` varchar(255) DEFAULT NULL,
  `product_level` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `product_sport` varchar(255) DEFAULT NULL,
  `associated_stores` int(11) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `FKe8jslsmxevjd3lk2x4x7td3b8` (`associated_stores`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1


CREATE TABLE `store` (
  `store_id` int(11) NOT NULL AUTO_INCREMENT,
  `store_city` varchar(255) DEFAULT NULL,
  `store_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`store_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1

select * from store;

insert into store (store_name,store_city) values ('sports','Bengaluru');

insert into store (store_name,store_city) values ('kids','Hyderabad');


select * from product;


insert into product(product_description,product_level,product_name,product_sport,associated_stores) values ('Ball descp','small','Ball','sports',1);

insert into product(product_description,product_level,product_name,product_sport,associated_stores) values ('bag descp','Medium','Bag','Kids',2);



