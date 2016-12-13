package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by piotrek on 07.12.16.
 */
public class Course {

    private String name;
    private String courseCode;
    private int term;
    private Department department;
    private String fieldOfStudy;
    private Boolean lecture;
    private Boolean excercises;
    private Boolean seminar;
    private Boolean laboratory;
    private Boolean project;
    private String specialization;
    private int ECTS;
    private Boolean obligatory;
    private ObservableList<Group> groups;

    public Course(String name, String courseCode, int term, Department department, String fieldOfStudy, Boolean lecture,Boolean excercises, Boolean seminar, Boolean laboratory, Boolean project, String specialization, int ECTS, Boolean obligatory){
        this.name = name;
        this.courseCode = courseCode;
        this.term = term;
        this. department = department;
        this.fieldOfStudy = fieldOfStudy;
        this.lecture = lecture;
        this.excercises = excercises;
        this.seminar = seminar;
        this.laboratory = laboratory;
        this.project = project;
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

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public Boolean getLecture() {
        return lecture;
    }

    public void setLecture(Boolean lecture) {
        this.lecture = lecture;
    }

    public Boolean getExcercises() {
        return excercises;
    }

    public void setExcercises(Boolean excercises) {
        this.excercises = excercises;
    }

    public Boolean getSeminar() {
        return seminar;
    }

    public void setSeminar(Boolean seminar) {
        this.seminar = seminar;
    }

    public Boolean getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(Boolean laboratory) {
        this.laboratory = laboratory;
    }

    public Boolean getProject() {
        return project;
    }

    public void setProject(Boolean project) {
        this.project = project;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
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
