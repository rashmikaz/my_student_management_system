
package org.example.controller;



import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import org.example.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class DashboardFormController {

    private Object year;
    private Object month;
    private Object DATE;

        public PieChart payPieChart;
        @FXML
        private Stage stage;

        @FXML
        private AnchorPane root;

        @FXML
        private Label lblTeachersCount;


        @FXML
        private Label lblStudentcount;


        @FXML
        private Label lblCourseTotal;

        @FXML
        private Label lblDate;


        @FXML
        private Label lblUsername;

    @FXML
    private Label lblTime;



        private StudentModel stumodel = new StudentModel();
        private Teachermodel teachmodel=new Teachermodel();

        private TobepaidModel tobepaidModel = new TobepaidModel();


        private Coursemodel coursemodel=new Coursemodel();
        private PaymentDetailModel paymentDetailModel = new PaymentDetailModel();

    private void updateTime() {
        LocalTime now = LocalTime.now();
        String formattedTime = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        lblTime.setText(formattedTime);
    }
    private void setDateandTime() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> updateTime()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        DateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
        Calendar cal = Calendar.getInstance();

        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        DATE = cal.get(Calendar.DATE);
        lblDate.setText(year + "-" + month + "-" + DATE);
    }



        public void initialize(){
            setDateandTime();
            setCountPieChart();



            int studentCount = 0;
            try {
                studentCount = stumodel.getStudentCount();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            int teachersCount = 0;
            try {
                teachersCount = teachmodel.getTeachersCount();

            }catch (SQLException e){
                throw new RuntimeException(e);
            }
            int courseCount = 0;
            try {
                courseCount = coursemodel.getCourseCount();

            }catch (SQLException e){
                throw new RuntimeException(e);
            }

            int needCount = 0;
            try {
                needCount = tobepaidModel.countNeed();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            int reCount = 0;
            try {
                reCount = paymentDetailModel.countRs();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


            lblStudentcount.setText(String.valueOf(studentCount));
            lblTeachersCount.setText(String.valueOf(teachersCount));
            lblCourseTotal.setText(String.valueOf(courseCount));
        }


        private void setCountPieChart() {
            try {
                int needCount = tobepaidModel.countNeed();
                int rsCount = paymentDetailModel.countRs();

                ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                        new PieChart.Data("Recieved Money" , rsCount),
                        new PieChart.Data("Paid Money" , needCount)
                );

                payPieChart.setData(pieChartData);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }




        @FXML
        void btnCustomerOnAction(MouseEvent event) throws IOException {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/StudentForm.fxml"));
            Scene scene = new Scene(anchorPane);

            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Student Manage");
            stage.centerOnScreen();
            stage.setFullScreen(true);

        }


        @FXML
        void btnPaymentOnAction(MouseEvent event) throws IOException {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Paymentstatus.fxml"));
            Scene scene = new Scene(anchorPane);

            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Payment");
            stage.centerOnScreen();
            stage.setFullScreen(true);

        }


        @FXML
        void btnTeacherOnAction(MouseEvent event) throws IOException {

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/TeachersManage.fxml"));
            Scene scene = new Scene(anchorPane);

            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Teachers Manage");
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

        public void btnstudenttableOnAction(MouseEvent mouseEvent) throws IOException {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Studenttable.fxml"));
            Scene scene = new Scene(anchorPane);

            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("All Student");
            stage.centerOnScreen();
            stage.setFullScreen(true);


        }

        @FXML
        void btnResultOnAction(MouseEvent event) throws IOException {


            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Result.fxml"));
            Scene scene = new Scene(anchorPane);

            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Result");
            stage.centerOnScreen();
            stage.setFullScreen(true);


        }
        @FXML
        public void btnLogoutOnAction(MouseEvent mouseEvent) throws IOException {

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Login_form.fxml"));
            Scene scene = new Scene(anchorPane);

            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Login ");
            stage.centerOnScreen();

        }

        @FXML
        void addcoursesOnAction(MouseEvent event) throws IOException {

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Course.fxml"));
            Scene scene = new Scene(anchorPane);

            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Program Manage");
            stage.centerOnScreen();
            stage.setFullScreen(true);

        }

        @FXML
        void addmodulesOnAction(MouseEvent event) throws IOException {

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Module.fxml"));
            Scene scene = new Scene(anchorPane);

            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Manage Module");
            stage.centerOnScreen();
            stage.setFullScreen(true);

        }

        @FXML
        void btnScheduleOnAction(MouseEvent event) throws IOException {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Schedule.fxml"));
            Scene scene = new Scene(anchorPane);

            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Manage Schedule");
            stage.centerOnScreen();
            stage.setFullScreen(true);

        }

        @FXML
        void btnteacherstableOnAction(MouseEvent event) throws IOException {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Teacherstable.fxml"));
            Scene scene = new Scene(anchorPane);

            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("All Teachers ");
            stage.centerOnScreen();
            stage.setFullScreen(true);


        }
        @FXML
        void btnAttOnAction(MouseEvent event) throws IOException {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/AttendancesStatus.fxml"));
            Scene scene = new Scene(anchorPane);

            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Attendance ");
            stage.centerOnScreen();
            stage.setFullScreen(true);



        }
        @FXML
        void btnFinancialOnAction(MouseEvent event) throws IOException {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Status.fxml"));
            Scene scene = new Scene(anchorPane);

            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Finance Status ");
            stage.centerOnScreen();
            stage.setFullScreen(true);
        }

        @FXML
        void btnGuidOnAction(MouseEvent event) throws IOException {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Guid.fxml"));
            Scene scene = new Scene(anchorPane);

            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Guid ");
            stage.centerOnScreen();
            stage.setFullScreen(true);

        }

    }
