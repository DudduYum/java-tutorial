/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  rkostyuk
 * Created: Jan 23, 2024
 */

CREATE TABLE IF NOT EXISTS Persone (
                Id MEDIUMINT NOT NULL AUTO_INCREMENT,
                Nome varchar(100),
                Cognome varchar(100),
                Citta varchar(100),
                Indirizzo varchar(255),
                PRIMARY KEY (Id)
);