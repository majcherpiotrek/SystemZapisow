package users.admin;
import javafx.scene.control.Label;
import sample.*;
import users.User;
import users.student.Student;

import java.util.ArrayList;

/**
 * Created by piotrek on 06.12.16.
 */
public class Admin extends User{

    /*public Admin(String login, String password, String name, String surname, String email){
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }*/

    public Admin(){
        this.login = "";
        this.password = "";
        this.name = "";
        this.surname = "";
        this.email = "";
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

     void deleteCourse(Course course) {

        for (Group group : course.getGroups()) {
            for (Student student : group.getSignedUpStudents())
                student.getGroupList().remove(group);
        }

        DataBase.INSTANCE.getCourseList().remove(course);
    }
     void deleteGroup(Group group){

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


    void changeSignUpRight(Student student){

        boolean signUpRight = student.getSignUpRight();
        student.setSignUpRight(!signUpRight);

    }

    Course createNewCourse(){
        Course course = new Course();
        DataBase.INSTANCE.addCourse(course);
        return course;
    }
     Group createGroupInDatabase(Course course){
        Group group = new Group();
        course.addGroup(group);
        return group;
    }
     void getStudentProfile(ArrayList<javafx.scene.control.Label> label, Student student){
        label.add(0,new Label("Imię : " + student.getName()));
        label.add(1,new Label("Nazwisko : " + student.getSurname()));
        label.add(2,new Label("Email : " + student.getEmail()));
        label.add(3,new Label("Indeks : " + student.getID()));
        label.add(4,new Label("Wydział : " + (student.getDepartmentName())));
        label.add(5,new Label("Kierunek : " + (student.getFieldOfStudyName())));
        label.add(6,new Label("Specjalizacja : " + (student.getSpecializationName())));
        label.add(7,new Label("Semestr : " + (Integer.toString(student.getTerm()))));
        label.add(8,new Label("Prawo do zapisów : " + (Boolean.toString(student.getSignUpRight()))));
        label.add(9,new Label("ECTS : " + (Integer.toString(student.getECTS()))));
    }

    //Funkcje umozliwiające ustawianie parametrów kursu
    void editCourse(Course course, String name, int term, Department department, ArrayList<GroupTypes> groupTypes, FieldsOfStudies field, Specialization spec, int ECTS, Boolean obligatory){
        course.setName(name);
        course.setTerm(term);
        course.setDepartment(department);
        course.setDepartmentName(department.getName());
        course.setGroupTypes(groupTypes);
        course.setFieldOfStudy(field);
        course.setFieldOfStudyName(field.getName());
        course.setSpecialization(spec);
        course.setSpecializationName(spec.getName());
        course.setECTS(ECTS);
        course.setObligatory(obligatory);
    }
    void setCourseCode(Course course , String code){
        course.setCourseCode(code);
    }

    //Funkcje umożliwiające ustawianie parametrów grupy
    void editGroup(Group group, String profesor, String date, int nH, int nP, int room){
        group.setProffesor(profesor);
        group.setDate(date);
        group.setNumberOfHours(nH);

        int signedUpStudents = group.getSignedUpStudents().size();
        if(nP>0)
            if (nP >= signedUpStudents)
                group.setNumberOfPlaces(nP);
            else
                AlertBox.Display("Uwaga", "Nie można zmniejszyć liczby miejsc, ponieważ do grupy zapisani są studenci.");
        else
            AlertBox.Display("Uwaga", "Liczba miejsc nie moze być mniejsza od zera.");

        group.setRoom(room);

    }
    void setGroupName(Group group, String name){
        group.setName(name);
    }
    void setGroupCode(Group group, String code){
        group.setGroupCode(code);
    }
    void setGroupCourseCode(Group group, String code){
        group.setCourseCode(code);
    }

}
