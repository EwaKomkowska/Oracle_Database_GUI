
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
	  REFERENCES "POMOCDYDAKTYCZNA" ("IDPOMOCY", "PRZEDSZKOLANKA_IDPRAC", "GRUPAPRZEDSZKOLNA_IDGRUPY") ON DELETE CASCADE;
  ALTER TABLE "OPLATA" ADD CONSTRAINT "OPLATA_ZAJECIA_FK" FOREIGN KEY ("ZAJECIADODATKOWE_IDZAJECIA")
	  REFERENCES "ZAJECIADODATKOWE" ("IDZAJECIA") ON DELETE CASCADE;
--------------------------------------------------------
--  Ref Constraints for Table POMOCDYDAKTYCZNA
--------------------------------------------------------

  ALTER TABLE "POMOCDYDAKTYCZNA" ADD CONSTRAINT "POMOC_OPLATA_FK" FOREIGN KEY ("OPLATA_IDOPLATY")
	  REFERENCES "OPLATA" ("IDOPLATY") ON DELETE CASCADE;
  ALTER TABLE "POMOCDYDAKTYCZNA" ADD CONSTRAINT "POMOC_PRZEDSZKOLANKA_FK" FOREIGN KEY ("PRZEDSZKOLANKA_IDPRAC")
	  REFERENCES "PRZEDSZKOLANKA" ("IDPRAC") ON DELETE CASCADE;
  ALTER TABLE "POMOCDYDAKTYCZNA" ADD CONSTRAINT "POMOC_GRUPA_FK" FOREIGN KEY ("GRUPAPRZEDSZKOLNA_IDGRUPY")
	  REFERENCES "GRUPAPRZEDSZKOLNA" ("IDGRUPY") ON DELETE CASCADE;

