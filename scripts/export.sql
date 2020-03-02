

--  DDL for Table DZIECKO
--------------------------------------------------------

  CREATE TABLE "DZIECKO" 
   (	"IDDZIECKA" NUMBER(*,0) DEFAULT "DZIECKO_SEQ"."NEXTVAL", 
	"IMIE" VARCHAR2(15 CHAR), 
	"NAZWISKO" VARCHAR2(25 CHAR), 
	"DATAURODZENIA" DATE, 
	"GRUPAPRZEDSZKOLNA_IDGRUPY" NUMBER(*,0), 
	"POSILEK_IDPOSILKU" NUMBER(*,0)
   ) 

  CREATE TABLE "FESTYN" 
   (	"IDFESTYNU" NUMBER(*,0) DEFAULT "FESTYN_SEQ"."NEXTVAL", 
	"GRUPAWYSTEPUJACA" NUMBER(*,0), 
	"OSOBAODPOWIEDZIALNA" NUMBER(*,0), 
	"TERMINWYDARZENA" DATE, 
	"HASLO" VARCHAR2(25 CHAR)
   ) 
--------------------------------------------------------
--  DDL for Table GRUPAPRZEDSZKOLNA
--------------------------------------------------------

  CREATE TABLE "GRUPAPRZEDSZKOLNA" 
   (	"IDGRUPY" NUMBER(*,0) DEFAULT "GRUPA_SEQ"."NEXTVAL", 
	"SALA" NUMBER(*,0), 
	"NAZWA" VARCHAR2(25 CHAR), 
	"WIEKDZIECI" NUMBER(*,0), 
	"IDPRAC" NUMBER(*,0)
   ) 
--------------------------------------------------------
--  DDL for Table HOSPITACJA
--------------------------------------------------------

  CREATE TABLE "HOSPITACJA" 
   (	"IDHOSPITACJI" NUMBER(*,0) DEFAULT "HOSPITACJA_SEQ"."NEXTVAL", 
	"TERMIN" DATE, 
	"KTONADZOROWANY" NUMBER(*,0), 
	"KTONADZORUJE" NUMBER(*,0)
   ) 
--------------------------------------------------------
--  DDL for Table OPLATA
--------------------------------------------------------

  CREATE TABLE "OPLATA" 
   (	"IDOPLATY" NUMBER(*,0) DEFAULT system.OPLATA_SEQ.nextval, 
	"WIELKOSC" NUMBER, 
	"PRZEDMIOTOPLATY" VARCHAR2(100 CHAR), 
	"CZESTOSC" VARCHAR2(25 CHAR), 
	"ZAJECIADODATKOWE_IDZAJECIA" NUMBER(*,0), 
	"POMOCDYDAKTYCZNA_IDPOMOCY" NUMBER(*,0), 
	"POMOCDYDAKTYCZNA_IDPRAC" NUMBER(*,0), 
	"POMOCDYDAKTYCZNA_IDGRUPY" NUMBER(*,0)
   ) 
--------------------------------------------------------
--  DDL for Table POMOCDYDAKTYCZNA
--------------------------------------------------------

  CREATE TABLE "POMOCDYDAKTYCZNA" 
   (	"IDPOMOCY" NUMBER(*,0) DEFAULT system.POMOCDYD_SEQ.nextval, 
	"RODZAJ" VARCHAR2(50 CHAR), 
	"DODATKOWEOPLATY" NUMBER(*,0), 
	"GRUPADOCELOWA" NUMBER(*,0), 
	"OSOBAODPOWIEDZIALNA" NUMBER(*,0), 
	"OPLATA_IDOPLATY" NUMBER(*,0), 
	"PRZEDSZKOLANKA_IDPRAC" NUMBER(*,0), 
	"GRUPAPRZEDSZKOLNA_IDGRUPY" NUMBER(*,0)
   ) 
