package users.admin;
import sample.Course;
import sample.DataBase;
import sample.Group;
import users.User;
import sample.Main;
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

}
