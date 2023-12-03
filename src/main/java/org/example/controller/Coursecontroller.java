package org.example.controller;

import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.dto.Coursedto;
import org.example.model.Coursemodel;

import java.io.IOException;
import java.sql.SQLException;

public class Coursecontroller {


    @FXML
    private AnchorPane root;



    @FXML
    private JFXTextArea txtCourseId;

    @FXML
    private JFXTextArea txtCourseName;

    @FXML
    private JFXTextArea txtDescription;

    @FXML
    private JFXTextArea txtDuration;

    @FXML
    private JFXTextArea txtQualification;

    @FXML
    private JFXTextArea txtCost;

    private Coursemodel courModel = new Coursemodel();
    @FXML
    void btnAddCourseOnAction(MouseEvent event) {

        String courseid = txtCourseId.getText();
        String coursename = txtCourseName.getText();
        String description = txtDescription.getText();
        String duration = txtDuration.getText();
        String qualification = txtQualification.getText();
        String cost = txtCost.getText();

        var dto = new Coursedto(courseid, coursename,description, duration,qualification,cost );

        try {
            boolean isSaved = courModel.Addforcourse(dto);

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


    public void btnDashboardOnAction(MouseEvent mouseEvent) throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard");
        stage.centerOnScreen();
        stage.setFullScreen(true);

    }



    public void btnDeleteOnAction(ActionEvent actionEvent) throws IOException {

    }
    @FXML
    void btnCoursetableOnAction(MouseEvent event) throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Coursetable.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("All Courses");
        stage.centerOnScreen();
        stage.setFullScreen(true);

    }


    public void btnCustomerOnAction(MouseEvent mouseEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/StudentForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Student Manage");
        stage.centerOnScreen();
        stage.setFullScreen(true);
    }



    @FXML
    void btnTeacherOnAction(MouseEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/TeachersManage.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Teachers Manage");
        stage.centerOnScreen();
        stage.setFullScreen(true);
    }




    @FXML
    void btnPaymentOnAction(MouseEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Paymentstatus.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Payment");
        stage.centerOnScreen();
        stage.setFullScreen(true);




    }





    @FXML
    void btnstudenttableOnAction(MouseEvent event) throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Studenttable.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("All Student");
        stage.centerOnScreen();
        stage.setFullScreen(true);


    }

    @FXML
    void btnregistrationOnAction(MouseEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Registration.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Item Manage");
        stage.centerOnScreen();
        stage.setFullScreen(true);



    }


    public void btnResultOnAction(MouseEvent mouseEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Module.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Results");
        stage.centerOnScreen();
        stage.setFullScreen(true);
    }

    public void btnLogoutOnAction(MouseEvent mouseEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Teacherstable.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login ");
        stage.centerOnScreen();
        stage.setFullScreen(true);

    }
}
