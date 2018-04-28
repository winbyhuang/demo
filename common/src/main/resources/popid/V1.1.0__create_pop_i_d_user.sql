drop user 'pop'@'localhost';
create user 'pop'@'localhost' IDENTIFIED by 'pop123';
drop user 'pop'@'%';
create user 'pop'@'%' IDENTIFIED by 'pop123';
grant all on  popid.* to pop@'localhost';
grant all on  popid.* to pop@'%';