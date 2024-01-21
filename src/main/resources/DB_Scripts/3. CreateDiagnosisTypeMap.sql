DROP TABLE IF EXISTS DiagnosisTypeMap;
CREATE TABLE DiagnosisTypeMap(
    id INT PRIMARY KEY IDENTITY(1,1),
    diagnosis VARCHAR(50),
    regimen VARCHAR(50),
    phaseId INT,
    type INT,
);