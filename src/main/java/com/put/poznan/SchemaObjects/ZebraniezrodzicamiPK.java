package com.put.poznan.SchemaObjects;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class ZebraniezrodzicamiPK implements Serializable {
    private long idzebrania;
    private long przedszkolankaIdhospitacji;

    @Column(name = "IDZEBRANIA")
    @Id
    public long getIdzebrania() {
        return idzebrania;
    }

    public void setIdzebrania(long idzebrania) {
        this.idzebrania = idzebrania;
    }

    @Column(name = "PRZEDSZKOLANKA_IDHOSPITACJI")
    @Id
    public long getPrzedszkolankaIdhospitacji() {
        return przedszkolankaIdhospitacji;
    }

    public void setPrzedszkolankaIdhospitacji(long przedszkolankaIdhospitacji) {
        this.przedszkolankaIdhospitacji = przedszkolankaIdhospitacji;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ZebraniezrodzicamiPK that = (ZebraniezrodzicamiPK) o;

        if (idzebrania != that.idzebrania) return false;
        if (przedszkolankaIdhospitacji != that.przedszkolankaIdhospitacji) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idzebrania ^ (idzebrania >>> 32));
        result = 31 * result + (int) (przedszkolankaIdhospitacji ^ (przedszkolankaIdhospitacji >>> 32));
        return result;
    }
}
