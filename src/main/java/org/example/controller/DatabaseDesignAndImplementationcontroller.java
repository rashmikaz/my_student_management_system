package org.example.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import org.example.dto.Resultdto;
import org.example.dto.StudentDto;
import org.example.model.Resultmodel;
import org.example.model.StudentModel;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public class DatabaseDesignAndImplementationcontroller {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtMarks;

    @FXML
    private JFXTextField txtModuleId;

    @FXML
    private JFXTextField txtPorF;

    @FXML
    private JFXTextField txtStudentId;

    @FXML
    private JFXTextField txtStudentName;

    private Resultmodel resultmodel = new Resultmodel();

    @FXML
    void btnReportOnAction(ActionEvent event) throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/report/Result Sheet.jrxml");
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


    @FXML
    void btnAddOnAction(ActionEvent event) {


        String moduleid = txtModuleId.getText();
        String studentid = txtStudentId.getText();
        String name=txtStudentName.getText();
        String marks = txtMarks.getText();
        String porf = txtPorF.getText();


        var dto = new Resultdto(moduleid, studentid, name, marks,porf);

        try {
            boolean isSaved = resultmodel.addResult(dto);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Result saved!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void clearFields() {

    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void btnstudenttableOnAction(MouseEvent event) {

    }
    @FXML
    void btnDashboardOnAction(MouseEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard");
        stage.centerOnScreen();
        stage.setFullScreen(true);


    }
    @FXML
    void btnCustomerOnAction(MouseEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Studenttable.fxml"));
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
    void btnResultOnAction(MouseEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Module.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Results");
        stage.centerOnScreen();
        stage.setFullScreen(true);

    }
    @FXML
    void btnLogoutOnAction(MouseEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Login_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Login");
        stage.centerOnScreen();



    }




}
