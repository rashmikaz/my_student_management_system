package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.dto.Signupdto;
import org.example.dto.tm.Signuptm;
import org.example.model.Signupmodel;

import java.sql.SQLException;
import java.util.List;

public class Usercontroller {

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colFirst;

    @FXML
    private TableColumn<?, ?> colLast;

    @FXML
    private TableColumn<?, ?> colNumber;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private TableColumn<?, ?> coluserNAme;

    @FXML
    private TableView<Signuptm> tblUser;

    public void initialize() {

        loadAllUser();
    }

    private void setCellValueFactory() {
        colFirst.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLast.setCellValueFactory(new PropertyValueFactory<>(" lastName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>(" emailAddress"));
        coluserNAme.setCellValueFactory(new PropertyValueFactory<>("username"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));


    }


    private void loadAllUser() {
        ObservableList<Signuptm> obList = FXCollections.observableArrayList();

        try {
            List<Signupdto> dtoList = Signupmodel.getAllUsers();

            for (Signupdto dto : dtoList) {
                obList.add(new Signuptm(
                        dto.getFirstName(),
                        dto.getLastName(),
                        dto.getEmailAddress(),
                        dto.getUsername(),
                        dto.getPhoneNumber(),
                        dto.getPassword()
                ));
            }
            tblUser.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


}
