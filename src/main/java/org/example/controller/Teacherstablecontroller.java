package org.example.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.dto.StudentDto;
import org.example.dto.Teacherdto;
import org.example.dto.tm.StudentTm;
import org.example.dto.tm.Teachertm;
import org.example.model.StudentModel;
import org.example.model.Teachermodel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class Teacherstablecontroller {

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colModuleid;

    @FXML
    private TableColumn<?, ?> colModulename;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colNic;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private TableColumn<?, ?> colQualification;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<Teachertm> tblTeacher;

    public void initialize() {
        setCellValueFactory();
        loadAllTeacher();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("teacherid"));
        colName.setCellValueFactory(new PropertyValueFactory<>("teachername"));
        colModuleid.setCellValueFactory(new PropertyValueFactory<>("moduleid"));
        colModulename.setCellValueFactory(new PropertyValueFactory<>("modulename"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("emailaddress"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nicnumber"));
        colQualification.setCellValueFactory(new PropertyValueFactory<>("qualification"));

    }

    private void loadAllTeacher() {
        var model = new Teachermodel();

        ObservableList<Teachertm> obList = FXCollections.observableArrayList();

        try {
            List<Teacherdto> dtoList = model.getAllTeacher();

            for (Teacherdto dto : dtoList) {
                obList.add(
                        new Teachertm(
                                dto.getTeacherid(),
                                dto.getTeachername(),
                                dto.getModuleid(),
                                dto.getModulename(),
                                dto.getEmailaddress(),
                                dto.getPhonenumber(),
                                dto.getNicnumber(),
                                dto.getQualification()


                        )
                );
            }

            tblTeacher.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    public void btnLogoutOnAction(MouseEvent mouseEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Login_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Login");
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
}
