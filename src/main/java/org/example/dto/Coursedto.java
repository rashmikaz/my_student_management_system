package org.example.dto;

public class Coursedto {

    private String CourseId;


    private String CourseName;


    private String Description;


    private String Duration;


    private String Qualification;

    private String cost;

    public Coursedto() {
    }

    public Coursedto(String courseId, String courseName, String description, String duration, String qualification, String cost) {
        CourseId = courseId;
        CourseName = courseName;
        Description = description;
        Duration = duration;
        Qualification = qualification;
        this.cost = cost;
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String qualification) {
        Qualification = qualification;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Coursedto{" +
                "CourseId='" + CourseId + '\'' +
                ", CourseName='" + CourseName + '\'' +
                ", Description='" + Description + '\'' +
                ", Duration='" + Duration + '\'' +
                ", Qualification='" + Qualification + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }
}
