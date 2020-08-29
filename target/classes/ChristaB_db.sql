-- phpMyAdmin SQL Dump
-- version 4.9.3
-- https://www.phpmyadmin.net/
--
-- Generation Time: Aug 27, 2020 at 09:08 PM
-- Server version: 5.7.26
-- PHP Version: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `christaB_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `alerte`
--

CREATE TABLE `alerte` (
  `id_alerte` int(11) NOT NULL,
  `date_alerte` varchar(255) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `motif_alerte` varchar(255) DEFAULT NULL,
  `nbre_alerte` bigint(20) DEFAULT NULL,
  `id_utilisateur` int(11) NOT NULL,
  `id_annonce` int(11) DEFAULT NULL,
  `rembourser` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `annonce`
--

CREATE TABLE `annonce` (
  `id_annonce` int(11) NOT NULL,
  `date_annonce` varchar(255) DEFAULT NULL,
  `date_depart` varchar(255) DEFAULT NULL,
  `depart` varchar(255) DEFAULT NULL,
  `destination` varchar(255) DEFAULT NULL,
  `libelle_annonce` varchar(255) DEFAULT NULL,
  `nbre_personne` int(11) DEFAULT NULL,
  `plage_prix` varchar(255) DEFAULT NULL,
  `publier` tinyint(1) DEFAULT NULL,
  `id_itineraire` int(11) NOT NULL,
  `id_utilisateur` int(11) NOT NULL,
  `lieu_arrivee` varchar(255) DEFAULT NULL,
  `lieu_depart` varchar(255) DEFAULT NULL,
  `prix` int(11) DEFAULT NULL,
  `prix_reservation` int(11) DEFAULT NULL,
  `total_prix` int(11) DEFAULT NULL,
  `id_engin` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `annonce`
--

INSERT INTO `annonce` (`id_annonce`, `date_annonce`, `date_depart`, `depart`, `destination`, `libelle_annonce`, `nbre_personne`, `plage_prix`, `publier`, `id_itineraire`, `id_utilisateur`, `lieu_arrivee`, `lieu_depart`, `prix`, `prix_reservation`, `total_prix`, `id_engin`) VALUES
(1, '2020-08-26', '2020-08-28', 'Ouagadougou', 'Bobo dioulasso', NULL, 4, NULL, 1, 3, 1, 'rond point de la femme', 'Stade du 4 août', 2000, NULL, NULL, 3),
(2, '2020-08-27', '2020-08-31', 'Ouagadougou', 'Koudougou', NULL, 4, NULL, 1, 4, 1, 'Mosquée de koudougou', 'Capuccino', 1500, NULL, NULL, 3),
(3, '2020-08-27', '2020-08-31', 'Bobo dioulasso', 'Koudougou', NULL, 2, NULL, 1, 5, 1, 'Mosquée de koudougou', 'Mosquée de dioulasso', 1000, NULL, NULL, 3),
(4, '2020-08-27', '2020-08-27', NULL, NULL, NULL, 4, NULL, 1, 6, 1, 'Airtel', 'Saint camille', 1000, NULL, NULL, 3),
(5, '2020-08-27', '2020-08-27', NULL, NULL, NULL, 4, NULL, 1, 7, 1, 'Airtel', 'Saint camille', 1000, NULL, NULL, 3),
(6, '2020-08-27', '2020-08-27', NULL, NULL, NULL, 4, NULL, 1, 8, 1, 'Airtel', 'Saint camille', 1000, NULL, NULL, 3),
(7, '2020-08-27', '2020-08-27', NULL, NULL, NULL, 4, NULL, 1, 9, 1, 'Airtel', 'Saint camille', 1000, NULL, NULL, 3),
(8, '2020-08-27', '2020-08-27', NULL, NULL, NULL, 4, NULL, 1, 10, 1, 'Airtel', 'Saint camille', 1000, NULL, NULL, 3);

-- --------------------------------------------------------

--
-- Table structure for table `avis`
--

CREATE TABLE `avis` (
  `id_avis` int(11) NOT NULL,
  `avis` varchar(255) DEFAULT NULL,
  `date_avis` varchar(255) DEFAULT NULL,
  `id_utilisateur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `demande`
--

