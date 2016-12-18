package users.admin;
import sample.*;
import users.User;
import users.student.Student;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by piotrek on 06.12.16.
 */
public class Admin extends User{

    public Admin(String login, String password, String name, String surname, String email){
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    @Override
    public ArrayList<String> getProfile(){
        ArrayList<String> profileData = new ArrayList<>();

        profileData.add(login);
        profileData.add(password);
        profileData.add(name);
        profileData.add(surname);
        profileData.add(email);

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

       return result;
    }

    public void deleteCourse(Course course) {

        for (Group group : course.getGroups()) {
            for (Student student : group.getSignedUpStudents())
                student.getGroupList().remove(group);
        }

        DataBase.INSTANCE.getCourseList().remove(course);
    }

    public void deleteGroup(Group group){

        for (Student student : group.getSignedUpStudents()) {
            student.getGroupList().remove(group);
        }

        for(Course c : DataBase.INSTANCE.getCourseList()){
            if(c.getCourseCode().equals(group.getCourseCode())){
                c.getGroups().remove(group);
                break;
            }
        }

    }

    public void giveSignUpLaw(Student student){
        student.setSignUpRight(true);
    }

    public void deleteSignUpLaw(Student student){ student.setSignUpRight(false); }

    public Course createNewCourse(){
        Course course = new Course();
        DataBase.INSTANCE.addCourse(course);
        return course;
    }

    public Group createNewGroup(Course course){
     Group group = new Group();
     course.addGroup(group);
     return group;
    }



    //Funkcje umozliwiające ustawianie parametrów kursu

    public void setCourseName(Course course , String name){
        course.setName(name);
    }
    public void setCourseCode(Course course , String code){
        course.setCourseCode(code);
    }
    public void setCourseTerm(Course course , int term){
        course.setTerm(term);
    }
    public void setCourseDepartment(Course course , Department department){
        course.setDepartment(department);
        course.setDepartmentName(department.getName());
    }
    public void setCourseFieldOfStudy(Course course , FieldsOfStudies field){
        course.setFieldOfStudy(field);
        course.setFieldOfStudyName(field.getName());
    }
    public void setCourseGroupTypes(Course course , ArrayList<GroupTypes> groupTypes){
        course.setGroupTypes(groupTypes);
    }
    public void setCourseSpecialization(Course course , Specialization specialization){
        course.setSpecialization(specialization);
        course.setSpecializationName(specialization.getName());
    }
    public void setCourseECTS(Course course , int ECTS){
        course.setECTS(ECTS);
    }
    public void setCourseObligatory(Course course , Boolean obligatory){
        course.setObligatory(obligatory);
    }



    //Funkcje umożliwiające ustawianie parametrów grupy

    public void setGroupName(Group group, String name){
        group.setName(name);
    }
    public void setGroupGroupCode(Group group, String code){
        group.setGroupCode(code);
    }
    public void setGroupCourseCode(Group group, String code){
        group.setCourseCode(code);
    }
    public void setGroupProffesor(Group group, String proffesor){
        group.setProffesor(proffesor);
    }
    public void setGroupDate(Group group, String date){
        group.setDate(date);
    }
    public void setGroupNumberOfHours(Group group, int numberOfHours){
        group.setNumberOfHours(numberOfHours);
    }
    public void setGroupNumberOfPlaces(Group group, int numberOfPlaces){
        group.setNumberOfPlaces(numberOfPlaces);
    }
    public void setGroupRoom(Group group, int room){
        group.setRoom(room);
    }
}
