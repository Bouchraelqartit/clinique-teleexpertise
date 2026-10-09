CREATE DATABASE clinique_teleexpertise;


USE clinique_teleexpertise;

CREATE TABLE utilisateur (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('INFIRMIER', 'GENERALISTE') NOT NULL
);

CREATE TABLE patient (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    date_naissance DATE NOT NULL,
    numero_securite_sociale VARCHAR(50) NOT NULL UNIQUE,
    tension_arterielle VARCHAR(20) NOT NULL,
    frequence_cardiaque INT NOT NULL,
    temperature DECIMAL(4,1) NOT NULL,
    frequence_respiratoire INT NOT NULL,
    heure_arrivee DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE consultation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    motif TEXT NOT NULL,
    observations TEXT,
    diagnostic TEXT NOT NULL,
    traitement TEXT,
    cout DECIMAL(10,2) NOT NULL DEFAULT 150.00,
    statut ENUM('TERMINEE') NOT NULL DEFAULT 'TERMINEE',
    date_consultation DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    patient_id INT NOT NULL,
    medecin_id INT NOT NULL,

    CONSTRAINT fk_consultation_patient
        FOREIGN KEY (patient_id)
        REFERENCES patient(id),

    CONSTRAINT fk_consultation_medecin
        FOREIGN KEY (medecin_id)
        REFERENCES utilisateur(id)
);