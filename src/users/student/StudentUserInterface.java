package users.student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.*;
import users.GeneralUserInteface;

/**
 * Created by piotrek on 06.12.16.
 */
public class StudentUserInterface extends GeneralUserInteface {

    private Student student;

    public StudentUserInterface(Stage _parentWindow, Scene _parentScene, String studentLogin){
        this.parentWindow = _parentWindow;
        this.parentScene = _parentScene;
        for(Student s : DataBase.INSTANCE.getStudentsList())
            if(s.login.equals(studentLogin)) {
                this.student = s;
                break;
            }

    }

    @Override
    public void displayMainMenu() {
        /*Inicjalizacja szkieletu GUI*/
        initInterfaceFrame("Panel studenta");

        Button buttonManageStudentsGroups = new Button("Zarządzaj Swoimi Grupami");
        buttonManageStudentsGroups.setPadding(new Insets(5,10,5,10));
        buttonManageStudentsGroups.setAlignment(Pos.CENTER);


        VBox mainMenuLayout = new VBox();
        mainMenuLayout.setPadding(new Insets(50,50,50,50));
        mainMenuLayout.setSpacing(40);
        mainMenuLayout.setAlignment(Pos.CENTER);

        mainMenuLayout.getChildren().addAll(buttonProfile, buttonCourses, buttonManageStudentsGroups, buttonLogout);
        Scene mainMenuScene = new Scene(mainMenuLayout);

         /*Obsługa zdarzeń przycisków*/
        buttonLogout.setOnAction(e -> logout());
        buttonProfile.setOnAction(e -> showProfile(mainMenuScene));
        buttonCourses.setOnAction(e -> manageCourses(mainMenuScene));
        buttonManageStudentsGroups.setOnAction(e-> manageSignedUp(mainMenuScene));

        this.parentWindow.setScene(mainMenuScene);
    }

    @Override
    public void showProfile(Scene lastScene) {
        System.out.println(student);
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(40,30,40,30));
        VBox v1 = new VBox();
        VBox v2 = new VBox();
        v1.setSpacing(20);
        v2.setSpacing(20);
        layout.setAlignment(Pos.CENTER_LEFT);

        Button powrot = new Button("Wróć");

        powrot.setOnAction(e->{
            parentWindow.setScene(lastScene);
        });


        Label login             = new Label("Login : ");
        Label email             = new Label("E-mail : ");
        Label name              = new Label("Imię : ");
        Label surr              = new Label("Nazwisko : ");
        Label id                = new Label("Indeks : ");
        Label department        = new Label("Wydział : ");
        Label fieldOfStudy      = new Label("Kierunek : ");
        Label specialization    = new Label("Specjalizacja : ");
        Label term              = new Label("Semestr : ");
        Label signUpRight       = new Label("Prawo do zapisów : ");
        Label ects              = new Label("ECTS : ");


        Label loginV2             = new Label(student.login);
        Label emailV2             = new Label(student.email);
        Label nameV2              = new Label(student.name);
        Label surrV2              = new Label(student.surname);
        Label idV2                = new Label(student.getID());
        Label departmentV2        = new Label(student.getDepartment().getName());
        Label fieldOfStudyV2      = new Label(student.getFieldOfStudy().getName());
        Label specializationV2    = new Label(student.getSpecialization().getName());
        Label termV2              = new Label(String.valueOf(student.getTerm()));
        Label signUpRightV2       = new Label(student.getSpecialization().getName());
        Label ectsV2              = new Label(String .valueOf(student.getECTS()));


        v1.getChildren().addAll(login,name,surr,email,id,department,fieldOfStudy,specialization,term,signUpRight,ects,powrot);
        v2.getChildren().addAll(loginV2,nameV2,surrV2,emailV2,idV2,departmentV2,fieldOfStudyV2,specializationV2,termV2,signUpRightV2,ectsV2);

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
        Button przegladajGrupy = new Button("Przeglądaj Grupy");
        przegladajGrupy.setDisable(true);

        javafx.scene.control.TableView<Course> table = new javafx.scene.control.TableView<>();

        TableColumn<Course,String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(300);
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

