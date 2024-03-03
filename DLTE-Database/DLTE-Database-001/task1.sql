-------------table creation---------------
1.create table transaction(transaction_id number not null,transaction_date date not null, transaction_to varchar(255) not null,transaction_amount number(10) not null, transaction_remarks varchar(255) not null);

---------create sequence--------------

2.create sequence trans_seq start with 1 increment by 1;

--------------insertion to table--------------------

3. insert into transaction(transaction_id,transaction_amount,transaction_date,transaction_to,transaction_remarks) values(transaction_seq.nextval,100,'20Feb2024','Suresh Kumar','Friend');

4. insert into transaction(transaction_id,transaction_amount,transaction_date,transaction_to,transaction_remarks) values(transaction_seq.nextval,200,'20Mar2024','Mahesh Kumar','Brother');

5. insert into transaction(transaction_id,transaction_amount,transaction_date,transaction_to,transaction_remarks) values(transaction_seq.nextval,300,'15Jan2024','Shwetha Kumari','Health');

6. insert into transaction(transaction_id,transaction_amount,transaction_date,transaction_to,transaction_remarks) values(transaction_seq.nextval,500,'15Mar2024','Chandra Kumari','Sister');

---------------Filter based on given ranges of date----------------

7.create view transc1 as select transaction_date from transaction where transaction_date between '01Jan2024' and '01Jan2025';

select * from transc1;
--------------least amount transferred--------------------------

8.create view transac2 as select min(transaction_amount) as min_transaction_amount from transaction;

select * from transac2;
-------------maximum amount transferred-------------------------

9.create view transac3 as select max(transaction_amount) as max_transaction_amount from transaction;

select * from transac3;
------------number of transaction made to particular beneficiary--------

10.create view transac4 as select transaction_to,count(*) as transaction_count from transaction group by transaction_to;

select * from transac4;
------------filter based on particular remarks--------------------------

11.create view transac5 as select transaction_to from transaction where transaction_remarks='health';

select * from transac5;