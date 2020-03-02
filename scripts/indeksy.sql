
--------------------------------------------------------
--  DDL for Index DZIECKO_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "DZIECKO_PK" ON "DZIECKO" ("IDDZIECKA", "GRUPAPRZEDSZKOLNA_IDGRUPY", "POSILEK_IDPOSILKU") ;
  
--------------------------------------------------------
--  DDL for Index FESTYN_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "FESTYN_PK" ON "FESTYN" ("IDFESTYNU") ;
  
--------------------------------------------------------
--  DDL for Index GRUPAPRZEDSZKOLNA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "GRUPAPRZEDSZKOLNA_PK" ON "GRUPAPRZEDSZKOLNA" ("IDGRUPY") ;
  
--------------------------------------------------------
--  DDL for Index HOSPITACJA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "HOSPITACJA_PK" ON "HOSPITACJA" ("IDHOSPITACJI") ;
  
--------------------------------------------------------
--  DDL for Index OPLATA__IDX
--------------------------------------------------------

  CREATE UNIQUE INDEX "OPLATA__IDX" ON "OPLATA" ("POMOCDYDAKTYCZNA_IDPOMOCY", "POMOCDYDAKTYCZNA_IDPRAC", "POMOCDYDAKTYCZNA_IDGRUPY") ;
 
--------------------------------------------------------
--  DDL for Index OPLATA__IDXV1
--------------------------------------------------------

  CREATE UNIQUE INDEX "OPLATA__IDXV1" ON "OPLATA" ("ZAJECIADODATKOWE_IDZAJECIA") ;
  
--------------------------------------------------------
--  DDL for Index OPLATA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "OPLATA_PK" ON "OPLATA" ("IDOPLATY") ;
 
--------------------------------------------------------
--  DDL for Index POMOCDYDAKTYCZNA__IDX
--------------------------------------------------------

  CREATE UNIQUE INDEX "POMOCDYDAKTYCZNA__IDX" ON "POMOCDYDAKTYCZNA" ("OPLATA_IDOPLATY") ;
  
--------------------------------------------------------
--  DDL for Index POMOCDYDAKTYCZNA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "POMOCDYDAKTYCZNA_PK" ON "POMOCDYDAKTYCZNA" ("IDPOMOCY", "PRZEDSZKOLANKA_IDPRAC", "GRUPAPRZEDSZKOLNA_IDGRUPY") ;
 
--------------------------------------------------------
--  DDL for Index POSILEK_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "POSILEK_PK" ON "POSILEK" ("IDPOSILKU") ;
  
--------------------------------------------------------
--  DDL for Index PRZEDSZKOLANKA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "PRZEDSZKOLANKA_PK" ON "PRZEDSZKOLANKA" ("IDPRAC") ;
  
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
  
