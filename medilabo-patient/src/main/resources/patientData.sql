-- Création de la table des patients
CREATE TABLE patient (
    id SERIAL PRIMARY KEY,  -- ID unique généré automatiquement
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    date_of_birth DATE NOT NULL,
    gender VARCHAR(10) CHECK (gender IN ('M', 'F', 'Other')) NOT NULL,
    address VARCHAR(255),
    phone_number VARCHAR(20)
);

-- Insertion des patients fournis
INSERT INTO patient (first_name, last_name, date_of_birth, gender, address, phone_number) VALUES
('Test', 'TestNone', '1966-12-31', 'F', '1 Brookside St', '100-222-3333'),
('Test', 'TestBorderline', '1945-06-24', 'M', '2 High St', '200-333-4444'),
('Test', 'TestInDanger', '2004-06-18', 'M', '3 Club Road', '300-444-5555'),
('Test', 'TestEarlyOnset', '2002-06-28', 'F', '4 Valley Dr', '400-555-6666');
