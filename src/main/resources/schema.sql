DROP TABLE IF EXISTS students;

CREATE TABLE students
   (
       id  BIGINT PRIMARY KEY AUTO_INCREMENT,
       fio VARCHAR(256),
       age INT
   );