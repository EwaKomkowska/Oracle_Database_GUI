package com.put.poznan.SchemaObjects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;
import java.util.Arrays;

@Entity
public class Hospitacja {
    private long idhospitacji;
    private Time termin;
    private Long ktonadzorowany;
    private byte[] ktonadzoruje; //TODO: CZY TO MA BYC TYP BYTE[]

    @Id
    @Column(name = "IDHOSPITACJI")
    public long getIdhospitacji() {
        return idhospitacji;
    }

    public void setIdhospitacji(long idhospitacji) {
        this.idhospitacji = idhospitacji;
    }

    @Basic
    @Column(name = "TERMIN")
    public Time getTermin() {
        return termin;
    }

    public void setTermin(Time termin) {
        this.termin = termin;
    }

    @Basic
    @Column(name = "KTONADZOROWANY")
    public Long getKtonadzorowany() {
        return ktonadzorowany;
    }

    public void setKtonadzorowany(Long ktonadzorowany) {
        this.ktonadzorowany = ktonadzorowany;
    }

    @Basic
    @Column(name = "KTONADZORUJE")
    public byte[] getKtonadzoruje() {
        return ktonadzoruje;
    }

    public void setKtonadzoruje(byte[] ktonadzoruje) {
        this.ktonadzoruje = ktonadzoruje;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hospitacja that = (Hospitacja) o;

        if (idhospitacji != that.idhospitacji) return false;
        if (termin != null ? !termin.equals(that.termin) : that.termin != null) return false;
        if (ktonadzorowany != null ? !ktonadzorowany.equals(that.ktonadzorowany) : that.ktonadzorowany != null)
            return false;
        if (!Arrays.equals(ktonadzoruje, that.ktonadzoruje)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idhospitacji ^ (idhospitacji >>> 32));
        result = 31 * result + (termin != null ? termin.hashCode() : 0);
        result = 31 * result + (ktonadzorowany != null ? ktonadzorowany.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(ktonadzoruje);
        return result;
    }
}
