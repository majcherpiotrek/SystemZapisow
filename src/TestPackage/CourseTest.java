package TestPackage;
import mockit.Verifications;
import org.junit.Test;
import sample.Course;
import sample.DataBase;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.runner.RunWith;
import sample.Group;
import sample.GroupTypes;

/**
 * Klasa do testowania klasy kursów za pomocą narzędzia JMockit
 * Created by piotrek on 17.01.17.
 */
@RunWith(JMockit.class)
public final class CourseTest {

    @Mocked
    Course course;

    @Test
    public void addGoodGroupToCourseTest(){
        Group group = new Group("nazwa grupy", course.getDepartment(),course.getFieldOfStudy(),
                course.getTerm(), course.getSpecialization(),
                course.getCourseCode()+"grupa1",course.getCourseCode(),
                course.getGroupTypesList().get(0),
                "profesor",
                "Poniedzialek 17",
                10, 20, 120);

    }
}



