package com.put.poznan;

public class Przedszkolanka {

    private int id; //IntegerProperty id = new SimpleIntegerProperty();
    private String imie; //StringProperty imie = new SimpleStringProperty();
    private String nazwisko; //StringProperty nazwisko = new SimpleStringProperty();
    private String kwalifikacje; //StringProperty kwalifikacje = new SimpleStringProperty();
    private float placa; //IntegerProperty placa = new SimpleIntegerProperty();
    private int idGrupy; //IntegerProperty idGrupy = new SimpleIntegerProperty();
    private int idHospitacji; //IntegerProperty idHospitacji = new SimpleIntegerProperty();

    public Przedszkolanka(){}

    public Przedszkolanka(int id, String imie,String nazwisko, String kwalifikacje, float placa){
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.kwalifikacje = kwalifikacje;
        this.placa = placa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public float getPlaca() {
        return placa;
    }

    public void setPlaca(int placa) {
        this.placa = placa;
    }

    public int getIdGrupy() {
        return idGrupy;
    }

    public void setIdGrupy(int idGrupy) {
        this.idGrupy = idGrupy;
    }

    public int getIdHospitacji() {
        return idHospitacji;
    }

    public void setIdHospitacji(int idHospitacji) {
        this.idHospitacji = idHospitacji;
    }









}
