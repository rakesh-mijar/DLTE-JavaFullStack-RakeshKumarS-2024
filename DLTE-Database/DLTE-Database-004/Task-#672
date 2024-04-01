---------Before Normalization----------------------

create table user_mobile_recharge(username varchar(255),upi varchar(10),mobile_number number(10),email varchar(255)
,wallet_type varchar(20),recharged_date date,recharge_provider varchar(255),recharged_to varchar(255),recharged_amount number);

--Table created

---------1NF--------------------------------------

create table user_mobile_recharge(username varchar(255) primary key,upi varchar(10),mobile_number number(10),email varchar(255),wallet_type varchar(20),recharged_date date,recharge_provider varchar(255),recharged_to varchar(255),recharged_amount number);

--Table created.

----------2NF-----------------------------------------

create table user_2nf(username varchar(255) primary key,upi varchar(20),mobile_number number(10),email varchar(255));

--Table created.

create table recharges_2nf(username varchar(255),wallet_type varchar(10),recharged_date date,recharged_provider varchar(255),recharged_to varchar(255),recharged_amount number,foreign key (username) references user_2nf(username));

--Table created.

--------------3NF----------------------------------------

 create table wallets_3nf(wallet_type varchar(15) primary key,recharged_provider varchar(255));

--Table created.

alter table recharges_2nf add constraint fk_wallet_type foreign key (wallet_type) references wallets_3nf(wallet_type);

--Table altered.