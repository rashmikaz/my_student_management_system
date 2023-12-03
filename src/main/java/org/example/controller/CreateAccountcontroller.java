package org.example.controller;

import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.dto.Logindto;
import org.example.dto.Signupdto;
import org.example.model.Loginmodel;
import org.example.model.Signupmodel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class CreateAccountcontroller {


    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextArea txtEmailAddress;

    @FXML
    private JFXTextArea txtFirstName;

    @FXML
    private JFXTextArea txtLastName;

    @FXML
    private JFXTextArea txtPassword;

    @FXML
    private JFXTextArea txtPhoneNumber;

    @FXML
    private JFXTextArea txtUsername;


    private Signupmodel signUpModel = new Signupmodel();



    @FXML
    void btnCreateAccountOnAction(ActionEvent event) {
        if (validateUser()) {
            String firstName = txtFirstName.getText();
            String lastName = txtLastName.getText();
            String emailAddress = txtEmailAddress.getText();
            String username = txtUsername.getText();
            String phoneNumber = txtPhoneNumber.getText();
            String password = txtPassword.getText();


            var dto = new Signupdto(firstName, lastName, emailAddress, username, phoneNumber, password);

            try {
                boolean isSaved = signUpModel.saveUser(dto);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Successfully Created An Account!").show();
                    clearFields();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        }
    }
    public boolean validateUser(){
        String firstName = txtFirstName.getText();

        boolean isFirstNameValidated = Pattern.matches("[A-Z][a-zA-Z\\s]+", firstName);
        if (!isFirstNameValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid First Name!").show();
            return false;
        }

        String lastName = txtLastName.getText();

        boolean isLastNameValidated = Pattern.matches("[A-Z][a-zA-Z\\s]+", lastName);
        if (!isLastNameValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid First Name!").show();
            return false;
        }

        String position = txtEmailAddress.getText();

        boolean isEmailValidated = Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", position);
        if (!isEmailValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid Email Address!").show();
            return false;
        }

        String userName = txtUsername.getText();

        boolean isUserNameValidated = Pattern.matches("[A-Z][a-zA-Z\\s]+", userName);
        if (!isUserNameValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid UserName!").show();
            return false;
        }

        String password = txtPassword.getText();

        boolean isPasswordValidated = Pattern.matches("[a-zA-Z\\s\\d]+", password);
        if (!isPasswordValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid Password!").show();
            return false;
        }
        String phonenumber = txtPhoneNumber.getText();

        boolean isphonenumberValidated = Pattern.matches("[0]\\d{9}", phonenumber);
        if (!isphonenumberValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid Phone number!").show();
            return false;
        }


        return true;
    }




    public void clearFields() {
        txtFirstName.setText("");
        txtLastName.setText("");
        txtEmailAddress.setText("");
        txtUsername.setText("");
        txtPhoneNumber.setText("");
        txtPassword.setText("");


    }

    @FXML
    void btnsigninOnAction(MouseEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Login_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Login");
        stage.centerOnScreen();
        stage.setFullScreen(true);

    }

}
