package com.put.poznan.SchemaObjects;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;

@Entity
public class Posilek {
    private long idposilku;
    private String nazwa;
    private Time godzrozwozenia;
    private String dieta;
    private Alert alert;


    public Posilek () {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
    }

    @Id
    @Column(name = "IDPOSILKU")
    public long getIdposilku() {
        return idposilku;
    }

    public void setIdposilku(long idposilku) {
        this.idposilku = idposilku;
    }

    @Basic
    @Column(name = "NAZWA")
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        if (nazwa.length() <= 50)
            this.nazwa = nazwa;
        else {
            alert.setContentText("Nazwa posiłku nie może być dłuższa niż 50 znaków!");
            alert.showAndWait();
            throw new IllegalArgumentException();
        }
    }

    @Basic
    @Column(name = "GODZROZWOZENIA")
    public Time getGodzrozwozenia() {
        return godzrozwozenia;
    }

    public void setGodzrozwozenia(Time godzrozwozenia) {
        this.godzrozwozenia = godzrozwozenia;
    }

    @Basic
    @Column(name = "DIETA")
    public String getDieta() {
        return dieta;
    }

    public void setDieta(String dieta) {
        if (dieta == null)
            this.dieta = dieta;
        else if (dieta.length() <= 20)
            this.dieta = dieta;
        else {
            alert.setContentText("Dieta nie może być dłuższa niż 20 znaków!");
            alert.showAndWait();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Posilek posilek = (Posilek) o;

        if (idposilku != posilek.idposilku) return false;
        if (nazwa != null ? !nazwa.equals(posilek.nazwa) : posilek.nazwa != null) return false;
        if (godzrozwozenia != null ? !godzrozwozenia.equals(posilek.godzrozwozenia) : posilek.godzrozwozenia != null)
            return false;
        if (dieta != null ? !dieta.equals(posilek.dieta) : posilek.dieta != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idposilku ^ (idposilku >>> 32));
        result = 31 * result + (nazwa != null ? nazwa.hashCode() : 0);
        result = 31 * result + (godzrozwozenia != null ? godzrozwozenia.hashCode() : 0);
        result = 31 * result + (dieta != null ? dieta.hashCode() : 0);
        return result;
    }
}
