package com.put.poznan.SchemaObjects;

import javax.persistence.*;

@Entity
@IdClass(PomocdydaktycznaPK.class)
public class Pomocdydaktyczna {
    private long idpomocy;
    private String rodzaj;
    private Long dodatkoweoplaty;
    private Long grupadocelowa;
    private Long osobaodpowiedzialna;
    private long przedszkolankaIdprac;
    private long grupaprzedszkolnaIdgrupy;

    @Id
    @Column(name = "IDPOMOCY")
    public long getIdpomocy() {
        return idpomocy;
    }

    public void setIdpomocy(long idpomocy) {
        this.idpomocy = idpomocy;
    }

    @Basic
    @Column(name = "RODZAJ")
    public String getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj = rodzaj;
    }

    @Basic
    @Column(name = "DODATKOWEOPLATY")
    public Long getDodatkoweoplaty() {
        return dodatkoweoplaty;
    }

    public void setDodatkoweoplaty(Long dodatkoweoplaty) {
        this.dodatkoweoplaty = dodatkoweoplaty;
    }

    @Basic
    @Column(name = "GRUPADOCELOWA")
    public Long getGrupadocelowa() {
        return grupadocelowa;
    }

    public void setGrupadocelowa(Long grupadocelowa) {
        this.grupadocelowa = grupadocelowa;
    }

    @Basic
    @Column(name = "OSOBAODPOWIEDZIALNA")
    public Long getOsobaodpowiedzialna() {
        return osobaodpowiedzialna;
    }

    public void setOsobaodpowiedzialna(Long osobaodpowiedzialna) {
        this.osobaodpowiedzialna = osobaodpowiedzialna;
    }

    @Id
    @Column(name = "PRZEDSZKOLANKA_IDPRAC")
    public long getPrzedszkolankaIdprac() {
        return przedszkolankaIdprac;
    }

    public void setPrzedszkolankaIdprac(long przedszkolankaIdprac) {
        this.przedszkolankaIdprac = przedszkolankaIdprac;
    }

    @Id
    @Column(name = "GRUPAPRZEDSZKOLNA_IDGRUPY")
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

        Pomocdydaktyczna that = (Pomocdydaktyczna) o;

        if (idpomocy != that.idpomocy) return false;
        if (przedszkolankaIdprac != that.przedszkolankaIdprac) return false;
        if (grupaprzedszkolnaIdgrupy != that.grupaprzedszkolnaIdgrupy) return false;
        if (rodzaj != null ? !rodzaj.equals(that.rodzaj) : that.rodzaj != null) return false;
        if (dodatkoweoplaty != null ? !dodatkoweoplaty.equals(that.dodatkoweoplaty) : that.dodatkoweoplaty != null)
            return false;
        if (grupadocelowa != null ? !grupadocelowa.equals(that.grupadocelowa) : that.grupadocelowa != null)
            return false;
        if (osobaodpowiedzialna != null ? !osobaodpowiedzialna.equals(that.osobaodpowiedzialna) : that.osobaodpowiedzialna != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idpomocy ^ (idpomocy >>> 32));
        result = 31 * result + (rodzaj != null ? rodzaj.hashCode() : 0);
        result = 31 * result + (dodatkoweoplaty != null ? dodatkoweoplaty.hashCode() : 0);
        result = 31 * result + (grupadocelowa != null ? grupadocelowa.hashCode() : 0);
        result = 31 * result + (osobaodpowiedzialna != null ? osobaodpowiedzialna.hashCode() : 0);
        result = 31 * result + (int) (przedszkolankaIdprac ^ (przedszkolankaIdprac >>> 32));
        result = 31 * result + (int) (grupaprzedszkolnaIdgrupy ^ (grupaprzedszkolnaIdgrupy >>> 32));
        return result;
    }
}
