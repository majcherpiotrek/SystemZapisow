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

    private String groupCode;
    private String courseCode;
    private String proffesor;
    private String date;
    private int numberOfHours;
    private int numberOfPlaces;
    private int room;
    private ObservableList<Student> signedUpStudents;

    public Group(String groupCode, String courseCode, String proffesor, String date, int numberOfHours, int numberOfPlaces, int room){
        this.groupCode = groupCode;
        this.courseCode = courseCode;
        this.proffesor = proffesor;
        this.date = date;
        this.numberOfHours = numberOfHours;
        this.numberOfPlaces = numberOfPlaces;
        this.room = room;
        this.signedUpStudents = FXCollections.observableArrayList();
    }

    public void addStudent(Student student){
        signedUpStudents.add(student);
    }
}
