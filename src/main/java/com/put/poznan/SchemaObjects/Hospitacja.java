package com.put.poznan.SchemaObjects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Hospitacja {
    private long idhospitacji;
    private Date termin;
    private Long ktonadzorowany;
    private Long ktonadzoruje;

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
    public Date getTermin() {
        return termin;
    }

    public void setTermin(Date termin) {
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
    public Long getKtonadzoruje() {
        return ktonadzoruje;
    }

    public void setKtonadzoruje(Long ktonadzoruje) {
       //TODO: nie mozna nadzorowac samego siebie!!!
        /* if (ktonadzoruje != this.getKtonadzorowany())
            this.ktonadzoruje = ktonadzoruje;
        else throw new IllegalArgumentException("Nie mozna nadzorowac samego siebie");*/
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
        if (ktonadzoruje != null ? !ktonadzoruje.equals(that.ktonadzoruje) : that.ktonadzoruje != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idhospitacji ^ (idhospitacji >>> 32));
        result = 31 * result + (termin != null ? termin.hashCode() : 0);
        result = 31 * result + (ktonadzorowany != null ? ktonadzorowany.hashCode() : 0);
        result = 31 * result + (ktonadzoruje != null ? ktonadzoruje.hashCode() : 0);
        return result;
    }
}
