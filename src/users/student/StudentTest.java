package users.student;

import sample.Course;
import sample.DataBase;

import static org.junit.Assert.*;

/**
 * Created by piotrek on 11.01.17.
 */
public class StudentTest {
    @org.junit.Test
    public void signUpToGoodGroupTest() throws Exception {
        Student student  = DataBase.INSTANCE.getStudentsList().get(0);
        Course course;
        for(Course c : DataBase.INSTANCE.getCourseList()){
            if(c.getDepartment().equals(student.getDepartment()))
                if(c.getFieldOfStudy().equals(student.getFieldOfStudy()))
                    if(c.getSpecialization().equals(student.getSpecialization()))
                        assertTrue(true);

        }
    }

    @org.junit.Test
    public void signOutFromGroupTest() throws Exception {
        assertTrue(true);
    }

}