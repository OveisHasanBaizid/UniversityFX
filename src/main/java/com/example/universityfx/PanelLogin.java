package com.example.universityfx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class PanelLogin {
    @FXML
    private Button btnExit;

    @FXML
    public void clickBtnProfessor() throws IOException {

        String[] info = showDialog_2_input("Name" , "Department");
        Professor professor = DataBase.getProfessor(info[0]);
        if (professor==null || !professor.getDepartment().equals(DataBase.getDepartment(info[1])))
            showMessage("The information entered is incorrect.");
        else{
            DataBase.professorHolder = professor;
            nextPage("MenuProfessor.fxml");
        }
    }

    @FXML
    public void clickBtnStudent() throws IOException {
        String[] info = showDialog_2_input("Name" , "Student Number");
        Student student = DataBase.getStudent(info[1]);
        if (student==null || student.getName().compareToIgnoreCase(info[0])!=0)
            showMessage("The information entered is incorrect.");
        else{
            DataBase.studentHolder = student;
            nextPage("MenuStudent.fxml");
        }
    }
    @FXML
    public void clickBtnGod() throws IOException {
        nextPage("MenuGod.fxml");
    }
    @FXML
    public void clickBtnDepartmentOfficer() throws IOException {
        String s = showDialog_1_input("Login Department");
        if (s!=null){
            Department department = DataBase.getDepartment(s);
            if (department==null)
                showMessage("The entered department name is incorrect");
            else{
                DataBase.departmentHolder = department;
                nextPage("MenuCoursesDepartment.fxml");
            }
        }
    }
    @FXML
    public void clickBtnExit() {
        System.exit(0);
    }
    public String showDialog_1_input(String m) {
        Dialog dialog = new Dialog();
        String[] input = new String[1];
        dialog.setHeaderText(null);
        dialog.setTitle(m);
        dialog.setResizable(true);
        Label label1 = new Label("Department Name : ");
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
                input[0] = result.get().toString();
            }
        });
        return input[0];
    }
    public static String[] showDialog_2_input(String s1 , String s2) {
        final String[] username_password = new String[2];

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        TextField text1 = new TextField();
        TextField text2 = new TextField();

        Button button = new Button("Submit");

        button.setOnAction(actionEvent -> {
            username_password[0] = text1.getText();
            username_password[1] = text2.getText();
            stage.close();
        });

        Label label2 = new Label(s1+":");
        Label label3 = new Label(s2+":");

        GridPane layout = new GridPane();

        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(5);
        layout.setHgap(5);

        layout.add(text1, 1,1);
        layout.add(text2, 1,2);
        layout.add(button, 1,3);
        layout.add(label2, 0,1);
        layout.add(label3, 0,2);

        Scene scene = new Scene(layout, 300, 130);
        stage.setTitle("Dialog");
        stage.setScene(scene);
        stage.showAndWait();

        return username_password;
    }
    public void showMessage(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.getDialogPane().setPrefSize(300, 100);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void nextPage(String namePage) throws IOException {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(namePage)));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
