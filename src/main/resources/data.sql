
-- create table USERS (USER_ID integer, EMAIL varchar, NAME varchar, PASSWORD varchar);
insert into USERS (USER_ID, EMAIL, NAME, PASSWORD)
 values (2, 'bb@bbb.bb', 'admin', '$2a$12$4d3duXAHmFcFs64ZABGyk.JChVZSZm4IHfaTU4/H1WXw.TIGaS9vO');

insert into USERS (USER_ID, EMAIL, NAME, PASSWORD)
values (1, 'aa@aa.aa', 'user', '$2a$12$Gj25UuBhqDPz8kpbqv5tS.SHKb9JpcDKY4Pb6KcJnyTW6YX5xDlUK');

insert into USERS (USER_ID, EMAIL, NAME, PASSWORD)
values (3, 'ss@ss.ss', 'new', '$2a$12$re/O6NYrYuTH38VJ8gIYteSaGomSlmcFwqvyu28V5q0EHhZl9RMUe');

-- create table USER_ROLE(ROLE_ID integer, ROLE varchar, USER_ID integer);

insert into USER_ROLE (ROLE, USER_ID)
values ('USER', 1);

insert into USER_ROLE (ROLE, USER_ID)
values ('USER', 2);

insert into USER_ROLE (ROLE, USER_ID)
values ('ADMIN', 2);

insert into USER_ROLE (ROLE, USER_ID)
values ('USER', 3);


insert into RESTAURANT (REST_ID, NAME)
values (1, 'aaaaaa' );
insert into RESTAURANT (REST_ID, NAME)
values (2, 'bbbbbbbb' );
insert into RESTAURANT (REST_ID, NAME)
values (3, 'cccccccc' );


insert into DISH (DISH_ID, DATE, DISH, PRICE, REST_ID)
values (1, now(), 'Каша', '12.53', 1 );

insert into VOIT(DATE, TIME, REST_ID, USER_ID)
values ( now(), now(), 1, 1 );

insert into VOIT(DATE, TIME, REST_ID, USER_ID)
values ( now(), now(), 1, 2 );

insert into VOIT(DATE, TIME, REST_ID, USER_ID)
values ( now(), now(), 1, 3 );