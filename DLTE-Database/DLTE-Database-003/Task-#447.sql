--1)insert new transaction
-----------------------
create or replace procedure insert_new_transaction(
trans_id number,
trans_date date,
trans_to varchar2,
trans_amt number,
trans_remarks varchar2,
trans_info out varchar2
)
as
begin
insert into transaction(transaction_id,transaction_date,transaction_to,transaction_amount,transaction_remarks) values(trans_id,trans_date,trans_to,trans_amt,trans_remarks);
trans_info:='new  transaction inserted';
exception
when others then
trans_info:='Transaction failed due to '||SQLERRM;
end;
/

--Procedure created.

variable error_or_info varchar2(255);
execute insert_new_transaction(1,'20-oct-2024','Mahesh',5000,'Health',:error_or_info);

--PL/SQL procedure successfully completed.

print error_or_info;

--ERROR_OR_INFO
--------------------------------------------------------------------------------
--new  transaction inserted

--===============================================================================

--2) Delete a transaction
---------------------------
create or replace procedure delete_transaction(
trans_to varchar2,
trans_info out varchar2
)
as
begin
delete from transaction where transaction_to=trans_to;
trans_info:='Transaction Deleted';
exception
when others then
trans_info:='Transaction Deletion Failed due to '||SQLERRM;
end;
/

--Procedure created.

variable error_or_info varchar2(225);
execute delete_transaction('Mahesh',:error_or_info);

--PL/SQL procedure successfully completed.

 print error_or_info;

 --ERROR_OR_INFO
--------------------------------------------------------------------------------
--Transaction Deleted

--==============================================================================

--3)filter transaction those done for Education

create or replace procedure filter_transaction(
trans_remarks varchar2,
trans_id out number,
trans_to out varchar2,
trans_info out varchar2
)
as
begin
select transaction_id,transaction_to into trans_id,trans_to from transaction where transaction_remarks=trans_remarks;
trans_info:='Filtered Successfully';
exception
when no_data_found then
trans_info:='No match';
when others then
trans_info:='Error due to '||SQLERRM;
end;
/

--Procedure created.

variable found_one number;
variable found_two varchar2(255);
variable error_or_info varchar2(255);
execute filter_transaction('Education',:found_one,:found_two,:error_or_info);

--PL/SQL procedure successfully completed.

print error_or_info;

--ERROR_OR_INFO
--------------------------------------------------------------------------------
--No match