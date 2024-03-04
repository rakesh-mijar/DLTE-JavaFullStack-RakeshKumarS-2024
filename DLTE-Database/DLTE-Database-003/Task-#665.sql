---------------Triggers---------------------
create or replace trigger transac_trigger2
before insert on transaction
for each row
begin
if :new.transaction_amount is null then :new.transaction_amount := 1000;
end if;
end;
/

--Trigger created.

insert into transaction values(10001,'20-Mar-2024','Manikanta',null,'Education');