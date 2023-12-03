package org.example.dto;

public class DatabaseImplementationattendancecontrollerdto {
   private String studentid;
    private String name ;
    private String date;

    private String type;

    public DatabaseImplementationattendancecontrollerdto() {
    }

    public DatabaseImplementationattendancecontrollerdto(String studentid, String name, String date, String type) {
        this.studentid = studentid;
        this.name = name;
        this.date = date;
        this.type = type;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DatabaseImplementationattendancecontrollerdto{" +
                "studentid='" + studentid + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
