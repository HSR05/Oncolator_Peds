DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id INT PRIMARY KEY,
  FIRST_NAME VARCHAR(250) NOT NULL,
  LAST_NAME VARCHAR(250) NOT NULL,
  EMAIL VARCHAR(250) NOT NULL
);

INSERT INTO users (ID, FIRST_NAME, LAST_NAME, EMAIL) VALUES
  (1, 'first', 'last 1', 'abc1@gmail.com'),
  (2, 'first', 'last 2', 'abc2@gmail.com'),
  (3, 'first', 'last 3', 'abc3@gmail.com'),
  (4, 'first', 'last 4', 'abc4@gmail.com');
  
  SELECT TOP(2) * from Patient
  SELECT  * from Diagnosis
  SELECT * from DiagnosisTypeMap
  SELECT TOP(20) * from Drugs
  SELECT TOP(2) * from Parameters
  SELECT * from Phases
  SELECT * from Results
  SELECT * from PhaseDrugMap
  
  SELECT TOP 1 type FROM DiagnosisTypeMap d WHERE d.diagnosis = 'ALL' AND d.regimen = 'ICICLE' AND d.phaseId = NULL
  
  SELECT * FROM PhaseDrugMap WHERE phaseId = 1;
  
  truncate table Results
  INSERT INTO Results (id, diagId, drugId, phaseId, startDate, duration, dosage) VALUES (3,1,3,2,'10/12/2022',35,60)
  
  SELECT TOP (1) * FROM Diagnosis d WHERE d.oncId = 10008 AND isActive = 1
  
  ALTER TABLE Diagnosis
  ADD isActive BIT
  
  UPDATE Diagnosis
  SET isActive = 1
  WHERE id = 1095
  
  UPDATE Diagnosis
  SET parameters = NULL
  WHERE id = 1095
  
  UPDATE Diagnosis
  SET phaseId = NULL
  WHERE id = 1095
  
  SELECT * FROM Results WHERE diagId = 1 AND phaseId = 1
  
  SELECT * FROM Results 
  WHERE diagId = (SELECT TOP 1 id FROM Diagnosis WHERE oncId = 10008 AND isActive = 1)
  AND phaseId = (SELECT TOP 1 phaseId FROM Diagnosis WHERE oncId = 10008 AND isActive = 1)
  
  select * from PhaseDrugMap
  
  INSERT INTO PhaseDrugMap (phaseId,drugId,duration,frequency,notesOverride,doses) VALUES ('1','1','35','','','[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0.8,0.6,0.4,0.2,0.1,0]');
  
  SELECT * FROM Results r LEFT JOIN Diagnosis d on r.diagId = d.id LEFT JOIN Phases p on r.phaseId = p.id WHERE d.oncId = 10008 AND d.isActive = 1
  SELECT * FROM Results WHERE diagId = (SELECT TOP 1 id FROM Diagnosis WHERE oncId = 10008 AND isActive = 1)AND phaseId = (SELECT TOP 1 phaseId FROM Diagnosis WHERE oncId = 10008 AND isActive = 1)
  
  SELECT * FROM Diagnosis WHERE oncId = 10008 AND isActive = 1
  
  SELECT * FROM Results WHERE diagId = (SELECT TOP 1 id FROM Diagnosis WHERE oncId = 10127 AND isActive = 1)AND phaseId = (SELECT TOP 1 phaseId FROM Diagnosis WHERE oncId = 10127 AND isActive = 1)
  
