package org.example.controller;

import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.dto.Registrationdto;
import org.example.model.Registrationmodel;

import java.io.IOException;
import java.sql.SQLException;

public class Registrationcontroller {

    @FXML
    private AnchorPane root;


    @FXML
    private JFXTextArea txtCourseId;

    @FXML
    private JFXTextArea txtDate;

    @FXML
    private JFXTextArea txtFees;

    @FXML
    private JFXTextArea txtRegistrationId;

    @FXML
    private JFXTextArea txtStudentId;

    @FXML
    private JFXTextArea txtCourseName;

    @FXML
    private JFXTextArea txtName;


    private Registrationmodel regModel = new Registrationmodel();

    @FXML
    void btnAdd1OnAction(ActionEvent event) {

        String courseid=txtCourseId.getText();
        String date=txtDate.getText();
        String fees=txtFees.getText();
        String registrationid=txtRegistrationId.getText();
        String studentid=txtStudentId.getText();
        String coursename=txtCourseName.getText();
        String studentname=txtName.getText();


        var dto = new Registrationdto(registrationid,studentid,date, fees,courseid,coursename,studentname);

        try {
            boolean isSaved = regModel.saveRegistration(dto);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student Add!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void clearFields() {
    }


    public void btnstudenttableOnAction(MouseEvent mouseEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Studenttable.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Student Table");
        stage.centerOnScreen();
        stage.setFullScreen(true);

    }

    public void btnregistrationOnAction(MouseEvent mouseEvent) {
    }
    public void btnDashboardOnAction(MouseEvent mouseEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard");
        stage.centerOnScreen();
        stage.setFullScreen(true);



    }

    public void btnCustomerOnAction(MouseEvent mouseEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/StudentForm.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Student Manage");
        stage.centerOnScreen();
        stage.setFullScreen(true);

    }

    public void btnTeacherOnAction(MouseEvent mouseEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/TeachersManage.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Teachers Manage");
        stage.centerOnScreen();
        stage.setFullScreen(true);

    }

    public void btnPaymentOnAction(MouseEvent mouseEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Paymentstatus.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Payment");
        stage.centerOnScreen();
        stage.setFullScreen(true);
    }

    public void btnResultOnAction(MouseEvent mouseEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Result.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Result");
        stage.centerOnScreen();
        stage.setFullScreen(true);
    }

    public void btnLogoutOnAction(MouseEvent mouseEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Login_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Login");
        stage.centerOnScreen();
        stage.setFullScreen(true);

    }

    public void cmbCourseIdOnAction(ActionEvent actionEvent) {
    }
}
