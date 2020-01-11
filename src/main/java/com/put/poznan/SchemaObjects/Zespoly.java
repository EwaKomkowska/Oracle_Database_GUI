package com.put.poznan.SchemaObjects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Zespoly {
    private long idZesp;
    private String nazwa;
    private String adres;
    private Long liczbaPrac;

    @Id
    @Column(name = "ID_ZESP")
    public long getIdZesp() {
        return idZesp;
    }

    public void setIdZesp(long idZesp) {
        this.idZesp = idZesp;
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
    @Column(name = "ADRES")
    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    @Basic
    @Column(name = "LICZBA_PRAC")
    public Long getLiczbaPrac() {
        return liczbaPrac;
    }

    public void setLiczbaPrac(Long liczbaPrac) {
        this.liczbaPrac = liczbaPrac;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Zespoly zespoly = (Zespoly) o;

        if (idZesp != zespoly.idZesp) return false;
        if (nazwa != null ? !nazwa.equals(zespoly.nazwa) : zespoly.nazwa != null) return false;
        if (adres != null ? !adres.equals(zespoly.adres) : zespoly.adres != null) return false;
        if (liczbaPrac != null ? !liczbaPrac.equals(zespoly.liczbaPrac) : zespoly.liczbaPrac != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idZesp ^ (idZesp >>> 32));
        result = 31 * result + (nazwa != null ? nazwa.hashCode() : 0);
        result = 31 * result + (adres != null ? adres.hashCode() : 0);
        result = 31 * result + (liczbaPrac != null ? liczbaPrac.hashCode() : 0);
        return result;
    }
}
