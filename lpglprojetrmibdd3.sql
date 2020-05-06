-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  sam. 28 mars 2020 à 20:26
-- Version du serveur :  5.7.23
-- Version de PHP :  7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `lpglprojetrmi`
--

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `rue` varchar(255) NOT NULL,
  `cp` int(11) NOT NULL,
  `numRue` int(11) NOT NULL,
  `age` int(11) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `mdp` varchar(255) NOT NULL,
  `idPanier` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idPanier` (`idPanier`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id`, `nom`, `prenom`, `rue`, `cp`, `numRue`, `age`, `mail`, `mdp`, `idPanier`) VALUES
(1, 'root', 'root', 'rue root', 57000, 10, 10, 'root@gmail.com', 'root', 1),
(2, 'Lelievre', 'Julien', 'rue Kellermann', 57070, 20, 26, 'julien@gmail.com', 'julien', 2),
(3, 'Muller', 'Geoffroy', 'rue de L\'abbé', 88000, 10, 21, 'geoff@gmail.com', 'geoff', 3),
(4, 'Robert', 'Etienne', 'rue du maire', 57000, 10, 21, 'etienne@gmail.com', 'etienne', 4);

-- --------------------------------------------------------

--
-- Structure de la table `client_banque`
--

DROP TABLE IF EXISTS `client_banque`;
CREATE TABLE IF NOT EXISTS `client_banque` (
  `idClient` int(11) NOT NULL AUTO_INCREMENT,
  `identifiantsBancaires` varchar(255) NOT NULL,
  `solde` double NOT NULL,
  PRIMARY KEY (`idClient`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `client_banque`
--

INSERT INTO `client_banque` (`idClient`, `identifiantsBancaires`, `solde`) VALUES
(1, 'FR123ROOT', 100),
(2, 'FR123JULIEN', 150),
(3, 'FR123GEOFF', 220),
(4, 'FFR123ETIENNE', 450);

-- --------------------------------------------------------

--
-- Structure de la table `constituer`
--

DROP TABLE IF EXISTS `constituer`;
CREATE TABLE IF NOT EXISTS `constituer` (
  `idPanier` int(11) NOT NULL,
  `idProduit` int(11) NOT NULL,
  `quantiteProduit` int(11) DEFAULT '1',
  PRIMARY KEY (`idPanier`,`idProduit`),
  KEY `FK_idProduit` (`idProduit`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `panier`
--

DROP TABLE IF EXISTS `panier`;
CREATE TABLE IF NOT EXISTS `panier` (
  `idPanier` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idPanier`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `panier`
--

INSERT INTO `panier` (`idPanier`) VALUES
(1),
(2),
(3),
(4);

-- --------------------------------------------------------

--
-- Structure de la table `produits`
--

DROP TABLE IF EXISTS `produits`;
CREATE TABLE IF NOT EXISTS `produits` (
  `idProduits` int(11) NOT NULL AUTO_INCREMENT,
  `nomProduit` varchar(255) NOT NULL,
  `unitStock` int(11) DEFAULT NULL,
  `prixUnit` double DEFAULT NULL,
  `qteParUnit` int(11) DEFAULT NULL,
  `taille` double DEFAULT NULL,
  `poids` double DEFAULT NULL,
  `couleur` varchar(255) DEFAULT NULL,
  `produitDispo` tinyint(1) DEFAULT NULL,
  `remise` double DEFAULT NULL,
  `produitDesc` varchar(255) DEFAULT NULL,
  `note` int(11) DEFAULT NULL,
  PRIMARY KEY (`idProduits`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `produits`
--

INSERT INTO `produits` (`idProduits`, `nomProduit`, `unitStock`, `prixUnit`, `qteParUnit`, `taille`, `poids`, `couleur`, `produitDispo`, `remise`, `produitDesc`, `note`) VALUES
(1, 'Scie Stanley', NULL, 16.27, NULL, 20, 10, 'jaune', 1, 0, 'Scie Jetcute coupe fine 500mm', NULL),
(2, 'Aspirateur Karcher', NULL, 113.94, NULL, 110, 34, 'jaune', 1, 0, 'Aspirateur VC3 sans sac', NULL),
(3, 'Meuleuse Bocsh', NULL, 81.9, NULL, 28, 12, 'bleu', 1, 0, 'Meuleuse angulaire 2 mains GWS 1400', NULL),
(4, 'Perceuse Yamaha', NULL, 230.4, NULL, 43, 6, 'rouge', 1, 0, 'Perceuse sans fil GSR 18V-28V', NULL),
(5, 'Gants Stanley', NULL, 2.57, NULL, 20, 2, 'vert', 1, 0, 'Gents vert tout nitrile nitrex 802', NULL);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `client_ibfk_1` FOREIGN KEY (`idPanier`) REFERENCES `panier` (`idPanier`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
