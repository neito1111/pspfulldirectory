package com.example.demo;


import com.example.demo.connection.DisciplineDao;
import com.example.demo.connection.ScheduleDao;
import com.example.demo.connection.TeacherDao;
import com.example.demo.connection.TimeDao;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.postgresql.util.PSQLException;

import java.sql.SQLException;
import java.util.Optional;

public class HelloApplication extends Application {

    private Stage primaryStage;
    private TeacherDao teacherDao;
    private DisciplineDao disciplineDao;
    private TimeDao timeDao;
    private ScheduleDao scheduleDao;
    private TableView<Teacher> teacherTable = new TableView<>();

    private TextField firstNameField = new TextField();
    private TextField lastNameField = new TextField();
    private TextField middleNameField = new TextField();

    private TableView<Discipline> disciplineTable = new TableView<>();
    private TextField disciplineNameField = new TextField();

    private ComboBox<Teacher> teacherListView = new ComboBox<>();
    private ComboBox<Discipline> disciplineListView = new ComboBox<>();

    private ComboBox<TimeEn> timeEnComboBox = new ComboBox<>();


    private TableView<Schedule> scheduleTableView = new TableView<>();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Teacher Management");

        this.teacherDao = new TeacherDao();
        this.disciplineDao = new DisciplineDao();
        this.timeDao = new TimeDao();
        this.scheduleDao = new ScheduleDao();
        // Set up home page
        VBox homePage = createTeacherPage();



        // Set up scene and show the stage
        Scene scene = new Scene(homePage);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void navigateToAnotherPage(String page) {
        VBox anotherPage = null;
        switch (page){
            case "teacher":{
                anotherPage = createTeacherPage();
                break;
            }
            case "discipline":{
                anotherPage = createAnotherPage();
                break;
            }
            case "schedule":{
                anotherPage = createSchedulePage();
                break;
            }

        }
        Scene anotherScene = new Scene(anotherPage);
        primaryStage.setScene(anotherScene);
    }


