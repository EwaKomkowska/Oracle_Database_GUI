package com.put.poznan.SchemaObjects;

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
        this.imie = imie;
    }

    @Basic
    @Column(name = "NAZWISKO")
    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Basic
    @Column(name = "KWALIFIKACJE")
    public String getKwalifikacje() {
        return kwalifikacje;
    }

    public void setKwalifikacje(String kwalifikacje) {
        this.kwalifikacje = kwalifikacje;
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
        this.godzrozpoczeciapracy = godzrozpoczeciapracy;
    }

    @Basic
    @Column(name = "GODZZAKONCZENIAPRACY")
    public Time getGodzzakonczeniapracy() {
        return godzzakonczeniapracy;
    }

    public void setGodzzakonczeniapracy(Time godzzakonczeniapracy) {
        this.godzzakonczeniapracy = godzzakonczeniapracy;
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
