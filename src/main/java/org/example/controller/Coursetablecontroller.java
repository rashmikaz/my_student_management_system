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
import org.example.dto.Coursedto;
import org.example.dto.Teacherdto;
import org.example.dto.tm.Coursetm;
import org.example.dto.tm.Teachertm;
import org.example.model.Coursemodel;
import org.example.model.Teachermodel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Coursetablecontroller {


    @FXML
    private TableColumn<?, ?> colCost;

    @FXML
    private TableColumn<?, ?> colDes;

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colQualification;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<Coursetm> tblCourse;

    public void initialize() {
        setCellValueFactory();
        loadAllCourse();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("courseid"));
        colName.setCellValueFactory(new PropertyValueFactory<>(" coursename"));
        colDes.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQualification.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("qualification"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

    }

    private void loadAllCourse() {
        var model = new Coursemodel();

        ObservableList<Coursetm> obList = FXCollections.observableArrayList();

        try {
            List<Coursedto> dtoList = model.getAllCourse();

            for (Coursedto dto : dtoList) {
                obList.add(
                        new Coursetm(
                                dto.getCourseId(),
                                dto.getCourseName(),
                                dto.getDescription(),
                                dto.getDuration(),
                                dto.getQualification(),
                                dto.getCost()



                        )
                );
            }

            tblCourse.setItems(obList);
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
