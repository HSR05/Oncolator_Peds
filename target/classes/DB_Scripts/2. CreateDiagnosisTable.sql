DROP TABLE IF EXISTS Diagnosis;
CREATE TABLE Diagnosis(
    id INT PRIMARY KEY IDENTITY(1,1),
    oncId INT,
    diagnosis VARCHAR(50),
    regimen VARCHAR(50),
    type INT,
    phaseId INT,
    isActive BIT,
    parameters VARCHAR(MAX)
    FOREIGN KEY (oncId) REFERENCES Patient(oncId)
);