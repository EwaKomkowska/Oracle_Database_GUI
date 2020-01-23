package com.put.poznan.SchemaObjects;

import javafx.scene.control.Alert;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Oplata {
    private long idoplaty;
    private Long wielkosc;
    private String przedmiotoplaty;
    private String czestosc;
    private Long idzajecia;
    private Alert alert;


    public Oplata() {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
    }

    @Id
    @Column(name = "IDOPLATY")
    public long getIdoplaty() {
        return idoplaty;
    }

    public void setIdoplaty(long idoplaty) {
        this.idoplaty = idoplaty;
    }

    @Basic
    @Column(name = "WIELKOSC")
    public Long getWielkosc() {
        return wielkosc;
    }

    public void setWielkosc(Long wielkosc) {
        if (wielkosc == null)
            this.wielkosc = wielkosc;
        else if (wielkosc >= 0)
            this.wielkosc = wielkosc;
        else throw new IllegalArgumentException("Opłata musi być większa od 0");
    }

    @Basic
    @Column(name = "PRZEDMIOTOPLATY")
    public String getPrzedmiotoplaty() {
        return przedmiotoplaty;
    }

    public void setPrzedmiotoplaty(String przedmiotoplaty) {
        if (przedmiotoplaty == null)
            this.przedmiotoplaty = przedmiotoplaty;
        else if (przedmiotoplaty.length() <= 100)
            this.przedmiotoplaty = przedmiotoplaty;
        else {
            alert.setContentText("Przedmiot opłaty nie może mieć więcej niż 100 znaków!");
            alert.showAndWait();
            throw new IllegalArgumentException();
        }
    }

    @Basic
    @Column(name = "CZESTOSC")
    public String getCzestosc() {
        return czestosc;
    }

    public void setCzestosc(String czestosc) {
        if (czestosc == null)
            this.czestosc = czestosc;
        else if (czestosc.length() <= 25)
            this.czestosc = czestosc;
        else {
            alert.setContentText("Częstość nie może być dłuższe niż 25 znaków!");
            alert.showAndWait();
            throw new IllegalArgumentException();
        }
    }


    @Basic
    @Column(name = "ZAJECIADODATKOWE_IDZAJECIA")
    public Long getIdzajecia() {
        return idzajecia;
    }

    public void setIdzajecia(Long idzajecia) {
        this.idzajecia = idzajecia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Oplata oplata = (Oplata) o;

        if (idoplaty != oplata.idoplaty) return false;
        if (wielkosc != null ? !wielkosc.equals(oplata.wielkosc) : oplata.wielkosc != null) return false;
        if (przedmiotoplaty != null ? !przedmiotoplaty.equals(oplata.przedmiotoplaty) : oplata.przedmiotoplaty != null)
            return false;
        if (czestosc != null ? !czestosc.equals(oplata.czestosc) : oplata.czestosc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idoplaty ^ (idoplaty >>> 32));
        result = 31 * result + (wielkosc != null ? wielkosc.hashCode() : 0);
        result = 31 * result + (przedmiotoplaty != null ? przedmiotoplaty.hashCode() : 0);
        result = 31 * result + (czestosc != null ? czestosc.hashCode() : 0);
        return result;
    }
}
