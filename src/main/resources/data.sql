insert into course(name, created_date, updated_date) values ('English', sysdate(), sysdate());
insert into course(name, created_date, updated_date) values ('Java', sysdate(), sysdate());
insert into course(name, created_date, updated_date) values ('Dotnet', sysdate(), sysdate());
insert into course(name, created_date, updated_date) values ('Mongdb', sysdate(), sysdate());

insert into passport(number) values ('AB4523');
insert into passport(number) values ('TRR4545');
insert into passport(number) values ('ESQ755');

insert into student(name, passport_id) values ('Fred', 1);
insert into student(name, passport_id) values ('Allan', 2);
insert into student(name, passport_id) values ('Jane', 3);


insert into review(rating, description, course_id) values ('5', 'Great Course', 1);
insert into review(rating, description, course_id) values ('3', 'Good Course', 1);
insert into review(rating, description, course_id) values ('5', 'Awesome Course', 2);