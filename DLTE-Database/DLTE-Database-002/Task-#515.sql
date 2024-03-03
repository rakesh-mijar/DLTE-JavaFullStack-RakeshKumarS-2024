#connect sys as sysdba;
#password=sys
-------------select privilges-----------------

create user razi identified by razi001;
grant connect to razi;
grant select on transaction to razi;
#alter session set current_schema=system;

--------------delete privilege-------------

create user pranav identified by pranav002;
grant connect to pranav;
grant delete on transaction to pranav;

----------------select privilege------------

create user vikyath identified by vik003;
grant connect to vikyath;
grant select on transaction to vikyath;

---------------insert privilege------------

create user ankith identified by anki004;
grant connect to ankith;
grant insert on transaction to ankith;

------------update privilege--------------

create user manoj identified by man005;
grant connect to manoj;
grant update on transaction to manoj;