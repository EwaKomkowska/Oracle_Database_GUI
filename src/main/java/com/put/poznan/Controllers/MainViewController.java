package com.put.poznan.Controllers;

import java.io.IOException;
import java.nio.file.attribute.FileTime;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.put.poznan.JDBC.DataBase;
import com.put.poznan.SchemaObjects.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.TableViewSkinBase;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class MainViewController {

    private DataBase dataBase;
    private EntityManager em = App.getEm();

    public MainViewController(){    }

    @FXML
    private TabPane mainTabPane;
    private int tabIndex = 0;

    @FXML
    private TextField searchField;

    //============PRZEDSZKOLANKA--------------------------------\\
    @FXML
    private Button przedszkolankaAddButton; //FIXME:!
    @FXML
    private Button przedszkolankaRemoveButton; //FIXME:!
    @FXML
    private TableView<Przedszkolanka> przedszkolankaTableView;
    @FXML
    private TableColumn<Przedszkolanka, Number> idPrzedszkolankaColumn;
    @FXML
    private TableColumn<Przedszkolanka, String> imiePrzedszkolankaColumn;
    @FXML
    private TableColumn<Przedszkolanka, String> nazwiskoPrzedszkolankaColumn;
    @FXML
    private TableColumn<Przedszkolanka, String> kwalifikacjePrzedszkolankaColumn;
    @FXML
    private TableColumn<Przedszkolanka, Number> placaPrzedszkolankaColumn;
    @FXML
    private TableColumn<Przedszkolanka, Number> idGrupyPrzedszkolankaColumn;
    @FXML
    private TableColumn<Przedszkolanka, Number> idHospitacjiPrzedszkolankaColumn;

    private ObservableList<Przedszkolanka> przedszkolanki;

    //============DZIECKO--------------------------------\\

    @FXML
    private TableView<Dziecko> dzieckoTableView;

    @FXML
    private TableColumn<Dziecko, Number> idDzieckoColumn;
    @FXML
    private TableColumn<Dziecko, String> imieDzieckoColumn;
    @FXML
    private TableColumn<Dziecko, String> nazwiskoDzieckoColumn;
    @FXML
    private TableColumn<Dziecko, Date> dataUrodzeniaDzieckoColumn;
    @FXML
    private TableColumn<Dziecko, Number> grupaPrzedszkolnaDzieckoColumn;
    @FXML
    private TableColumn<Dziecko, Number> idPosilkuDzieckoColumn;

    private ObservableList<Dziecko> dzieci;

    //============GRUPA_PRZEDSZKOLNA--------------------------------\\
    @FXML
    private TableView<Grupaprzedszkolna> grupa_przedszkolnaTableView;

    @FXML
    private TableColumn<Grupaprzedszkolna, Number> idGrupaPrzedszkolnaColumn;
    @FXML
    private TableColumn<Grupaprzedszkolna, Number> salaGrupaPrzedszkolnaColumn;
    @FXML
    private TableColumn<Grupaprzedszkolna, String> nazwaGrupaPrzedszkolnaColumn;
    @FXML
    private TableColumn<Grupaprzedszkolna, Number> wiekDzieciGrupaPrzedszkolnaColumn;
    @FXML
    private TableColumn<Grupaprzedszkolna, Number> idPracGrupaPrzedszkolnaColumn;

    private ObservableList<Grupaprzedszkolna> grupyPrzedszkolne;

    //============ETAT--------------------------------\\
   /* @FXML
    private TableView<Etaty> etatTableView;

    @FXML
    private TableColumn<Etaty, String> nazwaEtatColumn;
    @FXML
    private TableColumn<Etaty, Number> placaMinEtatColumn;
    @FXML
    private TableColumn<Etaty, Number> placaMaxEtatColumn;

    private ObservableList<Etaty> etaty;*/

    //============FESTYN--------------------------------\\

    @FXML
    private TableView<Festyn> festynTableView;

    @FXML
    private TableColumn<Festyn, Number> idFestynColumn;
    @FXML
    private TableColumn<Festyn, Number> grupaWystepujacaFestynColumn;
    @FXML
    private TableColumn<Festyn, Number> osobaOdpowiedzialnaFestynColumn;
    @FXML
    private TableColumn<Festyn, Date> terminWydarzeniaFestynColumn;
    @FXML
    private TableColumn<Festyn, String> nazwaFestynColumn;

    private ObservableList<Festyn> festyny;

    //============HOSPITACJA--------------------------------\\
    @FXML
    private TableView<Hospitacja> hospitacjaTableView;

    @FXML
    private TableColumn<Hospitacja, Number> idHospitacjaColumn;
    @FXML
    private TableColumn<Hospitacja, Date> terminHospitacjaColumn;
    @FXML
    private TableColumn<Hospitacja, Number> ktoNadzorowanyHospitacjaColumn;
    @FXML
    private TableColumn<Hospitacja, Number> ktoNadzorujeHospitacjaColumn;

    private ObservableList<Hospitacja> hospitacje;

    //============OPLATA--------------------------------\\
    @FXML
    private TableView<Oplata> oplataTableView;

    @FXML
    private TableColumn<Oplata, Number> idOplataColumn;
    @FXML
    private TableColumn<Oplata, Number> wielkoscOplataColumn;
    @FXML
    private TableColumn<Oplata, String> przedmiotOplataColumn;
    @FXML
    private TableColumn<Oplata, String> czestoscOplataColumn;

    private ObservableList<Oplata> oplaty;

    //============POMOC_DYDAKTYCZNA--------------------------------\\
    @FXML
    private TableView<Pomocdydaktyczna> pomoc_dydaktycznaTableView;

    @FXML
    private TableColumn<Pomocdydaktyczna, Number> idPomocDydatktycznaColumn;
    @FXML
    private TableColumn<Pomocdydaktyczna, String> rodzajPomocDydatktycznaColumn;
    @FXML
    private TableColumn<Pomocdydaktyczna, Number> dodatkoweOplatyPomocDydatktycznaColumn;
    @FXML
    private TableColumn<Pomocdydaktyczna, Number> grupaDocelowaPomocDydatktycznaColumn;
    @FXML
    private TableColumn<Pomocdydaktyczna, Number> osobaOdpowiedzialnaPomocDydatktycznaColumn;
    @FXML
    private TableColumn<Pomocdydaktyczna, Number> przedszkolankaIdPomocDydatktycznaColumn;
    @FXML
    private TableColumn<Pomocdydaktyczna, Number> grupaPrzedszkolnaIdPomocDydatktycznaColumn;

    private ObservableList<Pomocdydaktyczna> pomoceDydaktyczne;

    //============POSILEK--------------------------------\\
    @FXML
    private TableView<Posilek> posilekTableView;

    @FXML
    private TableColumn<Posilek, Number> idPosilekColumn;
    @FXML
    private TableColumn<Posilek, String> nazwaPosilekColumn;
    @FXML
    private TableColumn<Posilek, Time> godzRozwozeniaPosilekColumn;
    @FXML
    private TableColumn<Posilek, String> dietaPosilekColumn;

    private ObservableList<Posilek> posilki;

    //============SEKRETARKA--------------------------------\\
    @FXML
    private TableView<Sekretarka> sekretarkaTableView;

    @FXML
    private TableColumn<Sekretarka, Number> idSekretarkaColumn;
    @FXML
    private TableColumn<Sekretarka, String> imieSekretarkaColumn;
    @FXML
    private TableColumn<Sekretarka, String> nazwiskoSekretarkaColumn;
    @FXML
    private TableColumn<Sekretarka, String> kwalifikacjeSekretarkaColumn;
    @FXML
    private TableColumn<Sekretarka, Number> placaSekretarkaColumn;
    @FXML
    private TableColumn<Sekretarka, Time> godzRozpoczeciaPracySekretarkaColumn;
    @FXML
    private TableColumn<Sekretarka, Time> godzZakonczeniaPracySekretarkaColumn;

    private ObservableList<Sekretarka> sekretarki;

    //============ZAJECIA_DODATKOWE--------------------------------\\
    @FXML
    private TableView<Zajeciadodatkowe> zajecia_dodatkoweTableView;

    @FXML
    private TableColumn<Zajeciadodatkowe, Number> idZajeciaDodatkoweColumn;
    @FXML
    private TableColumn<Zajeciadodatkowe, String> rodzajZajeciaDodatkoweColumn;
    @FXML
    private TableColumn<Zajeciadodatkowe, Date> dataProwadzeniaZajeciaDodatkoweColumn;
    @FXML
    private TableColumn<Zajeciadodatkowe, Number> oplatyZajeciaDodatkoweColumn;
    @FXML
    private TableColumn<Zajeciadodatkowe, Number> czasTygodniowoZajeciaDodatkoweColumn;
    @FXML
    private TableColumn<Zajeciadodatkowe, Number> dlaKogoZajeciaDodatkoweColumn;

    private ObservableList<Zajeciadodatkowe> zajeciaDodatkowe;

    //============ZEBRANIE_Z_RODZICAMI--------------------------------\\
    @FXML
    private TableView<Zebraniezrodzicami> zebranie_rodziceTableView;

    @FXML
    private TableColumn<Zebraniezrodzicami, Number> idZebranieRodziceColumn;
    @FXML
    private TableColumn<Zebraniezrodzicami, Time> dataZebranieRodziceColumn;
    @FXML
    private TableColumn<Zebraniezrodzicami, Number> grupaZebranieRodziceColumn;
    @FXML
    private TableColumn<Zebraniezrodzicami, Number> miejscaSalaZebranieRodziceColumn;
    @FXML
    private TableColumn<Zebraniezrodzicami, Number> prowadzacyZebranieZebranieRodziceColumn;
    @FXML
    private TableColumn<Zebraniezrodzicami, String> czyObowiazkoweZebranieRodziceColumn;
    @FXML
    private TableColumn<Zebraniezrodzicami, Number> przedszkolankaIdHospitacjiZebranieRodziceColumn;

    private ObservableList<Zebraniezrodzicami> zebraniaZRodzicami;



    @FXML
    public void initialize() throws SQLException {

        //przedszkolankaTableView.getColumns().addAll(idColumn, imieColumn, nazwiskoColumn, kwalifikacjeColumn, placaColumn, idGrupyColumn, idHospitacjiColumn);

        //idPrzedszkolankaColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()));
        idPrzedszkolankaColumn.setCellValueFactory(new PropertyValueFactory<Przedszkolanka, Number>("idprac"));
        imiePrzedszkolankaColumn.setCellValueFactory(new PropertyValueFactory<Przedszkolanka, String>("imie"));
        nazwiskoPrzedszkolankaColumn.setCellValueFactory(new PropertyValueFactory<Przedszkolanka, String>("nazwisko"));
        kwalifikacjePrzedszkolankaColumn.setCellValueFactory(new PropertyValueFactory<Przedszkolanka, String>("kwalifikacje"));
        placaPrzedszkolankaColumn.setCellValueFactory(new PropertyValueFactory<Przedszkolanka, Number>("placa"));
        idGrupyPrzedszkolankaColumn.setCellValueFactory(new PropertyValueFactory<Przedszkolanka, Number>("nazwagrupy"));
        idHospitacjiPrzedszkolankaColumn.setCellValueFactory(new PropertyValueFactory<Przedszkolanka, Number>("hospitacja"));
        przedszkolankaTableView.setEditable(false);             //modyfikacja tylko przy przycisku


        //============DzieckoColumns--------------------------------\\
        idDzieckoColumn.setCellValueFactory(new PropertyValueFactory<Dziecko, Number>("iddziecka"));
        imieDzieckoColumn.setCellValueFactory(new PropertyValueFactory<Dziecko, String>("imie"));
        nazwiskoDzieckoColumn.setCellValueFactory(new PropertyValueFactory<Dziecko, String>("nazwisko"));
        dataUrodzeniaDzieckoColumn.setCellValueFactory(new PropertyValueFactory<Dziecko, Date>("dataurodzenia"));
        grupaPrzedszkolnaDzieckoColumn.setCellValueFactory(new PropertyValueFactory<Dziecko, Number>("grupaprzedszkolnaIdgrupy"));
        idPosilkuDzieckoColumn.setCellValueFactory(new PropertyValueFactory<Dziecko, Number>("posilekIdposilku"));

        //TODO: czy set eitable wszedzie tez???
        //============GrupaPrzedszkolnaColumns--------------------------------\\
        idGrupaPrzedszkolnaColumn.setCellValueFactory(new PropertyValueFactory<Grupaprzedszkolna, Number>("idgrupy"));
        salaGrupaPrzedszkolnaColumn.setCellValueFactory(new PropertyValueFactory<Grupaprzedszkolna, Number>("sala"));
        nazwaGrupaPrzedszkolnaColumn.setCellValueFactory(new PropertyValueFactory<Grupaprzedszkolna, String>("nazwa"));
        wiekDzieciGrupaPrzedszkolnaColumn.setCellValueFactory(new PropertyValueFactory<Grupaprzedszkolna, Number>("wiekdzieci"));
        idPracGrupaPrzedszkolnaColumn.setCellValueFactory(new PropertyValueFactory<Grupaprzedszkolna, Number>("idprac"));

        //============EtatColumns--------------------------------\\
       /* nazwaEtatColumn.setCellValueFactory(new PropertyValueFactory<Etaty, String>("nazwa"));
        placaMinEtatColumn.setCellValueFactory(new PropertyValueFactory<Etaty, Number>("placaMin"));
        placaMaxEtatColumn.setCellValueFactory(new PropertyValueFactory<Etaty, Number>("placaMax"));*/

        //============FestynColumns--------------------------------\\
        idFestynColumn.setCellValueFactory(new PropertyValueFactory<Festyn, Number>("idfestynu"));
        grupaWystepujacaFestynColumn.setCellValueFactory(new PropertyValueFactory<Festyn, Number>("grupawystepujaca"));
        osobaOdpowiedzialnaFestynColumn.setCellValueFactory(new PropertyValueFactory<Festyn, Number>("osobaodpowiedzialna"));
        terminWydarzeniaFestynColumn.setCellValueFactory(new PropertyValueFactory<Festyn, Date>("terminwydarzena"));
        nazwaFestynColumn.setCellValueFactory(new PropertyValueFactory<Festyn, String>("Haslo"));

        //============HospitacjaColumns--------------------------------\\
        idHospitacjaColumn.setCellValueFactory(new PropertyValueFactory<Hospitacja, Number>("idhospitacji"));
        terminHospitacjaColumn.setCellValueFactory(new PropertyValueFactory<Hospitacja, Date>("termin"));
        ktoNadzorowanyHospitacjaColumn.setCellValueFactory(new PropertyValueFactory<Hospitacja, Number>("ktonadzorowany"));
        ktoNadzorujeHospitacjaColumn.setCellValueFactory(new PropertyValueFactory<Hospitacja, Number>("ktonadzoruje"));

        //============OplataColumns--------------------------------\\
        idOplataColumn.setCellValueFactory(new PropertyValueFactory<Oplata, Number>("idoplaty"));
        wielkoscOplataColumn.setCellValueFactory(new PropertyValueFactory<Oplata, Number>("wielkosc"));
        przedmiotOplataColumn.setCellValueFactory(new PropertyValueFactory<Oplata, String>("przedmiotoplaty"));
        czestoscOplataColumn.setCellValueFactory(new PropertyValueFactory<Oplata, String>("czestosc"));

        //============PomocDydatktycznaColumns--------------------------------\\
        idPomocDydatktycznaColumn.setCellValueFactory(new PropertyValueFactory<Pomocdydaktyczna, Number>("idpomocy"));
        rodzajPomocDydatktycznaColumn.setCellValueFactory(new PropertyValueFactory<Pomocdydaktyczna, String>("rodzaj"));
        dodatkoweOplatyPomocDydatktycznaColumn.setCellValueFactory(new PropertyValueFactory<Pomocdydaktyczna, Number>("dodatkoweoplaty"));
        grupaDocelowaPomocDydatktycznaColumn.setCellValueFactory(new PropertyValueFactory<Pomocdydaktyczna, Number>("grupadocelowa"));
        osobaOdpowiedzialnaPomocDydatktycznaColumn.setCellValueFactory(new PropertyValueFactory<Pomocdydaktyczna, Number>("osobaodpowiedzialna"));
        przedszkolankaIdPomocDydatktycznaColumn.setCellValueFactory(new PropertyValueFactory<Pomocdydaktyczna, Number>("przedszkolankaIdprac"));
        grupaPrzedszkolnaIdPomocDydatktycznaColumn.setCellValueFactory(new PropertyValueFactory<Pomocdydaktyczna, Number>("grupaprzedszkolnaIdgrupy"));

        //============PosilekColumns--------------------------------\\
        idPosilekColumn.setCellValueFactory(new PropertyValueFactory<Posilek, Number>("idposilku"));
        nazwaPosilekColumn.setCellValueFactory(new PropertyValueFactory<Posilek, String>("nazwa"));
        godzRozwozeniaPosilekColumn.setCellValueFactory(new PropertyValueFactory<Posilek, Time>("godzrozwozenia"));
        dietaPosilekColumn.setCellValueFactory(new PropertyValueFactory<Posilek, String>("dieta"));

        //============SekretarkaColumns--------------------------------\\
        idSekretarkaColumn.setCellValueFactory(new PropertyValueFactory<Sekretarka, Number>("idprac"));
        imieSekretarkaColumn.setCellValueFactory(new PropertyValueFactory<Sekretarka, String>("imie"));
        nazwiskoSekretarkaColumn.setCellValueFactory(new PropertyValueFactory<Sekretarka, String>("nazwisko"));
        kwalifikacjeSekretarkaColumn.setCellValueFactory(new PropertyValueFactory<Sekretarka, String>("kwalifikacje"));
        placaSekretarkaColumn.setCellValueFactory(new PropertyValueFactory<Sekretarka, Number>("placa"));
        godzRozpoczeciaPracySekretarkaColumn.setCellValueFactory(new PropertyValueFactory<Sekretarka, Time>("godzrozpoczeciapracy"));
        godzZakonczeniaPracySekretarkaColumn.setCellValueFactory(new PropertyValueFactory<Sekretarka, Time>("godzzakonczeniapracy"));

        //============ZajeciaDodatkoweColumns--------------------------------\\
        idZajeciaDodatkoweColumn.setCellValueFactory(new PropertyValueFactory<Zajeciadodatkowe, Number>("idzajecia"));
        rodzajZajeciaDodatkoweColumn.setCellValueFactory(new PropertyValueFactory<Zajeciadodatkowe, String>("rodzaj"));
        dataProwadzeniaZajeciaDodatkoweColumn.setCellValueFactory(new PropertyValueFactory<Zajeciadodatkowe, Date>("dataprowadzenia"));
        oplatyZajeciaDodatkoweColumn.setCellValueFactory(new PropertyValueFactory<Zajeciadodatkowe, Number>("oplaty"));
        czasTygodniowoZajeciaDodatkoweColumn.setCellValueFactory(new PropertyValueFactory<Zajeciadodatkowe, Number>("czastygodniowo"));
        dlaKogoZajeciaDodatkoweColumn.setCellValueFactory(new PropertyValueFactory<Zajeciadodatkowe, Number>("dlakogo"));

        //============ZebranieRodziceColumns--------------------------------\\
        idZebranieRodziceColumn.setCellValueFactory(new PropertyValueFactory<Zebraniezrodzicami, Number>("idzebrania"));
        dataZebranieRodziceColumn.setCellValueFactory(new PropertyValueFactory<Zebraniezrodzicami, Time>("data"));
        grupaZebranieRodziceColumn.setCellValueFactory(new PropertyValueFactory<Zebraniezrodzicami, Number>("grupa"));
        miejscaSalaZebranieRodziceColumn.setCellValueFactory(new PropertyValueFactory<Zebraniezrodzicami, Number>("miejsca"));
        prowadzacyZebranieZebranieRodziceColumn.setCellValueFactory(new PropertyValueFactory<Zebraniezrodzicami, Number>("prowadzacyzebranie"));
        czyObowiazkoweZebranieRodziceColumn.setCellValueFactory(new PropertyValueFactory<Zebraniezrodzicami, String>("czyobowiazkowe"));
        przedszkolankaIdHospitacjiZebranieRodziceColumn.setCellValueFactory(new PropertyValueFactory<Zebraniezrodzicami, Number>("przedszkolankaIdhospitacji"));

        wyswietl();

        FilteredList<Dziecko> dzieckoFilteredList = new FilteredList<>(dzieci, b -> true);
        FilteredList<Festyn> festynFilteredList = new FilteredList<>(festyny, b-> true );
        FilteredList<Przedszkolanka> przedszkolankaFilteredList = new FilteredList<>(przedszkolanki, b-> true );
        FilteredList<Sekretarka> sekretarkaFilteredList = new FilteredList<>(sekretarki, b-> true );
        FilteredList<Grupaprzedszkolna> grupaprzedszkolnaFilteredList = new FilteredList<>(grupyPrzedszkolne, b-> true );
        FilteredList<Zajeciadodatkowe> zajeciadodatkoweFilteredList = new FilteredList<>(zajeciaDodatkowe, b-> true );
        FilteredList<Posilek> posilekFilteredList = new FilteredList<>(posilki, b-> true );
        FilteredList<Pomocdydaktyczna> pomocdydaktycznaFilteredList = new FilteredList<>(pomoceDydaktyczne, b-> true );
        FilteredList<Oplata> oplataFilteredList = new FilteredList<>(oplaty, b-> true );
        FilteredList<Hospitacja> hospitacjaFilteredList = new FilteredList<>(hospitacje, b-> true );
        FilteredList<Zebraniezrodzicami> zebraniezrodzicamiFilteredList = new FilteredList<>(zebraniaZRodzicami, b-> true );

        searchField.textProperty().addListener(((observable, oldValue, newValue) -> {

            dzieckoFilteredList.setPredicate( dziecko -> {

                //TO SPRAWIA ZE NIE FILTRUJE LISTY JAK JEST NA INNEJ ZAKLADCE NIZ TA ODPOWIEDNIA!
                if (this.getCurrentTabIndex() != 2){
                    return true;
                }

                if(newValue == null || newValue.isEmpty() ){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if ( String.valueOf(dziecko.getIddziecka()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                else if ( String.valueOf(dziecko.getGrupaprzedszkolnaIdgrupy()).toLowerCase().contains(lowerCaseFilter) ) {
                    return true;
                }
                else if (  String.valueOf(dziecko.getPosilekIdposilku()).toLowerCase().contains(lowerCaseFilter) ) {
                    return true;
                }
                else if (  String.valueOf(dziecko.getImie()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                else if (  String.valueOf(dziecko.getNazwisko()).toLowerCase().contains(lowerCaseFilter) ) {
                    return true;
                }
                else if ( String.valueOf(dziecko.getDataurodzenia()).contains(lowerCaseFilter)  ) {
                    return true;
                } else {
                    return false;
                }
            } );

            przedszkolankaFilteredList.setPredicate( przedszkolanka -> {

                //TO SPRAWIA ZE NIE FILTRUJE LISTY JAK JEST NA INNEJ ZAKLADCE NIZ TA ODPOWIEDNIA!
                if (this.getCurrentTabIndex() != 0){
                    return true;
                }

                if(newValue == null || newValue.isEmpty() ){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if( String.valueOf(przedszkolanka.getIdprac()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if (  String.valueOf(przedszkolanka.getImie()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if ( String.valueOf(przedszkolanka.getPlaca()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if (  String.valueOf(przedszkolanka.getKwalifikacje()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if ( String.valueOf(przedszkolanka.getNazwagrupy()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if (  String.valueOf(przedszkolanka.getNazwisko()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else {
                    return false;
                }
            } );


            sekretarkaFilteredList.setPredicate( sekretarka -> {

                if (this.getCurrentTabIndex() != 1){
                    return true;
                }

                if(newValue == null || newValue.isEmpty() ){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if( String.valueOf(sekretarka.getIdprac()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if (  String.valueOf(sekretarka.getImie()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if ( String.valueOf(sekretarka.getPlaca()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if (sekretarka.getKwalifikacje().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if ( String.valueOf(sekretarka.getGodzrozpoczeciapracy()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if (String.valueOf(sekretarka.getGodzzakonczeniapracy()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                else if (  String.valueOf(sekretarka.getNazwisko()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else {
                    return false;
                }
            });



            grupaprzedszkolnaFilteredList.setPredicate( grupaprzedszkolna -> {

                if (this.getCurrentTabIndex() != 3){
                    return true;
                }

                if(newValue == null || newValue.isEmpty() ){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if( String.valueOf(grupaprzedszkolna.getIdgrupy()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if ( String.valueOf(grupaprzedszkolna.getSala()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if ( String.valueOf(grupaprzedszkolna.getWiekdzieci()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if (  String.valueOf(grupaprzedszkolna.getNazwa()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if ( String.valueOf(grupaprzedszkolna.getIdprac()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else {
                    return false;
                }
            });


            festynFilteredList.setPredicate( festyn -> {

                if (this.getCurrentTabIndex() != 8){
                    return true;
                }

                if(newValue == null || newValue.isEmpty() ){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if( String.valueOf(festyn.getIdfestynu()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if ( String.valueOf(festyn.getGrupawystepujaca()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if ( String.valueOf(festyn.getOsobaodpowiedzialna()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if ( String.valueOf(festyn.getTerminwydarzena()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (  String.valueOf(festyn.getHaslo()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else {
                    return false;
                }
            });

            zebraniezrodzicamiFilteredList.setPredicate( zebraniezrodzicami -> {

                if (this.getCurrentTabIndex() != 9){
                    return true;
                }

                if(newValue == null || newValue.isEmpty() ){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if( String.valueOf(zebraniezrodzicami.getIdzebrania()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if ( String.valueOf(zebraniezrodzicami.getData()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if ( String.valueOf(zebraniezrodzicami.getGrupa()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if ( String.valueOf(zebraniezrodzicami.getMiejsca()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if ( String.valueOf(zebraniezrodzicami.getProwadzacyzebranie()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if ( String.valueOf(zebraniezrodzicami.getPrzedszkolankaIdhospitacji()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                else if (  String.valueOf(zebraniezrodzicami.getCzyobowiazkowe()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else {
                    return false;
                }
            });

            hospitacjaFilteredList.setPredicate( hospitacja -> {

                if (this.getCurrentTabIndex() != 10){
                    return true;
                }

                if(newValue == null || newValue.isEmpty() ){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if( String.valueOf(hospitacja.getIdhospitacji()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if ( String.valueOf(hospitacja.getTermin()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if ( String.valueOf(hospitacja.getKtonadzoruje()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if ( String.valueOf(hospitacja.getKtonadzorowany()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });


            oplataFilteredList.setPredicate( oplata -> {

                if (this.getCurrentTabIndex() != 7){
                    return true;
                }

                if(newValue == null || newValue.isEmpty() ){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if( String.valueOf(oplata.getIdoplaty()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if ( String.valueOf(oplata.getWielkosc()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if (  String.valueOf(oplata.getCzestosc()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if (  String.valueOf(oplata.getPrzedmiotoplaty()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });


            pomocdydaktycznaFilteredList.setPredicate( pomocdydaktyczna -> {

                if (this.getCurrentTabIndex() != 6){
                    return true;
                }

                if(newValue == null || newValue.isEmpty() ){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if( String.valueOf(pomocdydaktyczna.getIdpomocy()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if ( String.valueOf(pomocdydaktyczna.getDodatkoweoplaty()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if ( String.valueOf(pomocdydaktyczna.getGrupadocelowa()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if (pomocdydaktyczna.getRodzaj().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if ( String.valueOf(pomocdydaktyczna.getOsobaodpowiedzialna()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if (String.valueOf(pomocdydaktyczna.getPrzedszkolankaIdprac()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                else if ( String.valueOf(pomocdydaktyczna.getGrupaprzedszkolnaIdgrupy()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else {
                    return false;
                }
            });

            zajeciadodatkoweFilteredList.setPredicate( zajeciadodatkowe -> {

                if (this.getCurrentTabIndex() != 4){
                    return true;
                }

                if(newValue == null || newValue.isEmpty() ){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if( String.valueOf(zajeciadodatkowe.getIdzajecia()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if (  String.valueOf(zajeciadodatkowe.getRodzaj()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if ( String.valueOf(zajeciadodatkowe.getDataprowadzenia()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if ( String.valueOf(zajeciadodatkowe.getOplaty()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if (String.valueOf(zajeciadodatkowe.getCzastygodniowo()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                else if ( String.valueOf(zajeciadodatkowe.getDlakogo()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else {
                    return false;
                }
            });


            posilekFilteredList.setPredicate( posilek -> {

                if (this.getCurrentTabIndex() != 5){
                    return true;
                }

                if(newValue == null || newValue.isEmpty() ){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if( String.valueOf(posilek.getIdposilku()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if (  String.valueOf(posilek.getDieta()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if ( String.valueOf(posilek.getGodzrozwozenia()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if (  String.valueOf(posilek.getNazwa()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else {
                    return false;
                }
            });

        //oplata i posilek sie psuja zebranie z rodzicami tez


        }));

//FIXME: INDEXY WSZYSTKIE PRZEJZYJ BO NIE ZMIENIALES W PREDYKATACH

        SortedList<Dziecko> dzieckoSortedList = new SortedList<>(dzieckoFilteredList);
        dzieckoSortedList.comparatorProperty().bind(dzieckoTableView.comparatorProperty());
        dzieckoTableView.setItems(dzieckoSortedList);

        SortedList<Przedszkolanka> przedszkolankaSortedList = new SortedList<>(przedszkolankaFilteredList);
        przedszkolankaSortedList.comparatorProperty().bind(przedszkolankaTableView.comparatorProperty());
        przedszkolankaTableView.setItems(przedszkolankaSortedList);

        SortedList<Sekretarka> sekretarkaSortedList = new SortedList<>(sekretarkaFilteredList);
        sekretarkaSortedList.comparatorProperty().bind(sekretarkaTableView.comparatorProperty());
        sekretarkaTableView.setItems(sekretarkaSortedList);

        SortedList<Grupaprzedszkolna> grupaprzedszkolnaSortedList = new SortedList<>(grupaprzedszkolnaFilteredList);
        grupaprzedszkolnaSortedList.comparatorProperty().bind(grupa_przedszkolnaTableView.comparatorProperty());
        grupa_przedszkolnaTableView.setItems(grupaprzedszkolnaSortedList);

        SortedList<Festyn> festynSortedList = new SortedList<>(festynFilteredList);
        festynSortedList.comparatorProperty().bind(festynTableView.comparatorProperty());
        festynTableView.setItems(festynSortedList);

        SortedList<Zebraniezrodzicami> zebraniezrodzicamiSortedList = new SortedList<>(zebraniezrodzicamiFilteredList);
        zebraniezrodzicamiSortedList.comparatorProperty().bind(zebranie_rodziceTableView.comparatorProperty());
        zebranie_rodziceTableView.setItems(zebraniezrodzicamiSortedList);

        SortedList<Hospitacja> hospitacjaSortedList = new SortedList<>(hospitacjaFilteredList);
        hospitacjaSortedList.comparatorProperty().bind(hospitacjaTableView.comparatorProperty());
        hospitacjaTableView.setItems(hospitacjaSortedList);

        SortedList<Oplata> oplataSortedList = new SortedList<>(oplataFilteredList);
        oplataSortedList.comparatorProperty().bind(oplataTableView.comparatorProperty());
        oplataTableView.setItems(oplataSortedList);

        SortedList<Pomocdydaktyczna> pomocdydaktycznaSortedList = new SortedList<>(pomocdydaktycznaFilteredList);
        pomocdydaktycznaSortedList.comparatorProperty().bind(pomoc_dydaktycznaTableView.comparatorProperty());
        pomoc_dydaktycznaTableView.setItems(pomocdydaktycznaSortedList);

        SortedList<Zajeciadodatkowe> zajeciadodatkoweSortedList = new SortedList<>(zajeciadodatkoweFilteredList);
        zajeciadodatkoweSortedList.comparatorProperty().bind(zajecia_dodatkoweTableView.comparatorProperty());
        zajecia_dodatkoweTableView.setItems(zajeciadodatkoweSortedList);

        SortedList<Posilek> posilekSortedList = new SortedList<>(posilekFilteredList);
        posilekSortedList.comparatorProperty().bind(posilekTableView.comparatorProperty());
        posilekTableView.setItems(posilekSortedList);





    }



            posilekFilteredList.setPredicate( posilek -> {

                if (this.getCurrentTabIndex() != 5){
                    return true;
                }

                if(newValue == null || newValue.isEmpty() ){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if( String.valueOf(posilek.getIdposilku()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if (  String.valueOf(posilek.getDieta()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if ( String.valueOf(posilek.getGodzrozwozenia()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if (  String.valueOf(posilek.getNazwa()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else {
                    return false;
                }
            });

        //oplata i posilek sie psuja zebranie z rodzicami tez


        }));

//FIXME: INDEXY WSZYSTKIE PRZEJZYJ BO NIE ZMIENIALES W PREDYKATACH

        SortedList<Dziecko> dzieckoSortedList = new SortedList<>(dzieckoFilteredList);
        dzieckoSortedList.comparatorProperty().bind(dzieckoTableView.comparatorProperty());
        dzieckoTableView.setItems(dzieckoSortedList);

        SortedList<Przedszkolanka> przedszkolankaSortedList = new SortedList<>(przedszkolankaFilteredList);
        przedszkolankaSortedList.comparatorProperty().bind(przedszkolankaTableView.comparatorProperty());
        przedszkolankaTableView.setItems(przedszkolankaSortedList);

        SortedList<Sekretarka> sekretarkaSortedList = new SortedList<>(sekretarkaFilteredList);
        sekretarkaSortedList.comparatorProperty().bind(sekretarkaTableView.comparatorProperty());
        sekretarkaTableView.setItems(sekretarkaSortedList);

        SortedList<Grupaprzedszkolna> grupaprzedszkolnaSortedList = new SortedList<>(grupaprzedszkolnaFilteredList);
        grupaprzedszkolnaSortedList.comparatorProperty().bind(grupa_przedszkolnaTableView.comparatorProperty());
        grupa_przedszkolnaTableView.setItems(grupaprzedszkolnaSortedList);

        SortedList<Festyn> festynSortedList = new SortedList<>(festynFilteredList);
        festynSortedList.comparatorProperty().bind(festynTableView.comparatorProperty());
        festynTableView.setItems(festynSortedList);

        SortedList<Zebraniezrodzicami> zebraniezrodzicamiSortedList = new SortedList<>(zebraniezrodzicamiFilteredList);
        zebraniezrodzicamiSortedList.comparatorProperty().bind(zebranie_rodziceTableView.comparatorProperty());
        zebranie_rodziceTableView.setItems(zebraniezrodzicamiSortedList);

        SortedList<Hospitacja> hospitacjaSortedList = new SortedList<>(hospitacjaFilteredList);
        hospitacjaSortedList.comparatorProperty().bind(hospitacjaTableView.comparatorProperty());
        hospitacjaTableView.setItems(hospitacjaSortedList);

        SortedList<Oplata> oplataSortedList = new SortedList<>(oplataFilteredList);
        oplataSortedList.comparatorProperty().bind(oplataTableView.comparatorProperty());
        oplataTableView.setItems(oplataSortedList);

        SortedList<Pomocdydaktyczna> pomocdydaktycznaSortedList = new SortedList<>(pomocdydaktycznaFilteredList);
        pomocdydaktycznaSortedList.comparatorProperty().bind(pomoc_dydaktycznaTableView.comparatorProperty());
        pomoc_dydaktycznaTableView.setItems(pomocdydaktycznaSortedList);

        SortedList<Zajeciadodatkowe> zajeciadodatkoweSortedList = new SortedList<>(zajeciadodatkoweFilteredList);
        zajeciadodatkoweSortedList.comparatorProperty().bind(zajecia_dodatkoweTableView.comparatorProperty());
        zajecia_dodatkoweTableView.setItems(zajeciadodatkoweSortedList);

        SortedList<Posilek> posilekSortedList = new SortedList<>(posilekFilteredList);
        posilekSortedList.comparatorProperty().bind(posilekTableView.comparatorProperty());
        posilekTableView.setItems(posilekSortedList);





    }


    public void wyswietl() throws SQLException {
        //TODO: czy tego nie wrzucic w osobna funkcje zeby aktualizowac ladnie czy listenery wystarcza???


        PreparedStatement comparisonAlterSessionStatement = DataBase.getConnection().prepareStatement("ALTER SESSION SET CURRENT_SCHEMA = PAWEU");
        comparisonAlterSessionStatement.execute();
        //Session session = em.unwrap(Session.class);
        //session.setProperty("CURRENT_SCHEMA", "PAWEU");


        //inicjalizacje ID:
        PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT ZEBRANIE_SEQ.nextval FROM dual");
        pstm.executeQuery();

        pstm = DataBase.getConnection().prepareStatement("SELECT ZAJDOD_SEQ.nextval FROM dual");
        pstm.executeQuery();

        pstm = DataBase.getConnection().prepareStatement("SELECT SEKRETARKA_SEQ.nextval FROM dual");
        pstm.executeQuery();

        pstm = DataBase.getConnection().prepareStatement("SELECT PRZEDSZKOLANKA_SEQ.nextval FROM dual");
        pstm.executeQuery();

        pstm = DataBase.getConnection().prepareStatement("SELECT POSILEK_SEQ.nextval FROM dual");
        pstm.executeQuery();

        pstm = DataBase.getConnection().prepareStatement("SELECT POMOCDYD_SEQ.nextval FROM dual");
        pstm.executeQuery();

        pstm = DataBase.getConnection().prepareStatement("SELECT OPLATA_SEQ.nextval FROM dual");
        pstm.executeQuery();

        pstm = DataBase.getConnection().prepareStatement("SELECT HOSPITACJA_SEQ.nextval FROM dual");
        pstm.executeQuery();

        pstm = DataBase.getConnection().prepareStatement("SELECT GRUPA_SEQ.nextval FROM dual");
        pstm.executeQuery();

        pstm = DataBase.getConnection().prepareStatement("SELECT FESTYN_SEQ.nextval FROM dual");
        pstm.executeQuery();

        pstm = DataBase.getConnection().prepareStatement("SELECT DZIECKO_SEQ.nextval FROM dual");
        pstm.executeQuery();




        dzieci = FXCollections.observableList(new ArrayList<>());
        Query query=em.createQuery("SELECT p FROM Dziecko p");
        dzieci.addAll(query.getResultList());
        dzieckoTableView.setItems(dzieci);


        grupyPrzedszkolne = FXCollections.observableList(new ArrayList<>());
        query=em.createQuery("SELECT p FROM Grupaprzedszkolna p");
        grupyPrzedszkolne.addAll(query.getResultList());
        grupa_przedszkolnaTableView.setItems(grupyPrzedszkolne);


        zajeciaDodatkowe = FXCollections.observableList(new ArrayList<>());
        query = em.createQuery("SELECT zd from Zajeciadodatkowe zd");
        zajeciaDodatkowe.addAll(query.getResultList());
        zajecia_dodatkoweTableView.setItems(zajeciaDodatkowe);


        posilki = FXCollections.observableList(new ArrayList<>());
        query = em.createQuery("SELECT p FROM Posilek p");
        posilki.addAll(query.getResultList());
        posilekTableView.setItems(posilki);


        zebraniaZRodzicami = FXCollections.observableList(new ArrayList<>());
        query = em.createQuery("SELECT zr FROM Zebraniezrodzicami zr");
        zebraniaZRodzicami.addAll(query.getResultList());
        zebranie_rodziceTableView.setItems(zebraniaZRodzicami);


        festyny = FXCollections.observableList(new ArrayList<>());
        query = em.createQuery("SELECT f FROM Festyn f");
        festyny.addAll(query.getResultList());
        festynTableView.setItems(festyny);


        hospitacje = FXCollections.observableList(new ArrayList<>());
        query = em.createQuery("SELECT h FROM Hospitacja h");
        hospitacje.addAll(query.getResultList());
        hospitacjaTableView.setItems(hospitacje);


        oplaty = FXCollections.observableList(new ArrayList<>());
        query = em.createQuery("SELECT o FROM Oplata o");
        oplaty.addAll(query.getResultList());
        oplataTableView.setItems(oplaty);


        pomoceDydaktyczne = FXCollections.observableList(new ArrayList<>());
        query = em.createQuery("SELECT pd FROM Pomocdydaktyczna pd");
        pomoceDydaktyczne.addAll(query.getResultList());
        pomoc_dydaktycznaTableView.setItems(pomoceDydaktyczne);


        przedszkolanki = FXCollections.observableList(new ArrayList<>());
        query = em.createQuery("SELECT p FROM Przedszkolanka p");
        przedszkolanki.addAll(query.getResultList());
        przedszkolankaTableView.setItems(przedszkolanki);


        sekretarki = FXCollections.observableList(new ArrayList<>());
        query = em.createQuery("SELECT s FROM Sekretarka s");
        sekretarki.addAll(query.getResultList());
        sekretarkaTableView.setItems(sekretarki);
    }



    @FXML
    private void removePrzedszkolanka() {
        try {
            long idPrzed = przedszkolankaTableView.getSelectionModel().getSelectedItem().getIdprac();
            String statement = "DELETE FROM PRZEDSZKOLANKA WHERE IDPRAC = ?";

            remove(statement, idPrzed);
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś obiektu do usunięcia");
            alert.showAndWait();
        }
    }

    @FXML
    private void removeSekretarka() {
        try {
            long id = sekretarkaTableView.getSelectionModel().getSelectedItem().getIdprac();
            String statement = "DELETE FROM SEKRETARKA WHERE IDPRAC = ?";

            remove(statement, id);
        }catch (Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Nie wybrałeś obiektu do usunięcia");
        alert.showAndWait();
    }
    }

    @FXML
    private void removeDziecko() {
        try {
        long id = dzieckoTableView.getSelectionModel().getSelectedItem().getIddziecka();
        String statement = "DELETE FROM DZIECKO WHERE IDDZIECKA = ?";

        remove(statement, id);
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś obiektu do usunięcia");
            alert.showAndWait();
        }
    }

    @FXML
    private void removeFestyn() {
        try {
            long id = festynTableView.getSelectionModel().getSelectedItem().getIdfestynu();
            String statement = "DELETE FROM FESTYN WHERE IDFESTYNU = ?";

            remove(statement, id);
        }catch (Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Nie wybrałeś obiektu do usunięcia");
        alert.showAndWait();
    }
    }

    @FXML
    private void removeOplata() {
        try {
            long id = oplataTableView.getSelectionModel().getSelectedItem().getIdoplaty();
            String statement = "DELETE FROM OPLATA WHERE IDOPLATY = ?";

            remove(statement, id);
        }catch (Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Nie wybrałeś obiektu do usunięcia");
        alert.showAndWait();
    }
    }


    @FXML
    private void removeZajecia() {
        try {
        long id = zajecia_dodatkoweTableView.getSelectionModel().getSelectedItem().getIdzajecia();
        String statement = "DELETE FROM ZAJECIADODATKOWE WHERE IDZAJECIA = ?";

        remove(statement, id);

        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś obiektu do usunięcia");
            alert.showAndWait();
        }
    }

    @FXML
    private void removeZebranie() {
        try {
        long id = zebranie_rodziceTableView.getSelectionModel().getSelectedItem().getIdzebrania();
        String statement = "DELETE FROM ZEBRANIEZRODZICAMI WHERE IDZEBRANIA = ?";

        remove(statement, id);
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś obiektu do usunięcia");
            alert.showAndWait();
        }
    }

    @FXML
    private void removePosilek() {
        try {
        long id = posilekTableView.getSelectionModel().getSelectedItem().getIdposilku();
        String statement = "DELETE FROM POSILEK WHERE IDPOSILKU = ?";

        remove(statement, id);
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś obiektu do usunięcia");
            alert.showAndWait();
        }
    }

    @FXML
    private void removeHospitacja() {
        try {
        long id = hospitacjaTableView.getSelectionModel().getSelectedItem().getIdhospitacji();
        String statement = "DELETE FROM HOSPITACJA WHERE IDHOSPITACJI = ?";

        remove(statement, id);
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś obiektu do usunięcia");
            alert.showAndWait();
        }
    }

    @FXML
    private void removeGrupa() {
        try {
        long id = grupa_przedszkolnaTableView.getSelectionModel().getSelectedItem().getIdgrupy();
        String statement = "DELETE FROM GRUPAPRZEDSZKOLNA WHERE IDGRUPY = ?";

        remove(statement, id);
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś obiektu do usunięcia");
            alert.showAndWait();
        }
    }

    @FXML
    private void removePomoc() {
        try {
        long id = pomoc_dydaktycznaTableView.getSelectionModel().getSelectedItem().getIdpomocy();
        String statement = "DELETE FROM POMOCDYDAKTYCZNA WHERE IDPOMOCY = ?";

        remove(statement, id);
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś obiektu do usunięcia");
            alert.showAndWait();
        }
    }


    @FXML
    private void addPrzedszkolanka() throws IOException {
        //tu będzie wiecej zabawy, bo to chyba trzeba zrobić z pobieraniem danych w nowym oknie
        FXMLLoader loader = App.getFXMLLoader("przedszkolanka");
        Parent root = loader.load();
        PrzedszkolankaController c = loader.getController();
        c.setDataBase(this.dataBase);
        Scene scene = new Scene(root);
        App.getStage().setScene(scene);
    }

    @FXML
    private void addPosilek() throws IOException {
        FXMLLoader loader = App.getFXMLLoader("posilek");
        Parent root = loader.load();
        posilekController c = loader.getController();
        c.setDataBase(this.dataBase);
        Scene scene = new Scene(root);
        App.getStage().setScene(scene);
    }


    @FXML
    private void addDziecko() throws IOException {
        FXMLLoader loader = App.getFXMLLoader("dziecko");
        Parent root = loader.load();
        dzieckoController c = loader.getController();
        c.setDataBase(this.dataBase);
        Scene scene = new Scene(root);
        App.getStage().setScene(scene);
    }


    @FXML
    private void addFestyn() throws IOException {
        FXMLLoader loader = App.getFXMLLoader("festyn");
        Parent root = loader.load();
        festynController c = loader.getController();
        c.setDataBase(this.dataBase);
        Scene scene = new Scene(root);
        App.getStage().setScene(scene);
    }


    @FXML
    private void addGrupa() throws IOException {
        FXMLLoader loader = App.getFXMLLoader("grupa");
        Parent root = loader.load();
        grupaPrzedszkolnaController c = loader.getController();
        c.setDataBase(this.dataBase);
        Scene scene = new Scene(root);
        App.getStage().setScene(scene);
    }


    @FXML
    private void addHospitacja() throws IOException {
        FXMLLoader loader = App.getFXMLLoader("hospitacja");
        Parent root = loader.load();
        hospitacjaController c = loader.getController();
        c.setDataBase(this.dataBase);
        Scene scene = new Scene(root);
        App.getStage().setScene(scene);
    }


    @FXML
    private void addOplata() throws IOException {
        FXMLLoader loader = App.getFXMLLoader("oplata");
        Parent root = loader.load();
        oplataController c = loader.getController();
        c.setDataBase(this.dataBase);
        Scene scene = new Scene(root);
        App.getStage().setScene(scene);
    }


    @FXML
    private void addPomoc() throws IOException {
        FXMLLoader loader = App.getFXMLLoader("pomoc");
        Parent root = loader.load();
        pomocDydaktycznaController c = loader.getController();
        c.setDataBase(this.dataBase);
        Scene scene = new Scene(root);
        App.getStage().setScene(scene);
    }


    @FXML
    private void addZajecia() throws IOException {
        FXMLLoader loader = App.getFXMLLoader("zajecia");
        Parent root = loader.load();
        zajeciaDodatkoweController c = loader.getController();
        c.setDataBase(this.dataBase);
        Scene scene = new Scene(root);
        App.getStage().setScene(scene);
    }

    @FXML
    private void addZebranie() throws IOException {
        FXMLLoader loader = App.getFXMLLoader("zebranie");
        Parent root = loader.load();
        zebranieRodziceController c = loader.getController();
        c.setDataBase(this.dataBase);
        Scene scene = new Scene(root);
        App.getStage().setScene(scene);
    }


    @FXML
    private void addSekretarka() throws IOException {
        FXMLLoader loader = App.getFXMLLoader("sekretarka");
        Parent root = loader.load();
        sekretarkaController c = loader.getController();
        c.setDataBase(this.dataBase);
        Scene scene = new Scene(root);
        App.getStage().setScene(scene);
    }


    private void remove(String statement, long parameter) throws SQLException {
        PreparedStatement stmt;
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);

        try {
            stmt = App.getDataBase().getConnection().prepareStatement(statement);
            stmt.setLong(1, parameter);        //ustaw pierwszy znak zapytania ma wartosc paramtetru

            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Jesteś pewien, że chcesz usunąć obekt o id: " + parameter + "?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                int deletedRows = stmt.executeUpdate();         //ilosc usunietych wierszy

                if (deletedRows == 0) {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setContentText("You can't remove this object. \nMaybe it's not exist?\nPlease, try again!\n Choose one of object that you can see on the list.");
                    alert.showAndWait();
                } else {
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setContentText("Usunięto poprawnie " + deletedRows + " obiektów!");
                    alert.showAndWait();
                }
            } /*else {
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Zrezygnowałeś z usuwania!");
                alert.showAndWait();
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }

        wyswietl();
    }

    //TODO: CHECK IF THIS WORKS
    public MainViewController(DataBase dataBase){
        this.setDataBase(dataBase);
    }

    public void setDataBase(DataBase dataBase){
        this.dataBase = dataBase;
    }

    @FXML
    private void logOut() throws IOException {
        this.dataBase.closeConnection();
        //Platform.exit();
        //App.getStage().setScene(new Scene(App.loadFXML("login")) );
        FXMLLoader loader = App.getFXMLLoader("login");
        Parent root = loader.load();
        LoginController c = loader.getController();
        c.setDataBase(this.dataBase);
        Scene scene = new Scene(root);
        App.getStage().setScene(scene);
    }


    @FXML
    private void urodziny() throws SQLException{
        CallableStatement stmt = DataBase.getConnection().prepareCall("{? = call URODZINY()}");
        stmt.registerOutParameter(1, Types.INTEGER);
        try {
            stmt.execute();

            int idDziecka = stmt.getInt(1);

            PreparedStatement pstm = DataBase.getConnection().prepareStatement("SELECT imie, NAZWISKO FROM DZIECKO where IDDZIECKA = ?");
            pstm.setLong(1, idDziecka);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            String imie = rs.getString(1);
            String nazwisko = rs.getString(2);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Dziś urodizny ma " + imie + " " + nazwisko);
            alert.showAndWait();
            stmt.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Dziś nikt nie ma urodzin");
            alert.showAndWait();
            stmt.close();
        }
    }

   @FXML
   public void placa() {
       Alert alert = new Alert(Alert.AlertType.ERROR);
       alert.setHeaderText(null);
       try {
           //TODO: to samo co w update, czyli zmiany są, ale się nie wyswietlaja
           long id = przedszkolankaTableView.getSelectionModel().getSelectedItem().getIdprac();
           CallableStatement stmt = DataBase.getConnection().prepareCall("{call zwiekszPlace(?)}");
           stmt.setLong(1, id);
           stmt.execute();

           alert.setAlertType(Alert.AlertType.INFORMATION);
           alert.setContentText("Zwiększono placę przedszkolance o id: " + id);
           alert.showAndWait();

           FXMLLoader loader = App.getFXMLLoader("primary");
           Parent root = loader.load();
           MainViewController c = loader.getController();
           c.setDataBase(dataBase);
           c.setCurrentTab(0);
           Scene scene = new Scene(root);
           App.getStage().setScene(scene);
       } catch (Exception e) {
           alert.setContentText("Nie wybrałeś przedszkolanki, której chcesz zwiększyć płacę");
           alert.showAndWait();
       }
    }


    @FXML
    public void modifyDziecko() {
        try {
            Long id = dzieckoTableView.getSelectionModel().getSelectedItem().getIddziecka();

            FXMLLoader loader = App.getFXMLLoader("dziecko");
            Parent root = loader.load();
            dzieckoController dc = loader.getController();
            dc.setDataBase(this.dataBase);
            Scene scene = new Scene(root);
            App.getStage().setScene(scene);

            dc.modify(id);
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś dziecka do modyfikacji");
            alert.showAndWait();
        }
    }

    @FXML
    public void modifyFestyn() {
        try {
            Long id = festynTableView.getSelectionModel().getSelectedItem().getIdfestynu();

            FXMLLoader loader = App.getFXMLLoader("festyn");
            Parent root = loader.load();
            festynController fc = loader.getController();
            fc.setDataBase(this.dataBase);
            Scene scene = new Scene(root);
            App.getStage().setScene(scene);

            fc.modify(id);
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś festynu do modyfikacji");
            alert.showAndWait();
        }
    }

    @FXML
    public void modifyGrupa() {
        try {
            Long id = grupa_przedszkolnaTableView.getSelectionModel().getSelectedItem().getIdgrupy();

            FXMLLoader loader = App.getFXMLLoader("grupa");
            Parent root = loader.load();
            grupaPrzedszkolnaController gc = loader.getController();
            gc.setDataBase(this.dataBase);
            Scene scene = new Scene(root);
            App.getStage().setScene(scene);

            gc.modify(id);
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś grupy do modyfikacji");
            alert.showAndWait();
        }
    }

    @FXML
    public void modifyHospitacja() {
        try {
            Long id = hospitacjaTableView.getSelectionModel().getSelectedItem().getIdhospitacji();

            FXMLLoader loader = App.getFXMLLoader("hospitacja");
            Parent root = loader.load();
            hospitacjaController hc = loader.getController();
            hc.setDataBase(this.dataBase);
            Scene scene = new Scene(root);
            App.getStage().setScene(scene);

            hc.modify(id);
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś hospitacji do modyfikacji");
            alert.showAndWait();
        }
    }

    @FXML
    public void modifyOplata() {
        try {
            Long id = oplataTableView.getSelectionModel().getSelectedItem().getIdoplaty();

            FXMLLoader loader = App.getFXMLLoader("oplata");
            Parent root = loader.load();
            oplataController oc = loader.getController();
            oc.setDataBase(this.dataBase);
            Scene scene = new Scene(root);
            App.getStage().setScene(scene);

            oc.modify(id);
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś opłaty do modyfikacji");
            alert.showAndWait();
        }
    }

    @FXML
    public void modifyPomoc() {
        try {
            Long id = pomoc_dydaktycznaTableView.getSelectionModel().getSelectedItem().getIdpomocy();

            FXMLLoader loader = App.getFXMLLoader("pomoc");
            Parent root = loader.load();
            pomocDydaktycznaController pc = loader.getController();
            pc.setDataBase(this.dataBase);
            Scene scene = new Scene(root);
            App.getStage().setScene(scene);

            pc.modify(id);
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś pomocy dydaktycznej do modyfikacji");
            alert.showAndWait();
        }
    }

    @FXML
    public void modifyPosilek() {
        try {
            Long id = posilekTableView.getSelectionModel().getSelectedItem().getIdposilku();

            FXMLLoader loader = App.getFXMLLoader("posilek");
            Parent root = loader.load();
            posilekController pc = loader.getController();
            pc.setDataBase(this.dataBase);
            Scene scene = new Scene(root);
            App.getStage().setScene(scene);

            pc.modify(id);
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś posiłku do modyfikacji");
            alert.showAndWait();
        }
    }

    @FXML
    public void modifyPrzedszkolanka() {
        try {
            Long id = przedszkolankaTableView.getSelectionModel().getSelectedItem().getIdprac();

            FXMLLoader loader = App.getFXMLLoader("przedszkolanka");
            Parent root = loader.load();
            PrzedszkolankaController pc = loader.getController();
            pc.setDataBase(this.dataBase);
            Scene scene = new Scene(root);
            App.getStage().setScene(scene);

            pc.modify(id);
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś przedszkolanki do modyfikacji");
            alert.showAndWait();
        }
    }

    @FXML
    public void modifySekretarka() {
        try {
            Long id = sekretarkaTableView.getSelectionModel().getSelectedItem().getIdprac();

            FXMLLoader loader = App.getFXMLLoader("sekretarka");
            Parent root = loader.load();
            sekretarkaController sc = loader.getController();
            sc.setDataBase(this.dataBase);
            Scene scene = new Scene(root);
            App.getStage().setScene(scene);

            sc.modify(id);
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś sekretarki do modyfikacji");
            alert.showAndWait();
        }
    }

    @FXML
    public void modifyZajecia() {
        try {
            Long id = zajecia_dodatkoweTableView.getSelectionModel().getSelectedItem().getIdzajecia();

            FXMLLoader loader = App.getFXMLLoader("zajecia");
            Parent root = loader.load();
            zajeciaDodatkoweController zc = loader.getController();
            zc.setDataBase(this.dataBase);
            Scene scene = new Scene(root);
            App.getStage().setScene(scene);

            zc.modify(id);
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś zajęć do modyfikacji");
            alert.showAndWait();
        }
    }

    @FXML
    public void modifyZebranie() {
        try {
            Long id = zebranie_rodziceTableView.getSelectionModel().getSelectedItem().getIdzebrania();

            FXMLLoader loader = App.getFXMLLoader("zebranie");
            Parent root = loader.load();
            zebranieRodziceController zc = loader.getController();
            zc.setDataBase(this.dataBase);
            Scene scene = new Scene(root);
            App.getStage().setScene(scene);

            zc.modify(id);
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrałeś zebrnia do modyfikacji");
            alert.showAndWait();
        }
    }


    public static void add(DataBase dataBase, int idx) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Poprawnie zaktualizowano 1 obiekt");      //wywoluje sie przy modify i add
        alert.showAndWait();

        //przejście do menu głównego
        FXMLLoader loader = App.getFXMLLoader("primary");
        Parent root = loader.load();
        MainViewController c = loader.getController();
        c.setDataBase(dataBase);
        c.setCurrentTab(idx);
        Scene scene = new Scene(root);
        App.getStage().setScene(scene);
    }


    @FXML
    private void refreshDzieckoTableView(){
      App.setProperties();
      this.searchField.clear();
      this.dzieckoTableView.refresh();

    }

    @FXML
    private void refreshFestynTableView(){
        App.setProperties();
        this.searchField.clear();
        this.festynTableView.refresh();
    }

    @FXML
    private void refreshGrupaPrzedszkolnaTableView(){
        App.setProperties();
        this.searchField.clear();
        this.grupa_przedszkolnaTableView.refresh();
    }

    @FXML
    private void refreshHospitacjaTableView(){
        App.setProperties();
        this.searchField.clear();
        this.hospitacjaTableView.refresh();
    }

    @FXML
    private void refreshOplataTableView(){
        App.setProperties();
        this.searchField.clear();
        this.oplataTableView.refresh();
    }

    @FXML
    private void refreshPomocDydaktycznaTableView(){
        App.setProperties();
        this.searchField.clear();
        this.pomoc_dydaktycznaTableView.refresh();
    }

    @FXML
    private void refreshPosilekTableView(){
        App.setProperties();
        this.searchField.clear();
        this.posilekTableView.refresh();
    }

    @FXML
    private void refreshPrzedszkolankaTableView(){
        App.setProperties();
        this.searchField.clear();
        this.przedszkolankaTableView.refresh();
    }

    @FXML
    private void refreshSekretarkaTableView(){
        App.setProperties();
        this.searchField.clear();
        this.sekretarkaTableView.refresh();
    }

    @FXML
    private void refreshZajeciaDodatkoweTableView(){
        App.setProperties();
        this.searchField.clear();
        this.zajecia_dodatkoweTableView.refresh();
    }

    @FXML
    private void refreshZebranieRodziceTableView(){
        App.setProperties();
        this.searchField.clear();
        this.zebranie_rodziceTableView.refresh();
    }

    public int getCurrentTabIndex(){
        return mainTabPane.getSelectionModel().getSelectedIndex();
    }

    public void setCurrentTab(int index){
        App.setProperties();
        mainTabPane.getSelectionModel().select(index);
    }

}
