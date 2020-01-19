package com.put.poznan.SchemaObjects;

import javafx.scene.control.Alert;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Grupaprzedszkolna {
    private long idgrupy;
    private Long sala;
    private String nazwa;
    private Long wiekdzieci;
    private long idprac;

    @Id
    @Column(name = "IDGRUPY")
    public long getIdgrupy() {
        return idgrupy;
    }

    public void setIdgrupy(long idgrupy) {
        this.idgrupy = idgrupy;
    }

    @Basic
    @Column(name = "SALA")
    public Long getSala() {
        return sala;
    }

    public void setSala(Long sala) {
        if (sala == null) {
            this.sala = sala;
        } else if (sala > 0) {
            this.sala = sala;
        } else {
            throw new IllegalArgumentException("Sala musi być większa od 0");
        }
    }

    @Basic
    @Column(name = "NAZWA")
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        if (nazwa.length() <= 25)
            this.nazwa = nazwa;
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nazwa grupy nie może być dłuższa niż 25 znaków!");
            alert.showAndWait();
            throw new IllegalArgumentException();
        }
    }

    @Basic
    @Column(name = "WIEKDZIECI")
    public Long getWiekdzieci() {
        return wiekdzieci;
    }

    public void setWiekdzieci(Long wiekdzieci) {
        if (wiekdzieci == null) {
            this.wiekdzieci = wiekdzieci;
        } else if (wiekdzieci > 0) {
            this.wiekdzieci = wiekdzieci;
        } else {
            throw new IllegalArgumentException("Wiek dzieci musi być większy od 0");
        }
    }

    @Basic
    @Column(name = "IDPRAC")
    public long getIdprac() {
        return idprac;
    }

    public void setIdprac(long idprac) {
        this.idprac = idprac;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grupaprzedszkolna that = (Grupaprzedszkolna) o;

        if (idgrupy != that.idgrupy) return false;
        if (idprac != that.idprac) return false;
        if (sala != null ? !sala.equals(that.sala) : that.sala != null) return false;
        if (nazwa != null ? !nazwa.equals(that.nazwa) : that.nazwa != null) return false;
        if (wiekdzieci != null ? !wiekdzieci.equals(that.wiekdzieci) : that.wiekdzieci != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idgrupy ^ (idgrupy >>> 32));
        result = 31 * result + (sala != null ? sala.hashCode() : 0);
        result = 31 * result + (nazwa != null ? nazwa.hashCode() : 0);
        result = 31 * result + (wiekdzieci != null ? wiekdzieci.hashCode() : 0);
        result = 31 * result + (int) (idprac ^ (idprac >>> 32));
        return result;
    }
}
