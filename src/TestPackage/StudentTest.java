package TestPackage;

import exceptions.NotIntheGroupException;
import exceptions.WrongGroupException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import sample.*;
import users.student.Student;

import static org.junit.Assert.*;

/**
 * Created by piotrek on 11.01.17.
 */
public class StudentTest {

    private TestsData data;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init(){
        data = new TestsData();
    }

    @org.junit.Test
    public void signUpToGoodGroupTest() throws Exception {
        Group group = data.groupForTests;
        Student student = data.studentForTests;
        int availablePlaces = group.getAvaiablePlaces();
        student.signUpToGroup(group);
        int groupsNum = student.getGroupList().size();
        assertTrue(student.getGroupList().get(groupsNum-1).equals(group) && availablePlaces-1 == group.getAvaiablePlaces());

    }

    @org.junit.Test
    public void signUpToWrongDepartmentTest() throws WrongGroupException {
        Group group = data.groupForSignUpToWrongDepartmentTest;
        Student student = data.studentForTests;
        exception.expect(WrongGroupException.class);
        exception.expectMessage("Zła grupa! Nie możesz się do niej zapisać!");
        student.signUpToGroup(group);
    }

    @org.junit.Test
    public void signUpToWrongFieldTest() throws WrongGroupException {
        Group group = data.groupForSignUpToWrongFieldTest;
        Student student = data.studentForTests;
        exception.expect(WrongGroupException.class);
        exception.expectMessage("Zła grupa! Nie możesz się do niej zapisać!");
        student.signUpToGroup(group);
    }

    @org.junit.Test
    public void signUpToWrongSpecializationTest() throws WrongGroupException {
        Group group = data.groupForSignUpToWrongSpecializationTest;
        Student student = data.studentForTests;
        exception.expect(WrongGroupException.class);
        exception.expectMessage("Zła grupa! Nie możesz się do niej zapisać!");
        student.signUpToGroup(group);
    }

    @org.junit.Test
    public void signUpToWrongTermTest() throws WrongGroupException {
        Group group = data.groupForSignUpToWrongTermTest;
        Student student = data.studentForTests;
        exception.expect(WrongGroupException.class);
        exception.expectMessage("Zła grupa! Nie możesz się do niej zapisać!");
        student.signUpToGroup(group);
    }

    @org.junit.Test
    public void signUpToFullGroupTest() throws Exception {
        Group group = data.groupForSignUpToFullGroupTest;
        Student student = data.studentForTests;
        exception.expect(WrongGroupException.class);
        exception.expectMessage("W grupie nie ma juz miejsc!");
        student.signUpToGroup(group);
    }

    @org.junit.Test
    public void signOutFromGoodGroupTest() throws Exception {
        Group group = data.groupForSignOutFromGoodGroupTest;
        Student student = data.studentForTests;
        student.signUpToGroup(group);
        int availablePlaces = group.getAvaiablePlaces();
        int studentGroupNum = student.getGroupList().size();
        student.signOutFromGroup(group);
        assertTrue((availablePlaces+1 == group.getAvaiablePlaces()) && (studentGroupNum-1 ==student.getGroupList().size()));

    }

    @org.junit.Test
    public void signOutFromWrongGroupTest() throws Exception {
        Group group = data.groupForSignOutFromWrongGroupTest;
        Student student = data.studentForTests;
        exception.expect(NotIntheGroupException.class);
        exception.expectMessage("Studenta nie ma w tej grupie");
        student.signOutFromGroup(group);

    }
}