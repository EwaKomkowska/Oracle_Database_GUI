package com.put.poznan.SchemaObjects;

import javafx.scene.control.Alert;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;

@Entity
public class Sekretarka {
    private long idprac;
    private Time godzrozpoczeciapracy;
    private Time godzzakonczeniapracy;
    protected String imie;
    protected String nazwisko;
    protected String kwalifikacje;
    protected Long placa;
    private Alert alert;


    public Sekretarka () {
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
        if (placa > 0)
            this.placa = placa;
        else throw new IllegalArgumentException("Płaca musi byc dodatnia");
    }

    @Basic
    @Column(name = "GODZROZPOCZECIAPRACY")
    public Time getGodzrozpoczeciapracy() {
        return godzrozpoczeciapracy;
    }

    public void setGodzrozpoczeciapracy(Time godzrozpoczeciapracy) {
        if(godzrozpoczeciapracy == null){
            this.godzrozpoczeciapracy = null;
        }
        else if (this.godzzakonczeniapracy == null || this.godzzakonczeniapracy.after(godzrozpoczeciapracy))
            this.godzrozpoczeciapracy = godzrozpoczeciapracy;
        else {
            alert.setContentText("Godz rozpoczęcia musi być wcześniejsza od zakończenia pracy!");
            alert.showAndWait();
            throw new IllegalArgumentException();
        }
    }

    @Basic
    @Column(name = "GODZZAKONCZENIAPRACY")
    public Time getGodzzakonczeniapracy() {
        return godzzakonczeniapracy;
    }

    public void setGodzzakonczeniapracy(Time godzzakonczeniapracy) {
        if(godzzakonczeniapracy == null){
            this.godzzakonczeniapracy = null;
        }
        else if (this.godzrozpoczeciapracy == null || godzzakonczeniapracy.after(this.godzrozpoczeciapracy))
            this.godzzakonczeniapracy = godzzakonczeniapracy;
        else {
            alert.setContentText("Godz rozpoczęcia musi być wcześniejsza od zakończenia pracy!");
            alert.showAndWait();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sekretarka that = (Sekretarka) o;

        if (idprac != that.idprac) return false;
        if (godzrozpoczeciapracy != null ? !godzrozpoczeciapracy.equals(that.godzrozpoczeciapracy) : that.godzrozpoczeciapracy != null)
            return false;
        if (godzzakonczeniapracy != null ? !godzzakonczeniapracy.equals(that.godzzakonczeniapracy) : that.godzzakonczeniapracy != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idprac ^ (idprac >>> 32));
        result = 31 * result + (godzrozpoczeciapracy != null ? godzrozpoczeciapracy.hashCode() : 0);
        result = 31 * result + (godzzakonczeniapracy != null ? godzzakonczeniapracy.hashCode() : 0);
        return result;
    }
}
