alter table VOTE
    add constraint VOTE_USERS_USER_ID_FK
        foreign key (USER_ID) references USERS;


-- create table USERS (USER_ID integer, EMAIL varchar, NAME varchar, PASSWORD varchar);
insert into USERS (USER_ID, EMAIL, NAME, PASSWORD)
 values (2, 'bb@bbb.bb', 'admin', '$2a$12$4d3duXAHmFcFs64ZABGyk.JChVZSZm4IHfaTU4/H1WXw.TIGaS9vO');

insert into USERS (USER_ID, EMAIL, NAME, PASSWORD)
values (1, 'aa@aa.aa', 'user', '$2a$12$Gj25UuBhqDPz8kpbqv5tS.SHKb9JpcDKY4Pb6KcJnyTW6YX5xDlUK');

insert into USERS (USER_ID, EMAIL, NAME, PASSWORD)
values (3, 'ss@ss.ss', 'new', '$2a$12$re/O6NYrYuTH38VJ8gIYteSaGomSlmcFwqvyu28V5q0EHhZl9RMUe');

insert into USERS (USER_ID, EMAIL, NAME, PASSWORD)
values (4, 'sss@ss.ss', 'new', '$2a$12$re/O6NYrYuTH38VJ8gIYteSaGomSlmcFwqvyu28V5q0EHhZl9RMUe');

insert into USERS (USER_ID, EMAIL, NAME, PASSWORD)
values (5, 's@ss.ss', 'new', '$2a$12$re/O6NYrYuTH38VJ8gIYteSaGomSlmcFwqvyu28V5q0EHhZl9RMUe');

insert into USERS (USER_ID, EMAIL, NAME, PASSWORD)
values (6, 'ssa@ss.ss', 'new', '$2a$12$re/O6NYrYuTH38VJ8gIYteSaGomSlmcFwqvyu28V5q0EHhZl9RMUe');

insert into USERS (USER_ID, EMAIL, NAME, PASSWORD)
values (7, 'ssff@ss.ss', 'new', '$2a$12$re/O6NYrYuTH38VJ8gIYteSaGomSlmcFwqvyu28V5q0EHhZl9RMUe');

insert into USERS (USER_ID, EMAIL, NAME, PASSWORD)
values (8, 'ssgg@ss.ss', 'new', '$2a$12$re/O6NYrYuTH38VJ8gIYteSaGomSlmcFwqvyu28V5q0EHhZl9RMUe');

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
values (1, 'Рестроран № 1' );
insert into RESTAURANT (REST_ID, NAME)
values (2, 'Рестроран № 2' );
insert into RESTAURANT (REST_ID, NAME)
values (3, 'Рестроран № 3' );


insert into DISH (DISH_ID, DATE, DISH, PRICE, REST_ID)
values (1, now(), 'Каша', '12.53', 1 );

insert into DISH (DISH_ID, DATE, DISH, PRICE, REST_ID)
values (2, now(), 'Суп', '5.60', 1 );

insert into DISH (DISH_ID, DATE, DISH, PRICE, REST_ID)
values (3, now(), 'Шашлык', '15.90', 1 );

-- insert into VOIT(date_time, rest_id, user_id) VALUES ( '2021-12-23 15:34:35.662860', 1, 2 );
-- insert into VOIT(date_time, rest_id, user_id) VALUES ( '2021-12-22 15:34:35.662860', 2, 2 );
-- insert into VOIT(date_time, rest_id, user_id) VALUES ( '2021-12-21 15:34:35.662860', 1, 2 );
-- insert into VOIT(date_time, rest_id, user_id) VALUES ( '2021-12-20 15:34:35.662860', 1, 1 );

insert into VOTE(date, time, rest_id, user_id) VALUES ( '2021-12-23', '15:34:35.662860', 1, 2 );
insert into VOTE(date, time, rest_id, user_id) VALUES ( '2021-12-30', '10:34:35.662860', 1, 2 );
