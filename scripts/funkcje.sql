
--------------------------------------------------------
--  DDL for Procedure ZWIEKSZPLACE
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "ZWIEKSZPLACE" (
IdPrzed IN NUMBER) IS
BEGIN 
    UPDATE Przedszkolanka SET placa = placa * 1.1 WHERE idprac = IdPrzed;
END ZwiekszPlace;



-- FUNKCJA - zwraca IDDZIECKA, KTÓRE MA DZIS URODZINY
CREATE OR REPLACE FUNCTION Urodziny
    RETURN NUMBER IS id_Dziecka NUMBER := 0;

    BEGIN 
        SELECT idDziecka INTO id_Dziecka FROM dziecko WHERE --iddziecka = szukane AND EXTRACT (YEAR FROM current_date) - EXTRACT(YEAR FROM dataurodzenia)
        EXTRACT(DAY FROM dataurodzenia) = EXTRACT(DAY FROM current_date) AND EXTRACT(MONTH FROM dataurodzenia) = EXTRACT(MONTH FROM current_date);
        RETURN id_Dziecka;
    END Urodziny;

SELECT Urodziny() AS dzisCzyNie FROM dual;
