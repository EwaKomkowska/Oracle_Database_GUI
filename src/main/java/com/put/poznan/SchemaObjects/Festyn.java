package com.put.poznan.SchemaObjects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;

@Entity
public class Festyn {
    private long idfestynu;
    private Long grupawystepujaca;
    private Long osobaodpowiedzialna;
    private Time terminwydarzena;
    private String haslo;

    @Id
    @Column(name = "IDFESTYNU")
    public long getIdfestynu() {
        return idfestynu;
    }

    public void setIdfestynu(long idfestynu) {
        this.idfestynu = idfestynu;
    }

    @Basic
    @Column(name = "GRUPAWYSTEPUJACA")
    public Long getGrupawystepujaca() {
        return grupawystepujaca;
    }

    public void setGrupawystepujaca(Long grupawystepujaca) {
        this.grupawystepujaca = grupawystepujaca;
    }

    @Basic
    @Column(name = "OSOBAODPOWIEDZIALNA")
    public Long getOsobaodpowiedzialna() {
        return osobaodpowiedzialna;
    }

    public void setOsobaodpowiedzialna(Long osobaodpowiedzialna) {
        this.osobaodpowiedzialna = osobaodpowiedzialna;
    }

    @Basic
    @Column(name = "TERMINWYDARZENA")
    public Time getTerminwydarzena() {
        return terminwydarzena;
    }

    public void setTerminwydarzena(Time terminwydarzena) {
        this.terminwydarzena = terminwydarzena;
    }

    @Basic
    @Column(name = "HASLO")
    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String nazwaHaslo) {
        this.haslo = nazwaHaslo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Festyn festyn = (Festyn) o;

        if (idfestynu != festyn.idfestynu) return false;
        if (grupawystepujaca != null ? !grupawystepujaca.equals(festyn.grupawystepujaca) : festyn.grupawystepujaca != null)
            return false;
        if (osobaodpowiedzialna != null ? !osobaodpowiedzialna.equals(festyn.osobaodpowiedzialna) : festyn.osobaodpowiedzialna != null)
            return false;
        if (terminwydarzena != null ? !terminwydarzena.equals(festyn.terminwydarzena) : festyn.terminwydarzena != null)
            return false;
        if (haslo != null ? !haslo.equals(festyn.haslo) : festyn.haslo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idfestynu ^ (idfestynu >>> 32));
        result = 31 * result + (grupawystepujaca != null ? grupawystepujaca.hashCode() : 0);
        result = 31 * result + (osobaodpowiedzialna != null ? osobaodpowiedzialna.hashCode() : 0);
        result = 31 * result + (terminwydarzena != null ? terminwydarzena.hashCode() : 0);
        result = 31 * result + (haslo != null ? haslo.hashCode() : 0);
        return result;
    }
}
