create table user(
account VARCHAR(20) PRIMARY KEY,
PASSWORD VARCHAR(20) NOT NULL
)

create table goods(
goodid VARCHAR (20) PRIMARY KEY,
goodname VARCHAR(20) NOT null,
goodprice int NOT NULL
)

create table goodorder(
useraccount VARCHAR (20),#订单账户
orderid VARCHAR(20) not NULL,#订单编号
username VARCHAR(20) not NULL,#下单人
tell int not null,#联系电话
address VARCHAR(20) not NULL,#收货地址
addressMode VARCHAR(20) not NULL,#派送方式
goodname VARCHAR(20) not NULL,#购买的商品名
goodprice int not NULL,#商品的单价
buynumber int NOT NULL,#购买的数量
allmoney int NOT null, #购买的总价
orderTime Date
)

drop table goodorder
SHOW tables
SHOW table goodorder
desc goodorder

SELECT* FROM goods

SELECT* FROM user

SELECT* FROM goodorder

INSERT into goods VALUES ('g001','iphone11',5999);
SELECT* FROM goods
INSERT into goods VALUES ('g002','小米10',3999);
INSERT into goods VALUES ('g003','华为p40',4999);
INSERT into goods VALUES ('g004','sonyZ11',6999);