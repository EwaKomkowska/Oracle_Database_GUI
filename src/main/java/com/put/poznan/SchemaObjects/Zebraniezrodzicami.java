package com.put.poznan.SchemaObjects;

import javax.persistence.*;
import java.sql.Time;

@Entity
@IdClass(ZebraniezrodzicamiPK.class)
public class Zebraniezrodzicami {
    private long idzebrania;
    private Time data;
    private Long grupa;
    private Long miejsca;
    private Long prowadzacyzebranie;
    private String czyobowiazkowe;
    private long przedszkolankaIdhospitacji;

    @Id
    @Column(name = "IDZEBRANIA")
    public long getIdzebrania() {
        return idzebrania;
    }

    public void setIdzebrania(long idzebrania) {
        this.idzebrania = idzebrania;
    }

    @Basic
    @Column(name = "DATA")
    public Time getData() {
        return data;
    }

    public void setData(Time data) {
        this.data = data;
    }

    @Basic
    @Column(name = "GRUPA")
    public Long getGrupa() {
        return grupa;
    }

    public void setGrupa(Long grupa) {
        this.grupa = grupa;
    }

    @Basic
    @Column(name = "Miejsca")
    public Long getMiejsca() {
        return miejsca;
    }

    public void setMiejsca(Long miejscaSala) {
        if (miejscaSala == null) {
            this.miejsca = miejscaSala;
        } else if (miejscaSala > 0) {
            this.miejsca = miejscaSala;
        } else {
            throw new IllegalArgumentException("Sala musi być większa od 0");
        }
    }

    @Basic
    @Column(name = "PROWADZACYZEBRANIE")
    public Long getProwadzacyzebranie() {
        return prowadzacyzebranie;
    }

    public void setProwadzacyzebranie(Long prowadzacyzebranie) {
        this.prowadzacyzebranie = prowadzacyzebranie;
    }

    @Basic
    @Column(name = "CZYOBOWIAZKOWE")
    public String getCzyobowiazkowe() {
        return czyobowiazkowe;
    }

    public void setCzyobowiazkowe(String czyobowiazkowe) {
        this.czyobowiazkowe = czyobowiazkowe;
    }

    @Id
    @Column(name = "PRZEDSZKOLANKA_IDHOSPITACJI")
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

        Zebraniezrodzicami that = (Zebraniezrodzicami) o;

        if (idzebrania != that.idzebrania) return false;
        if (przedszkolankaIdhospitacji != that.przedszkolankaIdhospitacji) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        if (grupa != null ? !grupa.equals(that.grupa) : that.grupa != null) return false;
        if (miejsca != null ? !miejsca.equals(that.miejsca) : that.miejsca != null) return false;
        if (prowadzacyzebranie != null ? !prowadzacyzebranie.equals(that.prowadzacyzebranie) : that.prowadzacyzebranie != null)
            return false;
        if (czyobowiazkowe != null ? !czyobowiazkowe.equals(that.czyobowiazkowe) : that.czyobowiazkowe != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idzebrania ^ (idzebrania >>> 32));
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (grupa != null ? grupa.hashCode() : 0);
        result = 31 * result + (miejsca != null ? miejsca.hashCode() : 0);
        result = 31 * result + (prowadzacyzebranie != null ? prowadzacyzebranie.hashCode() : 0);
        result = 31 * result + (czyobowiazkowe != null ? czyobowiazkowe.hashCode() : 0);
        result = 31 * result + (int) (przedszkolankaIdhospitacji ^ (przedszkolankaIdhospitacji >>> 32));
        return result;
    }
}
