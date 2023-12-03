package org.example.dto.tm;

public class Scheduletm {
    private String program ;
    private String module ;
    private String teacher ;
    private String day;
    private String start;
    private String end;

    public Scheduletm() {
    }

    public Scheduletm(String program, String module, String teacher, String day, String start, String end) {
        this.program = program;
        this.module = module;
        this.teacher = teacher;
        this.day = day;
        this.start = start;
        this.end = end;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Scheduletm{" +
                "program='" + program + '\'' +
                ", module='" + module + '\'' +
                ", teacher='" + teacher + '\'' +
                ", day='" + day + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                '}';
    }
}
