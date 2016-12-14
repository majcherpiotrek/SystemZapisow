package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import users.student.Student;
import users.admin.Admin;

/**
 * Created by Maciej on 13.12.2016.
 */
public final class DataBase {
    public static final DataBase INSTANCE = new DataBase();

    private final ObservableList<Student> studentsList = FXCollections.observableArrayList();
    private final ObservableList<Course>  courseList   = FXCollections.observableArrayList();
    private final ObservableList<Admin>  adminList   = FXCollections.observableArrayList();

    private DataBase(){

        //login,password,name,surname,email,ID,department,fieldOfStudy,specialization,term,signUpRight ECTS)
        studentsList.add(new Student("S1","pass","Jan","Kowalski","Jan@email.com","218180",Department.W1, "Informatyka", "ISK", 6, true, 150) );
        studentsList.add(new Student("S2","pass","Jacek","Nowak","Jacek@email.com","218230",Department.W1, "Informatyka", "INT", 6, true, 148));

        //login,password,name,surname,email
        adminList.add(new Admin("A1","pass","Jurek","Owsiak","Jurek@email.com"));


        //courseCode,term,department,fieldOfStudy,lecture,excercises,seminar,laboratory,project,specialization,ECTS,obligatory
        courseList.add(new Course("AK1","C1",6,Department.W1,"Informatyka",true,true,false,false,true,"ISK",5,true));

    }

    public ObservableList<Student> getStudentsList() {
        return studentsList;
    }

    public ObservableList<Course> getCourseList() {return courseList; }

    public void addStudent(Student student){ studentsList.add(student); }

    public void addCourse(Course course){ courseList.add(course); }
}
