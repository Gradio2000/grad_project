
-- create table USERS (USER_ID integer, EMAIL varchar, NAME varchar, PASSWORD varchar);
insert into USERS (USER_ID, EMAIL, NAME, PASSWORD)
 values (2, 'bb@bbb.bb', 'admin', 'password');

insert into USERS (USER_ID, EMAIL, NAME, PASSWORD)
values (1, 'aa@aa.aa', 'user', 'password');

-- create table USER_ROLE(ROLE_ID integer, ROLE varchar, USER_ID integer);

insert into USER_ROLE (ROLE, USER_ID)
values ('ADMIN_ROLE', 1);