ALTER TABLE estudio DROP COLUMN ano_fundacao;

ALTER TABLE estudio ADD COLUMN data_fundacao DATE NOT NULL;