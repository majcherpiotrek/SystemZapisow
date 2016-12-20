package users.admin;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import sample.*;
import users.GeneralUserInteface;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import users.student.Student;
import java.util.ArrayList;

/**
 * Klasa administrator w systemie. Posiada metody do wyświetlnia
 * interfejsu użytkownika oraz do edycji danych w systemie.
 * Created by piotrek on 06.12.16.
 */
public class AdminUserInterface extends GeneralUserInteface {

    private Admin admin;

    public AdminUserInterface(Stage _parentWindow, Scene _previousScene, String adminLogin){
        /*Przypisanie okna i sceny rodzica*/
        this.parentWindow = _parentWindow;
        this.parentScene = _previousScene;
        for(Admin a : DataBase.INSTANCE.getAdminList())
            if(a.login.equals(adminLogin)) {
                this.admin = a;
                break;
            }
    }

    @Override
    public void displayMainMenu(){
        /*Inicjalizacja szkieletu GUI*/
        initInterfaceFrame("Panel administracyjny");

        VBox mainMenuLayout = new VBox();
        mainMenuLayout.setPadding(new Insets(50,50,50,50));
        mainMenuLayout.setSpacing(40);
        mainMenuLayout.setAlignment(Pos.CENTER);

        Button buttonManageStudents = new Button("Zarządzaj katalogiem studentów");
        buttonManageStudents.setPadding(new Insets(5,10,5,10));
        buttonManageStudents.setAlignment(Pos.CENTER);

        mainMenuLayout.getChildren().addAll(buttonProfile, buttonCourses,buttonManageStudents, buttonLogout);
        Scene mainMenuScene = new Scene(mainMenuLayout);

         /*Obsługa zdarzeń przycisków*/
        buttonLogout.setOnAction(e -> logout());
        buttonProfile.setOnAction(e -> showProfile(mainMenuScene));
        buttonCourses.setOnAction(e -> manageCourses(mainMenuScene));
        buttonManageStudents.setOnAction((e-> manageStudents(mainMenuScene)));

        this.parentWindow.setScene(mainMenuScene);

    }

    @Override
    public void showProfile(Scene lastScene){
        System.out.println(admin);
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(40,30,40,30));
        VBox v1 = new VBox();
        VBox v2 = new VBox();
        v1.setSpacing(20);
        v2.setSpacing(20);
        layout.setAlignment(Pos.CENTER);

        Button powrot = new Button("Wróć");

        powrot.setOnAction(e->{
            parentWindow.setScene(lastScene);
        });

        Label login = new Label("Login : ");
        Label email = new Label("E-mail");
        Label name  = new Label("Imię : ");
        Label surr  = new Label("Nazwisko : ");

        Label loginV2 = new Label(admin.login);
        Label nameV2  = new Label(admin.name);
        Label surrV2  = new Label(admin.surname);
        Label emailV2 = new Label(admin.email);

        v1.getChildren().addAll(login,name,surr,email,powrot);
        v2.getChildren().addAll(loginV2,nameV2,surrV2,emailV2);

        layout.setHgap(10);
        layout.setVgap(10);

        layout.setConstraints(v1,0,0);
        layout.setConstraints(v2,1,0);
        layout.getChildren().addAll(v1,v2);

