package org.example.dto;

public class Registrationdto {
    private String RegistrationId;

    private String StudentId;


    private String Date;


    private String Fees;


    private String CourseId;


    private String CourseName;


    private String Name;

    public Registrationdto() {
    }

    public Registrationdto(String registrationId, String studentId, String date, String fees, String courseId, String courseName, String name) {
        RegistrationId = registrationId;
        StudentId = studentId;
        Date = date;
        Fees = fees;
        CourseId = courseId;
        CourseName = courseName;
        Name = name;
    }

    public String getRegistrationId() {
        return RegistrationId;
    }

    public void setRegistrationId(String registrationId) {
        RegistrationId = registrationId;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getFees() {
        return Fees;
    }

    public void setFees(String fees) {
        Fees = fees;
    }

    public String getCourseId() {
        return CourseId;
    }

    public void setCourseId(String courseId) {
        CourseId = courseId;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "Registrationdto{" +
                "RegistrationId='" + RegistrationId + '\'' +
                ", StudentId='" + StudentId + '\'' +
                ", Date='" + Date + '\'' +
                ", Fees='" + Fees + '\'' +
                ", CourseId='" + CourseId + '\'' +
                ", CourseName='" + CourseName + '\'' +
                ", Name='" + Name + '\'' +
                '}';
    }
}
