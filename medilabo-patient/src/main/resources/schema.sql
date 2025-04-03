-- Création de la table des patients
CREATE TABLE patient (
    id INT NOT NULL AUTO_INCREMENT,  -- ID unique généré automatiquement
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    date_of_birth DATE NOT NULL,
    gender VARCHAR(10) CHECK (gender IN ('M', 'F', 'Other')) NOT NULL,
    address VARCHAR(255),
    phone_number VARCHAR(20)
);