--------------------------------------------------------
--  DDL for Table POSILEK
--------------------------------------------------------

  CREATE TABLE "POSILEK" 
   (	"IDPOSILKU" NUMBER(*,0) DEFAULT "POSILEK_SEQ"."NEXTVAL", 
	"NAZWA" VARCHAR2(50 CHAR), 
	"GODZROZWOZENIA" DATE, 
	"DIETA" VARCHAR2(20 CHAR)
   ) -----------------------
--  DDL for Table PRZEDSZKOLANKA
--------------------------------------------------------

  CREATE TABLE "PRZEDSZKOLANKA" 
   (	"IDPRAC" NUMBER(*,0) DEFAULT SYSTEM.PRZEDSZKOLANKA_SEQ.nextval, 
	"NAZWAGRUPY" NUMBER(*,0), 
	"HOS_IDHOS" NUMBER(*,0), 
	"IMIE" VARCHAR2(25 BYTE), 
	"NAZWISKO" VARCHAR2(50 BYTE), 
	"KWALIFIKACJE" VARCHAR2(100 BYTE), 
	"PLACA" FLOAT(126)
   ) 
--------------------------------------------------------
--  DDL for Table SEKRETARKA
--------------------------------------------------------

  CREATE TABLE "SEKRETARKA" 
   (	"IDPRAC" NUMBER(*,0) DEFAULT "SEKRETARKA_SEQ"."NEXTVAL", 
	"GODZROZPOCZECIAPRACY" DATE, 
	"GODZZAKONCZENIAPRACY" DATE, 
	"IMIE" VARCHAR2(25 BYTE), 
	"NAZWISKO" VARCHAR2(50 BYTE), 
	"KWALIFIKACJE" VARCHAR2(100 BYTE), 
	"PLACA" FLOAT(126)
   ) 
--------------------------------------------------------
--  DDL for Table ZAJECIADODATKOWE
--------------------------------------------------------

  CREATE TABLE "ZAJECIADODATKOWE" 
   (	"IDZAJECIA" NUMBER(*,0) DEFAULT "ZAJDOD_SEQ"."NEXTVAL", 
	"RODZAJ" VARCHAR2(50 CHAR), 
	"DATAPROWADZENIA" DATE, 
	"OPLATY" NUMBER(*,0), 
	"CZASTYGODNIOWO" NUMBER(*,0), 
	"DLAKOGO" NUMBER(*,0)
   ) 
--------------------------------------------------------
--  DDL for Table ZEBRANIEZRODZICAMI
--------------------------------------------------------

  CREATE TABLE "ZEBRANIEZRODZICAMI" 
   (	"IDZEBRANIA" NUMBER(*,0) DEFAULT "ZEBRANIE_SEQ"."NEXTVAL", 
	"DATA" DATE, 
	"GRUPA" NUMBER(*,0), 
	"MIEJSCA" NUMBER(*,0), 
	"PROWADZACYZEBRANIE" NUMBER(*,0), 
	"CZYOBOWIAZKOWE" CHAR(5 BYTE), 
	"PRZEDSZKOLANKA_IDHOSPITACJI" NUMBER(*,0)
   ) 
--------------------------------------------------------
--  DDL for Table ZESPOLY
--------------------------------------------------------

  CREATE TABLE "ZESPOLY" 
   (	"ID_ZESP" NUMBER(19,0), 
	"ADRES" VARCHAR2(255 CHAR), 
	"LICZBA_PRAC" NUMBER(19,0), 
	"NAZWA" VARCHAR2(255 CHAR)
   ) 



--------------------------------------------------------
--  DDL for Index DZIECKO_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "DZIECKO_PK" ON "DZIECKO" ("IDDZIECKA", "GRUPAPRZEDSZKOLNA_IDGRUPY", "POSILEK_IDPOSILKU") 
  
--------------------------------------------------------
--  DDL for Index FESTYN_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "FESTYN_PK" ON "FESTYN" ("IDFESTYNU") 
  
