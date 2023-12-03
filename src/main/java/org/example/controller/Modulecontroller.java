package org.example.controller;

import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.dto.Moduledto;
import org.example.dto.StudentDto;
import org.example.dto.tm.Moduletm;
import org.example.dto.tm.StudentTm;
import org.example.model.Modulemodel;
import org.example.model.StudentModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Modulecontroller {


    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextArea txtModuleId;

    @FXML
    private JFXTextArea txtModuleName;

    @FXML
    private JFXTextArea txtDays;


    @FXML
    private TableColumn<?, ?> colModuleDays;

    @FXML
    private TableColumn<?, ?> colModuleName;

    @FXML
    private TableColumn<?, ?> colModuleid;

    @FXML
    private TableView<Moduletm> tblModule;

    private Modulemodel moduModel = new Modulemodel();

    public void initialize() {
        setCellValueFactory();
        loadAllModule();
    }

    private void setCellValueFactory() {
        colModuleid.setCellValueFactory(new PropertyValueFactory<>("moduleid"));
        colModuleName.setCellValueFactory(new PropertyValueFactory<>("modulename"));
        colModuleDays.setCellValueFactory(new PropertyValueFactory<>("description"));

    }

    private void loadAllModule() {
        var model = new Modulemodel();

        ObservableList<Moduletm> obList = FXCollections.observableArrayList();

        try {
            List<Moduledto> dtoList = model.getAllofModules();

            for (Moduledto dto : dtoList) {
                obList.add(
                        new Moduletm(
                                dto.getModuleid(),
                                dto.getModulename(),
                                dto.getDescription()


                        )
                );
            }

            tblModule.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


}

    @FXML
    void btnAddOnAction(ActionEvent event) {


        String moduleid = txtModuleId.getText();
        String modulename = txtModuleName.getText();
        String description = txtDays.getText();

        var dto = new Moduledto(moduleid, modulename,description );

        try {
            boolean isSaved = moduModel.saveModule(dto);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Module Add!").show();
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
}
