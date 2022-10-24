package ru.itsjava.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Faculty;
import ru.itsjava.domain.Student;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Import(StudentDaoImpl.class)
public class StudentJdbcDaoImplTest {
    private static final String DEFAULT_NAME = "Ivanov 2";
    private static final int DEFAULT_AGE = 100;
    private static final Faculty DEFAULT_FACULTY = new Faculty(1L,"Android Development");

    @Autowired
    private StudentDao studentDao;

    @Test
    public void shouldHaveCorrectCount() {
        int actualStudentsCount = studentDao.count();
        assertEquals(2, actualStudentsCount);
    }

    @Test
    public void shouldHaveCorrectInsert() {
        Student expectedStudent = new Student(DEFAULT_NAME, DEFAULT_AGE, DEFAULT_FACULTY);
        long idFromDB = studentDao.insert(expectedStudent);
        System.out.println(idFromDB);
        Student actualStudent = studentDao.findById(idFromDB);

        assertAll(() -> assertEquals(expectedStudent.getFio(), actualStudent.getFio()),
                () -> assertEquals(expectedStudent.getAge(), actualStudent.getAge()));

    }

    @Test
    public void shouldHaveCorrectUpdate() {
        Student expectedStudent = new Student(1L, DEFAULT_NAME, DEFAULT_AGE, DEFAULT_FACULTY);
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
