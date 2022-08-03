package com.example.universityfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class MenuStudent {
    Student student;
    @FXML
    Button btnExit, btnRemove, btnAdd;
    @FXML
    TextField textField_Name, textField_stdNumber, textField_BirthDate, textField_Department;
    @FXML
    TableColumn column_name , column_number , column_credits , column_professor , column_grade;
    @FXML
    TableView<GradeReport> table;

    private static Object call(Object column) {
        return new TableCell<GradeReport, Course>() {
            @Override
            protected void updateItem(Course course, boolean b) {
                super.updateItem(course, b);
                if (b || course == null)
                    setText("");
                else
                    setText(String.valueOf(course.getCredits()));
            }
        };
    }

    public void initialize() {
        student = DataBase.studentHolder;

        textField_Name.setText(student.getName());
        textField_stdNumber.setText(student.getStudent());
        textField_Department.setText(student.getDepartment().getName());
        textField_BirthDate.setText(student.getBirthDate().toString());
        showTableMyCourse();
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
        showDialog_1_input("Add Course");
    }

    @FXML
    public void clickBtnRemove() {
        showDialog_1_input("Remove Course");
    }

    @FXML
    public void clickBtnListCourse(){
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

        table.getColumns().addAll(column_number,column_name, column_credits, column_instructor);

        ObservableList<Course> data = FXCollections.observableArrayList(DataBase.getAllCoursesDepartment(student.getDepartment()));
        table.setItems(data);
    }

    public void showTableMyCourse() {
        column_grade.setCellValueFactory(new PropertyValueFactory<>("grade"));

        column_professor.setCellValueFactory(new PropertyValueFactory<Course, String>("course"));
        column_professor.setCellFactory(column -> new TableCell<GradeReport, Course>() {
            @Override
            protected void updateItem(Course course, boolean b) {
                super.updateItem(course, b);
                if (b || course == null)
                    setText("");
                else
                    setText(course.getInstructor().getName());
            }
        });
        column_credits.setCellValueFactory(new PropertyValueFactory<Course, Integer>("course"));
        column_credits.setCellFactory(MenuStudent::call);
        column_name.setCellValueFactory(new PropertyValueFactory<Course, String>("course"));
        column_name.setCellFactory(column -> new TableCell<GradeReport, Course>() {
            @Override
            protected void updateItem(Course course, boolean b) {
                super.updateItem(course, b);
                if (b || course == null)
                    setText("");
                else
                    setText(course.getName());
            }
        });
        table.setEditable(false);
        column_number.setCellFactory(new NumberTableCellFactory<>(1));
        ObservableList<GradeReport> data = FXCollections.observableArrayList(student.getGradeReports());

        table.setItems(data);
    }

    public void showDialog_1_input(String m) {
        Dialog dialog = new Dialog();
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
            if (result.isPresent()){
                if (m.equals("Add Course")) {
                    try {
                        addCourse(result.get().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        removeCourse(result.get().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void addCourse(String nameCourse) throws IOException {
        Course course = DataBase.getCourse(nameCourse);
        if (student.getCourse().contains(course))
            showMessage("You have already taken this course.", "Error");
        else {
            student.takeCourse(course);
            DataBase.writeGradeReports();
            showMessage("Course added successfully.", "Message");
            showTableMyCourse();
        }
    }
    public void removeCourse(String nameCourse) throws IOException {
        Course course = DataBase.getCourse(nameCourse);
        if (course==null){
            showMessage("The desired course is not available in list courses", "Error");
        }else {
            GradeReport gradeReport = student.getGradeReport(nameCourse);
            if (gradeReport==null){
                showMessage("The desired course is not available in your list of courses", "Error");
            }else if(gradeReport.getGrade()!=-1){
                showMessage("This lesson has already finished and it is not possible to delete it", "Error");
            }else{
                DataBase.gradeReports.remove(gradeReport);
                DataBase.writeGradeReports();
                showMessage("The desired lesson was successfully deleted.", "Message");
                showTableMyCourse();
            }
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
