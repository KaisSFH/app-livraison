CREATE DATABASE IF NOT EXISTS BDG_LivraisonCom_25;
USE BDG_LivraisonCom_25;

-- 7. Postes
CREATE TABLE Postes (
    codeposte INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL,
    indice INT
);

-- 6. Personnel
CREATE TABLE Personnel (
    idpers INT AUTO_INCREMENT PRIMARY KEY,
    nompers VARCHAR(50) NOT NULL,
    prenompers VARCHAR(50) NOT NULL,
    adrpers VARCHAR(255),
    villepers VARCHAR(100),
    telpers VARCHAR(20),
    d_embauche DATE,
    login VARCHAR(50) UNIQUE NOT NULL,
    motP VARCHAR(255) NOT NULL,
    codeposte INT,
    FOREIGN KEY (codeposte) REFERENCES Postes(codeposte)
);

-- 2. Clients
CREATE TABLE Clients (
    noclt INT AUTO_INCREMENT PRIMARY KEY,
    nomclt VARCHAR(50) NOT NULL,
    prenomclt VARCHAR(50),
    adrclt VARCHAR(255),
    villeclt VARCHAR(100),
    code_postal VARCHAR(20),
    telclt VARCHAR(20),
    adrmail VARCHAR(100)
);

-- 1. Articles
CREATE TABLE Articles (
    refart VARCHAR(20) PRIMARY KEY,
    designation VARCHAR(100) NOT NULL,
    prixA DECIMAL(10, 2),
    prixV DECIMAL(10, 2),
    codetva INT,
    categorie VARCHAR(50),
    qtestk INT
);

-- 3. Commandes
CREATE TABLE Commandes (
    nocde INT AUTO_INCREMENT PRIMARY KEY,
    noclt INT,
    datecde DATE,
    etatcde VARCHAR(50),
    FOREIGN KEY (noclt) REFERENCES Clients(noclt)
);

-- 4. LigCdes (Lignes de commandes)
CREATE TABLE LigCdes (
    nocde INT,
    refart VARCHAR(20),
    qtecde INT,
    PRIMARY KEY (nocde, refart),
    FOREIGN KEY (nocde) REFERENCES Commandes(nocde),
    FOREIGN KEY (refart) REFERENCES Articles(refart)
);

-- 5. LivraisonCom
CREATE TABLE LivraisonCom (
    nocde INT PRIMARY KEY,
    dateliv DATE,
    livreur INT,
    modepay VARCHAR(50),
    etatliv VARCHAR(50),
    remarque TEXT,
    FOREIGN KEY (nocde) REFERENCES Commandes(nocde),
    FOREIGN KEY (livreur) REFERENCES Personnel(idpers)
);

-- 8. Messages
CREATE TABLE Messages (
    idMessage INT AUTO_INCREMENT PRIMARY KEY,
    expediteur INT,
    destinataire INT,
    nocde INT,
    contactClient VARCHAR(50),
    contenu TEXT NOT NULL,
    typeMessage VARCHAR(50),
    dateMessage DATETIME,
    lu BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (expediteur) REFERENCES Personnel(idpers),
    FOREIGN KEY (destinataire) REFERENCES Personnel(idpers),
    FOREIGN KEY (nocde) REFERENCES Commandes(nocde)
);

-- ==========================================
-- JEU DE DONNÉES DE TEST
-- ==========================================

INSERT INTO Postes (libelle, indice) VALUES 
('Contrôleur', 1), 
('Livreur', 2);

-- Mot de passe "1234" pour tous les utilisateurs pour faciliter les tests
INSERT INTO Personnel (nompers, prenompers, login, motP, codeposte) VALUES 
('Dupont', 'Jean', 'controleur1', '1234', 1),
('Martin', 'Paul', 'livreur1', '1234', 2),
('Durand', 'Jacques', 'livreur2', '1234', 2);

INSERT INTO Clients (nomclt, prenomclt, villeclt, telclt, adrclt) VALUES 
('Ben Salah', 'Ahmed', 'Tunis', '22000001', '10 Rue de la Liberté'),
('Trabelsi', 'Sami', 'Sousse', '22000002', '15 Avenue Habib Bourguiba'),
('Mejri', 'Fatma', 'Sfax', '22000003', 'Route de Tunis km 2');

INSERT INTO Articles (refart, designation, prixV, qtestk) VALUES 
('ART01', 'PC Portable Dell', 1500.00, 50),
('ART02', 'Souris sans fil', 40.00, 200),
('ART03', 'Clavier Mécanique', 120.00, 100);

INSERT INTO Commandes (noclt, datecde, etatcde) VALUES 
(1, CURDATE(), 'Validée'),
(2, CURDATE(), 'Validée'),
(3, CURDATE(), 'Validée');

INSERT INTO LigCdes (nocde, refart, qtecde) VALUES 
(1, 'ART01', 1),
(1, 'ART02', 1),
(2, 'ART01', 2),
(3, 'ART03', 5);

INSERT INTO LivraisonCom (nocde, dateliv, livreur, modepay, etatliv, remarque) VALUES 
(1, CURDATE(), 2, 'Espèces', 'En cours', ''),
(2, CURDATE(), 3, 'Carte Bancaire', 'Livrée', 'Colis déposé au gardien'),
(3, CURDATE(), 2, 'Espèces', 'En cours', '');
