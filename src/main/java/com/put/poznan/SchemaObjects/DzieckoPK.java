package com.put.poznan.SchemaObjects;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class DzieckoPK implements Serializable {
    private long iddziecka;
    private long grupaprzedszkolnaIdgrupy;
    private long posilekIdposilku;

    @Column(name = "IDDZIECKA")
    @Id
    public long getIddziecka() {
        return iddziecka;
    }

    public void setIddziecka(long iddziecka) {
        this.iddziecka = iddziecka;
    }

    @Column(name = "GRUPAPRZEDSZKOLNA_IDGRUPY")
    @Id
    public long getGrupaprzedszkolnaIdgrupy() {
        return grupaprzedszkolnaIdgrupy;
    }

    public void setGrupaprzedszkolnaIdgrupy(long grupaprzedszkolnaIdgrupy) {
        this.grupaprzedszkolnaIdgrupy = grupaprzedszkolnaIdgrupy;
    }

    @Column(name = "POSILEK_IDPOSILKU")
    @Id
    public long getPosilekIdposilku() {
        return posilekIdposilku;
    }

    public void setPosilekIdposilku(long posilekIdposilku) {
        this.posilekIdposilku = posilekIdposilku;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DzieckoPK dzieckoPK = (DzieckoPK) o;

        if (iddziecka != dzieckoPK.iddziecka) return false;
        if (grupaprzedszkolnaIdgrupy != dzieckoPK.grupaprzedszkolnaIdgrupy) return false;
        if (posilekIdposilku != dzieckoPK.posilekIdposilku) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (iddziecka ^ (iddziecka >>> 32));
        result = 31 * result + (int) (grupaprzedszkolnaIdgrupy ^ (grupaprzedszkolnaIdgrupy >>> 32));
        result = 31 * result + (int) (posilekIdposilku ^ (posilekIdposilku >>> 32));
        return result;
    }
}
