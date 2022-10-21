package ru.itsjava.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Import(StudentDaoImpl.class)
public class StudentJdbcDaoImplTest {
    private static final String DEFAULT_NAME = "Ivanov 2";
    private static final int DEFAULT_AGE = 100;

    @Autowired
    private StudentDao studentDao;

    @Test
    public void shouldHaveCorrectCount() {
        int actualStudentsCount = studentDao.count();
        assertEquals(2, actualStudentsCount);
    }

    @Test
    public void shouldHaveCorrectInsert() {
        Student expectedStudent = new Student(3L, DEFAULT_NAME, DEFAULT_AGE);
        studentDao.insert(expectedStudent);
        Student actualStudent = studentDao.findById(3L);

        assertEquals(expectedStudent, actualStudent);
    }

    @Test
    public void shouldHaveCorrectUpdate() {
        Student expectedStudent = new Student(1L, DEFAULT_NAME, DEFAULT_AGE);
        studentDao.update(expectedStudent);
        Student actualStudent = studentDao.findById(1L);

        assertEquals(expectedStudent, actualStudent);
    }

    @Test
    public void shouldHaveCorrectDelete() {
        Student deleteStudent = studentDao.findById(1L);
        studentDao.delete(deleteStudent);

        assertEquals(studentDao.count(), 1);
    }



}
