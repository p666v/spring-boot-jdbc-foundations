DROP TABLE IF EXISTS students, faculties;

CREATE TABLE students
   (
       id  BIGINT PRIMARY KEY AUTO_INCREMENT,
       fio VARCHAR(256),
       age INT,
       faculty_id BIGINT
   );


CREATE TABLE faculties
   (
       id  BIGINT PRIMARY KEY AUTO_INCREMENT,
       name VARCHAR(256)
   );

ALTER TABLE students ADD FOREIGN KEY (faculty_id) REFERENCES faculties(id);
