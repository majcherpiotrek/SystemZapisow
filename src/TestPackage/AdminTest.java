package TestPackage;

import exceptions.WrongGroupException;
import sample.Course;
import sample.DataBase;
import users.admin.Admin;

import static org.junit.Assert.assertTrue;

/**
 * Created by Maciej on 17.01.2017.
 */
public class AdminTest {

    private DataBase db= DataBase.INSTANCE;
    private Admin admin;

    @org.junit.Test
    public void isNewGroupCreated(){
        admin = new Admin();
        Course course = new Course();

        boolean passed=true;
        try {
            admin.createGroupInDatabase(course);
        }catch (WrongGroupException ex){
            passed=false;
        }
        assertTrue(passed);
    }
}