--------------------------------------------------------
--  DDL for Index GRUPAPRZEDSZKOLNA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "GRUPAPRZEDSZKOLNA_PK" ON "GRUPAPRZEDSZKOLNA" ("IDGRUPY") 
  
--------------------------------------------------------
--  DDL for Index HOSPITACJA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "HOSPITACJA_PK" ON "HOSPITACJA" ("IDHOSPITACJI") 
  
--------------------------------------------------------
--  DDL for Index OPLATA__IDX
--------------------------------------------------------

  CREATE UNIQUE INDEX "OPLATA__IDX" ON "OPLATA" ("POMOCDYDAKTYCZNA_IDPOMOCY", "POMOCDYDAKTYCZNA_IDPRAC", "POMOCDYDAKTYCZNA_IDGRUPY") 
 
--------------------------------------------------------
--  DDL for Index OPLATA__IDXV1
--------------------------------------------------------

  CREATE UNIQUE INDEX "OPLATA__IDXV1" ON "OPLATA" ("ZAJECIADODATKOWE_IDZAJECIA") 
  
--------------------------------------------------------
--  DDL for Index OPLATA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "OPLATA_PK" ON "OPLATA" ("IDOPLATY") 
 
--------------------------------------------------------
--  DDL for Index POMOCDYDAKTYCZNA__IDX
--------------------------------------------------------

  CREATE UNIQUE INDEX "POMOCDYDAKTYCZNA__IDX" ON "POMOCDYDAKTYCZNA" ("OPLATA_IDOPLATY") 
  
--------------------------------------------------------
--  DDL for Index POMOCDYDAKTYCZNA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "POMOCDYDAKTYCZNA_PK" ON "POMOCDYDAKTYCZNA" ("IDPOMOCY", "PRZEDSZKOLANKA_IDPRAC", "GRUPAPRZEDSZKOLNA_IDGRUPY") 
 
--------------------------------------------------------
--  DDL for Index POSILEK_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "POSILEK_PK" ON "POSILEK" ("IDPOSILKU") 
  
--------------------------------------------------------
--  DDL for Index PRZEDSZKOLANKA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "PRZEDSZKOLANKA_PK" ON "PRZEDSZKOLANKA" ("IDPRAC") 
  
--------------------------------------------------------
--  DDL for Index SEKRETARKA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SEKRETARKA_PK" ON "SEKRETARKA" ("IDPRAC") ;
  
--------------------------------------------------------
--  DDL for Index ZAJECIADODATKOWE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ZAJECIADODATKOWE_PK" ON "ZAJECIADODATKOWE" ("IDZAJECIA") ;
  
--------------------------------------------------------
--  DDL for Index ZEBRANIEZRODZICAMI_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ZEBRANIEZRODZICAMI_PK" ON "ZEBRANIEZRODZICAMI" ("IDZEBRANIA", "PRZEDSZKOLANKA_IDHOSPITACJI") ;
  
--------------------------------------------------------
--  DDL for Index SYS_C0013150
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_C0013150" ON "ZESPOLY" ("ID_ZESP") ;
  

--  Constraints for Table DZIECKO
--------------------------------------------------------

  ALTER TABLE "DZIECKO" MODIFY ("IDDZIECKA" NOT NULL ENABLE);
  ALTER TABLE "DZIECKO" MODIFY ("IMIE" NOT NULL ENABLE);
  ALTER TABLE "DZIECKO" MODIFY ("NAZWISKO" NOT NULL ENABLE);
  ALTER TABLE "DZIECKO" MODIFY ("GRUPAPRZEDSZKOLNA_IDGRUPY" NOT NULL ENABLE);
  ALTER TABLE "DZIECKO" MODIFY ("POSILEK_IDPOSILKU" NOT NULL ENABLE);
  ALTER TABLE "DZIECKO" ADD CONSTRAINT "DZIECKO_PK" PRIMARY KEY ("IDDZIECKA", "GRUPAPRZEDSZKOLNA_IDGRUPY", "POSILEK_IDPOSILKU");

