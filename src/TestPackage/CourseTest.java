package TestPackage;
import exceptions.WrongGroupException;
import junit.framework.Assert;
import mockit.Expectations;
import mockit.Verifications;
import org.junit.Test;
import sample.*;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.runner.RunWith;

import java.util.ArrayList;

/**
 * Klasa do testowania klasy kursów za pomocą narzędzia JMockit
 * Created by piotrek on 17.01.17.
 */
@RunWith(JMockit.class)
public final class CourseTest {

    private DataBase db =DataBase.INSTANCE;
    @Mocked
    Group group;

    @Test
    public void addGroupToCourseTest(){

        Course course = db.getCourseList().get(0);


        new Expectations(){
            {
                group.getDepartment(); result = course.getDepartment();
                group.getFieldOfStudy(); result = course.getFieldOfStudy();
                group.getSpecialization(); result = course.getSpecialization();
                group.getTerm(); result = course.getTerm();
            }
        };

        Boolean added = false;
        try {
            course.addGroup(group);
            added=true;
        }catch(WrongGroupException ex){
            System.err.println(ex.getMessage());
        }

        Assert.assertTrue(added && course.getGroups().indexOf(group) > 0);
    }

    @Test(expected = WrongGroupException.class)
    public void shouldThrowExceptionAddingWrongGroupToCourse() throws WrongGroupException{
        Course course = db.getCourseList().get(0);

        new Expectations(){
            {
                group.getDepartment(); result = Department.W2;
            }
        };

        course.addGroup(group);
    }
}



