package com.put.poznan.SchemaObjects;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class PomocdydaktycznaPK implements Serializable {
    private long idpomocy;
    private long przedszkolankaIdprac;
    private long grupaprzedszkolnaIdgrupy;

    @Column(name = "IDPOMOCY")
    @Id
    public long getIdpomocy() {
        return idpomocy;
    }

    public void setIdpomocy(long idpomocy) {
        this.idpomocy = idpomocy;
    }

    @Column(name = "PRZEDSZKOLANKA_IDPRAC")
    @Id
    public long getPrzedszkolankaIdprac() {
        return przedszkolankaIdprac;
    }

    public void setPrzedszkolankaIdprac(long przedszkolankaIdprac) {
        this.przedszkolankaIdprac = przedszkolankaIdprac;
    }

    @Column(name = "GRUPAPRZEDSZKOLNA_IDGRUPY")
    @Id
    public long getGrupaprzedszkolnaIdgrupy() {
        return grupaprzedszkolnaIdgrupy;
    }

    public void setGrupaprzedszkolnaIdgrupy(long grupaprzedszkolnaIdgrupy) {
        this.grupaprzedszkolnaIdgrupy = grupaprzedszkolnaIdgrupy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PomocdydaktycznaPK that = (PomocdydaktycznaPK) o;

        if (idpomocy != that.idpomocy) return false;
        if (przedszkolankaIdprac != that.przedszkolankaIdprac) return false;
        if (grupaprzedszkolnaIdgrupy != that.grupaprzedszkolnaIdgrupy) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idpomocy ^ (idpomocy >>> 32));
        result = 31 * result + (int) (przedszkolankaIdprac ^ (przedszkolankaIdprac >>> 32));
        result = 31 * result + (int) (grupaprzedszkolnaIdgrupy ^ (grupaprzedszkolnaIdgrupy >>> 32));
        return result;
    }
}