--------------------------------------------------------
--  Constraints for Table FESTYN
--------------------------------------------------------

  ALTER TABLE "FESTYN" MODIFY ("IDFESTYNU" NOT NULL ENABLE);
  ALTER TABLE "FESTYN" MODIFY ("TERMINWYDARZENA" NOT NULL ENABLE);
  ALTER TABLE "FESTYN" ADD CONSTRAINT "FESTYN_PK" PRIMARY KEY ("IDFESTYNU");
  
--------------------------------------------------------
--  Constraints for Table GRUPAPRZEDSZKOLNA
--------------------------------------------------------

  ALTER TABLE "GRUPAPRZEDSZKOLNA" MODIFY ("IDGRUPY" NOT NULL ENABLE);
  ALTER TABLE "GRUPAPRZEDSZKOLNA" MODIFY ("IDPRAC" NOT NULL ENABLE);
  ALTER TABLE "GRUPAPRZEDSZKOLNA" ADD CONSTRAINT "GRUPAPRZEDSZKOLNA_PK" PRIMARY KEY ("IDGRUPY");
  
--------------------------------------------------------
--  Constraints for Table HOSPITACJA
--------------------------------------------------------

  ALTER TABLE "HOSPITACJA" MODIFY ("IDHOSPITACJI" NOT NULL ENABLE);
  ALTER TABLE "HOSPITACJA" ADD CONSTRAINT "HOSPITACJA_PK" PRIMARY KEY ("IDHOSPITACJI");
  
--------------------------------------------------------
--  Constraints for Table OPLATA
--------------------------------------------------------

  ALTER TABLE "OPLATA" MODIFY ("IDOPLATY" NOT NULL ENABLE);
  ALTER TABLE "OPLATA" MODIFY ("ZAJECIADODATKOWE_IDZAJECIA" NOT NULL ENABLE);
  ALTER TABLE "OPLATA" ADD CONSTRAINT "OPLATA_PK" PRIMARY KEY ("IDOPLATY");
 
--------------------------------------------------------
--  Constraints for Table POMOCDYDAKTYCZNA
--------------------------------------------------------

  ALTER TABLE "POMOCDYDAKTYCZNA" MODIFY ("IDPOMOCY" NOT NULL ENABLE);
  ALTER TABLE "POMOCDYDAKTYCZNA" MODIFY ("PRZEDSZKOLANKA_IDPRAC" NOT NULL ENABLE);
  ALTER TABLE "POMOCDYDAKTYCZNA" MODIFY ("GRUPAPRZEDSZKOLNA_IDGRUPY" NOT NULL ENABLE);
  ALTER TABLE "POMOCDYDAKTYCZNA" ADD CONSTRAINT "POMOCDYDAKTYCZNA_PK" PRIMARY KEY ("IDPOMOCY", "PRZEDSZKOLANKA_IDPRAC", "GRUPAPRZEDSZKOLNA_IDGRUPY");
 
--------------------------------------------------------
--  Constraints for Table POSILEK
--------------------------------------------------------

  ALTER TABLE "POSILEK" MODIFY ("IDPOSILKU" NOT NULL ENABLE);
  ALTER TABLE "POSILEK" ADD CONSTRAINT "POSILEK_PK" PRIMARY KEY ("IDPOSILKU");
  
--------------------------------------------------------
--  Constraints for Table PRZEDSZKOLANKA
--------------------------------------------------------

  ALTER TABLE "PRZEDSZKOLANKA" MODIFY ("IDPRAC" NOT NULL ENABLE);
  ALTER TABLE "PRZEDSZKOLANKA" MODIFY ("HOS_IDHOS" NOT NULL ENABLE);
  ALTER TABLE "PRZEDSZKOLANKA" ADD CONSTRAINT "PRZEDSZKOLANKA_PK" PRIMARY KEY ("IDPRAC");
 
