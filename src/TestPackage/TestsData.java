package TestPackage;

import sample.*;
import users.student.Student;

/**
 * Klasa z danymi przeznaczonymi do testów jednostkowych.
 * Created by piotrek on 17.01.17.
 */
public class TestsData {

    public Group groupForTests;

    public Group groupForSignUpToFullGroupTest;

    public Group groupForSignUpToWrongDepartmentTest;

    public Group groupForSignUpToWrongFieldTest;

    public Group groupForSignUpToWrongSpecializationTest;

    public Group groupForSignUpToWrongTermTest;

    public Group groupForSignOutFromGoodGroupTest;

    public Group groupForSignOutFromWrongGroupTest;

    public Student studentForTests;

    public TestsData() {
        groupForTests = new Group("grupa",
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

        groupForSignUpToFullGroupTest = new Group("grupa",
                Department.W1,
                FieldsOfStudies.W1K1,
                1,
                Specialization.NOSPECIALIZATION,
                "123",
                "124",
                GroupTypes.EX,
                "profesor",
                "date",
                10,
                10,
                120);

        groupForSignUpToWrongDepartmentTest = new Group("grupa",
                Department.W1,
                FieldsOfStudies.W1K1,
                1,
                Specialization.NOSPECIALIZATION,
                "125",
                "123",
                GroupTypes.EX,
                "profesor",
                "date",
                10,
                10,
                120);

        groupForSignUpToWrongFieldTest = new Group("grupa",
                Department.W1,
                FieldsOfStudies.W1K1,
                1,
                Specialization.NOSPECIALIZATION,
                "126",
                "123",
                GroupTypes.EX,
                "profesor",
                "date",
                10,
                10,
                120);

        groupForSignUpToWrongSpecializationTest = new Group("grupa",
                Department.W1,
                FieldsOfStudies.W1K1,
                1,
                Specialization.NOSPECIALIZATION,
                "127",
                "123",
                GroupTypes.EX,
                "profesor",
                "date",
                10,
                10,
                120);

        groupForSignUpToWrongTermTest = new Group("grupa",
                Department.W1,
                FieldsOfStudies.W1K1,
                1,
                Specialization.NOSPECIALIZATION,
                "128",
                "123",
                GroupTypes.EX,
                "profesor",
                "date",
                10,
                10,
                120);

        groupForSignOutFromGoodGroupTest = new Group("grupa",
                Department.W1,
                FieldsOfStudies.W1K1,
                1,
                Specialization.NOSPECIALIZATION,
                "129",
                "123",
                GroupTypes.EX,
                "profesor",
                "date",
                10,
                10,
                120);

        groupForSignOutFromWrongGroupTest = new Group("grupa",
                Department.W1,
                FieldsOfStudies.W1K1,
                1,
                Specialization.NOSPECIALIZATION,
                "130",
                "123",
                GroupTypes.EX,
                "profesor",
                "date",
                10,
                10,
                120);

        studentForTests = new Student();
        studentForTests.setSignUpRight(true);
        studentForTests.setSpecialization(Specialization.NOSPECIALIZATION);
        studentForTests.setDepartment(Department.W1);
        studentForTests.setFieldOfStudy(FieldsOfStudies.W1K1);
        studentForTests.setTerm(1);

        groupForSignUpToWrongDepartmentTest.setDepartment(Department.W2);
        groupForSignUpToWrongDepartmentTest.setDepartment(Department.W2);
        groupForSignUpToWrongDepartmentTest.setFieldOfStudy(FieldsOfStudies.W2K1);
        groupForSignUpToWrongDepartmentTest.setSpecialization(Specialization.NOSPECIALIZATION);

        groupForSignUpToWrongFieldTest.setFieldOfStudy(FieldsOfStudies.W1K2);
        groupForSignUpToWrongFieldTest.setSpecialization(Specialization.NOSPECIALIZATION);

        groupForSignUpToWrongSpecializationTest.setSpecialization(Specialization.W1K1S2);

        groupForSignUpToWrongTermTest.setTerm(3);

        groupForSignUpToFullGroupTest.setAvaiablePlaces(0);
    }
}
