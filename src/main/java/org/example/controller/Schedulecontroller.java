package org.example.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.dto.Coursedto;
import org.example.dto.Moduledto;
import org.example.dto.Scheduledto;
import org.example.dto.Teacherdto;
import org.example.dto.tm.Scheduletm;
import org.example.model.Coursemodel;
import org.example.model.Modulemodel;
import org.example.model.Schedulemodel;
import org.example.model.Teachermodel;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Schedulecontroller {


    @FXML
    private AnchorPane root;

    @FXML
    private ComboBox<String> cmbProgram;

    @FXML
    private ComboBox<String> cmbModule;



    @FXML
    private ComboBox<String> cmbTeacher;



    @FXML
    private TableView<Scheduletm> tblschedule;


    @FXML
    private TableColumn<?, ?> colDay;

    @FXML
    private TableColumn<?, ?> colEnd;

    @FXML
    private TableColumn<?, ?> colModule;

    @FXML
    private TableColumn<?, ?> colProgram;

    @FXML
    private TableColumn<?, ?> colStart;

    @FXML
    private TableColumn<?, ?> colTeacher;

  
    @FXML
    private JFXTextField txtDay;

    @FXML
    private JFXTextField txtEnd;


    @FXML
    private JFXTextField txtStart;


    private Schedulemodel scheduleModel = new Schedulemodel();

    private Modulemodel modulemodel = new Modulemodel();

    private Teachermodel teachermodel = new Teachermodel();

    private Coursemodel coursemodel = new Coursemodel();


    public void initialize() {
        setCellValueFactory();
        loadModule();
        loadTeacher();
        loadAllSchedule();
        loadProgram();
    }

    private void setCellValueFactory() {
        colProgram.setCellValueFactory(new PropertyValueFactory<>("program"));
        colModule.setCellValueFactory(new PropertyValueFactory<>("module"));
        colTeacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        colDay.setCellValueFactory(new PropertyValueFactory<>("day"));
        colStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        colEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
    }
    @FXML
    void btnAddOnAction(ActionEvent event) {

        String program = cmbProgram.getValue();
        String module = cmbModule.getValue();
        String teacher = cmbTeacher.getValue();
        String day = txtDay.getText();
        String start = txtStart.getText();
        String end = txtEnd.getText();


        var dto = new Scheduledto(program, module, teacher, day, start , end);

        try {
            boolean isSaved = scheduleModel.saveSchedule(dto);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }



}

    private void clearFields() {
        cmbProgram.setValue("");
        cmbModule.setValue("");
        cmbTeacher.setValue("");
        txtDay.setText("");
        txtStart.setText("");
        txtEnd.setText("");
    }

    private void loadProgram() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<Coursedto> idList = coursemodel.getAllCourse();

            for (Coursedto dto : idList) {
                obList.add(dto.getCourseName());
            }

            cmbProgram.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadModule() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<Moduledto> idList = modulemodel.getAllModules();

            for ( Moduledto dto : idList) {
                obList.add(dto.getModulename());
            }

            cmbModule.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadTeacher() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<Teacherdto> idList = teachermodel.getAllTeacher();

            for (Teacherdto dto  : idList) {
                obList.add(dto.getTeachername());
            }

            cmbTeacher.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
  

    private void loadAllSchedule() {
        var model = new Schedulemodel();

        ObservableList<Scheduletm> obList = FXCollections.observableArrayList();

        try {
            List<Scheduledto> dtoList = model.getAllSchedule();

            for (Scheduledto dto : dtoList) {
                obList.add(
                        new Scheduletm(
                                dto.getProgram(),
                                dto.getModule(),
                                dto.getTeacher(),
                                dto.getDay(),
                                dto.getStart(),
                                dto.getEnd()
                        )
                );
            }

            tblschedule.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    void btnDeleteOnAction(ActionEvent event) {

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
