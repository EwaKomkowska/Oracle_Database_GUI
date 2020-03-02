

--  DDL for Table DZIECKO
--------------------------------------------------------

  CREATE TABLE DZIECKO 
   (	IDDZIECKA NUMBER(*,0) DEFAULT DZIECKO_SEQ.NEXTVAL, 
	IMIE VARCHAR2(15 CHAR), 
	NAZWISKO VARCHAR2(25 CHAR), 
	DATAURODZENIA DATE, 
	GRUPAPRZEDSZKOLNA_IDGRUPY NUMBER(*,0), 
	POSILEK_IDPOSILKU NUMBER(*,0)
   ) ;

  CREATE TABLE FESTYN 
   (	IDFESTYNU NUMBER(*,0) DEFAULT FESTYN_SEQ.NEXTVAL, 
	GRUPAWYSTEPUJACA NUMBER(*,0), 
	OSOBAODPOWIEDZIALNA NUMBER(*,0), 
	TERMINWYDARZENA DATE, 
	HASLO VARCHAR2(25 CHAR)
   ) ;
--------------------------------------------------------
--  DDL for Table GRUPAPRZEDSZKOLNA
--------------------------------------------------------

  CREATE TABLE GRUPAPRZEDSZKOLNA 
   (	IDGRUPY NUMBER(*,0) DEFAULT GRUPA_SEQ.NEXTVAL, 
	SALA NUMBER(*,0), 
	NAZWA VARCHAR2(25 CHAR), 
	WIEKDZIECI NUMBER(*,0), 
	IDPRAC NUMBER(*,0)
   ) ;
--------------------------------------------------------
--  DDL for Table HOSPITACJA
--------------------------------------------------------

  CREATE TABLE HOSPITACJA 
   (	IDHOSPITACJI NUMBER(*,0) DEFAULT HOSPITACJA_SEQ.NEXTVAL, 
	TERMIN DATE, 
	KTONADZOROWANY NUMBER(*,0), 
	KTONADZORUJE NUMBER(*,0)
   ) ;
--------------------------------------------------------
--  DDL for Table OPLATA
--------------------------------------------------------

  CREATE TABLE OPLATA 
   (	IDOPLATY NUMBER(*,0) DEFAULT OPLATA_SEQ.nextval, 
	WIELKOSC NUMBER, 
	PRZEDMIOTOPLATY VARCHAR2(100 CHAR), 
	CZESTOSC VARCHAR2(25 CHAR), 
	ZAJECIADODATKOWE_IDZAJECIA NUMBER(*,0), 
	POMOCDYDAKTYCZNA_IDPOMOCY NUMBER(*,0), 
	POMOCDYDAKTYCZNA_IDPRAC NUMBER(*,0), 
	POMOCDYDAKTYCZNA_IDGRUPY NUMBER(*,0)
   ) ;
--------------------------------------------------------
--  DDL for Table POMOCDYDAKTYCZNA
--------------------------------------------------------

  CREATE TABLE POMOCDYDAKTYCZNA 
   (	IDPOMOCY NUMBER(*,0) DEFAULT POMOCDYD_SEQ.nextval, 
	RODZAJ VARCHAR2(50 CHAR), 
	DODATKOWEOPLATY NUMBER(*,0), 
	GRUPADOCELOWA NUMBER(*,0), 
	OSOBAODPOWIEDZIALNA NUMBER(*,0), 
	OPLATA_IDOPLATY NUMBER(*,0), 
	PRZEDSZKOLANKA_IDPRAC NUMBER(*,0), 
	GRUPAPRZEDSZKOLNA_IDGRUPY NUMBER(*,0)
   ) ;
--------------------------------------------------------
--  DDL for Table POSILEK
--------------------------------------------------------

  CREATE TABLE POSILEK 
   (	IDPOSILKU NUMBER(*,0) DEFAULT POSILEK_SEQ.NEXTVAL, 
	NAZWA VARCHAR2(50 CHAR), 
	GODZROZWOZENIA DATE, 
	DIETA VARCHAR2(20 CHAR)
   ) ;
--  DDL for Table PRZEDSZKOLANKA
--------------------------------------------------------

  CREATE TABLE PRZEDSZKOLANKA 
   (	IDPRAC NUMBER(*,0) DEFAULT PRZEDSZKOLANKA_SEQ.nextval, 
	NAZWAGRUPY NUMBER(*,0), 
	HOS_IDHOS NUMBER(*,0), 
	IMIE VARCHAR2(25 BYTE), 
	NAZWISKO VARCHAR2(50 BYTE), 
	KWALIFIKACJE VARCHAR2(100 BYTE), 
	PLACA FLOAT(126)
   ) ;
--------------------------------------------------------
--  DDL for Table SEKRETARKA
--------------------------------------------------------

  CREATE TABLE SEKRETARKA 
   (	IDPRAC NUMBER(*,0) DEFAULT SEKRETARKA_SEQ.NEXTVAL, 
	GODZROZPOCZECIAPRACY DATE, 
	GODZZAKONCZENIAPRACY DATE, 
	IMIE VARCHAR2(25 BYTE), 
	NAZWISKO VARCHAR2(50 BYTE), 
	KWALIFIKACJE VARCHAR2(100 BYTE), 
	PLACA FLOAT(126)
   ) ;
--------------------------------------------------------
--  DDL for Table ZAJECIADODATKOWE
--------------------------------------------------------

  CREATE TABLE ZAJECIADODATKOWE 
   (	IDZAJECIA NUMBER(*,0) DEFAULT ZAJDOD_SEQ.NEXTVAL, 
	RODZAJ VARCHAR2(50 CHAR), 
	DATAPROWADZENIA DATE, 
	OPLATY NUMBER(*,0), 
	CZASTYGODNIOWO NUMBER(*,0), 
	DLAKOGO NUMBER(*,0)
   ) ;
--------------------------------------------------------
--  DDL for Table ZEBRANIEZRODZICAMI
--------------------------------------------------------

  CREATE TABLE ZEBRANIEZRODZICAMI 
   (	IDZEBRANIA NUMBER(*,0) DEFAULT ZEBRANIE_SEQ.NEXTVAL, 
	DATA DATE, 
	GRUPA NUMBER(*,0), 
	MIEJSCA NUMBER(*,0), 
	PROWADZACYZEBRANIE NUMBER(*,0), 
	CZYOBOWIAZKOWE CHAR(5 BYTE), 
	PRZEDSZKOLANKA_IDHOSPITACJI NUMBER(*,0)
   ) ;
--------------------------------------------------------
--  DDL for Table ZESPOLY
--------------------------------------------------------

  CREATE TABLE ZESPOLY 
   (	ID_ZESP NUMBER(19,0), 
	ADRES VARCHAR2(255 CHAR), 
	LICZBA_PRAC NUMBER(19,0), 
	NAZWA VARCHAR2(255 CHAR)
   ) ;



