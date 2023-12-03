package org.example.controller;

import com.jfoenix.controls.JFXPasswordField;
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
import org.example.dto.Logindto;
import org.example.model.Loginmodel;

import java.io.IOException;
import java.sql.SQLException;

public class Login_formcontroller {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextArea txtUsername;

    @FXML
    private JFXPasswordField txtpassword;




    @FXML
    void btnCreateAccountOnAction(MouseEvent event) throws IOException {


        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/CreateAccount.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Create Account");
        stage.centerOnScreen();
    }

    @FXML
    void btnsignOnAction(ActionEvent event) throws IOException {

        String userName = txtUsername.getText();
        String password = txtpassword.getText();
        if ( userName.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill in all the fields.").show();
            return;
        }



        Loginmodel loginModel = new Loginmodel();

        try {
            Logindto loginDto = loginModel.searchUser(userName,password);

            if(loginDto != null){
                clearFields();
                AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));
                Scene scene = new Scene(anchorPane);

                Stage stage = (Stage) root.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Student Management System");
                stage.centerOnScreen();
                stage.setFullScreen(true);

            }else{
                clearFields();
                new Alert(Alert.AlertType.INFORMATION,"Username or Password incorrect").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }



    }

    private void clearFields() {
        txtUsername.setText("");
        txtpassword.setText("");
    }


}
