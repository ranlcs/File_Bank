CREATE TABLE compte(
		id VARCHAR(30) NOT NULL,
		salt VARCHAR(700),
        cle VARCHAR(100),
		admin INT,
		nom VARCHAR(10),

	PRIMARY KEY(id)
);

CREATE TABLE fichier(
        id INT NOT NULL AUTO_INCREMENT,
        uploader_par VARCHAR(30),
        approuver_par VARCHAR(30),
        commentaire VARCHAR(50),
        format VARCHAR(7),
        fichier LONGBLOB,
        date_up DATE,
        sary MEDIUMBLOB,
        
    FOREIGN KEY (uploader_par) REFERENCES compte(id),
    FOREIGN KEY (approuver_par) REFERENCES compte(id),
    PRIMARY KEY(id)
);