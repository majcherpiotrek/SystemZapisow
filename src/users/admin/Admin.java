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

    /*Konstruktor klasy Admin - tworzy obiekt użytkownika
     *na podstawie danych z pliku. Znajduje odpowiednie dane
     *na podstawie loginu, pozyskanego przy logowaniu*/
    public Admin(String _login){

        this.login = _login;
        String adminData = "";
        String fileName = Main.adminDataFilename;
        File file = new File(fileName);
        Scanner input;
        boolean found = false;

        try {
            input = new Scanner(file);
        }catch(IOException ex){
            Main.closeOnError("FATAL ERROR - database disappeared");
            return;
        }

        while(input.hasNextLine()){
            String temp = input.findInLine(login);
            if(temp != null)
            if(temp.equals(login)){
                found = true;
                adminData = input.nextLine();
                break;
            }
            input.nextLine();
        }
        if(found)
            input = new Scanner(adminData);
        else{
            Main.closeOnError("FATAL ERROR - Admin not found after login");
            return;
        }

        input.useDelimiter(" ");

        password = input.next();
        email = input.next();
        name = input.next();
        surname = input.next();

    }

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

    public void deleteSignUpLaw(Student student){
        student.setSignUpRight(false);
    }


}
