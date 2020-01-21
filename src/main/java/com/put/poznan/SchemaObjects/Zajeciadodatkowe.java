package com.put.poznan.SchemaObjects;

import javafx.scene.control.Alert;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Zajeciadodatkowe {

    private long idzajecia;
    private String rodzaj;
    private Date dataprowadzenia;
    private Long oplaty;
    private Long czastygodniowo;
    private Long dlakogo;

    @Id
    @Column(name = "IDZAJECIA")
    public long getIdzajecia() {
        return idzajecia;
    }

    public void setIdzajecia(long idzajecia) {
        this.idzajecia = idzajecia;
    }

    @Basic
    @Column(name = "RODZAJ")
    public String getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        if (rodzaj.length() <= 50)
            this.rodzaj = rodzaj;
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Rodzaj zajęć musi być krótszy niż 50 znaków!");
            alert.showAndWait();
            throw new IllegalArgumentException();
        }
    }

    @Basic
    @Column(name = "DATAPROWADZENIA")
    public Date getDataprowadzenia() {
        return dataprowadzenia;
    }

    public void setDataprowadzenia(Date dataprowadzenia) {
        this.dataprowadzenia = dataprowadzenia;
    }

    @Basic
    @Column(name = "OPLATY")
    public Long getOplaty() {
        return oplaty;
    }

    public void setOplaty(Long oplaty) {
        this.oplaty = oplaty;
    }

    @Basic
    @Column(name = "CZASTYGODNIOWO")
    public Long getCzastygodniowo() {
        return czastygodniowo;
    }

    public void setCzastygodniowo(Long czastygodniowo) {
        if(czastygodniowo > 0)
            this.czastygodniowo = czastygodniowo;
        else throw new IllegalArgumentException("Czas musi być dodatni");
    }

    @Basic
    @Column(name = "DLAKOGO")
    public Long getDlakogo() {
        return dlakogo;
    }

    public void setDlakogo(Long dlakogo) {
        this.dlakogo = dlakogo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Zajeciadodatkowe that = (Zajeciadodatkowe) o;

        if (idzajecia != that.idzajecia) return false;
        if (rodzaj != null ? !rodzaj.equals(that.rodzaj) : that.rodzaj != null) return false;
        if (dataprowadzenia != null ? !dataprowadzenia.equals(that.dataprowadzenia) : that.dataprowadzenia != null)
            return false;
        if (oplaty != null ? !oplaty.equals(that.oplaty) : that.oplaty != null) return false;
        if (czastygodniowo != null ? !czastygodniowo.equals(that.czastygodniowo) : that.czastygodniowo != null)
            return false;
        if (dlakogo != null ? !dlakogo.equals(that.dlakogo) : that.dlakogo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idzajecia ^ (idzajecia >>> 32));
        result = 31 * result + (rodzaj != null ? rodzaj.hashCode() : 0);
        result = 31 * result + (dataprowadzenia != null ? dataprowadzenia.hashCode() : 0);
        result = 31 * result + (oplaty != null ? oplaty.hashCode() : 0);
        result = 31 * result + (czastygodniowo != null ? czastygodniowo.hashCode() : 0);
        result = 31 * result + (dlakogo != null ? dlakogo.hashCode() : 0);
        return result;
    }
}
