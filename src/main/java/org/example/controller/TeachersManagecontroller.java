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
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.example.db.DbConnection;
import org.example.dto.Teacherdto;
import org.example.model.Teachermodel;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public class TeachersManagecontroller {




    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextArea txtTeacherId;

    @FXML
    private JFXTextArea txtTeacherName;

    @FXML
    private JFXTextArea txtModuleId;

    @FXML
    private JFXTextArea txtModuleName;

    @FXML
    private JFXTextArea txtEmailAddress;

    @FXML
    private JFXTextArea txtPhoneNumber;

    @FXML
    private JFXTextArea txtNicNumber;


    @FXML
    private JFXTextArea txtQualification;


    private Teachermodel teachModel = new Teachermodel();


    @FXML
    void btnAddOnAction(ActionEvent event) {
        String teacherid = txtTeacherId.getText();
        String teachername = txtTeacherName.getText();
        String moduleid = txtModuleId.getText();
        String modulename = txtModuleName.getText();
        String emailaddress = txtEmailAddress.getText();
        String phonenumber = txtPhoneNumber.getText();
        String nicnumber = txtNicNumber.getText();
        String qualification = txtQualification.getText();

        var dto = new Teacherdto(teacherid, teachername, moduleid, modulename, emailaddress, phonenumber, nicnumber, qualification);
        System.out.println(dto.toString());
        try {
            boolean isSaved = teachModel.saveTeacher(dto);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student Add!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void clearFields() {
        txtTeacherId.setText("");
        txtTeacherName.setText("");
        txtModuleId.setText("");
        txtModuleName.setText("");
        txtEmailAddress.setText("");
        txtPhoneNumber.setText("");
        txtNicNumber.setText("");
        txtQualification.setText("");

    }




    public void btnstudenttableOnAction(MouseEvent mouseEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Teacherstable.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("");
        stage.centerOnScreen();

    }




    @FXML
    void btnReportOnAction(MouseEvent event) throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/report/Teachers Report.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);

        JasperPrint jasperPrint =
                JasperFillManager.fillReport(
                        jasperReport, //compiled report
                        null,
                        DbConnection.getInstance().getConnection() //database connection
                );

        JasperViewer.viewReport(jasperPrint, false);

    }

    public void btnDashboardOnAction(MouseEvent mouseEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard");
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

    public void btnStudentOnAction(MouseEvent mouseEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/StudentForm.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Student Manage");
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

}
