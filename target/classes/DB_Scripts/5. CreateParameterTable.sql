DROP TABLE IF EXISTS Parameters;
CREATE TABLE Parameters(
    id INT PRIMARY KEY IDENTITY(1,1),
    parameterName VARCHAR(50),
    parameterType VARCHAR(50),
    possibleValues VARCHAR(100)
);