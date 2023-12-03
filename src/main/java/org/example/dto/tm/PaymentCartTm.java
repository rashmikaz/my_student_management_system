package org.example.dto.tm;


import javafx.scene.control.Button;

public class PaymentCartTm {
    private String code;
    private String description;
    private int need;
    private double totalcost;
    private double tot;
    private Button btn;

    public PaymentCartTm() {
    }

    public PaymentCartTm(String code, String description, int need, double totalcost, double tot, Button btn) {
        this.code = code;
        this.description = description;
        this.need = need;
        this.totalcost = totalcost;
        this.tot = tot;
        this.btn = btn;
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

    public int getNeed() {
        return need;
    }

    public void setNeed(int need) {
        this.need = need;
    }

    public double getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(double totalcost) {
        this.totalcost = totalcost;
    }

    public double getTot() {
        return tot;
    }

    public void setTot(double tot) {
        this.tot = tot;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "CartTm{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", need=" + need +
                ", totalcost=" + totalcost +
                ", tot=" + tot +
                ", btn=" + btn +
                '}';
    }
}
