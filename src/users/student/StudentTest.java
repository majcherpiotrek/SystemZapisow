package users.student;

import sample.Course;
import sample.DataBase;
import sample.Specialization;

import static org.junit.Assert.*;

/**
 * Created by piotrek on 11.01.17.
 */
public class StudentTest {
    @org.junit.Test
    public void signUpToGoodGroupTest() throws Exception {
        Student student  = DataBase.INSTANCE.getStudentsList().get(0);
        Course course = DataBase.INSTANCE.getCourseList().get(0);
        for(Course c : DataBase.INSTANCE.getCourseList())
            if(c.getDepartment().equals(student.getDepartment()))
                if(c.getFieldOfStudy().equals(student.getFieldOfStudy()))
                    if(c.getSpecialization().equals(student.getSpecialization()) || c.getSpecialization().equals(Specialization.NOSPECIALIZATION))
                        if(c.getTerm() == student.getTerm() && student.getSignUpRight()) {
                            course = c;
                            student.signUpToGroup(c.getGroups().get(0));
                            break;
                        }
        assertTrue(student.getGroupList().get(0).equals(course.getGroups().get(0)));

    }

    @org.junit.Test
    public void signOutFromGroupTest() throws Exception {
        assertTrue(true);
    }

}