--------------------------------------------------------
--  Constraints for Table SEKRETARKA
--------------------------------------------------------

  ALTER TABLE "SEKRETARKA" MODIFY ("IDPRAC" NOT NULL ENABLE);
  ALTER TABLE "SEKRETARKA" ADD CONSTRAINT "SEKRETARKA_PK" PRIMARY KEY ("IDPRAC");
 
--------------------------------------------------------
--  Constraints for Table ZAJECIADODATKOWE
--------------------------------------------------------

  ALTER TABLE "ZAJECIADODATKOWE" MODIFY ("IDZAJECIA" NOT NULL ENABLE);
  ALTER TABLE "ZAJECIADODATKOWE" ADD CONSTRAINT "ZAJECIADODATKOWE_PK" PRIMARY KEY ("IDZAJECIA");
  
--------------------------------------------------------
--  Constraints for Table ZEBRANIEZRODZICAMI
--------------------------------------------------------

  ALTER TABLE "ZEBRANIEZRODZICAMI" MODIFY ("IDZEBRANIA" NOT NULL ENABLE);
  ALTER TABLE "ZEBRANIEZRODZICAMI" MODIFY ("PRZEDSZKOLANKA_IDHOSPITACJI" NOT NULL ENABLE);
  ALTER TABLE "ZEBRANIEZRODZICAMI" ADD CONSTRAINT "ZEBRANIEZRODZICAMI_PK" PRIMARY KEY ("IDZEBRANIA", "PRZEDSZKOLANKA_IDHOSPITACJI");
 
--------------------------------------------------------
--  Constraints for Table ZESPOLY
--------------------------------------------------------

  ALTER TABLE "ZESPOLY" MODIFY ("ID_ZESP" NOT NULL ENABLE);
  ALTER TABLE "ZESPOLY" ADD PRIMARY KEY ("ID_ZESP");
  
--------------------------------------------------------
--  Ref Constraints for Table OPLATA
--------------------------------------------------------

  ALTER TABLE "OPLATA" ADD CONSTRAINT "OPLATA_POMOC_FK" FOREIGN KEY ("POMOCDYDAKTYCZNA_IDPOMOCY", "POMOCDYDAKTYCZNA_IDPRAC", "POMOCDYDAKTYCZNA_IDGRUPY")
	  REFERENCES "POMOCDYDAKTYCZNA" ("IDPOMOCY", "PRZEDSZKOLANKA_IDPRAC", "GRUPAPRZEDSZKOLNA_IDGRUPY") ENABLE;
  ALTER TABLE "OPLATA" ADD CONSTRAINT "OPLATA_ZAJECIA_FK" FOREIGN KEY ("ZAJECIADODATKOWE_IDZAJECIA");
	  REFERENCES "ZAJECIADODATKOWE" ("IDZAJECIA") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table POMOCDYDAKTYCZNA
--------------------------------------------------------

  ALTER TABLE "POMOCDYDAKTYCZNA" ADD CONSTRAINT "POMOC_OPLATA_FK" FOREIGN KEY ("OPLATA_IDOPLATY")
	  REFERENCES "OPLATA" ("IDOPLATY") ENABLE;
  ALTER TABLE "POMOCDYDAKTYCZNA" ADD CONSTRAINT "POMOC_PRZEDSZKOLANKA_FK" FOREIGN KEY ("PRZEDSZKOLANKA_IDPRAC")
	  REFERENCES "PRZEDSZKOLANKA" ("IDPRAC") ENABLE;
  ALTER TABLE "POMOCDYDAKTYCZNA" ADD CONSTRAINT "POMOC_GRUPA_FK" FOREIGN KEY ("GRUPAPRZEDSZKOLNA_IDGRUPY")
	  REFERENCES "GRUPAPRZEDSZKOLNA" ("IDGRUPY") ENABLE;
