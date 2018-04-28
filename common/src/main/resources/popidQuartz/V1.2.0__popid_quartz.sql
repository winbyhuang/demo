drop database popid_quartz;
create database popid_quartz default character set=utf8;
use popid_quartz;
grant create, alter, select, insert, update, delete on popid_quartz.* to pop@'localhost';
grant create, alter, select, insert, update, delete on popid_quartz.* to pop@'%';