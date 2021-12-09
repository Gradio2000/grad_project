
-- create table USERS (USER_ID integer, EMAIL varchar, NAME varchar, PASSWORD varchar);
insert into USERS (USER_ID, EMAIL, NAME, PASSWORD)
 values (12, 'bbw@bbb.bb', 'admin', '$2a$12$4d3duXAHmFcFs64ZABGyk.JChVZSZm4IHfaTU4/H1WXw.TIGaS9vO');

insert into USERS (USER_ID, EMAIL, NAME, PASSWORD)
values (11, 'aaw@aa.aa', 'user1', '$2a$12$Gj25UuBhqDPz8kpbqv5tS.SHKb9JpcDKY4Pb6KcJnyTW6YX5xDlUK');

insert into USERS (USER_ID, EMAIL, NAME, PASSWORD)
values (13, 'ssw@ss.ss', 'new', '$2a$12$re/O6NYrYuTH38VJ8gIYteSaGomSlmcFwqvyu28V5q0EHhZl9RMUe');

insert into USERS (USER_ID, EMAIL, NAME, PASSWORD)
values (14, 'sssw@ss.ss', 'new', '$2a$12$re/O6NYrYuTH38VJ8gIYteSaGomSlmcFwqvyu28V5q0EHhZl9RMUe');

insert into USERS (USER_ID, EMAIL, NAME, PASSWORD)
values (15, 'sw@ss.ss', 'new', '$2a$12$re/O6NYrYuTH38VJ8gIYteSaGomSlmcFwqvyu28V5q0EHhZl9RMUe');

insert into USERS (USER_ID, EMAIL, NAME, PASSWORD)
values (16, 'ssaw@ss.ss', 'new', '$2a$12$re/O6NYrYuTH38VJ8gIYteSaGomSlmcFwqvyu28V5q0EHhZl9RMUe');

insert into USERS (USER_ID, EMAIL, NAME, PASSWORD)
values (17, 'ssffw@ss.ss', 'new', '$2a$12$re/O6NYrYuTH38VJ8gIYteSaGomSlmcFwqvyu28V5q0EHhZl9RMUe');

insert into USERS (USER_ID, EMAIL, NAME, PASSWORD)
values (18, 'ssggw@ss.ss', 'new', '$2a$12$re/O6NYrYuTH38VJ8gIYteSaGomSlmcFwqvyu28V5q0EHhZl9RMUe');

-- create table USER_ROLE(ROLE_ID integer, ROLE varchar, USER_ID integer);

insert into USER_ROLE (ROLE, USER_ID)
values ('USER', 11);

insert into USER_ROLE (ROLE, USER_ID)
values ('USER', 12);

insert into USER_ROLE (ROLE, USER_ID)
values ('ADMIN', 12);

insert into USER_ROLE (ROLE, USER_ID)
values ('USER', 13);


insert into RESTAURANT (REST_ID, NAME)
values (11, 'aaaaaa' );
insert into RESTAURANT (REST_ID, NAME)
values (12, 'bbbbbbbb' );
insert into RESTAURANT (REST_ID, NAME)
values (13, 'cccccccc' );


insert into DISH (DISH_ID, DATE, DISH, PRICE, REST_ID)
values (11, now(), 'Каша', '12.53', 11 );

-- insert into VOIT(DATE, TIME, REST_ID, USER_ID)
-- values ( now(), now(), 1, 1 );
--
-- insert into VOIT(DATE, TIME, REST_ID, USER_ID)
-- values ( now(), now(), 1, 2 );
--
-- insert into VOIT(DATE, TIME, REST_ID, USER_ID)
-- values ( now(), now(), 1, 3 );