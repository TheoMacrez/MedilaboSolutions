-- Création de la table des patients
CREATE TABLE IF NOT EXISTS patient (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    date_of_birth DATE NOT NULL,
    gender VARCHAR(10) NOT NULL,
    address VARCHAR(255),
    phone_number VARCHAR(20),
    CONSTRAINT unique_patient UNIQUE (first_name, last_name, date_of_birth)
);

