DROP TABLE IF EXISTS Results;
CREATE TABLE Results(
    id INT PRIMARY KEY IDENTITY(1,1),
    diagId INT,
    drugId INT,
    phaseId INT,
    dosage FLOAT,
    startDate DATE,
    duration INT,
    doses VARCHAR(MAX),
    checkList VARCHAR(MAX),
    -- FOREIGN KEY (diagId) REFERENCES Diagnosis(id),
    -- FOREIGN KEY (drugId) REFERENCES Drugs(id),
    -- FOREIGN KEY (phaseId) REFERENCES Phases(id),
    
);