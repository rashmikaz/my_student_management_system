package org.example.controller;


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
import org.example.dto.TobepaidTDto;
import org.example.dto.tm.PaymentCartTm;
import org.example.dto.tm.TobepaidTm;
import org.example.model.TobepaidModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TobepaidController {

    @FXML
    private AnchorPane root;
    @FXML
    private TableView<TobepaidTm> tblItem;
    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableColumn<?, ?> colstudentid;

    @FXML
    private TableColumn<?, ?> colstudentname;


    @FXML
    private TextField txtName;

    @FXML
    private TextField txtStudentId;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtUnitPrice;

    private TobepaidModel tobepaidModel = new TobepaidModel();

    public void initialize() {
        setCellValueFactory();
        loadAllItems();
        tableListener();
    }

    private void tableListener() {
        tblItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
//            System.out.println(newValue);
            setData(newValue);
        });
    }

    private void setData(TobepaidTm row) {
        txtCode.setText(row.getCode());
        txtDescription.setText(row.getDescription());
        txtUnitPrice.setText(String.valueOf(row.getTotalcost()));
        txtQtyOnHand.setText(String.valueOf(row.getNeed()));
        txtStudentId.setText(row.getId());
        txtName.setText(row.getName());
    }

    private void setCellValueFactory() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("totalcost"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("need"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
        colstudentid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colstudentname.setCellValueFactory(new PropertyValueFactory<>("name"));

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String code = txtCode.getText();
        String description = txtDescription.getText();
        double totalcost = Double.parseDouble(txtUnitPrice.getText());
        int need = Integer.parseInt(txtQtyOnHand.getText());
        String id = txtStudentId.getText();
        String name = txtName.getText();

        var dto = new TobepaidTDto(code, description, totalcost, need,id,name);

//        var model = new ItemModel();
        try {
            boolean isSaved = tobepaidModel.savePaid(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "item saved!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadAllItems() {
//        var model = new ItemModel();
        ObservableList<TobepaidTm> obList = FXCollections.observableArrayList();
        try {
            List<TobepaidTDto> dtoList = tobepaidModel.loadAllPaid();

            for (TobepaidTDto dto : dtoList) {
                obList.add(new TobepaidTm(
                        dto.getCode(),
                        dto.getDescription(),
                        (double) dto.getNeed(),
                        (int) dto.getTotalcost(),
                        new Button("Delete"),
                        dto.getId(),
                        dto.getName()
                ));
            }
            tblItem.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String code = txtCode.getText();

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String code = txtCode.getText();
        String description = txtDescription.getText();
        double totalcost = Double.parseDouble(txtUnitPrice.getText());
        int need = Integer.parseInt(txtQtyOnHand.getText());
        String id = txtStudentId.getText();
        String name = txtName.getText();


        try {
            boolean isUpdated = tobepaidModel.updatePaid((List<PaymentCartTm>) new TobepaidTDto(code, description, totalcost, need,id,name));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "item updated").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void codeSearchOnAction(ActionEvent event) {
        String code = txtCode.getText();

        try {
            TobepaidTDto dto = tobepaidModel.searchPaid(code);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "item not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void clearFields() {
        txtCode.setText("");
        txtDescription.setText("");
        txtUnitPrice.setText("");
        txtQtyOnHand.setText("");
    }

    private void setFields(TobepaidTDto dto) {
        txtCode.setText(dto.getCode());
        txtDescription.setText(dto.getDescription());
        txtUnitPrice.setText(String.valueOf(dto.getTotalcost()));
        txtQtyOnHand.setText(String.valueOf(dto.getNeed()));
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
