DROP TABLE IF EXISTS Phases;
CREATE TABLE Phases(
    id INT PRIMARY KEY IDENTITY(1,1),
    phaseCode VARCHAR(50),
    phaseName VARCHAR(50),
    risk VARCHAR(100),
    randomisation VARCHAR(100),
    daysOfTherapy INT,
    startWeek INT,
    endWeek INT,
    intrathecalInjections INT,
    phaseDescription VARCHAR(500),
    possibleValues VARCHAR(100)
);