CREATE TABLE `demande` (
  `id_demande` int(11) NOT NULL,
  `date_demande` varchar(255) DEFAULT NULL,
  `destination` varchar(255) DEFAULT NULL,
  `id_itineraire` int(11) NOT NULL,
  `id_utilisateur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `demande`
--

INSERT INTO `demande` (`id_demande`, `date_demande`, `destination`, `id_itineraire`, `id_utilisateur`) VALUES
(1, '2020-08-26T17:14:14.027Z[Africa/Ouagadougou]', 'Bobo dioulasso', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `engin`
--

CREATE TABLE `engin` (
  `id_engin` int(11) NOT NULL,
  `annee_modele` int(11) DEFAULT NULL,
  `carte_grise` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `marque` varchar(255) DEFAULT NULL,
  `modele` varchar(255) DEFAULT NULL,
  `type_engin` varchar(50) NOT NULL,
  `nbre_place` int(11) DEFAULT NULL,
  `photo_engin` varchar(255) DEFAULT NULL,
  `id_utilisateur` int(11) NOT NULL,
  `couleur` varchar(255) DEFAULT NULL,
  `immatriculation` varchar(255) DEFAULT NULL,
  `photo_assurance` varchar(255) DEFAULT NULL,
  `photo_permis` varchar(255) DEFAULT NULL,
  `type_vehicule` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `engin`
--

INSERT INTO `engin` (`id_engin`, `annee_modele`, `carte_grise`, `description`, `marque`, `modele`, `type_engin`, `nbre_place`, `photo_engin`, `id_utilisateur`, `couleur`, `immatriculation`, `photo_assurance`, `photo_permis`, `type_vehicule`) VALUES
(1, 2018, NULL, 'En bon état', 'Toyota', 'Camry', 'Auto', 3, NULL, 2, NULL, NULL, NULL, NULL, NULL),
(2, 2018, NULL, 'Neuve', 'Mercedes', '4 matic amg', 'Auto', 4, NULL, 2, NULL, NULL, NULL, NULL, NULL),
(3, 2019, NULL, 'Très belle et douce', 'Audi', 'Q2', 'Auto', 4, NULL, 1, 'Bleue', '11 TA 4564', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `historique`
--

CREATE TABLE `historique` (
  `id_historique` int(11) NOT NULL,
  `actif` bit(1) DEFAULT NULL,
  `action` varchar(255) DEFAULT NULL,
  `adresse_ip` varchar(255) DEFAULT NULL,
  `agent_web` varchar(255) DEFAULT NULL,
  `date_operation` datetime DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `id_entite` int(11) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `nom_entite` varchar(255) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  `valeur` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `historique`
--

INSERT INTO `historique` (`id_historique`, `actif`, `action`, `adresse_ip`, `agent_web`, `date_operation`, `deleted`, `id_entite`, `latitude`, `longitude`, `nom_entite`, `user`, `valeur`) VALUES
(1, b'1', 'CREATION', '127.0.0.1', 'Unknown', '2020-06-24 10:35:52', b'0', 1, NULL, NULL, 'TypeFonction', NULL, 'Conducteur'),
(2, b'1', 'CREATION', '127.0.0.1', 'Unknown', '2020-06-24 10:35:59', b'0', 2, NULL, NULL, 'TypeFonction', NULL, 'Passager'),
(3, b'1', 'CREATION', '192.168.2.101', 'Unknown', '2020-07-07 09:40:29', b'0', 1, NULL, NULL, 'Utilisateur', 'anonymousUser', 'Email: wendyataps@gmail.com Password: 12345678 Nom: TAPSOBA Prénom: Wendy Cnib:  Tel: 79079215 Type fonction: 3'),
(4, b'1', 'CREATION', '192.168.2.101', 'Unknown', '2020-07-07 09:46:57', b'0', 1, 0, 0, 'Itineraire', 'wendyataps@gmail.com', 'Type de voyage: urbain Date de départ: 2020-07-11 Date d\'arrivée: 2020-07-11 Heure de départ: 15:00 Heure d\'arrivée: 15:00 Destination: ville Confirmer départ?: null Confrimer arrivée??: null'),
(5, b'1', 'CREATION', '192.168.2.101', 'Unknown', '2020-07-07 09:47:57', b'0', 2, NULL, NULL, 'Annonce', 'wendyataps@gmail.com', 'Libellé: Voyage sur Bobo Date: 2020-07-07 Date de départ: 2020-07-11 Lieu de départ: station total de goughin Destination: Bobo dioulasso Plage prix: 1500-3000 Place: 4 Publier?: true'),
(6, b'1', 'SUPPRESSION', '192.168.2.101', 'Unknown', '2020-07-07 09:48:49', b'1', 2, NULL, NULL, 'Annonce', 'wendyataps@gmail.com', 'Libellé: Voyage sur Bobo Date: 2020-07-07 Date de départ: 2020-07-11 Lieu de départ: station total de goughin Destination: Bobo dioulasso Plage prix: 1500-3000 Place: 4 Publier?: true'),
(7, b'1', 'CREATION', '192.168.0.2', 'Unknown', '2020-07-07 16:37:40', b'0', 2, NULL, NULL, 'Utilisateur', 'anonymousUser', 'Email: dorisflora6@gmail.com Password: 12345678 Nom: SOUDRE Prénom: Doris Flora Cnib: /Users/geraldine/Documents/BAFA-TECH/ChristaB/fichiers/2020-07-07T16:37:39.528.jpeg Tel: 76093421 Type fonction: 2'),
(8, b'1', 'CREATION', '192.168.0.2', 'Unknown', '2020-07-07 16:43:15', b'0', 3, NULL, NULL, 'Utilisateur', 'anonymousUser', 'Email: eakakPovi656@gmail.com Password: 12345678 Nom: AKAKPOVI Prénom: Kevin Cnib: /Users/geraldine/Documents/BAFA-TECH/ChristaB/fichiers/2020-07-07T16:43:14.390.jpeg Tel: 75981431 Type fonction: 2'),
(9, b'1', 'CREATION', '192.168.0.2', 'Unknown', '2020-07-07 17:25:20', b'0', 2, 12.3635004, -1.4961533, 'Itineraire', 'wendyataps@gmail.com', 'Type de voyage: rural Date de départ: 2020-07-11 Date d\'arrivée: 2020-07-11 Heure de départ: 09:30 Heure d\'arrivée: 09:30 Destination: ville Confirmer départ?: null Confrimer arrivée??: null'),
(10, b'1', 'CREATION', '192.168.0.2', 'Unknown', '2020-07-07 17:25:20', b'0', 2, NULL, NULL, 'Annonce', 'wendyataps@gmail.com', 'Libellé: Voyage sur Koudougou Date: 2020-07-07 Date de départ: 2020-07-11 Lieu de départ: rond point de tampouy Destination: Koudougou Plage prix: 1000-2000 Place: 2 Publier?: true');

-- --------------------------------------------------------

--
-- Table structure for table `informations_supplementaires`
--

CREATE TABLE `informations_supplementaires` (
  `id_informations_supplementaires` int(11) NOT NULL,
  `id_annonce` int(11) DEFAULT NULL,
  `id_utilisateur` int(11) DEFAULT NULL,
  `libelle_informations_supplementaires` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `itineraire`
--

CREATE TABLE `itineraire` (
  `id_itineraire` int(11) NOT NULL,
  `confirmer_arrivee` tinyint(4) DEFAULT NULL,
  `confirmer_depart` tinyint(4) DEFAULT NULL,
  `date_arrivee` varchar(255) DEFAULT NULL,
  `date_depart` varchar(255) DEFAULT NULL,
  `heure_arrivee` varchar(255) DEFAULT NULL,
  `heure_depart` varchar(255) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `type_localite` varchar(255) DEFAULT NULL,
  `type_voyage` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `itineraire`
--

INSERT INTO `itineraire` (`id_itineraire`, `confirmer_arrivee`, `confirmer_depart`, `date_arrivee`, `date_depart`, `heure_arrivee`, `heure_depart`, `latitude`, `longitude`, `type_localite`, `type_voyage`) VALUES
(1, NULL, NULL, '2020-07-11', '2020-07-11', '15:00', '10:00', 0, 0, 'ville', 'urbain'),
(2, NULL, NULL, '2020-07-11', '2020-07-11', '09:30', '07:30', 12.3635004, -1.4961533, 'ville', 'rural'),
(3, NULL, NULL, '2020-08-28', '2020-08-28', '18:53', '12:53', 12.3635092, -1.4961818999999998, NULL, NULL),
(4, NULL, NULL, '2020-08-31', '2020-08-31', '11:30', '10:30', 12.3635029, -1.4961673, NULL, NULL),
(5, NULL, NULL, '2020-08-31', '2020-08-31', '14:30', '11:30', 12.3635029, -1.4961673, NULL, NULL),
(6, NULL, NULL, '2020-08-27', '2020-08-27', '13:45', '13:32', 12.3635458, -1.4961786, NULL, NULL),
(7, NULL, NULL, '2020-08-27', '2020-08-27', '13:45', '13:32', 12.3635458, -1.4961786, NULL, NULL),
(8, NULL, NULL, '2020-08-27', '2020-08-27', '13:45', '13:32', 12.3635458, -1.4961786, NULL, NULL),
(9, NULL, NULL, '2020-08-27', '2020-08-27', '13:45', '13:32', 12.3635458, -1.4961786, NULL, NULL),
(10, NULL, NULL, '2020-08-27', '2020-08-27', '13:45', '13:32', 12.3635458, -1.4961786, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `paiement`
--

CREATE TABLE `paiement` (
  `id_paiement` int(11) NOT NULL,
  `code_otp` int(11) DEFAULT NULL,
  `date_paiement` varchar(255) DEFAULT NULL,
  `montant_paiement` bigint(20) DEFAULT NULL,
  `numero_client` bigint(20) DEFAULT NULL,
  `id_utilisateur` int(11) NOT NULL,
  `heure_paiement` varchar(255) DEFAULT NULL,
  `numero_paiement` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `type_avis`
--

CREATE TABLE `type_avis` (
  `id_type_avis` int(11) NOT NULL,
  `libelle_type_avis` varchar(255) DEFAULT NULL,
  `id_avis` int(11) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `type_avis`
--

INSERT INTO `type_avis` (`id_type_avis`, `libelle_type_avis`, `id_avis`, `note`) VALUES
(1, NULL, 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `type_fonction`
--

CREATE TABLE `type_fonction` (
  `id_type_fonction` smallint(6) NOT NULL,
  `libelle_type_fonction` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `type_fonction`
--

INSERT INTO `type_fonction` (`id_type_fonction`, `libelle_type_fonction`) VALUES
(1, 'Conducteur'),
(2, 'Passager');

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id_utilisateur` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `cnib` varchar(10500) DEFAULT NULL,
  `id_type_fonction` int(11) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `tel` bigint(20) DEFAULT NULL,
  `date_delivrance` varchar(255) DEFAULT NULL,
  `date_expiration` varchar(255) DEFAULT NULL,
  `lieu_delivrance` varchar(255) DEFAULT NULL,
  `numero_cnib` varchar(255) DEFAULT NULL,
  `type_document` varchar(255) DEFAULT NULL,
  `afficher_email` bit(1) DEFAULT NULL,
  `afficher_tel` bit(1) DEFAULT NULL,
  `courrier_evaluation` bit(1) DEFAULT NULL,
  `courrier_promotion` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `utilisateur`
--

INSERT INTO `utilisateur` (`id_utilisateur`, `email`, `cnib`, `id_type_fonction`, `nom`, `photo`, `prenom`, `tel`, `date_delivrance`, `date_expiration`, `lieu_delivrance`, `numero_cnib`, `type_document`, `afficher_email`, `afficher_tel`, `courrier_evaluation`, `courrier_promotion`) VALUES
(1, 'wendyataps@gmail.com', NULL, 1, 'TAPSOBA', '2020-08-20T00:24:10.474.jpeg', 'Wendy', 79079218, '2016-01-04', '2026-01-06', 'Ouagadougou', 'B079813', 'CNIB', NULL, NULL, NULL, NULL),
(2, 'dorisflora6@gmail.com', NULL, 1, 'SOUDRE', NULL, 'Doris Flora', 76093421, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `alerte`
--
ALTER TABLE `alerte`
  ADD PRIMARY KEY (`id_alerte`),
  ADD KEY `FK1o53mdy7lhopl5rtox787u2kh` (`id_utilisateur`);

--
-- Indexes for table `annonce`
--
ALTER TABLE `annonce`
  ADD PRIMARY KEY (`id_annonce`),
  ADD KEY `FKpu6ku9pwv0uh4shpy4f7qpmox` (`id_itineraire`),
  ADD KEY `FK5nxvosrdfhyche1rdl1gha3nv` (`id_utilisateur`);

--
-- Indexes for table `avis`
--
ALTER TABLE `avis`
  ADD PRIMARY KEY (`id_avis`),
  ADD KEY `FKf881mvur1o8kp834d15fgb2i3` (`id_utilisateur`);

--
-- Indexes for table `demande`
--
ALTER TABLE `demande`
  ADD PRIMARY KEY (`id_demande`),
  ADD KEY `FKniutip13hcqds3i2uhwdnklyp` (`id_itineraire`),
  ADD KEY `FKb059d4y82fx7wlpgh74kucbxm` (`id_utilisateur`);

--
-- Indexes for table `engin`
--
ALTER TABLE `engin`
  ADD PRIMARY KEY (`id_engin`),
  ADD KEY `FKhnql0qagrmpq42vwyivjca1gs` (`id_utilisateur`);

--
-- Indexes for table `historique`
--
ALTER TABLE `historique`
  ADD PRIMARY KEY (`id_historique`);

--
-- Indexes for table `informations_supplementaires`
--
ALTER TABLE `informations_supplementaires`
  ADD PRIMARY KEY (`id_informations_supplementaires`);

--
-- Indexes for table `itineraire`
--
ALTER TABLE `itineraire`
  ADD PRIMARY KEY (`id_itineraire`);

--
-- Indexes for table `paiement`
--
ALTER TABLE `paiement`
  ADD PRIMARY KEY (`id_paiement`),
  ADD KEY `FKlgaejmjhcf6n3wnk09e0a61w3` (`id_utilisateur`);

--
-- Indexes for table `type_avis`
--
ALTER TABLE `type_avis`
  ADD PRIMARY KEY (`id_type_avis`);

--
-- Indexes for table `type_fonction`
--
ALTER TABLE `type_fonction`
  ADD PRIMARY KEY (`id_type_fonction`);

--
-- Indexes for table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id_utilisateur`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `alerte`
--
ALTER TABLE `alerte`
  MODIFY `id_alerte` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `annonce`
--
ALTER TABLE `annonce`
  MODIFY `id_annonce` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `avis`
--
ALTER TABLE `avis`
  MODIFY `id_avis` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `demande`
--
ALTER TABLE `demande`
  MODIFY `id_demande` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `engin`
--
ALTER TABLE `engin`
  MODIFY `id_engin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `historique`
--
ALTER TABLE `historique`
  MODIFY `id_historique` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2869;

--
-- AUTO_INCREMENT for table `paiement`
--
ALTER TABLE `paiement`
  MODIFY `id_paiement` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `type_avis`
--
ALTER TABLE `type_avis`
  MODIFY `id_type_avis` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `alerte`
--
ALTER TABLE `alerte`
  ADD CONSTRAINT `FK1o53mdy7lhopl5rtox787u2kh` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`);

--
-- Constraints for table `annonce`
--
ALTER TABLE `annonce`
  ADD CONSTRAINT `FK5nxvosrdfhyche1rdl1gha3nv` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`),
  ADD CONSTRAINT `FKpu6ku9pwv0uh4shpy4f7qpmox` FOREIGN KEY (`id_itineraire`) REFERENCES `itineraire` (`id_itineraire`);

--
-- Constraints for table `avis`
--
ALTER TABLE `avis`
  ADD CONSTRAINT `FKf881mvur1o8kp834d15fgb2i3` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`);

--
-- Constraints for table `demande`
--
ALTER TABLE `demande`
  ADD CONSTRAINT `FKb059d4y82fx7wlpgh74kucbxm` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`),
  ADD CONSTRAINT `FKniutip13hcqds3i2uhwdnklyp` FOREIGN KEY (`id_itineraire`) REFERENCES `itineraire` (`id_itineraire`);

--
-- Constraints for table `engin`
--
ALTER TABLE `engin`
  ADD CONSTRAINT `FKhnql0qagrmpq42vwyivjca1gs` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`);

--
-- Constraints for table `paiement`
--
ALTER TABLE `paiement`
  ADD CONSTRAINT `FKlgaejmjhcf6n3wnk09e0a61w3` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`);
