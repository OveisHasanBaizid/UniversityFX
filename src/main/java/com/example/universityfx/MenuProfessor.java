package com.example.universityfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MenuProfessor {
    Professor professor;
    @FXML
    TableColumn<Object, Object> column_number;
    @FXML
    TableColumn<Course, String> column_name;
    @FXML
    TableColumn<Course, Integer> column_credits;
    @FXML
    TableColumn<Course, Integer> column_NStudent;
    @FXML
    TableView<Course> table;
    @FXML
    TextField textField_Name, textField_Rank, textField_BirthDate, textField_Department;
    @FXML
    Button btnExit, btnListCourse, btnListProfessor;

    @FXML
    public void clickBtnListProfessor() {
        Dialog dialog = new Dialog();
        dialog.setHeaderText(null);
        dialog.setResizable(true);
        Label label1 = new Label("List of professors of the department : ");
        TableView<Professor> table = new TableView<>();
        showListAllProfessor(table);
        GridPane grid = new GridPane();
        grid.add(label1, 1, 1);
        grid.add(table, 1, 2);
        dialog.getDialogPane().setContent(grid);
        ButtonType buttonTypeCANCEL = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeCANCEL);
        dialog.showAndWait();
    }

    public void showListAllProfessor(TableView<Professor> table) {
        table.setPrefWidth(300);

        TableColumn<Professor, String> columnName = new TableColumn<>("Name");
        columnName.setPrefWidth(90);
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Professor, String> columnBirthDate = new TableColumn<>("BirthDate");
        columnBirthDate.setPrefWidth(90);
        columnBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

        TableColumn<Professor, String> columnRank = new TableColumn<>("Rank");
        columnRank.setPrefWidth(90);
        columnRank.setCellValueFactory(new PropertyValueFactory<>("rank"));

        TableColumn<Professor, String> columnNumber = new TableColumn<>("#");
        columnNumber.setPrefWidth(30);
        columnNumber.setCellFactory(new NumberTableCellFactory<>(1));

        table.getColumns().addAll(columnNumber, columnName, columnBirthDate, columnRank);
        List<Professor> professors = new ArrayList<>();
        DataBase.professors.forEach(p -> {
            if (p.getDepartment().equals(professor.getDepartment())) professors.add(p);
        });

        ObservableList<Professor> data = FXCollections.observableArrayList(professors);
        table.setItems(data);
    }

    public void initialize() {
        professor = DataBase.professorHolder;

        textField_Name.setText(professor.getName());
        textField_Rank.setText(professor.getRank().toString().toLowerCase());
        textField_Department.setText(professor.getDepartment().getName());
        textField_BirthDate.setText(professor.getBirthDate().toString());

        showTableMyCourse();


    }

    public void showTableMyCourse() {

        column_number.setCellFactory(new NumberTableCellFactory<>(1));

        column_name.setCellValueFactory(new PropertyValueFactory<>("name"));

        column_credits.setCellValueFactory(new PropertyValueFactory<>("credits"));
        column_NStudent.setCellValueFactory(new PropertyValueFactory<>("numberStudent"));

        table.setEditable(false);

        ObservableList<Course> data = FXCollections.observableArrayList(professor.getCourse());

        table.setItems(data);
    }

    @FXML
    public void clickBtnExit() throws IOException {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("PanelLogin.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void clickBtnAdd() {
        if (table.getSelectionModel() != null) {
            Course course = table.getSelectionModel().getSelectedItem();
            System.out.println(course.getName());
        }
        addCourse(showDialog_1_input("Add Course")[0]);
    }

    @FXML
    public void clickBtnListAllCourse() {
        Dialog dialog = new Dialog();
        dialog.setHeaderText(null);
        dialog.setResizable(true);
        Label label1 = new Label("List of courses offered by the faculty: ");
        TableView<Course> table = new TableView<>();
        showListAllCourse(table);
        GridPane grid = new GridPane();
        grid.add(label1, 1, 1);
        grid.add(table, 1, 2);
        dialog.getDialogPane().setContent(grid);
        ButtonType buttonTypeCANCEL = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeCANCEL);
        dialog.showAndWait();
    }

    public void showListAllCourse(TableView<Course> table) {
        table.setPrefWidth(300);

        TableColumn<Course, String> column_name = new TableColumn<>("Name");
        column_name.setPrefWidth(90);
        column_name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Course, String> column_credits = new TableColumn<>("Credits");
        column_credits.setPrefWidth(90);
        column_credits.setCellValueFactory(new PropertyValueFactory<>("credits"));

        TableColumn<Course, String> column_instructor = new TableColumn<>("Instructor");
        column_instructor.setPrefWidth(90);
        column_instructor.setCellValueFactory(new PropertyValueFactory<>("instructor"));

        TableColumn<Course, String> column_number = new TableColumn<>("#");
        column_number.setPrefWidth(30);
        column_number.setCellFactory(new NumberTableCellFactory<>(1));

        table.getColumns().addAll(column_number, column_name, column_credits, column_instructor);

        List<Course> courses = new ArrayList<>();
        DataBase.courses.forEach(c -> {
            if (c.getDepartment().equals(professor.getDepartment())) courses.add(c);
        });
        ObservableList<Course> data = FXCollections.observableArrayList(courses);
        table.setItems(data);
    }

    public void clickItemTable(MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (mouseEvent.getClickCount() == 2 && !table.getSelectionModel().isEmpty()) {
                Course course = table.getSelectionModel().getSelectedItem();
                showDialogStudentsCourse(course);
            }
        }
    }

    public void showDialogStudentsCourse(Course course) {
        Dialog dialog = new Dialog();
        dialog.setHeaderText(null);
        dialog.setResizable(true);
        Label label1 = new Label("List of courses offered by the faculty: ");
        TableView<GradeReport> table = new TableView<>();
        tableListStudentsCourse(table, course);
        table.setOnMouseClicked(mouseEvent -> {
            if (table.getSelectionModel().getSelectedItem()!=null){
                String grade = showDialog_1_input("Grade")[0];
                if (!validGradeInput(grade))
                    showMessage("The number entered for the score is invalid","Error");
                else{
                    GradeReport gradeReport = table.getSelectionModel().getSelectedItem();
                    gradeReport.getStudent().finishCourse(gradeReport.getCourse(),Double.parseDouble(grade));
                    tableListStudentsCourse(table, course);
                    showMessage("The grade was successfully registered","Message");
                }
            }
        });
        GridPane grid = new GridPane();
        grid.add(label1, 1, 1);
        grid.add(table, 1, 2);
        dialog.getDialogPane().setContent(grid);
        ButtonType buttonTypeCANCEL = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeCANCEL);
        dialog.showAndWait();
    }
    public boolean validGradeInput(String s){
        if (s==null)
            return false;
        for (int i = 0; i < s.length() ; i++) {
            if (!(Character.isDigit(s.charAt(i)) || s.charAt(i)=='.'))
                return false;
        }

        double x;
        try {
             x = Double.parseDouble(s);
        }catch (NumberFormatException n){
            return false;
        }
        return !(x < 0) && !(x > 20);
    }
    public void tableListStudentsCourse(TableView<GradeReport> table, Course course) {
        table.setPrefWidth(300);

        TableColumn<GradeReport, Student> columnName = new TableColumn<>("Name");
        columnName.setPrefWidth(90);
        columnName.setCellValueFactory(new PropertyValueFactory<>("student"));
        columnName.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Student student, boolean b) {
                super.updateItem(student, b);
                if (b || student == null)
                    setText("");
                else
                    setText(student.getName());
            }
        });

        TableColumn<GradeReport, Student> columnStudentNumber = new TableColumn<>("StdNumber");
        columnStudentNumber.setPrefWidth(90);
        columnStudentNumber.setCellValueFactory(new PropertyValueFactory<>("Student"));
        columnStudentNumber.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Student student, boolean b) {
                super.updateItem(student, b);
                if (b || student == null)
                    setText("");
                else
                    setText(student.getStudent());
            }
        });

        TableColumn<GradeReport, String> columnGrade = new TableColumn<>("Grade");
        columnGrade.setPrefWidth(90);
        columnGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        columnGrade.setEditable(true);

        TableColumn<GradeReport, String> columnNumber = new TableColumn<>("#");
        columnNumber.setPrefWidth(30);
        columnNumber.setCellFactory(new NumberTableCellFactory<>(1));

        table.getColumns().addAll(columnNumber, columnName, columnStudentNumber, columnGrade);

        List<GradeReport> gradeReport = new ArrayList<>();
        DataBase.gradeReports.forEach(c -> {
            if (c.getCourse().equals(course)) gradeReport.add(c);
        });
        ObservableList<GradeReport> data = FXCollections.observableArrayList(gradeReport);
        table.setItems(data);
    }

    public String[] showDialog_1_input(String m) {
        Dialog dialog = new Dialog();
        final String[] grade = new String[1];
        dialog.setHeaderText(null);
        dialog.setTitle(m);
        dialog.setResizable(true);
        Label label1 = new Label("Name Course: ");
        TextField text1 = new TextField();

        GridPane grid = new GridPane();
        grid.add(label1, 1, 1);
        grid.add(text1, 2, 1);
        dialog.getDialogPane().setContent(grid);

        ButtonType buttonTypeOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCANCEL = new ButtonType("CANCEL", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeCANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == buttonTypeOk) {
                return text1.getText();
            }
            return null;
        });
        Optional result = dialog.showAndWait();
        result.ifPresent(pair -> {
            grade[0] = result.get().toString();
        });
        return grade;
    }

    public void addCourse(String nameCourse) {
        System.out.println(nameCourse);
        Course course = DataBase.getCourse(nameCourse);
        if (course == null)
            showMessage("There is no course with this name.", "Error");
        else if (course.getInstructor() != null) {
            if (course.getInstructor().equals(professor))
                showMessage("This course is offered by you.", "Message");
            else
                showMessage("This course is offered by another professor.", "Message");
        } else {
            course.setInstructor(professor);
            showTableMyCourse();
            showMessage("Course added successfully.", "Message");
        }
    }

    public void showMessage(String message, String type) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.getDialogPane().setPrefSize(300, 100);
        alert.setTitle(type);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
