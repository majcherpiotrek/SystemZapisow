package users.student;
import javafx.collections.FXCollections;
import sample.*;
import users.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import javafx.collections.ObservableList;

/**
 * Created by piotrek on 06.12.16.
 */
public class Student extends User {
    //atrybuty studenta
    private String ID;
    private Department department;
    private String departmentName;
    private FieldsOfStudies fieldOfStudy;
    private String fieldOfStudyName;
    private Specialization specialization;
    private String specializationName;
    private int term;
    private boolean signUpRight;
    private int ECTS;
    private ObservableList<Group> groupList = FXCollections.observableArrayList();

    public Student(String login, String password, String name, String surname, String email, String ID, Department department, FieldsOfStudies fieldOfStudy, Specialization specialization, int term, boolean signUpRight, int ECTS){
        this.login = login;
        this.password = password;
        this. name = name;
        this.surname = surname;
        this.email = email;
        this.ID = ID;
        this.department = department;
        this.departmentName = this.department.getName();
        this.fieldOfStudy = fieldOfStudy;
        this.fieldOfStudyName = this.fieldOfStudy.getName();
        this.specialization = specialization;
        this.specializationName = this.specialization.getName();
        this.term = term;
        this.signUpRight = signUpRight;
        this.ECTS = ECTS;
        this.groupList = FXCollections.observableArrayList();
    }

    public Student(){

        Integer i = null;

        this.login = null;
        this.password = null;
        this. name = null;
        this.surname = null;
        this.email = null;
        this.ID = null;
        this.department = null;
        this.departmentName = null;
        this.fieldOfStudy = null;
        this.fieldOfStudyName = null;
        this.specialization = null;
        this.specializationName = null;
        this.term = i;
        this.signUpRight = null;
        this.ECTS = i;
        this.groupList = FXCollections.observableArrayList();
    }

    public ObservableList<Group> getGroupList() {return groupList;}




    public void signUpToGroup(Group group){

            if (group.getSignedUpStudents().size() < group.getNumberOfPlaces()) {
                for(Group g : this.groupList){
                    if(g.equals(group)){
                        AlertBox.Display("Błąd","Zostałeś już zapisany do tej grupy");
                        return;
                    }
                }
                //dodanie studenta dla obiektu grupy w bazie danych
                group.getSignedUpStudents().add(this);

                //dodanie studentowi grupy
                this.groupList.add(group);

            }
    }

    public void signOutFromGroup(Group group){
      for(Course c : DataBase.INSTANCE.getCourseList())
          if(c.getCourseCode().equals(group.getCourseCode()))
              for(Group g : c.getGroups())
                  if(g.equals(group))
                      g.getSignedUpStudents().remove(this);

      this.groupList.remove(group);

    }

    public String getID() {
        return ID;
    }

    void setID(String ID) {
        this.ID = ID;
    }

    Department getDepartment() {
        return department;
    }

    void setDepartment(Department department) {
        this.department = department;
    }

    FieldsOfStudies getFieldOfStudy() {
        return fieldOfStudy;
    }

    void setFieldOfStudy(FieldsOfStudies fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    Specialization getSpecialization() {
        return specialization;
    }

    void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public int getTerm() {
        return term;
    }

    void setTerm(int term) {
        this.term = term;
    }

    public boolean isSignUpRight() {
        return signUpRight;
    }

    public void setSignUpRight(boolean signUpRight) {
        this.signUpRight = signUpRight;
    }

    public int getECTS() {
        return ECTS;
    }

    void setECTS(int ECTS) {
        this.ECTS = ECTS;
    }

    public String getDepartmentName() { return departmentName; }

    public String getFieldOfStudyName() { return fieldOfStudyName; }

    public String getSpecializationName() { return specializationName; }

    @Override
    public ArrayList<String> getProfile(){
        ArrayList<String> profileData = new ArrayList<>();

        profileData.add(login);
        profileData.add(password);
        profileData.add(name);
        profileData.add(surname);
        profileData.add(email);
        profileData.add(ID);
        profileData.add(department.getName());
        profileData.add(fieldOfStudy.getName());
        profileData.add(specialization.getName());
        profileData.add(String.valueOf(term));
        profileData.add(String.valueOf(signUpRight));
        profileData.add(String.valueOf(ECTS));
        return profileData;
    }

    @Override
    public String toString(){
        String result = "";
        result += login + " ";
        result += password + " ";
        result += name + " ";
        result += surname + " ";
        result += email + " ";
        result += ID + " ";
        result += department + " ";
        result += fieldOfStudy + " ";
        result += specialization + " ";
        result += term + " ";
        result += signUpRight + " ";
        result += ECTS + " ";

        return result;
    }


}
