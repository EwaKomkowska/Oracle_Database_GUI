/*
INSERT INTO posilek (idposilku) VALUES (1);
INSERT INTO pracownik (idprac, imie, nazwisko) VALUES (1, 'Aga', 'Koazak');
INSERT INTO pracownik (idprac, imie, nazwisko) VALUES (2, 'Beata', 'Nowak');
INSERT INTO hospitacja(idhospitacji) VALUES (1);
INSERT INTO przedszkolanka (idprac, hos_idhos) VALUES (1, 1);
--zeby wrzucic grupe pracownik musi byc przedszkolanka!!!
INSERT INTO grupaprzedszkolna (idgrupy, idprac, przed_hos_idhospitacji) VALUES (1, 1, 1);
INSERT INTO dziecko 
(iddziecka, imie, nazwisko, grupaprzedszkolna_idgrupy, posilek_idposilku) VALUES (1, 'Adam', 'Baran', 1, 1);

Select * from dziecko;
Update dziecko set dataurodzenia = DATE '2002-12-07' where iddziecka = 1;

SELECT * FROM dziecko WHERE EXTRACT(DAY FROM dataurodzenia) = EXTRACT(DAY FROM current_date);

*/

--funkcja sprawdzajaca max wykorzystane id?
CREATE OR REPLACE FUNCTION NoweID
        RETURN NUMBER IS 
        NoweDziecko NUMBER;
    BEGIN 
        SELECT MAX(iddziecka) + 1 INTO NoweDziecko
        FROM dziecko;
        RETURN NoweDziecko;
    END NoweId;

--SELECT NoweID AS NewId FROM Dual;


-- FUNKCJA - zwraca IDDZIECKA, KTÓRE MA DZIS URODZINY, nie moge zrobic funkcji na dwa parametry, wiec zwroce tylko id, mozna wyliczyc osobno, ktore urodziny
CREATE OR REPLACE FUNCTION Urodziny
    RETURN NUMBER IS id_Dziecka NUMBER := 0;
    
    BEGIN 
        SELECT idDziecka INTO id_Dziecka FROM dziecko WHERE --iddziecka = szukane AND EXTRACT (YEAR FROM current_date) - EXTRACT(YEAR FROM dataurodzenia)
        EXTRACT(DAY FROM dataurodzenia) = EXTRACT(DAY FROM current_date) AND EXTRACT(MONTH FROM dataurodzenia) = EXTRACT(MONTH FROM current_date);
        RETURN id_Dziecka;
    END Urodziny;
    
--SELECT Urodziny() AS dzisCzyNie FROM dual;



/* -- proby utworzenia funkcji zwracajacej dwa parametry
create or replace
type urodzDziecko as object
    ( val_1 number,
     val_2 number
  );

CREATE OR REPLACE FUNCTION UrodzinyHard
    RETURNS TABLE( 
        idDziecka NUMBER;
        ile_Lat NUMBER) AS
    
BEGIN 
    SELECT idDziecka, EXTRACT (YEAR FROM current_date) - EXTRACT(YEAR FROM dataurodzenia) INTO idDziecka, ile_Lat FROM dziecko WHERE --iddziecka = szukane AND 
    EXTRACT(DAY FROM dataurodzenia) = EXTRACT(DAY FROM current_date) AND EXTRACT(MONTH FROM dataurodzenia) = EXTRACT(MONTH FROM current_date);
    return urodzDziecko(idDziecka, ile_Lat);
END UrodzinyHard;*/


--PROCEDURA - zwieksza place konkretnej przedszkolanki o 10%
CREATE OR REPLACE PROCEDURE ZwiekszPlace (
IdPrzed IN NUMBER) IS
BEGIN 
    UPDATE Przedszkolanka SET placa = placa * 1.1 WHERE idprac = IdPrzed;
END ZwiekszPlace;

/*
SELECT * FROM przedszkolanka;
exec zwiekszplace(1);
*/

--PROCEDURA - tworzy nowe dziecko
CREATE OR REPLACE PROCEDURE NoweDziecko(
    imie IN VARCHAR,
    nazwisko IN VARCHAR,
    dataurodzenia IN DATE,
    grupa IN NUMBER,
    posilek IN NUMBER DEFAULT 1) IS
BEGIN 
    INSERT INTO dziecko (iddziecka, imie, nazwisko, dataurodzenia, grupaprzedszkolna, posilek) VALUES (NoweID, imie, nazwisko, dataurodzenia, grupa, posilek); 
END NoweDziecko;

/*
EXEC NoweDziecko('Mateusz', 'DYNDALSKI', '2000-06-15', 1, 1);

SELECT * FROM dziecko;

DELETE from dziecko where iddziecka = 3;
*/