        TableColumn<Course,String> fieldOfStudeyColumn = new TableColumn<>("Fied");
        fieldOfStudeyColumn.setMinWidth(40);
        fieldOfStudeyColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("fieldOfStudyName"));

        TableColumn<Course,String> groupTypesColumn = new TableColumn<>("Group types");
        groupTypesColumn.setMinWidth(40);
        groupTypesColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("groupTypes"));

        TableColumn<Course,String> specializationColumn = new TableColumn<>("Spec");
        specializationColumn.setMinWidth(40);
        specializationColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("specializationName"));

        TableColumn<Course,String> ECTSColumn = new TableColumn<>("ECTS");
        ECTSColumn.setMinWidth(40);
        ECTSColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("ECTS"));


        ObservableList<Course> avaiableCourses = FXCollections.observableArrayList();

        for(Course c : DataBase.INSTANCE.getCourseList()){
            if(c.getDepartment().equals(student.getDepartment())){
                if(c.getFieldOfStudy().equals(student.getFieldOfStudy())){
                    if(c.getSpecialization().equals(student.getSpecialization()) || c.getSpecialization().equals(Specialization.NOSPECIALIZATION)) {
                        if (c.getTerm() == student.getTerm()) {
                            avaiableCourses.add(c);
                        }
                    }
                }
            }
        }

        table.setItems(avaiableCourses);
        table.getColumns().addAll(nameColumn,courseCodeColumn,termColumn,departmentColumn,fieldOfStudeyColumn,groupTypesColumn,specializationColumn,ECTSColumn);
        table.setMinWidth(1500);

        bottomBar.getChildren().addAll(powrot,przegladajGrupy);
        layout.getChildren().addAll(table,bottomBar);
        Scene scene = new Scene(layout);
        parentWindow.setScene(scene);

        table.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newvalue)->{
            przegladajGrupy.setDisable(false);
        });

        przegladajGrupy.setOnAction(e->{
            try {
                manageGroups(scene, table.getSelectionModel().getSelectedItem());
            }catch(NullPointerException exc) {
                AlertBox.Display("Błąd", "Nie wybrano żadnego kursu.");
            }
        });

        powrot.setOnAction(e->{
            parentWindow.setScene(lastScene);
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
        Button zapisz = new Button("Zapisz");
        zapisz.setDisable(true);

        javafx.scene.control.TableView<Group> table = new javafx.scene.control.TableView<>();

        TableColumn<Group,String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(300);
        nameColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("name"));

        TableColumn<Group,String> groupCodeColumn = new TableColumn<>("GroupCode");
        groupCodeColumn.setMinWidth(40);
        groupCodeColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("groupCode"));

        TableColumn<Group,String> courseCodeColumn = new TableColumn<>("CourseCode");
        courseCodeColumn.setMinWidth(70);
        courseCodeColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("courseCode"));

        TableColumn<Group,String> proffesorColumn = new TableColumn<>("Proffesor");
        proffesorColumn.setMinWidth(40);
        proffesorColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("proffesor"));

        TableColumn<Group,String> typeColumn = new TableColumn<>("Type");
        typeColumn.setMinWidth(40);
        typeColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("type"));

        TableColumn<Group,String> dataColumn = new TableColumn<>("Date");
        dataColumn.setMinWidth(40);
        dataColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("date"));

        TableColumn<Group,String> numberOfHoursColumn = new TableColumn<>("NumberOfHours");
        numberOfHoursColumn.setMinWidth(200);
        numberOfHoursColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("numberOfHours"));

        TableColumn<Group,String> numberOfPlacesColumn = new TableColumn<>("NumberOfPlaces");
        numberOfPlacesColumn.setMinWidth(200);
        numberOfPlacesColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("numberOfPlaces"));

        TableColumn<Group,String> avaibalePlacesColumn = new TableColumn<>("AvaiablePlaces");
        avaibalePlacesColumn.setMinWidth(20);
        avaibalePlacesColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("avaiablePlaces"));

        TableColumn<Group,String> roomColumn = new TableColumn<>("room");
        roomColumn.setMinWidth(40);
        roomColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("room"));

        table.setItems(course.getGroups());
        table.getColumns().addAll(nameColumn,courseCodeColumn,proffesorColumn,typeColumn,dataColumn,numberOfHoursColumn, numberOfPlacesColumn,avaibalePlacesColumn,roomColumn);
        table.setMinWidth(1500);

        bottomBar.getChildren().addAll(powrot,zapisz);
        layout.getChildren().addAll(table,bottomBar);
        Scene scene = new Scene(layout);
        parentWindow.setScene(scene);

        table.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newvalue)->{
            zapisz.setDisable(false);
        });

        zapisz.setOnAction(e->{
            try{
                student.signUpToGroup(table.getSelectionModel().getSelectedItem());
                table.refresh();
            }catch(NullPointerException exc){
                AlertBox.Display("Błąd","Nie wybrano żadnej grupy do zapisania.");
            }
        });

        powrot.setOnAction(e-> {
            parentWindow.setScene(lastScene);
        });
    }


    public void manageSignedUp(Scene lastScene){
        VBox layout = new VBox();
        HBox bottomBar = new HBox();
        layout.setPadding(new Insets(10,10,5,5));
        layout.setSpacing(10);
        bottomBar.setPadding(new Insets(10,10,5,5));
        bottomBar.setSpacing(20);
        Button powrot = new Button("Wróć");
        Button wypisz = new Button("Wypisz");
        wypisz.setDisable(false);

        javafx.scene.control.TableView<Group> table = new javafx.scene.control.TableView<>();

        TableColumn<Group,String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(300);
        nameColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("name"));

        TableColumn<Group,String> groupCodeColumn = new TableColumn<>("GroupCode");
        groupCodeColumn.setMinWidth(70);
        groupCodeColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("groupCode"));

        TableColumn<Group,String> courseCodeColumn = new TableColumn<>("CourseCode");
        courseCodeColumn.setMinWidth(40);
        courseCodeColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("courseCode"));

        TableColumn<Group,String> proffesorColumn = new TableColumn<>("Proffesor");
        proffesorColumn.setMinWidth(40);
        proffesorColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("proffesor"));

        TableColumn<Group,String> dataColumn = new TableColumn<>("Date");
        dataColumn.setMinWidth(40);
        dataColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("date"));

        TableColumn<Group,String> numberOfHoursColumn = new TableColumn<>("NumberOfHours");
        numberOfHoursColumn.setMinWidth(200);
        numberOfHoursColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("numberOfHours"));

        TableColumn<Group,String> numberOfPlacesColumn = new TableColumn<>("NumberOfPlaces");
        numberOfPlacesColumn.setMinWidth(200);
        numberOfPlacesColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("numberOfPlaces"));

        TableColumn<Group,String> avaibalePlacesColumn = new TableColumn<>("AvaiablePlaces");
        avaibalePlacesColumn.setMinWidth(20);
        avaibalePlacesColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("avaiablePlaces"));

        TableColumn<Group,String> roomColumn = new TableColumn<>("room");
        roomColumn.setMinWidth(40);
        roomColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("room"));

        table.setItems(student.getGroupList());
        table.getColumns().addAll(nameColumn,courseCodeColumn,proffesorColumn,dataColumn,numberOfHoursColumn, numberOfPlacesColumn,avaibalePlacesColumn,roomColumn);
        table.setMinWidth(1500);
        table.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newvalue)->{
            wypisz.setDisable(false);
        });

        bottomBar.getChildren().addAll(powrot,wypisz);
        layout.getChildren().addAll(table,bottomBar);
        Scene scene = new Scene(layout);
        parentWindow.setScene(scene);

        wypisz.setOnAction(e->{
                try {
                    student.signOutFromGroup(table.getSelectionModel().getSelectedItem());
                }catch(NullPointerException exc){
                    AlertBox.Display("Błąd","Nie wybrano żadnej grupy do usunięcia.");
                }
        });

        powrot.setOnAction(e-> {
            parentWindow.setScene(lastScene);
        });
    }
}
