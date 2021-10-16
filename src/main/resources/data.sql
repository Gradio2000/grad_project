
-- create table USERS (USER_ID integer, EMAIL varchar, NAME varchar, PASSWORD varchar);
insert into USERS (USER_ID, EMAIL, NAME, PASSWORD)
 values (2, 'bb@bbb.bb', 'admin', '{noop}admin');

insert into USERS (USER_ID, EMAIL, NAME, PASSWORD)
values (1, 'aa@aa.aa', 'user', '{noop}user');

-- create table USER_ROLE(ROLE_ID integer, ROLE varchar, USER_ID integer);

insert into USER_ROLE (ROLE, USER_ID)
values ('USER', 1);

insert into USER_ROLE (ROLE, USER_ID)
values ('USER', 2);

insert into USER_ROLE (ROLE, USER_ID)
values ('ADMIN', 2);


insert into RESTAURANT (REST_ID, NAME)
values (1, 'aaaaaa' );
insert into RESTAURANT (REST_ID, NAME)
values (2, 'bbbbbbbb' );
insert into RESTAURANT (REST_ID, NAME)
values (3, 'cccccccc' );


insert into DISH (DISH_ID, DATE, DISH, PRICE, REST_ID)
values (1, now(), 'Каша', '12.53', 1 );

insert into VOIT(DATE, REST_ID, USER_ID)
values ( '2021-10-13 20:02:25.685000', 1, 1 );
insert into VOIT(DATE, REST_ID, USER_ID)
values ( '2021-10-14 10:02:25.685000', 1, 1 );
insert into VOIT(DATE, REST_ID, USER_ID)
values ( '2021-10-15 20:02:25.685000', 1, 1 );