package TestPackage;

import mockit.Mocked;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import sample.Group;
import users.student.Student;

import java.util.Arrays;
import java.util.Collection;


import static org.junit.Assert.*;
/**
 * Created by piotrek on 17.01.17.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Parameterized.class)
public class GroupTest {

    private static TestsData data = new TestsData();

    @Parameterized.Parameter(value = 0)
    public Student student;

    @Parameterized.Parameter(value = 1)
    public Group group;

    @Parameterized.Parameters
    public static Collection<Object[]> parameters(){
        Object[][] params = new Object[][]{{data.studentForTests,data.groupForTests}};
        return Arrays.asList(params);
    }

    @org.junit.Test
    public void AAAshouldReturnFalseIfStudentNotInGroup(){
        assertFalse(group.isInTheGroup(student));
    }

    @org.junit.Test
    public void BBBshouldDecrementAvailablePlacesAfterAddingStudent(){
        int availablePlaces = group.getAvaiablePlaces();
        availablePlaces--;
        group.addStudent(student);
        assertEquals(availablePlaces, group.getAvaiablePlaces());
    }

    @org.junit.Test
    public void CCCshouldHaveStudentInGroupAfterAddingStudent(){
        assertTrue(group.isInTheGroup(student));
    }

    @org.junit.Test
    public void DDDshouldDecrementAvailablePlacesWhenGroupIsEmpty(){
        int available = group.getAvaiablePlaces();
        available--;
        group.decAvaiablePlaces();
        assertEquals(available, group.getAvaiablePlaces());
    }

    @org.junit.Test
    public void EEEincAvailablePlacesInGroupTest(){
        int availablePlaces = group.getAvaiablePlaces();
        availablePlaces++;
        group.incAvaiablePlaces();
        assertEquals(availablePlaces, group.getAvaiablePlaces());
    }

    @org.junit.Test
    public void FFFshouldNotDecrementWhenZeroAvailablePlaces(){
        group.setAvaiablePlaces(0);
        group.decAvaiablePlaces();
        assertEquals(0, group.getAvaiablePlaces());
    }

    @Mocked
    Student studentMocked;

    @Test
    public void isStudentInGroup(){
        group.addStudent(studentMocked);
        boolean passed=true;
        if(group.getSignedUpStudents().indexOf(studentMocked)<0)
            passed=false;
        Assert.assertTrue(passed);
    }
}
