package org.example.controller;


import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.example.db.DbConnection;
import org.example.dto.StudentDto;
import org.example.dto.TobepaidTDto;
import org.example.dto.PlacePaymentDto;
import org.example.dto.tm.PaymentCartTm;
import org.example.model.StudentModel;
import org.example.model.TobepaidModel;
import org.example.model.newPaymentmodel;
import org.example.model.PlacePaymentModel;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlacePaymentFormController {

    @FXML
    private AnchorPane root;


    @FXML
    private JFXComboBox<String> cmbCustomerId;

    @FXML
    private JFXComboBox<String> cmbItemCode;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private Label lblNetTotal;




    @FXML
    private TableView<PaymentCartTm> tblOrderCart;

    @FXML
    private TextField txtQty;


    private StudentModel studentModel = new StudentModel();
    private TobepaidModel tobepaidModel = new TobepaidModel();
    private newPaymentmodel newPaymentmodel = new newPaymentmodel();
    private PlacePaymentModel placePaymentModel = new PlacePaymentModel();
    private ObservableList<PaymentCartTm> obList = FXCollections.observableArrayList();

    public void initialize() {
        setCellValueFactory();
        generateNextPaymentId();
        setDate();
        loadStudentIds();
        loadCodes();
    }

    private void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("need"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("totalcost"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("tot"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void generateNextPaymentId() {
        try {
            String paymentId = newPaymentmodel.generateNextPaymentId();
            lblOrderId.setText(paymentId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadCodes() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<TobepaidTDto> TobepaidTDtos = tobepaidModel.loadAllPaid();

            for (TobepaidTDto dto : TobepaidTDtos) {
                obList.add(dto.getCode());
            }
            cmbItemCode.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadStudentIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<StudentDto> idList = studentModel.getAllStudent();

            for (StudentDto dto : idList) {
                obList.add(dto.getId());
            }

            cmbCustomerId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setDate() {

        lblOrderDate.setText(String.valueOf(LocalDate.now()));
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String code = cmbItemCode.getValue();
        String description = lblDescription.getText();
        int need = Integer.parseInt(txtQty.getText());
        double totalcost = Double.parseDouble(lblUnitPrice.getText());
        double tot = totalcost - need;
        Button btn = new Button("Remove");

        setRemoveBtnAction(btn);
        btn.setCursor(Cursor.HAND);


        if (!obList.isEmpty()) {
            for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
                if (colItemCode.getCellData(i).equals(code)) {
                    int col_qty = (int) colQty.getCellData(i);
                    need += col_qty;
                    tot = need * need;

                    obList.get(i).setNeed(need);
                    obList.get(i).setTot(tot);

                    calculateTotal();
                    tblOrderCart.refresh();
                    return;
                }
            }
        }
        var cartTm = new PaymentCartTm(code, description, need, totalcost, tot, btn);

        obList.add(cartTm);

        tblOrderCart.setItems(obList);
        calculateTotal();
        txtQty.clear();
    }

    private void setRemoveBtnAction(Button btn) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                int focusedIndex = tblOrderCart.getSelectionModel().getSelectedIndex();

                obList.remove(focusedIndex);
                tblOrderCart.refresh();
                calculateTotal();
            }
        });
    }

    private void calculateTotal() {
        double total = 0;
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            total += (double) colTotal.getCellData(i);
        }
        lblNetTotal.setText(String.valueOf(total));
    }




    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        String orderId = lblOrderId.getText();
        LocalDate date = LocalDate.parse(lblOrderDate.getText());
        String customerId = cmbCustomerId.getValue();

        List<PaymentCartTm> paymentCartTmList = new ArrayList<>();
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            PaymentCartTm paymentCartTm = obList.get(i);

            paymentCartTmList.add(paymentCartTm);
        }

        System.out.println("Place order form controller: " + paymentCartTmList);
        var placeOrderDto = new PlacePaymentDto(orderId, date, customerId, paymentCartTmList);
        try {
            boolean isSuccess = placePaymentModel.placeOrder(placeOrderDto);
            if (isSuccess) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order Success!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbItemOnAction(ActionEvent event) {
        String code = cmbItemCode.getValue();

        txtQty.requestFocus();
        try {
            TobepaidTDto dto = tobepaidModel.searchPaid(code);
            lblDescription.setText(dto.getDescription());
            lblUnitPrice.setText(String.valueOf(dto.getTotalcost()));
            lblQtyOnHand.setText(String.valueOf(dto.getNeed()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbCustomerOnAction(ActionEvent event) {
        String id = cmbCustomerId.getValue();

        try {
            StudentDto studentDto = studentModel.searchStudent(id);
            lblCustomerName.setText(studentDto.getName());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {
        btnAddToCartOnAction(event);
    }




    @FXML
    void btnPaymentreportOnAction(ActionEvent event) throws JRException, SQLException {

        InputStream resourceAsStream = getClass().getResourceAsStream("/report/Due Money Report.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);

        JasperPrint jasperPrint =
                JasperFillManager.fillReport(
                        jasperReport,
                        null,
                        DbConnection.getInstance().getConnection()
                );

        JasperViewer.viewReport(jasperPrint, false);

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

