package com.put.poznan.SchemaObjects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Pracownik {
    protected long idprac;
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
        this.placa = placa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pracownik pracownik = (Pracownik) o;

        if (idprac != pracownik.idprac) return false;
        if (imie != null ? !imie.equals(pracownik.imie) : pracownik.imie != null) return false;
        if (nazwisko != null ? !nazwisko.equals(pracownik.nazwisko) : pracownik.nazwisko != null) return false;
        if (kwalifikacje != null ? !kwalifikacje.equals(pracownik.kwalifikacje) : pracownik.kwalifikacje != null)
            return false;
        if (placa != null ? !placa.equals(pracownik.placa) : pracownik.placa != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idprac ^ (idprac >>> 32));
        result = 31 * result + (imie != null ? imie.hashCode() : 0);
        result = 31 * result + (nazwisko != null ? nazwisko.hashCode() : 0);
        result = 31 * result + (kwalifikacje != null ? kwalifikacje.hashCode() : 0);
        result = 31 * result + (placa != null ? placa.hashCode() : 0);
        return result;
    }
}
