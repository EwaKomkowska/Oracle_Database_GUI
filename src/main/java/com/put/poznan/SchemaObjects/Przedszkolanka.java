package com.put.poznan.SchemaObjects;

import javafx.scene.control.Alert;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Przedszkolanka {
    private long idprac;
    private Long nazwagrupy;
    protected String imie;
    protected String nazwisko;
    protected String kwalifikacje;
    protected Long placa;
    private  Long hospitacja;
    private Alert alert;


    public Przedszkolanka () {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
    }

    @Id
    @Column(name = "IDPRAC")
    public long getIdprac() {
        return idprac;
    }

    public void setIdprac(long idprac) {
        this.idprac = idprac;
    }

    @Basic
    @Column(name = "IMIE")
    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        if (imie.length() <= 25)
            this.imie = imie;
        else {
            alert.setContentText("Imie nie może być dłuższe niż 25 znaków!");
            alert.showAndWait();
            throw new IllegalArgumentException();
        }
    }

    @Basic
    @Column(name = "NAZWISKO")
    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        if (nazwisko.length() <= 50)
            this.nazwisko = nazwisko;
        else {
            alert.setContentText("Nazwisko nie może być dłuższe niż 50 znaków!");
            alert.showAndWait();
            throw new IllegalArgumentException();
        }
    }

    @Basic
    @Column(name = "KWALIFIKACJE")
    public String getKwalifikacje() {
        return kwalifikacje;
    }

    public void setKwalifikacje(String kwalifikacje) {
        if (kwalifikacje.length() <= 100)
            this.kwalifikacje = kwalifikacje;
        else {
            alert.setContentText("Kwalifikacje nie mogą być dłuższe niż 100 znaków!");
            alert.showAndWait();
            throw new IllegalArgumentException();
        }
    }

    @Basic
    @Column(name = "PLACA")
    public Long getPlaca() {
        return placa;
    }

    public void setPlaca(Long placa) {
        if (placa == null)
            this.placa = placa;
        else if (placa > 0)
            this.placa = placa;
        else throw new IllegalArgumentException("Płaca musi być dodatnia");
    }

    @Basic
    @Column(name = "NAZWAGRUPY")
    public Long getNazwagrupy() {
        return nazwagrupy;
    }

    public void setNazwagrupy(Long nazwagrupy) {
        this.nazwagrupy = nazwagrupy;
    }

    @Basic
    @Column(name = "HOS_IDHOS")
    public Long getHospitacja() {
        return hospitacja;
    }

    public void setHospitacja(Long hospitacja) {
        this.hospitacja = hospitacja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Przedszkolanka that = (Przedszkolanka) o;

        if (idprac != that.idprac) return false;
        if (!Objects.equals(nazwagrupy, that.nazwagrupy)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idprac ^ (idprac >>> 32));
        result = 31 * result + (nazwagrupy != null ? nazwagrupy.hashCode() : 0);        //było duże Long nazwagrupy
        return result;
    }
}
