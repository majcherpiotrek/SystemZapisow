package users.student;

import exceptions.NotIntheGroupException;
import exceptions.WrongGroupException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import sample.*;

import static org.junit.Assert.*;

/**
 * Created by piotrek on 11.01.17.
 */
public class StudentTest {

    private Group group;
    private Student student;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init(){
       group = new Group("grupa",
                Department.W1,
                FieldsOfStudies.W1K1,
                1,
                Specialization.NOSPECIALIZATION,
                "123",
                "123",
                GroupTypes.EX,
                "profesor",
                "date",
                10,
                10,
                120);

        student = new Student();
        student.setSignUpRight(true);
        student.setSpecialization(Specialization.NOSPECIALIZATION);
        student.setDepartment(Department.W1);
        student.setFieldOfStudy(FieldsOfStudies.W1K1);
        student.setTerm(1);
    }

    @org.junit.Test
    public void signUpToGoodGroupTest() throws Exception {
        int availablePlaces = group.getAvaiablePlaces();
        student.signUpToGroup(group);
        assertTrue(student.getGroupList().get(0).equals(group) && availablePlaces-1 == group.getAvaiablePlaces());

    }

    @org.junit.Test
    public void signUpToWrongDepartmentTest() throws WrongGroupException {
        group.setDepartment(Department.W2);
        group.setFieldOfStudy(FieldsOfStudies.W2K1);
        group.setSpecialization(Specialization.NOSPECIALIZATION);

        exception.expect(WrongGroupException.class);
        exception.expectMessage("Zła grupa! Nie możesz się do niej zapisać!");

        student.signUpToGroup(group);
    }

    @org.junit.Test
    public void signUpToWrongFieldTest() throws WrongGroupException {
        group.setFieldOfStudy(FieldsOfStudies.W1K2);
        group.setSpecialization(Specialization.NOSPECIALIZATION);

        exception.expect(WrongGroupException.class);
        exception.expectMessage("Zła grupa! Nie możesz się do niej zapisać!");

        student.signUpToGroup(group);
    }

    @org.junit.Test
    public void signUpToWrongSpecializationTest() throws WrongGroupException {
        group.setSpecialization(Specialization.W1K1S2);

        exception.expect(WrongGroupException.class);
        exception.expectMessage("Zła grupa! Nie możesz się do niej zapisać!");

        student.signUpToGroup(group);
    }

    @org.junit.Test
    public void signUpToWrongTermTest() throws WrongGroupException {
        group.setTerm(3);

        exception.expect(WrongGroupException.class);
        exception.expectMessage("Zła grupa! Nie możesz się do niej zapisać!");
        student.signUpToGroup(group);
    }

    @org.junit.Test
    public void signUpToFullGroupTest() throws Exception {

        group.setAvaiablePlaces(0);

        exception.expect(WrongGroupException.class);
        exception.expectMessage("W grupie nie ma juz miejsc!");
        student.signUpToGroup(group);
    }

    @org.junit.Test
    public void signOutFromGoodGroupTest() throws Exception {

        student.signUpToGroup(group);
        int availablePlaces = group.getAvaiablePlaces();
        int studentGroupNum = student.getGroupList().size();
        student.signOutFromGroup(group);
        assertTrue((availablePlaces+1 == group.getAvaiablePlaces()) && (studentGroupNum-1 ==student.getGroupList().size()));

    }

    @org.junit.Test
    public void signOutFromWrongGroupTest() throws Exception {

        exception.expect(NotIntheGroupException.class);
        exception.expectMessage("Studenta nie ma w tej grupie");
        student.signOutFromGroup(group);

    }
}