package users.admin;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import sample.AlertBox;
import sample.Course;
import sample.DataBase;
import sample.Group;
import users.GeneralUserInteface;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
        admin = new Admin(adminLogin);
    }

    @Override
    public void displayMainMenu(){
        /*Inicjalizacja szkieletu GUI*/
        initInterfaceFrame("Panel administracyjny");

        VBox mainMenuLayout = new VBox();
        mainMenuLayout.setPadding(new Insets(50,50,50,50));
        mainMenuLayout.setSpacing(40);
        mainMenuLayout.setAlignment(Pos.CENTER);

        mainMenuLayout.getChildren().addAll(buttonProfile, buttonCourses, buttonLogout);
        Scene mainMenuScene = new Scene(mainMenuLayout);

         /*Obsługa zdarzeń przycisków*/
        buttonLogout.setOnAction(e -> logout());
        buttonProfile.setOnAction(e -> showProfile(mainMenuScene));
        buttonCourses.setOnAction(e -> manageCourses(mainMenuScene));

        this.parentWindow.setScene(mainMenuScene);

    }

    @Override
    public void showProfile(Scene lastScene){
        System.out.println(admin);
        GridPane layout = new GridPane();
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



        javafx.scene.control.TableView<Course> table = new javafx.scene.control.TableView<>();

        TableColumn<Course,String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));

        TableColumn<Course,String> courseCodeColumn = new TableColumn<>("Code");
        courseCodeColumn.setMinWidth(40);
        courseCodeColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("courseCode"));

        TableColumn<Course,String> termColumn = new TableColumn<>("Term");
        termColumn.setMinWidth(40);
        termColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("term"));

        TableColumn<Course,String> departmentColumn = new TableColumn<>("Dep");
        departmentColumn.setMinWidth(40);
        departmentColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("department"));

        TableColumn<Course,String> fieldOfStudeyColumn = new TableColumn<>("Fied");
        fieldOfStudeyColumn.setMinWidth(40);
        fieldOfStudeyColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("fieldOfStudy"));

        TableColumn<Course,String> lectureColumn = new TableColumn<>("Lec");
        lectureColumn.setMinWidth(20);
        lectureColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("lecture"));

        TableColumn<Course,String> excercisesColumn = new TableColumn<>("Exc");
        excercisesColumn.setMinWidth(20);
        excercisesColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("excercises"));

        TableColumn<Course,String> seminarColumn = new TableColumn<>("Sem");
        seminarColumn.setMinWidth(20);
        seminarColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("seminar"));

        TableColumn<Course,String> laboratoryColumn = new TableColumn<>("Lab");
        laboratoryColumn.setMinWidth(20);
        laboratoryColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("laboratory"));

        TableColumn<Course,String> projectColumn = new TableColumn<>("Prj");
        projectColumn.setMinWidth(20);
        projectColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("project"));

        TableColumn<Course,String> specializationColumn = new TableColumn<>("Spec");
        specializationColumn.setMinWidth(20);
        specializationColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("specialization"));

        TableColumn<Course,String> ECTSColumn = new TableColumn<>("ECTS");
        ECTSColumn.setMinWidth(20);
        ECTSColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("ECTS"));

        table.setItems(DataBase.INSTANCE.getCourseList());
        table.getColumns().addAll(nameColumn,courseCodeColumn,termColumn,departmentColumn,fieldOfStudeyColumn,lectureColumn,excercisesColumn,seminarColumn,laboratoryColumn,projectColumn,specializationColumn,ECTSColumn);

        bottomBar.getChildren().addAll(powrot,usun,dodaj,edytuj);

        layout.getChildren().addAll(table,bottomBar);
        Scene scene = new Scene(layout);
        parentWindow.setScene(scene);



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
            nameColumn.setMinWidth(100);
            nameColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("name"));

            TableColumn<Group,String> groupCodeColumn = new TableColumn<>("GroupCode");
            groupCodeColumn.setMinWidth(40);
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
            numberOfHoursColumn.setMinWidth(20);
            numberOfHoursColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("numberOfHours"));

            TableColumn<Group,String> numberOfPlacesColumn = new TableColumn<>("NumberOfPlaces");
            numberOfPlacesColumn.setMinWidth(20);
            numberOfPlacesColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("numberOfPlaces"));

            TableColumn<Group,String> roomColumn = new TableColumn<>("room");
            roomColumn.setMinWidth(20);
            roomColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("room"));

            table.setItems(course.getGroups());
            table.getColumns().addAll(nameColumn,courseCodeColumn,proffesorColumn,dataColumn,numberOfHoursColumn, numberOfPlacesColumn,roomColumn);

            bottomBar.getChildren().addAll(powrot,usun,dodaj,edytuj);
            layout.getChildren().addAll(table,bottomBar);
            Scene scene = new Scene(layout);
            parentWindow.setScene(scene);

            usun.setOnAction(e->{
                try {
                    admin.deleteGroup(table.getSelectionModel().getSelectedItem());
                }catch(NullPointerException exc){
                    AlertBox.Display("Błąd","Nie wybrano żadnej grupy do usunięcia.");
                }
            });

            powrot.setOnAction(e->{
            parentWindow.setScene(lastScene);
            });
    }


}














