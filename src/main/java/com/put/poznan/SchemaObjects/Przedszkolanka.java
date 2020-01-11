package com.put.poznan.SchemaObjects;


import javafx.beans.value.ObservableValue;

public class Przedszkolanka {

    private Integer id; // IntegerProperty id = new SimpleIntegerProperty();
    private String imie; //StringProperty imie = new SimpleStringProperty();
    private String nazwisko; //StringProperty nazwisko = new SimpleStringProperty();
    private String kwalifikacje; //StringProperty kwalifikacje = new SimpleStringProperty();
    private Float placa; //IntegerProperty placa = new SimpleIntegerProperty();
    private Integer idGrupy; //IntegerProperty idGrupy = new SimpleIntegerProperty();
    private Integer idHospitacji; //IntegerProperty idHospitacji = new SimpleIntegerProperty();
/*
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty imie = new SimpleStringProperty();
    private StringProperty nazwisko = new SimpleStringProperty();
    private StringProperty kwalifikacje = new SimpleStringProperty();
    private float IntegerProperty placa = new SimpleIntegerProperty();
    private int IntegerProperty idGrupy = new SimpleIntegerProperty();
    private int IntegerProperty idHospitacji = new SimpleIntegerProperty();
*/
    public Przedszkolanka(){
        this.id =  new Integer(34);
        this.imie = "imie";
        this.nazwisko = "łohoho";
        this.kwalifikacje = "kwalifikacje";
        this.placa = new Float(1.2);
        this.idGrupy =  new Integer(23);
        this.idHospitacji= new Integer(0);
    }

    public Przedszkolanka(int id, String imie, String nazwisko, String kwalifikacje, float placa){
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.kwalifikacje = kwalifikacje;
        this.placa = placa;
    }

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getKwalifikacje() {
        return kwalifikacje;
    }

    public void setKwalifikacje(String kwalifikacje) {
        this.kwalifikacje = kwalifikacje;
    }

    public Float getPlaca() {
        return placa;
    }

    public void setPlaca(Float placa) {
        this.placa = placa;
    }

    public Integer getIdGrupy() {
        return idGrupy;
    }

    public void setIdGrupy(Integer idGrupy) {
        this.idGrupy = idGrupy;
    }

    public Integer getIdHospitacji() {
        return idHospitacji;
    }

    public void setIdHospitacji(Integer idHospitacji) {
        this.idHospitacji = idHospitacji;
    }
}
