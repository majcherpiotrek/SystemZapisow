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
    private FieldsOfStudies fieldOfStudy;
    private Specialization specialization;
    private int term;
    private boolean signUpRight;
    private int ECTS;
    private ObservableList<Group> groupList;

    public Student(String login, String password, String name, String surname, String email, String ID, Department department, FieldsOfStudies fieldOfStudy, Specialization specialization, int term, boolean signUpRight, int ECTS){
        this.login = login;
        this.password = password;
        this. name = name;
        this.surname = surname;
        this.email = email;
        this.ID = ID;
        this.department = department;
        this.fieldOfStudy = fieldOfStudy;
        this.specialization = specialization;
        this.term = term;
        this.signUpRight = signUpRight;
        this.ECTS = ECTS;
        this.groupList = FXCollections.observableArrayList();
    }

    public ObservableList<Group> getGroupList() {return groupList;}

    public void addGroup(Group group){

            if (group.getSignedUpStudents().size() < group.getNumberOfPlaces()) {

                //dodanie studenta dla obiektu grupy w bazie danych
                group.getSignedUpStudents().add(this);

                //dodanie studentowi grupy
                groupList.add(group);

            }
    }

    public void deleteGroup(Group group){
      for(Course c : DataBase.INSTANCE.getCourseList()){
          if(c.getCourseCode().equals(group.getCourseCode())){

              for(Student student : group.getSignedUpStudents()){
                  student.getGroupList().remove(group);
              }

              c.getGroups().remove((group));
          }
      }
    }

    String getID() {
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

    int getTerm() {
        return term;
    }

    void setTerm(int term) {
        this.term = term;
    }

    boolean isSignUpRight() {
        return signUpRight;
    }

    void setSignUpRight(boolean signUpRight) {
        this.signUpRight = signUpRight;
    }

    int getECTS() {
        return ECTS;
    }

    void setECTS(int ECTS) {
        this.ECTS = ECTS;
    }

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
