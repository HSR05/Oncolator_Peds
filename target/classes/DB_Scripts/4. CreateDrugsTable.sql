DROP TABLE IF EXISTS Drugs;
CREATE TABLE Drugs(
    id INT PRIMARY KEY IDENTITY(1,1),
    drugName VARCHAR(50),
    route VARCHAR(50),
    maxDosage FLOAT,
    rounded FLOAT,
    unit VARCHAR(50),
    frequency VARCHAR(50),
    description VARCHAR(MAX),
    footNote VARCHAR(MAX),
    CONSTRAINT UQ_NAME_ROUTE UNIQUE (drugName, route) 
);