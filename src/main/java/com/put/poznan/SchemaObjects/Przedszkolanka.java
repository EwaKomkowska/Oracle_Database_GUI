package com.put.poznan.SchemaObjects;

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
