DROP TABLE IF EXISTS PhaseDrugMap;
CREATE TABLE PhaseDrugMap(
    id INT PRIMARY KEY IDENTITY(1,1),
    phaseId INT,
    drugId INT,
    dose FLOAT,
    duration INT,
    frequency VARCHAR(50),
    notesOverride VARCHAR(MAX),
    doses VARCHAR(MAX),
    -- FOREIGN KEY (phaseId) REFERENCES Phases(id),
    -- FOREIGN KEY (drugId) REFERENCES Drugs(id),
);