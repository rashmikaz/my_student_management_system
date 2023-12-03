package org.example.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.dto.*;
import org.example.dto.tm.DatabaseImplementationattendancecontrollertm;
import org.example.dto.tm.Teachertm;
import org.example.model.DatabaseImplementationattendancecontrollermodel;
import org.example.model.Modulemodel;
import org.example.model.StudentModel;
import org.example.model.Teachermodel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DatabaseImplementationattendancecontroller {

    @FXML
    private AnchorPane root;



    @FXML
    private ComboBox<String> cmbStudentId;

    @FXML
    private Label lblStudentName;

    @FXML
    private JFXTextField txtdate;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableView<DatabaseImplementationattendancecontrollertm> tblattendance;


    private Modulemodel modulemodel = new Modulemodel();

    private StudentModel studentmodel = new StudentModel();

    public void initialize() {

        loadStudent ();
        loadAllAttendance();
    }

    private void setCellValueFactory() {
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentid"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));


    }

    private void loadAllAttendance() {
        var model = new DatabaseImplementationattendancecontrollermodel();

        ObservableList<DatabaseImplementationattendancecontrollertm> obList = FXCollections.observableArrayList();

        try {
            List<DatabaseImplementationattendancecontrollerdto> dtoList = model.getAllattendance();

            for (DatabaseImplementationattendancecontrollerdto dto : dtoList) {
                obList.add(
                        new DatabaseImplementationattendancecontrollertm(
                                dto.getStudentid(),
                                dto.getName(),
                                dto.getDate(),
                                dto.getType()



                        )
                );
            }

            tblattendance.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadStudent (){
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<StudentDto> itemDtos = studentmodel.getStudent();

            for (StudentDto dto : itemDtos) {
                obList.add(dto.getId());
            }
            cmbStudentId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnAbsentOnAction(ActionEvent event) {
        String studentid = cmbStudentId.getValue();
        String name = lblStudentName.getText();
        String date = txtdate.getText();

        String type = "Absent";

        if ( studentid.isEmpty() || name.isEmpty() || date.isEmpty() ) {
            new Alert(Alert.AlertType.ERROR,"Some Fields are Empty!!!").show();
            return;
        }

        DatabaseImplementationattendancecontrollerdto dto = new DatabaseImplementationattendancecontrollerdto(studentid,name,date,type);

        try {
            boolean isAdd = DatabaseImplementationattendancecontrollermodel.AddAttendant(dto);
            if (isAdd) {
                new Alert(Alert.AlertType.INFORMATION,"Add Success!!!").show();
                clearFields();
                
            } else {
                new Alert(Alert.AlertType.ERROR, "Add Failed!!!").showAndWait();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }



}

    private void clearFields() {
    }


    @FXML
    void btnPresentOnAction(ActionEvent event) {
        String studentid = cmbStudentId.getValue();
        String name = lblStudentName.getText();
        String date = txtdate.getText();

        String type = "present";

        if (studentid.isEmpty() || name.isEmpty() || date.isEmpty()  ) {
            new Alert(Alert.AlertType.ERROR,"Some Fields are Empty!!!").show();
            return;
        }

        DatabaseImplementationattendancecontrollerdto dto = new DatabaseImplementationattendancecontrollerdto(studentid,name,date,type);

        try {
            boolean isAdd = DatabaseImplementationattendancecontrollermodel.AddAttendant(dto);
            if (isAdd) {
                new Alert(Alert.AlertType.INFORMATION,"Add Success!!!").show();
                clearFields();

            } else {
                new Alert(Alert.AlertType.ERROR, "Add Failed!!!").showAndWait();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }


    @FXML
    void cmbStudentOnAction(ActionEvent event) {
        String id = cmbStudentId.getValue();
//        CustomerModel customerModel = new CustomerModel();
        try {
            StudentDto StudentDto = StudentModel.searchStudent(id);
            lblStudentName.setText(StudentDto.getName());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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
        stage.setFullScreen(true);


    }



}
