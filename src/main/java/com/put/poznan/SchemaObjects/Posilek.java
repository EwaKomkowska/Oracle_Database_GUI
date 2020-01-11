package com.put.poznan.SchemaObjects;

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
        this.nazwa = nazwa;
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
        this.dieta = dieta;
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
