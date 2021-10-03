
-- create table USERS (USER_ID integer, EMAIL varchar, NAME varchar, PASSWORD varchar);
insert into USERS (USER_ID, EMAIL, NAME, PASSWORD)
 values (2, 'bb@bbb.bb', 'admin', 'password');

-- create table ROLE(ROLE_ID integer, ROLE varchar, USER_ID integer);
insert into ROLE (ROLE_ID, ROLE, USER_ID)
values (1, 'Admin_Role', 1);