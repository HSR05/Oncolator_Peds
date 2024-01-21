TRUNCATE TABLE dbo.Parameters

INSERT INTO Parameters (parameterName, parameterType, possibleValues) VALUES ('Highest WBC', 'number', NULL);
INSERT INTO Parameters (parameterName, parameterType, possibleValues) VALUES ('Enlarged Liver', 'radio', 'yes/no');
INSERT INTO Parameters (parameterName, parameterType, possibleValues) VALUES ('Enlarged Spleen', 'radio', 'yes/no');
INSERT INTO Parameters (parameterName, parameterType, possibleValues) VALUES ('Enlarged Lymph Nodes', 'radio', 'yes/no');
INSERT INTO Parameters (parameterName, parameterType, possibleValues) VALUES ('Serum LDH', 'number', NULL);
INSERT INTO Parameters (parameterName, parameterType, possibleValues) VALUES ('Mediastinal Mass', 'radio', 'yes/no');
INSERT INTO Parameters (parameterName, parameterType, possibleValues) VALUES ('Extramedullary Disease', 'radio', 'yes/no');
INSERT INTO Parameters (parameterName, parameterType, possibleValues) VALUES ('Immunophenotype using eight colour panel', 'dropdown', 'dunno yet');
INSERT INTO Parameters (parameterName, parameterType, possibleValues) VALUES ('Bone Marrow studies', 'dropdown', 'dunno yet');