package com.put.poznan.SchemaObjects;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@IdClass(DzieckoPK.class)
public class Dziecko {
    private long iddziecka;
    private String imie;
    private String nazwisko;
    private Date dataurodzenia;
    private long grupaprzedszkolnaIdgrupy;
    private long posilekIdposilku;

    @Id
    @Column(name = "IDDZIECKA")
    public long getIddziecka() {
        return iddziecka;
    }

    public void setIddziecka(long iddziecka) {
        this.iddziecka = iddziecka;
    }

    @Basic
    @Column(name = "IMIE")
    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    @Basic
    @Column(name = "NAZWISKO")
    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Basic
    @Column(name = "DATAURODZENIA")
    public Date getDataurodzenia() {
        return dataurodzenia;
    }

    public void setDataurodzenia(Date dataurodzenia) {
        this.dataurodzenia = dataurodzenia;
    }

    @Id
    @Column(name = "GRUPAPRZEDSZKOLNA_IDGRUPY")
    public long getGrupaprzedszkolnaIdgrupy() {
        return grupaprzedszkolnaIdgrupy;
    }

    public void setGrupaprzedszkolnaIdgrupy(long grupaprzedszkolnaIdgrupy) {
        this.grupaprzedszkolnaIdgrupy = grupaprzedszkolnaIdgrupy;
    }

    @Id
    @Column(name = "POSILEK_IDPOSILKU")
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

        Dziecko dziecko = (Dziecko) o;

        if (iddziecka != dziecko.iddziecka) return false;
        if (grupaprzedszkolnaIdgrupy != dziecko.grupaprzedszkolnaIdgrupy) return false;
        if (posilekIdposilku != dziecko.posilekIdposilku) return false;
        if (!Objects.equals(imie, dziecko.imie)) return false;
        if (!Objects.equals(nazwisko, dziecko.nazwisko)) return false;
        if (!Objects.equals(dataurodzenia, dziecko.dataurodzenia))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (iddziecka ^ (iddziecka >>> 32));
        result = 31 * result + (imie != null ? imie.hashCode() : 0);
        result = 31 * result + (nazwisko != null ? nazwisko.hashCode() : 0);
        result = 31 * result + (dataurodzenia != null ? dataurodzenia.hashCode() : 0);
        result = 31 * result + (int) (grupaprzedszkolnaIdgrupy ^ (grupaprzedszkolnaIdgrupy >>> 32));
        result = 31 * result + (int) (posilekIdposilku ^ (posilekIdposilku >>> 32));
        return result;
    }
}
