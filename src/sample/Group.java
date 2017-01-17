package sample;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import users.student.Student;

/**
 * Created by piotrek on 07.12.16.
 */
public class Group {

    private String name;
    private String groupCode;
    private String courseCode;
    private GroupTypes type;
    private String proffesor;
    private String date;
    private int numberOfHours;
    private int numberOfPlaces;
    private int avaiablePlaces;
    private int room;
    private ObservableList<Student> signedUpStudents;
    private Department department;
    private FieldsOfStudies fieldOfStudy;
    private int term;

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    private Specialization specialization;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public FieldsOfStudies getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(FieldsOfStudies fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public Group(String name, Department department, FieldsOfStudies fieldsOfStudies, int term, Specialization spec, String groupCode, String courseCode, GroupTypes type, String proffesor, String date, int numberOfHours, int numberOfPlaces, int room){
        this.name = name;
        this.department = department;
        this.fieldOfStudy = fieldsOfStudies;
        this.term = term;
        this.specialization = spec;
        this.groupCode = groupCode;
        this.courseCode = courseCode;
        this.type = type;
        this.proffesor = proffesor;
        this.date = date;
        this.numberOfHours = numberOfHours;
        this.numberOfPlaces = numberOfPlaces;
        this.avaiablePlaces = numberOfPlaces;
        this.room = room;
        this.signedUpStudents = FXCollections.observableArrayList();
    }

    public Group(){
        this.name = null;
        this.department = null;
        this.fieldOfStudy = null;
        this.specialization = null;
        this.groupCode = null;
        this.courseCode = null;
        this.type = null;
        this.proffesor = null;
        this.date = null;
        this.numberOfHours = 0;
        this.numberOfPlaces = 0;
        this.avaiablePlaces = 0;
        this.room = 0;
        this.signedUpStudents = FXCollections.observableArrayList();
    }

    public void addStudent(Student student){
        signedUpStudents.add(student);
        this.decAvaiablePlaces();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getProffesor() {
        return proffesor;
    }

    public void setProffesor(String proffesor) {
        this.proffesor = proffesor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(int numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public ObservableList<Student> getSignedUpStudents() {
        return signedUpStudents;
    }

    public int getAvaiablePlaces() {return avaiablePlaces;}

    public void setAvaiablePlaces(int avaiablePlaces) {this.avaiablePlaces = avaiablePlaces;}

    public void decAvaiablePlaces(){if(avaiablePlaces>0)avaiablePlaces--;}

    public void incAvaiablePlaces(){avaiablePlaces++;}

    public GroupTypes getType() {return type;}

    public void setType(GroupTypes type) {this.type = type;}

    public Boolean isInTheGroup(Student student){
        for(Student s : this.signedUpStudents)
            if(s.equals(student))
                return true;

        return false;
    }
}
