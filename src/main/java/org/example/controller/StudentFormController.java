package org.example.controller;

import com.jfoenix.controls.JFXTextArea;
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
import org.example.dto.StudentDto;
import org.example.model.StudentModel;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class StudentFormController {
    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextArea txtAddress;

    @FXML
    private JFXTextArea txtBirthday;

    @FXML
    private JFXTextArea txtEmail;

    @FXML
    private JFXTextArea txtId;

    @FXML
    private JFXTextArea txtName;

    @FXML
    private JFXTextArea txtNic;

    @FXML
    private JFXTextArea txtTel;


    private StudentModel stuModel = new StudentModel();




    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (validateStudent()) {
            String id = txtId.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            String tel = txtTel.getText();
            String birth = txtBirthday.getText();
            String email = txtEmail.getText();
            String nic = txtNic.getText();

            var dto = new StudentDto(id, name, address, tel, birth, email, nic);

            try {
                boolean isSaved = stuModel.saveStudent(dto);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Student  saved!").show();
                    clearFields();
                    String studentData = "ID: " + dto.getId() + "Name: " + dto.getName();

                    EmailSender mail = new EmailSender();
                    mail.setMsg("Congratz" + txtName.getText() + "you are successfully added to the Student Management System of our Institute.");
                    mail.setTo(dto.getEmail());
                    mail.setSubject("Subject");

                    Thread thread = new Thread(mail);
                    thread.start();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    public boolean validateStudent() {
        String id = txtId.getText();

        boolean isIdValidated = Pattern.matches("[S][0-9]{3,}", id);
        if (!isIdValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid Student Id!").show();
            return false;
        }
        String name = txtName.getText();

        boolean isNameValidated = Pattern.matches("[A-Z][a-zA-Z\\s]+", name);
        if (!isNameValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid Student Name!").show();
            return false;
        }
        String address = txtAddress.getText();

        boolean isAddressValidated = Pattern.matches("[A-Za-z0-9/,.]+", address);
        if (!isAddressValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid Address!").show();
            return false;
        }

        String tel = txtTel.getText();

        boolean istelValidated = Pattern.matches("[0]\\d{9}", tel);
        if (!istelValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid Phone number!").show();
            return false;
        }

        String email = txtEmail.getText();

        boolean isemailValidated = Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email);
        if (!isemailValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid Email!").show();
            return false;
        }
        String nic = txtNic.getText();

        boolean isnicValidated = Pattern.matches("^\\d{10}$", nic);
        if (!isnicValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid Nic!").show();
            return false;
        }
        return true;

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();


        try {
            boolean isDeleted = stuModel.deleteStudent(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student deleted!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Student not deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String tel = txtTel.getText();
        String birth=txtBirthday.getText();
        String email=txtEmail.getText();
        String nic=txtNic.getText();

        var dto = new StudentDto(id, name, address, tel,birth,email,birth);


        try {
            boolean isUpdated = stuModel.updateStudent(dto);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student updated!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String id = txtId.getText();


        try {
            StudentDto studentDto = stuModel.searchStudent(id);

            if (studentDto != null) {
                txtId.setText(studentDto.getId());
                txtName.setText(studentDto.getName());
                txtAddress.setText(studentDto.getAddress());
                txtTel.setText(studentDto.getTel());
                txtBirthday.setText(studentDto.getBirth());
                txtEmail.setText(studentDto.getEmail());
                txtNic.setText(studentDto.getNic());

            } else {
                new Alert(Alert.AlertType.INFORMATION, "Student not found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtTel.setText("");
        txtBirthday.setText("");
        txtEmail.setText("");
        txtNic.setText("");


    }

    @FXML
    void btnReportOnAction(ActionEvent event) throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/report/New Student Form.jrxml");
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
    void btnstudenttableOnAction(MouseEvent event) throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Studenttable.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("All Student");
        stage.centerOnScreen();
        stage.setFullScreen(true);


    }

    @FXML
    void btnregistrationOnAction(MouseEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Registration.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Registration");
        stage.centerOnScreen();
        stage.setFullScreen(true);



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
