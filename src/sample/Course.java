package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by piotrek on 07.12.16.
 */
public class Course {

    private String name;
    private String courseCode;
    private int term;
    private Department department;
    private FieldsOfStudies fieldOfStudy;
    private ArrayList<GroupTypes> groupTypes;
    private Specialization specialization;
    private int ECTS;
    private Boolean obligatory;
    private ObservableList<Group> groups;

    public Course(String name, String courseCode, int term, Department department, FieldsOfStudies fieldOfStudy, ArrayList<GroupTypes> groupTypes, Specialization specialization, int ECTS, Boolean obligatory){
        this.name = name;
        this.courseCode = courseCode;
        this.term = term;
        this. department = department;
        this.fieldOfStudy = fieldOfStudy;
        this.groupTypes = groupTypes;
        this.specialization = specialization;
        this.ECTS = ECTS;
        this.obligatory = obligatory;
        groups = FXCollections.observableArrayList();
    }

    public void addGroup(Group group){
        groups.add(group);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public FieldsOfStudies getFieldOfStudy() {
        return fieldOfStudy;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public int getECTS() {
        return ECTS;
    }

    public void setECTS(int ECTS) {
        this.ECTS = ECTS;
    }

    public Boolean getObligatory() {
        return obligatory;
    }

    public void setObligatory(Boolean obligatory) {
        this.obligatory = obligatory;
    }

    public ObservableList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ObservableList<Group> groups) {
        this.groups = groups;
    }
}
