package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.util.ArrayList;

/**
 * Created by piotrek on 07.12.16.
 */
public class Course {

    private String name;
    private String courseCode;
    private int term;
    private Department department;
    private String departmentName;
    private FieldsOfStudies fieldOfStudy;
    private String fieldOfStudyName;

    private ArrayList<GroupTypes> groupTypes;
    private Specialization specialization;
    private String specializationName;
    private int ECTS;
    private Boolean obligatory;
    private ObservableList<Group> groups;

    public Course(String name, String courseCode, int term, Department department, FieldsOfStudies fieldOfStudy, ArrayList<GroupTypes> groupTypes, Specialization specialization, int ECTS, Boolean obligatory){
        this.name = name;
        this.courseCode = courseCode;
        this.term = term;
        this. department = department;
        this.departmentName = this.department.getName();
        this.fieldOfStudy = fieldOfStudy;
        this.fieldOfStudyName = this.fieldOfStudy.getName();
        this.groupTypes = groupTypes;
        this.specialization = specialization;
        this.specializationName = this.specialization.getName();
        this.ECTS = ECTS;
        this.obligatory = obligatory;
        groups = FXCollections.observableArrayList();
    }

    public Course(){

        Boolean b = null;

        this.name = null;
        this.courseCode = null;
        this.term = 0;
        this. department = null;
        this.departmentName = null;
        this.fieldOfStudy = null;
        this.fieldOfStudyName = null;
        this.groupTypes = null;
        this.specialization = null;
        this.specializationName = null;
        this.ECTS = 0;
        this.obligatory = null;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public String getFieldOfStudyName() {
        return fieldOfStudyName;
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public String getGroupTypes() {
        String ret = "";
        for(GroupTypes g : groupTypes)
            ret += g.getName() + ",";
        return ret;
    }

    public ArrayList<GroupTypes> getGroupTypesList(){
        return groupTypes;
    }

    public void setGroupTypes(ArrayList<GroupTypes> groupTypes) {
        this.groupTypes = groupTypes;
    }

    public void setFieldOfStudy(FieldsOfStudies fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setFieldOfStudyName(String fieldOfStudyName) {
        this.fieldOfStudyName = fieldOfStudyName;
    }

    public void setSpecializationName(String specializationName) {
        this.specializationName = specializationName;
    }


}
