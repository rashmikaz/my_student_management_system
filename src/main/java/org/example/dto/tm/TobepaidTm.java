package org.example.dto.tm;


import javafx.scene.control.Button;

public class TobepaidTm {
    private String code;
    private String description;
    private double totalcost;
    private int need;
    private Button btn;

    private String id ;
    private String name;

    public TobepaidTm() {
    }

    public TobepaidTm(String code, String description, double totalcost, int need, Button btn, String id, String name) {
        this.code = code;
        this.description = description;
        this.totalcost = totalcost;
        this.need = need;
        this.btn = btn;
        this.id = id;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(double totalcost) {
        this.totalcost = totalcost;
    }

    public int getNeed() {
        return need;
    }

    public void setNeed(int need) {
        this.need = need;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TobepaidTm{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", totalcost=" + totalcost +
                ", need=" + need +
                ", btn=" + btn +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
