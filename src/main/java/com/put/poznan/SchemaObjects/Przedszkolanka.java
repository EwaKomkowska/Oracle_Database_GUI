package com.put.poznan.SchemaObjects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Przedszkolanka extends Pracownik{ //FIXME: DODALEM do przedszkolanki i skeretarki ze extends Pracownik czy robi sens????
    private long idprac; //TODO: wywal idprac???? bo protected odziedziczone z pracownika dalem
    private Long nazwagrupy;

    @Id
    @Column(name = "IDPRAC")
    public long getIdprac() {
        return idprac;
    }

    public void setIdprac(long idprac) {
        this.idprac = idprac;
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
        if (nazwagrupy != null ? !nazwagrupy.equals(that.nazwagrupy) : that.nazwagrupy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idprac ^ (idprac >>> 32));
        result = 31 * result + (nazwagrupy != null ? nazwagrupy.hashCode() : 0);
        return result;
    }
}