        Scene scene = new Scene(layout);
        parentWindow.setScene(scene);
    }

    @Override
    public void manageCourses(Scene lastScene) {
        VBox layout = new VBox();
        HBox bottomBar = new HBox();
        layout.setPadding(new Insets(10,10,5,5));
        layout.setSpacing(10);
        bottomBar.setPadding(new Insets(10,10,5,5));
        bottomBar.setSpacing(20);

        Button powrot = new Button("Wróć");
        Button usun = new Button("Usuń Kurs");
        Button dodaj = new Button("Dodaj Kurs");
        Button edytuj = new Button("Edytuj Kurs");
        Button zarzadzajGrupami = new Button("Zarządzaj Grupami");
        zarzadzajGrupami.setDisable(true);
        usun.setDisable(true);
        edytuj.setDisable(true);

        javafx.scene.control.TableView<Course> table = new javafx.scene.control.TableView<>();

        TableColumn<Course,String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(250);
        nameColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));

        TableColumn<Course,String> courseCodeColumn = new TableColumn<>("Code");
        courseCodeColumn.setMinWidth(40);
        courseCodeColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("courseCode"));

        TableColumn<Course,String> termColumn = new TableColumn<>("Term");
        termColumn.setMinWidth(40);
        termColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("term"));

        TableColumn<Course,String> departmentColumn = new TableColumn<>("Dep");
        departmentColumn.setMinWidth(40);
        departmentColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("departmentName"));

        TableColumn<Course,String> fieldOfStudyColumn = new TableColumn<>("Field");
        fieldOfStudyColumn.setMinWidth(40);
        fieldOfStudyColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("fieldOfStudyName"));

        TableColumn<Course,String> groupTypesColumn = new TableColumn<>("Group types");
        groupTypesColumn.setMinWidth(40);
        groupTypesColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("groupTypes"));

        TableColumn<Course,String> specializationColumn = new TableColumn<>("Spec");
        specializationColumn.setMinWidth(20);
        specializationColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("specializationName"));

        TableColumn<Course,String> ECTSColumn = new TableColumn<>("ECTS");
        ECTSColumn.setMinWidth(20);
        ECTSColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("ECTS"));

        table.setItems(DataBase.INSTANCE.getCourseList());
        table.getColumns().addAll(nameColumn,courseCodeColumn,termColumn,departmentColumn,fieldOfStudyColumn,groupTypesColumn,specializationColumn,ECTSColumn);

        bottomBar.getChildren().addAll(powrot,usun,dodaj,edytuj,zarzadzajGrupami);

        table.setMinWidth(1500);

        layout.getChildren().addAll(table,bottomBar);
        Scene scene = new Scene(layout);
        parentWindow.setScene(scene);



        table.getSelectionModel().selectedItemProperty().addListener((v,OldValue,NewValue)->{
            zarzadzajGrupami.setDisable(false);
            usun.setDisable(false);
            edytuj.setDisable(false);
        });

        powrot.setOnAction(e->{
            parentWindow.setScene(lastScene);
        });

        usun.setOnAction(e->{
            try {
                admin.deleteCourse(table.getSelectionModel().getSelectedItem());
            }catch(NullPointerException exc){
                AlertBox.Display("Błąd","Nie wybrano żadnego kursu do usunięcia.");
            }
        });

        zarzadzajGrupami.setOnAction(e->{
            try {
                manageGroups(scene, table.getSelectionModel().getSelectedItem());
            }catch(NullPointerException exc){
                AlertBox.Display("Błąd","Nie wybrano żadnego kursu.");
            }
        });

        dodaj.setOnAction(e->{
            addCourse(scene);
            }
        );

        edytuj.setOnAction(e->{
            editCourse(scene,table.getSelectionModel().getSelectedItem());
        });
    }

    @Override
    public void manageGroups(Scene lastScene, Course course) {
            VBox layout = new VBox();
            HBox bottomBar = new HBox();
            layout.setPadding(new Insets(10,10,5,5));
            layout.setSpacing(10);
            bottomBar.setPadding(new Insets(10,10,5,5));
            bottomBar.setSpacing(20);
            Button powrot = new Button("Wróć");
            Button usun = new Button("Usuń Grupę");
            Button dodaj = new Button("Dodaj Grupę");
            Button edytuj = new Button("Edytuj Grupę");

            javafx.scene.control.TableView<Group> table = new javafx.scene.control.TableView<>();

            TableColumn<Group,String> nameColumn = new TableColumn<>("Name");
            nameColumn.setMinWidth(300);
            nameColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("name"));

            TableColumn<Group,String> groupCodeColumn = new TableColumn<>("GroupCode");
            groupCodeColumn.setMinWidth(40);
            groupCodeColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("groupCode"));

            TableColumn<Group,String> courseCodeColumn = new TableColumn<>("CourseCode");
            courseCodeColumn.setMinWidth(40);
            courseCodeColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("courseCode"));

            TableColumn<Group,String> typeColumn = new TableColumn<>("Type");
            typeColumn.setMinWidth(40);
            typeColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("type"));

            TableColumn<Group,String> proffesorColumn = new TableColumn<>("Proffesor");
            proffesorColumn.setMinWidth(40);
            proffesorColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("proffesor"));

            TableColumn<Group,String> dataColumn = new TableColumn<>("Date");
            dataColumn.setMinWidth(40);
            dataColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("date"));

            TableColumn<Group,String> numberOfHoursColumn = new TableColumn<>("NumberOfHours");
            numberOfHoursColumn.setMinWidth(20);
            numberOfHoursColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("numberOfHours"));

            TableColumn<Group,String> numberOfPlacesColumn = new TableColumn<>("NumberOfPlaces");
            numberOfPlacesColumn.setMinWidth(20);
            numberOfPlacesColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("numberOfPlaces"));

            TableColumn<Group,String> avaibalePlacesColumn = new TableColumn<>("AvaiablePlaces");
            avaibalePlacesColumn.setMinWidth(20);
            avaibalePlacesColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("avaiablePlaces"));

            TableColumn<Group,String> roomColumn = new TableColumn<>("room");
            roomColumn.setMinWidth(20);
            roomColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("room"));

            table.setItems(course.getGroups());
            table.getColumns().addAll(nameColumn,groupCodeColumn,courseCodeColumn,typeColumn,proffesorColumn,dataColumn,numberOfHoursColumn, numberOfPlacesColumn,avaibalePlacesColumn,roomColumn);
            table.setMinWidth(1500);

            bottomBar.getChildren().addAll(powrot,usun,dodaj,edytuj);
            layout.getChildren().addAll(table,bottomBar);
            Scene scene = new Scene(layout);
            parentWindow.setScene(scene);

            usun.setOnAction(e->{
                try {
                    admin.deleteGroup(table.getSelectionModel().getSelectedItem());
                    table.refresh();
                }catch(NullPointerException exc){
                    AlertBox.Display("Błąd","Nie wybrano żadnej grupy do usunięcia.");
                }
            });

            dodaj.setOnAction(e->{
                addGroup(scene,course);
                table.refresh();
            });

            powrot.setOnAction(e->{
            parentWindow.setScene(lastScene);
            });

            edytuj.setOnAction(e->{
                if(!table.getSelectionModel().isEmpty()) {
                    editGroup(scene, table.getSelectionModel().getSelectedItem(), course);
                    table.refresh();
                }else{
                    AlertBox.Display("Błąd","Nie wybrano żadnej grupy.");
                }
            });
    }

    public void manageStudents(Scene lastScene){
        VBox layout = new VBox();
        HBox bottomBar = new HBox();
        layout.setPadding(new Insets(10,10,5,5));
        layout.setSpacing(10);
        bottomBar.setPadding(new Insets(10,10,5,5));
        bottomBar.setSpacing(20);
        Button returnButton = new Button("Wróć");
        Button changeSignUpRightButton = new Button("Zmień prawo do zapisów");
        Button showProfileButton = new Button("Wyswietl profil studenta");

        javafx.scene.control.TableView<Student> table = new javafx.scene.control.TableView<>();

        TableColumn<Student,String> loginColumn = new TableColumn<>("Login");
        loginColumn.setMinWidth(200);
        loginColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("login"));

        TableColumn<Student,String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(40);
        nameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));

        TableColumn<Student,String> surnameColumn = new TableColumn<>("Surname");
        surnameColumn.setMinWidth(40);
        surnameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));

        TableColumn<Student,String> emailColumn = new TableColumn<>("Email");
        emailColumn.setMinWidth(40);
        emailColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));

        TableColumn<Student,String> IDColumn = new TableColumn<>("ID");
        IDColumn.setMinWidth(40);
        IDColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("ID"));

        TableColumn<Student,String> departmentColumn = new TableColumn<>("Department");
        departmentColumn.setMinWidth(20);
        departmentColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("departmentName"));

        TableColumn<Student,String> fieldOfStudyColumn = new TableColumn<>("Field");
        fieldOfStudyColumn.setMinWidth(20);
        fieldOfStudyColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("fieldOfStudyName"));

        TableColumn<Student,String> specializationColumn = new TableColumn<>("Spec");
        specializationColumn.setMinWidth(20);
        specializationColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("specializationName"));

        TableColumn<Student,String> termColumn = new TableColumn<>("Term");
        termColumn.setMinWidth(20);
        termColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("term"));

        TableColumn<Student,String> signUpRightColumn = new TableColumn<>("SignUpRight");
        signUpRightColumn.setMinWidth(20);
        signUpRightColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("signUpRight"));

        TableColumn<Student,String> ECTSColumn = new TableColumn<>("ECTS");
        ECTSColumn.setMinWidth(20);
        ECTSColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("ECTS"));

        table.setItems(DataBase.INSTANCE.getStudentsList());
        table.getColumns().addAll(loginColumn,nameColumn,surnameColumn,emailColumn,IDColumn,departmentColumn,fieldOfStudyColumn,specializationColumn,termColumn,signUpRightColumn,ECTSColumn);
        table.setMinWidth(1500);
        //table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        bottomBar.getChildren().addAll(returnButton,changeSignUpRightButton,showProfileButton);
        layout.getChildren().addAll(table,bottomBar);
        Scene scene = new Scene(layout);
        parentWindow.setScene(scene);

        changeSignUpRightButton.setOnAction(e->{
            try {
                admin.changeSignUpRight(table.getSelectionModel().getSelectedItem());
                //manageStudents(lastScene);
                table.refresh();
            }catch(NullPointerException exc){
                AlertBox.Display("Błąd","Nie wybrano studenta.");
            }
        });

        showProfileButton.setOnAction(e->{
            try {
            showStudentProfile(scene,table.getSelectionModel().getSelectedItem());
            }catch(NullPointerException exc){
                AlertBox.Display("Błąd","Nie wybrano studenta.");
            }
        });

        returnButton.setOnAction(e->{
            parentWindow.setScene(lastScene);
        });
    }

    public void addCourse(Scene lastScene){
        Label nameLabel = new Label("Nazwa Kursu :");
        TextField name = new TextField();

        Label courseCodeLabel = new Label("Kod Kursu :");
        TextField courseCode = new TextField();;

        Label termLabel = new Label("Semestr :");
        ChoiceBox<Integer> term = new ChoiceBox<>();
        term.getItems().addAll(1,2,3,4,5,6,7);

        Label departmentLabel = new Label("Wydział :");
        ChoiceBox<Department> department = new ChoiceBox<>();
        department.getItems().addAll(Department.values());

        Label lectureLabel = new Label("Wykład :");
        CheckBox lecture = new CheckBox();

        Label excercisesLabel = new Label("Ćwiczenia :");
        CheckBox excercises = new CheckBox();

        Label seminarLabel = new Label("Seminarium :");
        CheckBox seminar = new CheckBox();

        Label laboratoryLabel = new Label("Laboratorium :");
        CheckBox laboratory = new CheckBox();

        Label projectLabel = new Label("Projekt :");
        CheckBox project = new CheckBox();

        Label fieldOfStudysLabel = new Label("Kierunek :");
        ChoiceBox<FieldsOfStudies> fieldOfStudys = new ChoiceBox<>();;
        fieldOfStudys.getItems().addAll(FieldsOfStudies.values());

        Label specializationLabel = new Label("Specjalizacja :");
        ChoiceBox<Specialization> specialization = new ChoiceBox<>();;
        specialization.getItems().addAll(Specialization.values());

        Label ECTSLabel = new Label("ECTS :");
        ChoiceBox<Integer> ECTS = new ChoiceBox<>();
        ECTS.getItems().addAll(1,2,3,4,5,6,7,8,9,10);

        Label obligatoryLabel = new Label("Obowiązkowy :");
        CheckBox obligatory = new CheckBox();


        HBox nameBox = new HBox(nameLabel,name);
        nameBox.setSpacing(10);

        HBox courseCodeBox = new HBox(courseCodeLabel,courseCode);
        courseCodeBox.setSpacing(10);

        HBox termBox = new HBox(termLabel,term);
        termBox.setSpacing(10);

        HBox departmentBox = new HBox(departmentLabel,department);
        departmentBox.setSpacing(10);

        HBox lectureBox = new HBox(lectureLabel,lecture);
        lectureBox.setSpacing(10);

        HBox excerciseBox = new HBox(excercisesLabel,excercises);
        excerciseBox.setSpacing(10);

        HBox seminarBox = new HBox(seminarLabel,seminar);
        seminarBox.setSpacing(10);

        HBox laboratoryBox = new HBox(laboratoryLabel,laboratory);
        laboratoryBox.setSpacing(10);

        HBox projectBox = new HBox(projectLabel,project);
        projectBox.setSpacing(10);

        HBox fieldOfStudyBox = new HBox(fieldOfStudysLabel,fieldOfStudys);
        fieldOfStudyBox.setSpacing(10);

        HBox specializationBox = new HBox(specializationLabel,specialization);
        specializationBox.setSpacing(10);

        HBox ECTSBox = new HBox(ECTSLabel,ECTS);
        ECTSBox.setSpacing(10);

        HBox obligatoryBox = new HBox(obligatoryLabel,obligatory);
        obligatoryBox.setSpacing(10);

        VBox box = new VBox(nameBox,courseCodeBox,termBox,departmentBox,lectureBox,excerciseBox,seminarBox,laboratoryBox,projectBox,fieldOfStudyBox,specializationBox,ECTSBox,obligatoryBox);
        box.setSpacing(4);


        Button confirm = new Button("Zatwierdź");
        Button powrot = new Button("Wróć");

        HBox buttons = new HBox(powrot,confirm);

        VBox layout = new VBox();
        layout.getChildren().addAll(box,buttons);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(40,30,40,30));

        Scene scene = new Scene(layout);
        parentWindow.setScene(scene);

        powrot.setOnAction(e->{
            parentWindow.setScene(lastScene);
        });

        confirm.setOnAction(e->{

            ArrayList<GroupTypes> groupsTypes = new ArrayList<>();
            if(lecture.isSelected()) groupsTypes.add(GroupTypes.LCTR);
            if(excercises.isSelected()) groupsTypes.add(GroupTypes.EX);
            if(seminar.isSelected()) groupsTypes.add(GroupTypes.SEM);
            if(laboratory.isSelected()) groupsTypes.add(GroupTypes.LAB);
            if(project.isSelected()) groupsTypes.add(GroupTypes.PR);


            Course course = admin.createNewCourse();

            admin.setCourseName(course,name.getText());
            admin.setCourseCode(course,courseCode.getText());
            admin.setCourseTerm(course,term.getValue());
            admin.setCourseDepartment(course,department.getValue());
            admin.setCourseFieldOfStudy(course,fieldOfStudys.getValue());
            admin.setCourseGroupTypes(course,groupsTypes);
            admin.setCourseSpecialization(course,specialization.getValue());
            admin.setCourseECTS(course,ECTS.getValue());
            admin.setCourseObligatory(course,obligatory.isSelected());


            AlertBox.Display("Potwierdzenie","Dodano kurs.");
            parentWindow.setScene(lastScene);
        });
    }

    void addGroup(Scene lastScene, Course course){
        Label nameLabel = new Label("Nazwa Kursu :");
        TextField name = new TextField();
        name.setText(course.getName());
        name.setDisable(true);

        Label groupCodeLabel = new Label("Kod Grupy :");
        TextField groupCode = new TextField();
        groupCode.setText("Automatyczna generacja");
        groupCode.setDisable(true);

        Label courseCodeLabel = new Label("Kod Kursu :");
        TextField courseCode = new TextField();
        courseCode.setText(course.getCourseCode());
        courseCode.setDisable(true);

        Label groupTypeLabel = new Label("Type : ");
        ChoiceBox<GroupTypes> type= new ChoiceBox<>();
        type.getItems().addAll(GroupTypes.values());

        Label profesorLabel = new Label("Profesor :");
        TextField profesor = new TextField();

        Label dateLabel = new Label("Termin :");
        TextField date = new TextField();

        Label numberOfHoursLabel = new Label("Liczba godzin :");
        ChoiceBox<Integer> numberOfHours= new ChoiceBox<>();
        numberOfHours.getItems().addAll(15,25,30);

        Label numberOfPlacesLabel = new Label("Liczba miejsc :");
        Spinner<Integer> numberOfPlaces = new Spinner<>(30,60,30);
        numberOfPlaces.setEditable(true);

        Label roomLabel = new Label("Pokój :");
        Spinner<Integer> room = new Spinner<>(1,200,1);
        room.setEditable(true);

        HBox nameBox = new HBox(nameLabel,name);
        nameBox.setSpacing(10);

        HBox groupCodeBox = new HBox(groupCodeLabel,groupCode);
        groupCodeBox.setSpacing(10);

        HBox courseCodeBox = new HBox(courseCodeLabel,courseCode);
        courseCodeBox.setSpacing(10);

        HBox typeBox = new HBox(groupTypeLabel,type);
        typeBox.setSpacing(10);

        HBox profesorBox = new HBox(profesorLabel,profesor);
        profesorBox.setSpacing(10);

        HBox dateBox = new HBox(dateLabel,date);
        dateBox.setSpacing(10);

        HBox numberOfHoursBox = new HBox(numberOfHoursLabel,numberOfHours);
        numberOfHoursBox.setSpacing(10);

        HBox numberOfPlacesBox = new HBox(numberOfPlacesLabel,numberOfPlaces);
        numberOfPlacesBox.setSpacing(10);

        HBox roomBox = new HBox(roomLabel,room);
        roomBox.setSpacing(10);

        VBox box = new VBox(nameBox,groupCodeBox,courseCodeBox,typeBox,profesorBox,dateBox,numberOfHoursBox,numberOfPlacesBox,roomBox);
        box.setSpacing(4);


        Button confirm = new Button("Zatwierdź");
        Button powrot = new Button("Wróć");

        HBox buttons = new HBox(powrot,confirm);

        VBox layout = new VBox();
        layout.getChildren().addAll(box,buttons);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(40,30,40,30));

        Scene scene = new Scene(layout);
        parentWindow.setScene(scene);

        powrot.setOnAction(e->{
            parentWindow.setScene(lastScene);
        });

        confirm.setOnAction(e->{

            if(name.getText().isEmpty()){
                AlertBox.Display("Błąd","Wypełnij wszystkie pola.");
                return;
            }
            if(profesor.getText().isEmpty()){
                AlertBox.Display("Błąd","Wypełnij wszystkie pola.");
                return;
            }
            if(date.getText().isEmpty()){
                AlertBox.Display("Błąd","Wypełnij wszystkie pola.");
                return;
            }
            if(numberOfHours.getSelectionModel().isEmpty()){
                AlertBox.Display("Błąd","Wypełnij wszystkie pola.");
                return;
            }
            if(numberOfPlaces.getValue().toString().isEmpty()){
                AlertBox.Display("Błąd","Wypełnij wszystkie pola.");
                return;
            }
            if(room.getValue().toString().isEmpty()){
                AlertBox.Display("Błąd","Wypełnij wszystkie pola.");
                return;
            }


            String generatedGroupCode ="";
            generatedGroupCode+=course.getCourseCode();

            int groupNumber=0;
            for(Group g : course.getGroups()){
                if(g.getType().equals(type.getValue())){
                    groupNumber++;
                }
            }
            generatedGroupCode+=Integer.toString(groupNumber+1);
            generatedGroupCode+=type.toString();


            Group group = admin.createNewGroup(course);

            admin.setGroupName(group,name.getText());
            admin.setGroupGroupCode(group,generatedGroupCode);
            admin.setGroupCourseCode(group,courseCode.getText());
            admin.setGroupProffesor(group,profesor.getText());
            admin.setGroupDate(group,date.getText());
            admin.setGroupNumberOfHours(group,numberOfHours.getValue());
            admin.setGroupNumberOfPlaces(group,numberOfPlaces.getValue());
            admin.setGroupRoom(group,room.getValue());


            AlertBox.Display("Potwierdzenie","Dodano grupę.");
            parentWindow.setScene(lastScene);
        });
    }

    void editGroup(Scene lastScene, Group group, Course course){
        Label nameLabel = new Label("Nazwa Kursu :");
        TextField name = new TextField();
        name.setText(course.getName());
        name.setDisable(true);

        Label groupCodeLabel = new Label("Kod Grupy :");
        TextField groupCode = new TextField();
        groupCode.setText(group.getGroupCode());
        groupCode.setDisable(true);

        Label courseCodeLabel = new Label("Kod Kursu :");
        TextField courseCode = new TextField();
        courseCode.setText(course.getCourseCode());
        courseCode.setDisable(true);

        Label groupTypeLabel = new Label("Type : ");
        TextField type = new TextField(group.getType().toString());
        type.setDisable(true);

        Label profesorLabel = new Label("Profesor :");
        TextField profesor = new TextField();
        profesor.setText(group.getProffesor());

        Label dateLabel = new Label("Termin :");
        TextField date = new TextField();
        date.setText(group.getDate());

        Label numberOfHoursLabel = new Label("Liczba godzin :");
        ChoiceBox<Integer> numberOfHours= new ChoiceBox<>();
        numberOfHours.getItems().addAll(15,25,30);
        numberOfHours.getSelectionModel().select(Integer.valueOf(group.getNumberOfHours()));

        Label numberOfPlacesLabel = new Label("Liczba miejsc :");
        Spinner<Integer> numberOfPlaces = new Spinner<>(30,60,group.getNumberOfPlaces());
        numberOfPlaces.setEditable(true);

        Label roomLabel = new Label("Pokój :");
        Spinner<Integer> room = new Spinner<>(1,200,group.getRoom());
        room.setEditable(true);

        HBox nameBox = new HBox(nameLabel,name);
        nameBox.setSpacing(10);

        HBox groupCodeBox = new HBox(groupCodeLabel,groupCode);
        groupCodeBox.setSpacing(10);

        HBox courseCodeBox = new HBox(courseCodeLabel,courseCode);
        courseCodeBox.setSpacing(10);

        HBox typeBox = new HBox(groupTypeLabel,type);
        typeBox.setSpacing(10);

        HBox profesorBox = new HBox(profesorLabel,profesor);
        profesorBox.setSpacing(10);

        HBox dateBox = new HBox(dateLabel,date);
        dateBox.setSpacing(10);

        HBox numberOfHoursBox = new HBox(numberOfHoursLabel,numberOfHours);
        numberOfHoursBox.setSpacing(10);

        HBox numberOfPlacesBox = new HBox(numberOfPlacesLabel,numberOfPlaces);
        numberOfPlacesBox.setSpacing(10);

        HBox roomBox = new HBox(roomLabel,room);
        roomBox.setSpacing(10);

        VBox box = new VBox(nameBox,groupCodeBox,courseCodeBox,typeBox,profesorBox,dateBox,numberOfHoursBox,numberOfPlacesBox,roomBox);
        box.setSpacing(4);


        Button confirm = new Button("Zatwierdź");
        Button powrot = new Button("Wróć");

        HBox buttons = new HBox(powrot,confirm);

        VBox layout = new VBox();
        layout.getChildren().addAll(box,buttons);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(40,30,40,30));

        Scene scene = new Scene(layout);
        parentWindow.setScene(scene);

        powrot.setOnAction(e->{
            parentWindow.setScene(lastScene);
        });

        confirm.setOnAction(e->{

            if(name.getText().isEmpty()){
                AlertBox.Display("Błąd","Wypełnij wszystkie pola.");
                return;
            }
            if(profesor.getText().isEmpty()){
                AlertBox.Display("Błąd","Wypełnij wszystkie pola.");
                return;
            }
            if(date.getText().isEmpty()){
                AlertBox.Display("Błąd","Wypełnij wszystkie pola.");
                return;
            }
            if(numberOfHours.getSelectionModel().isEmpty()){
                AlertBox.Display("Błąd","Wypełnij wszystkie pola.");
                return;
            }
            if(numberOfPlaces.getValue().toString().isEmpty()){
                AlertBox.Display("Błąd","Wypełnij wszystkie pola.");
                return;
            }
            if(room.getValue().toString().isEmpty()) {
                AlertBox.Display("Błąd", "Wypełnij wszystkie pola.");
                return;
            }


            admin.editGroup(group, profesor.getText(), date.getText(), numberOfHours.getValue(), numberOfPlaces.getValue(), room.getValue());

            AlertBox.Display("Potwierdzenie","Wprowadzono zmiany.");
            parentWindow.setScene(lastScene);
        });
    }

    void editCourse(Scene lastScene, Course course){

        if(course.getGroups().isEmpty()) {

            Label nameLabel = new Label("Nazwa Kursu :");
            TextField name = new TextField();
            name.setText(course.getName());

            Label courseCodeLabel = new Label("Kod Kursu :");
            TextField courseCode = new TextField();

            courseCode.setText(course.getCourseCode());
            courseCode.setDisable(true);

            Label termLabel = new Label("Semestr :");
            ChoiceBox<Integer> term = new ChoiceBox<>();
            term.getItems().addAll(1,2,3,4,5,6,7);
            term.getSelectionModel().select(course.getTerm());

            Label departmentLabel = new Label("Wydział :");
            ChoiceBox<Department> department = new ChoiceBox<>();
            department.getItems().addAll(Department.values());
            department.getSelectionModel().select(course.getDepartment());

            Label lectureLabel = new Label("Wykład :");
            CheckBox lecture = new CheckBox();
            lecture.setSelected(false);
            for (GroupTypes g : course.getGroupTypesList()) {
                if (g.equals(GroupTypes.LCTR)) {
                    lecture.setSelected(true);
                    break;
                }
            }

            Label excercisesLabel = new Label("Ćwiczenia :");
            CheckBox excercises = new CheckBox();
            excercises.setSelected(false);
            for (GroupTypes g : course.getGroupTypesList()) {
                if (g.equals(GroupTypes.EX)) {
                    excercises.setSelected(true);
                    break;
                }
            }


            Label seminarLabel = new Label("Seminarium :");
            CheckBox seminar = new CheckBox();
            seminar.setSelected(false);
            for (GroupTypes g : course.getGroupTypesList()) {
                if (g.equals(GroupTypes.SEM)) {
                    seminar.setSelected(true);
                    break;
                }
            }


            Label laboratoryLabel = new Label("Laboratorium :");
            CheckBox laboratory = new CheckBox();
            laboratory.setSelected(false);
            for (GroupTypes g : course.getGroupTypesList()) {
                if (g.equals(GroupTypes.LAB)) {
                    laboratory.setSelected(true);
                    break;
                }
            }

            Label projectLabel = new Label("Projekt :");
            CheckBox project = new CheckBox();
            project.setSelected(false);
            for (GroupTypes g : course.getGroupTypesList()) {
                if (g.equals(GroupTypes.PR)) {
                    project.setSelected(true);
                    break;
                }
            }

            Label fieldOfStudysLabel = new Label("Kierunek :");
            ChoiceBox<FieldsOfStudies> fieldOfStudys = new ChoiceBox<>();
            ;
            fieldOfStudys.getItems().addAll(FieldsOfStudies.values());
            fieldOfStudys.getSelectionModel().select(course.getFieldOfStudy());

            Label specializationLabel = new Label("Specjalizacja :");
            ChoiceBox<Specialization> specialization = new ChoiceBox<>();
            ;
            specialization.getItems().addAll(Specialization.values());
            specialization.getSelectionModel().select(course.getSpecialization());

            Label ECTSLabel = new Label("ECTS :");
            ChoiceBox<Integer> ECTS = new ChoiceBox<>();
            ECTS.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
            ECTS.getSelectionModel().select(course.getECTS());

            Label obligatoryLabel = new Label("Obowiązkowy :");
            CheckBox obligatory = new CheckBox();
            if (course.getObligatory())
                obligatory.setSelected(true);


            HBox nameBox = new HBox(nameLabel, name);
            nameBox.setSpacing(10);

            HBox courseCodeBox = new HBox(courseCodeLabel, courseCode);
            courseCodeBox.setSpacing(10);

            HBox termBox = new HBox(termLabel, term);
            termBox.setSpacing(10);

            HBox departmentBox = new HBox(departmentLabel, department);
            departmentBox.setSpacing(10);

            HBox lectureBox = new HBox(lectureLabel, lecture);
            lectureBox.setSpacing(10);

            HBox excerciseBox = new HBox(excercisesLabel, excercises);
            excerciseBox.setSpacing(10);

            HBox seminarBox = new HBox(seminarLabel, seminar);
            seminarBox.setSpacing(10);

            HBox laboratoryBox = new HBox(laboratoryLabel, laboratory);
            laboratoryBox.setSpacing(10);

            HBox projectBox = new HBox(projectLabel, project);
            projectBox.setSpacing(10);

            HBox fieldOfStudyBox = new HBox(fieldOfStudysLabel, fieldOfStudys);
            fieldOfStudyBox.setSpacing(10);

            HBox specializationBox = new HBox(specializationLabel, specialization);
            specializationBox.setSpacing(10);

            HBox ECTSBox = new HBox(ECTSLabel, ECTS);
            ECTSBox.setSpacing(10);

            HBox obligatoryBox = new HBox(obligatoryLabel, obligatory);
            obligatoryBox.setSpacing(10);

            VBox box = new VBox(nameBox, courseCodeBox, termBox, departmentBox, lectureBox, excerciseBox, seminarBox, laboratoryBox, projectBox, fieldOfStudyBox, specializationBox, ECTSBox, obligatoryBox);
            box.setSpacing(4);


            Button confirm = new Button("Zatwierdź");
            Button powrot = new Button("Wróć");

            HBox buttons = new HBox(powrot, confirm);

            VBox layout = new VBox();
            layout.getChildren().addAll(box, buttons);
            layout.setAlignment(Pos.CENTER);
            layout.setPadding(new Insets(40, 30, 40, 30));

            Scene scene = new Scene(layout);
            parentWindow.setScene(scene);

            powrot.setOnAction(e -> {
                parentWindow.setScene(lastScene);
            });

            confirm.setOnAction(e -> {

                ArrayList<GroupTypes> groupsTypes = new ArrayList<>();
                if (lecture.isSelected()) groupsTypes.add(GroupTypes.LCTR);
                if (excercises.isSelected()) groupsTypes.add(GroupTypes.EX);
                if (seminar.isSelected()) groupsTypes.add(GroupTypes.SEM);
                if (laboratory.isSelected()) groupsTypes.add(GroupTypes.LAB);
                if (project.isSelected()) groupsTypes.add(GroupTypes.PR);

                admin.editCourse(course, name.getText(), term.getValue(), department.getValue(), groupsTypes, fieldOfStudys.getValue(), specialization.getValue(), ECTS.getValue(), obligatory.isSelected());

                AlertBox.Display("Potwierdzenie", "Dodano wprowadzone zmiany.");
                parentWindow.setScene(lastScene);
            });
        }else{
            AlertBox.Display("Błąd","Nie można edytować kursu który zawiera utworzone grupy.");
        }
    }

    void showStudentProfile(Scene lastScene,Student student){
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(40,30,40,30));
        VBox box = new VBox();
        box.setSpacing(20);
        layout.setAlignment(Pos.CENTER_LEFT);

        Button powrot = new Button("Wróć");

        powrot.setOnAction(e->{
            parentWindow.setScene(lastScene);
        });

        ArrayList<Label> labels = new ArrayList<>();
        admin.getStudentProfile(labels,student);

        for(Label l : labels) {
            box.getChildren().add(l);
        }
        layout.setHgap(10);
        layout.setVgap(10);


        layout.setConstraints(box,0,0);
        layout.setConstraints(powrot,0,1);
        layout.getChildren().addAll(box,powrot);

        Scene scene = new Scene(layout);

        parentWindow.setScene(scene);
    }
}














