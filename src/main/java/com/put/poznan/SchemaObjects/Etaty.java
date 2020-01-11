package com.put.poznan.SchemaObjects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Etaty {
    private String nazwa;
    private Long placaMin;
    private Long placaMax;

    @Id
    @Column(name = "NAZWA")
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Basic
    @Column(name = "PLACA_MIN")
    public Long getPlacaMin() {
        return placaMin;
    }

    public void setPlacaMin(Long placaMin) {
        this.placaMin = placaMin;
    }

    @Basic
    @Column(name = "PLACA_MAX")
    public Long getPlacaMax() {
        return placaMax;
    }

    public void setPlacaMax(Long placaMax) {
        this.placaMax = placaMax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Etaty etaty = (Etaty) o;

        if (nazwa != null ? !nazwa.equals(etaty.nazwa) : etaty.nazwa != null) return false;
        if (placaMin != null ? !placaMin.equals(etaty.placaMin) : etaty.placaMin != null) return false;
        if (placaMax != null ? !placaMax.equals(etaty.placaMax) : etaty.placaMax != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nazwa != null ? nazwa.hashCode() : 0;
        result = 31 * result + (placaMin != null ? placaMin.hashCode() : 0);
        result = 31 * result + (placaMax != null ? placaMax.hashCode() : 0);
        return result;
    }
}
