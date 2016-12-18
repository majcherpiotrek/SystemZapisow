package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import users.student.Student;
import users.admin.Admin;
import static java.lang.StrictMath.abs;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Maciej on 13.12.2016.
 */
public final class DataBase {
    public static final DataBase INSTANCE = new DataBase();

    private final ObservableList<Student> studentsList = FXCollections.observableArrayList();
    private final ObservableList<Course>  courseList   = FXCollections.observableArrayList();
    private final ObservableList<Admin>  adminList   = FXCollections.observableArrayList();

    private DataBase(){
        Random generator = new Random();

        //Generowanie listy studentów
        for (int i = 0; i < 100; i++) {
            String login = "S" + i;
            String password = "pass" + i;
            String name = "Name" + i;
            String surname = "Surname" + i;
            String email = login + "@email.com";
            Integer id = 1000 - i;
            String ID = id.toString();
            Department department = (i % 2 == 0) ? Department.W1 : Department.W2;
            FieldsOfStudies fieldsOfStudies = (department == Department.W1) ?
                    ((i % 2 == 0) ? FieldsOfStudies.W1K1 : FieldsOfStudies.W1K2) : ((i % 2 == 0) ? FieldsOfStudies.W2K1 : FieldsOfStudies.W2K2);
            /*losujemy na którym semestrze jest student*/
            int term = abs(generator.nextInt() % 7) + 1;
            Specialization specialization = Specialization.NOSPECIALIZATION;
            /*Specjalizację prypisujemy tylko studentom na semestrze 6 i 7*/
            if (term >= 6) {
                switch (fieldsOfStudies) {
                    case W1K1: {
                        specialization = (i % 2 == 0) ? Specialization.W1K1S1 : Specialization.W1K1S2;
                        break;
                    }
                    case W1K2: {
                        specialization = (i % 2 == 0) ? Specialization.W1K2S1 : Specialization.W1K2S2;
                        break;
                    }
                    case W2K1: {
                        specialization = (i % 2 == 0) ? Specialization.W2K1S1 : Specialization.W2K1S2;
                        break;
                    }
                    case W2K2: {
                        specialization = (i % 2 == 0) ? Specialization.W2K2S1 : Specialization.W2K2S2;
                    }
                }
            }

            //login,password,name,surname,email,ID,department,fieldOfStudy,specialization,term,signUpRight ECTS)
            Student student = new Student(login, password, name, surname, email, ID, department, fieldsOfStudies, specialization, term, true, 30);
            studentsList.add(student);
        }

        //login,password,name,surname,email
        adminList.add(new Admin("piotrek","piotrek","Piotr","Majcher","piotrek@email.com"));
        adminList.add(new Admin("maciek", "maciek", "Maciej", "Łuczak", "maciek@gmail.com"));

        //Generowanie kursów
        //dla każdegu wydziału
        for(Department dep : Department.values() ) {
            //Tworzymy listę kierunków dla każdego wydziału
            ArrayList<FieldsOfStudies> fields = new ArrayList<>();
            if(dep.equals(Department.W1)){
                fields.add(FieldsOfStudies.W1K1);
                fields.add(FieldsOfStudies.W1K2);
            }
            else
            {
                fields.add(FieldsOfStudies.W2K1);
                fields.add(FieldsOfStudies.W2K2);
            }
            //Dla każdego kierunku na danym wydziale
            for(FieldsOfStudies field : fields){
                //Dla każdego semestru bez specjalizacji
                for(int i=1; i<=5; i++){
                    //Generujemy 5 kursów
                    for (int k = 1; k <= 5 ; k++) {

                            int term = i;
                            //Kod kursu, np  W1K1SEM3NR4
                            String code = field.toString() + "SEM" + i + "NR"+k;
                            String courseName = "Kurs "+code;

                            //losujemy dwa różne typy grup dla każdego kursu
                            int gr_type_1, gr_type_2;
                            do{
                                gr_type_1 = abs(generator.nextInt()%5);
                                gr_type_2 = abs(generator.nextInt()%5);

                            }while(gr_type_1 == gr_type_2);

                            ArrayList<GroupTypes> groups = new ArrayList<>();
                            groups.add(GroupTypes.values()[gr_type_1]);
                            groups.add(GroupTypes.values()[gr_type_2]);

                            Specialization spec = Specialization.NOSPECIALIZATION;

                            Course course = new Course(courseName, code, term, dep, field, groups, spec, 6, true);
                            courseList.add(course);

                    }
                }

                ArrayList<Specialization> specializations = new ArrayList<>();
                switch (field){
                    case W1K1:{
                        specializations.add(Specialization.W1K1S1);
                        specializations.add(Specialization.W1K1S2);
                        break;
                    }
                    case W1K2:{
                        specializations.add(Specialization.W1K2S1);
                        specializations.add(Specialization.W1K2S2);
                        break;
                    }
                    case W2K1:{
                        specializations.add(Specialization.W2K1S1);
                        specializations.add(Specialization.W2K1S2);
                        break;
                    }
                    case W2K2:{
                        specializations.add(Specialization.W2K2S1);
                        specializations.add(Specialization.W2K2S2);
                        break;
                    }
                }

                for (int i = 6; i <=7 ; i++) {
                    /*tworzymy kursy dla każdej specjalizacji*/
                    for( Specialization spec : specializations){
                        for (int k = 1; k <= 5 ; k++) {

                            int term = i;
                            //Kod kursu, np  W1K1SEM3(SPEC1)NR4
                            String code;
                            if(k <= 3)
                                code = field.toString() + "SEM" + i + "NR"+k;
                            else{
                                int nr = k-3;
                                code = field.toString() + "SEM" + i + spec.toString() + "NR" +nr;
                            }

                            String courseName = "Kurs "+code;

                            //losujemy dwa różne typy grup dla każdego kursu
                            int gr_type_1, gr_type_2;
                            do{
                                gr_type_1 = abs(generator.nextInt()%5);
                                gr_type_2 = abs(generator.nextInt()%5);

                            }while(gr_type_1 == gr_type_2);

                            ArrayList<GroupTypes> groups = new ArrayList<>();
                            groups.add(GroupTypes.values()[gr_type_1]);
                            groups.add(GroupTypes.values()[gr_type_2]);

                            Course course = new Course(courseName, code, term, dep, field, groups, spec, 6, true);
                            courseList.add(course);

                        }
                    }
                }

            }
        }



        courseList.get(0).addGroup(new Group("AK1","1",courseList.get(0).getCourseCode(),"JB","sroda 7:30",30,70,15));
    }

    public ObservableList<Student> getStudentsList() {
        return studentsList;
    }

    public ObservableList<Admin> getAdminList() {
        return adminList;
    }

    public ObservableList<Course> getCourseList() {return courseList; }

    public void addStudent(Student student){ studentsList.add(student); }

    public void addCourse(Course course){ courseList.add(course); }

}