    private VBox createTeacherPage(){
        TableColumn<Teacher, Integer> idcolumn = new TableColumn<>("id");
        idcolumn.setCellValueFactory(new PropertyValueFactory<>("id"));


        TableColumn<Teacher, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Teacher, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Teacher, String> middleNameColumn = new TableColumn<>("Middle Name");
        middleNameColumn.setCellValueFactory(new PropertyValueFactory<>("middleName"));

        if(teacherTable.getColumns().isEmpty()){
            teacherTable.getColumns().addAll(idcolumn, firstNameColumn, lastNameColumn, middleNameColumn);
        }

        teacherTable.setItems(FXCollections.observableArrayList(teacherDao.fetchAll()));
        teacherTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Double-click
                Teacher selectedTeacher = teacherTable.getSelectionModel().getSelectedItem();
                if (selectedTeacher != null) {
                    firstNameField.setText(selectedTeacher.getFirstName());
                    lastNameField.setText(selectedTeacher.getLastName());
                    middleNameField.setText(selectedTeacher.getMiddleName());

                }
            }
        });

        // Set up form fields
        GridPane formPane = new GridPane();
        formPane.setHgap(10);
        formPane.setVgap(10);
        formPane.setPadding(new Insets(10));
        formPane.add(new Label("First Name:"), 0, 0);
        formPane.add(firstNameField, 1, 0);
        formPane.add(new Label("Last Name:"), 0, 1);
        formPane.add(lastNameField, 1, 1);
        formPane.add(new Label("Middle Name:"), 0, 2);
        formPane.add(middleNameField, 1, 2);

        // Set up buttons
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addTeacher());
        Button updateButton = new Button("Update");
        updateButton.setOnAction(e -> updateTeacher());
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteTeacher());

        HBox buttonPane = new HBox(10);
        buttonPane.setPadding(new Insets(10));
        buttonPane.getChildren().addAll(addButton, updateButton, deleteButton);



        Button navigateDi = new Button("disciplines");
        navigateDi.setOnAction(e -> navigateToAnotherPage("discipline"));
        Button navigateS = new Button("schedule");
        navigateS.setOnAction(e -> navigateToAnotherPage("schedule"));

        HBox nav = new HBox(10);
        nav.setPadding(new Insets(10));
        nav.getChildren().addAll(navigateDi, navigateS);

        // Set up root layout
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(nav, formPane, buttonPane, teacherTable);
        return root;
    }


    private VBox createAnotherPage() {
        TableColumn<Discipline, Integer> idcolumn = new TableColumn<>("id");
        idcolumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Discipline, String> nameColumn = new TableColumn<>("name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        if(disciplineTable.getColumns().isEmpty()){
            disciplineTable.getColumns().addAll(idcolumn, nameColumn);
        }

        disciplineTable.setItems(FXCollections.observableArrayList(disciplineDao.fetchAll()));
        disciplineTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Double-click
                Discipline selectedItem = disciplineTable.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    disciplineNameField.setText(selectedItem.getName());
                }
            }
        });

        // Set up form fields
        GridPane formPane = new GridPane();
        formPane.setHgap(10);
        formPane.setVgap(10);
        formPane.setPadding(new Insets(10));
        formPane.add(new Label("name:"), 0, 0);
        formPane.add(disciplineNameField, 1, 0);


        // Set up buttons
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addDiscipline());
        Button updateButton = new Button("Update");
        updateButton.setOnAction(e -> updateDiscipline());
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteDiscipline());

        HBox buttonPane = new HBox(10);
        buttonPane.setPadding(new Insets(10));
        buttonPane.getChildren().addAll(addButton, updateButton, deleteButton);



        Button navigateT = new Button("teachers");
        navigateT.setOnAction(e -> navigateToAnotherPage("teacher"));
        Button navigateS = new Button("schedule");
        navigateS.setOnAction(e -> navigateToAnotherPage("schedule"));

        HBox nav = new HBox(10);
        nav.setPadding(new Insets(10));
        nav.getChildren().addAll(navigateT, navigateS);

        // Set up root layout
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(nav, formPane, buttonPane, disciplineTable);
        return root;
    }


    private VBox createSchedulePage() {

        TableColumn<Schedule, Integer> idColumn = new TableColumn<>("id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));


        TableColumn<Schedule, Teacher> teacherTableColumn = new TableColumn<>("teacher");
        teacherTableColumn.setCellValueFactory(new PropertyValueFactory<>("teacher"));

        TableColumn<Schedule, Discipline> disciplineTableColumn = new TableColumn<>("discipline");
        disciplineTableColumn.setCellValueFactory(new PropertyValueFactory<>("discipline"));

        TableColumn<Schedule, TimeEn> timeEnTableColumnTableColumn = new TableColumn<>("time");
        timeEnTableColumnTableColumn.setCellValueFactory(new PropertyValueFactory<>("time"));


        if(scheduleTableView.getColumns().isEmpty()){
            scheduleTableView.getColumns().addAll(idColumn, teacherTableColumn, disciplineTableColumn, timeEnTableColumnTableColumn);
        }
        scheduleTableView.setItems(FXCollections.observableList(scheduleDao.fetchAll()));

        teacherListView.setItems(FXCollections.observableList(teacherDao.fetchAll()));
        disciplineListView.setItems(FXCollections.observableList(disciplineDao.fetchAll()));
        timeEnComboBox.setItems(FXCollections.observableList(timeDao.fetchAll()));

        HBox v = new HBox(teacherListView, disciplineListView, timeEnComboBox);

        Button navigateT = new Button("teachers");
        navigateT.setOnAction(e -> navigateToAnotherPage("teacher"));
        Button navigateD = new Button("discipline");
        navigateD.setOnAction(e -> navigateToAnotherPage("discipline"));

        HBox nav = new HBox(10);
        nav.setPadding(new Insets(10));
        nav.getChildren().addAll(navigateT, navigateD);


        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addSchedule());

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteSchedule());

        HBox buttons = new HBox(10, addButton, deleteButton);


        // Set up root layout
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(nav, v, buttons, scheduleTableView);
        return root;
    }

    private void deleteSchedule() {
        Schedule selected = scheduleTableView.getSelectionModel().getSelectedItem();
        if(selected!=null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete disciplibe");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete the selected discipline?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                scheduleDao.delete(selected.getId());
                scheduleTableView.setItems(FXCollections.observableArrayList(scheduleDao.fetchAll()));

            }
        }
    }

    private void addSchedule() {
       if(teacherListView.getSelectionModel().getSelectedItem()!=null &&
               disciplineListView.getSelectionModel().getSelectedItem()!=null &&
               timeEnComboBox.getSelectionModel().getSelectedItem()!=null){


           ScheduleDB s = new ScheduleDB();
           s.setTeacherId(teacherListView.getValue().getId());
           s.setDisciplineId(disciplineListView.getValue().getId());
           s.setTimeId(timeEnComboBox.getValue().getId());

            try {
                scheduleDao.insert(s);
                scheduleTableView.setItems(FXCollections.observableArrayList(scheduleDao.fetchAll()));

            } catch (RuntimeException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("error");
                alert.setContentText(e.getMessage());
                alert.show();
            }

       } else{
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setHeaderText("error");
           alert.setContentText("choose all fields!!");
           alert.show();
       }

    }

    private void deleteDiscipline() {
        Discipline selected = disciplineTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete disciplibe");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete the selected discipline?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                disciplineDao.delete(selected.getId());
                disciplineTable.setItems(FXCollections.observableArrayList(disciplineDao.fetchAll()));

            }
        }
    }

    private void updateDiscipline() {
        Discipline selected = disciplineTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Discipline d = new Discipline();
            d.setName(disciplineNameField.getText());
            d.setId(selected.getId());
            disciplineDao.update(d);
            disciplineTable.setItems(FXCollections.observableArrayList(disciplineDao.fetchAll()));
            clearField();
        }
    }

    private void addDiscipline() {
        String name = disciplineNameField.getText();
        Discipline d = new Discipline(name);
        disciplineDao.insert(d);
        disciplineTable.setItems(FXCollections.observableList(disciplineDao.fetchAll()));
        clearField();
    }


    private void addTeacher() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String middleName = middleNameField.getText();

        Teacher teacher = new Teacher(firstName, lastName, middleName);

        teacherDao.insert(teacher);
        teacherTable.setItems(FXCollections.observableArrayList(teacherDao.fetchAll()));

        clearFields();
    }

    private void updateTeacher() {
        Teacher selectedTeacher = teacherTable.getSelectionModel().getSelectedItem();
        if (selectedTeacher != null) {
            Teacher t = new Teacher();
            t.setFirstName(firstNameField.getText());
            t.setLastName(lastNameField.getText());
            t.setMiddleName(middleNameField.getText());
            t.setId(selectedTeacher.getId());
            teacherDao.update(t);
            teacherTable.setItems(FXCollections.observableArrayList(teacherDao.fetchAll()));
            clearFields();
        }
    }

    private void deleteTeacher() {
        Teacher selectedTeacher = teacherTable.getSelectionModel().getSelectedItem();
        if (selectedTeacher != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Teacher");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete the selected teacher?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                teacherDao.delete(selectedTeacher.getId());
                teacherTable.setItems(FXCollections.observableArrayList(teacherDao.fetchAll()));

            }
        }
    }

    private void clearFields() {
        firstNameField.clear();
        lastNameField.clear();
        middleNameField.clear();
    }
    private void clearField(){
        disciplineNameField.clear();
    }
}