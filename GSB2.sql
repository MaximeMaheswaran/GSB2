-- Adminer 4.8.1 MySQL 5.5.5-10.5.21-MariaDB-0+deb11u1 dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `visiter`;
DROP TABLE IF EXISTS `animer`;
DROP TABLE IF EXISTS `reserver`;
DROP TABLE IF EXISTS `presentation`;
DROP TABLE IF EXISTS `animateur`;
DROP TABLE IF EXISTS `conference`;
DROP TABLE IF EXISTS `intervenant`;
DROP TABLE IF EXISTS `personne`;
DROP TABLE IF EXISTS `salle`;
DROP TABLE IF EXISTS `visiteur`;






CREATE TABLE `animateur` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `animateur` (`id`) VALUES
(3),
(4),
(5),
(6),
(7);


CREATE TABLE `conference` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `theme` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `conference` (`id`, `theme`) VALUES
(1,	'Test'),
(2,	'test'),
(3,	'testdeux'),
(4,	'testtrois'),
(5,	'test');

CREATE TABLE `intervenant` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `intervenant` (`id`) VALUES
(2);

CREATE TABLE `personne` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) DEFAULT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `login` varchar(50) DEFAULT NULL,
  `mdp` varchar(50) DEFAULT NULL,
  `secretaire` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `personne` (`id`, `nom`, `prenom`, `login`, `mdp`, `secretaire`) VALUES
(1,	'Calvo',	'Maxence',	'mcalvo',	'root',	1),
(2,	'Mahesweran',	'Maxime',	'mmaheswaran',	'user',	0),
(3,	'Dubois',	'Marie',	'mdubois',	'mdubois123',	0),
(4,	'Lefevre',	'Antoine',	'alefevre',	'alefevre123',	0),
(5,	'Martin',	'Sophie',	'smartin',	'smartin123',	0),
(6,	'Bernard',	'Thomas',	'tbernard',	'tbernard123',	0),
(7,	'Dupont',	'Camille',	'cdupont',	'cdupont123',	0);




CREATE TABLE `salle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) DEFAULT NULL,
  `capaciteMax` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `salle` (`id`, `nom`, `capaciteMax`) VALUES
(1,	'D-203',	18),
(2,	'D-201',	32);

CREATE TABLE `presentation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datee` date DEFAULT NULL,
  `nbPlacesDispo` int(11) DEFAULT NULL,
  `horaire` time DEFAULT NULL,
  `dureePrevue` time DEFAULT NULL,
  `salle_id` int(11) DEFAULT NULL,
  `conference_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `salle_id` (`salle_id`),
  KEY `conference_id` (`conference_id`),
  CONSTRAINT `presentation_ibfk_1` FOREIGN KEY (`salle_id`) REFERENCES `salle` (`id`),
  CONSTRAINT `presentation_ibfk_2` FOREIGN KEY (`conference_id`) REFERENCES `conference` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
INSERT INTO `presentation` (`id`, `datee`, `nbPlacesDispo`, `horaire`, `dureePrevue`, `salle_id`, `conference_id`) VALUES
(1,	NULL,	NULL,	NULL,	NULL,	NULL,	2),
(2,	NULL,	NULL,	NULL,	NULL,	NULL,	3),
(3,	NULL,	NULL,	NULL,	NULL,	NULL,	4),
(4,	NULL,	NULL,	NULL,	NULL,	NULL,	4),
(5,	NULL,	NULL,	NULL,	NULL,	NULL,	5),
(6,	NULL,	NULL,	NULL,	NULL,	NULL,	5);


CREATE TABLE `animer` (
  `animateur_id` int(11) NOT NULL,
  `presentation_id` int(11) NOT NULL,
  PRIMARY KEY (`animateur_id`,`presentation_id`),
  KEY `presentation_id` (`presentation_id`),
  CONSTRAINT `animer_ibfk_1` FOREIGN KEY (`presentation_id`) REFERENCES `presentation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
INSERT INTO `animer` (`animateur_id`, `presentation_id`) VALUES
(3,	1);

CREATE TABLE `reserver` (
  `intervenant_id` int(11) NOT NULL,
  `presentation_id` int(11) NOT NULL,
  PRIMARY KEY (`intervenant_id`,`presentation_id`),
  KEY `presentation_id` (`presentation_id`),
  CONSTRAINT `reserver_ibfk_1` FOREIGN KEY (`presentation_id`) REFERENCES `presentation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `visiteur` (
  `id` char(4) NOT NULL,
  `nom` char(30) DEFAULT NULL,
  `prenom` char(30) DEFAULT NULL,
  `login` char(20) DEFAULT NULL,
  `mdp` char(20) DEFAULT NULL,
  `adresse` char(30) DEFAULT NULL,
  `cp` char(5) DEFAULT NULL,
  `ville` char(30) DEFAULT NULL,
  `dateEmbauche` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `visiteur` (`id`, `nom`, `prenom`, `login`, `mdp`, `adresse`, `cp`, `ville`, `dateEmbauche`) VALUES
('a131',	'Villechalane',	'Louis',	'lvillachane',	'jux7g',	'8 rue des Charmes',	'46000',	'Cahors',	'2005-12-21'),
('a17',	'Andre',	'David',	'dandre',	'oppg5',	'1 rue Petit',	'46200',	'Lalbenque',	'1998-11-23'),
('a55',	'Bedos',	'Christian',	'cbedos',	'gmhxd',	'1 rue Peranud',	'46250',	'Montcuq',	'1995-01-12'),
('a93',	'Tusseau',	'Louis',	'ltusseau',	'ktp3s',	'22 rue des Ternes',	'46123',	'Gramat',	'2000-05-01'),
('b13',	'Bentot',	'Pascal',	'pbentot',	'doyw1',	'11 allée des Cerises',	'46512',	'Bessines',	'1992-07-09'),
('b16',	'Bioret',	'Luc',	'lbioret',	'hrjfs',	'1 Avenue gambetta',	'46000',	'Cahors',	'1998-05-11'),
('b19',	'Bunisset',	'Francis',	'fbunisset',	'4vbnd',	'10 rue des Perles',	'93100',	'Montreuil',	'1987-10-21'),
('b25',	'Bunisset',	'Denise',	'dbunisset',	's1y1r',	'23 rue Manin',	'75019',	'paris',	'2010-12-05'),
('b28',	'Cacheux',	'Bernard',	'bcacheux',	'uf7r3',	'114 rue Blanche',	'75017',	'Paris',	'2009-11-12'),
('b34',	'Cadic',	'Eric',	'ecadic',	'6u8dc',	'123 avenue de la République',	'75011',	'Paris',	'2008-09-23'),
('b4',	'Charoze',	'Catherine',	'ccharoze',	'u817o',	'100 rue Petit',	'75019',	'Paris',	'2005-11-12'),
('b50',	'Clepkens',	'Christophe',	'cclepkens',	'bw1us',	'12 allée des Anges',	'93230',	'Romainville',	'2003-08-11'),
('b59',	'Cottin',	'Vincenne',	'vcottin',	'2hoh9',	'36 rue Des Roches',	'93100',	'Monteuil',	'2001-11-18'),
('c14',	'Daburon',	'François',	'fdaburon',	'7oqpv',	'13 rue de Chanzy',	'94000',	'Créteil',	'2002-02-11'),
('c3',	'De',	'Philippe',	'pde',	'gk9kx',	'13 rue Barthes',	'94000',	'Créteil',	'2010-12-14'),
('c54',	'Debelle',	'Michel',	'mdebelle',	'od5rt',	'181 avenue Barbusse',	'93210',	'Rosny',	'2006-11-23'),
('d13',	'Debelle',	'Jeanne',	'jdebelle',	'nvwqq',	'134 allée des Joncs',	'44000',	'Nantes',	'2000-05-11'),
('d51',	'Debroise',	'Michel',	'mdebroise',	'sghkb',	'2 Bld Jourdain',	'44000',	'Nantes',	'2001-04-17'),
('e22',	'Desmarquest',	'Nathalie',	'ndesmarquest',	'f1fob',	'14 Place d Arc',	'45000',	'Orléans',	'2005-11-12'),
('e24',	'Desnost',	'Pierre',	'pdesnost',	'4k2o5',	'16 avenue des Cèdres',	'23200',	'Guéret',	'2001-02-05'),
('e39',	'Dudouit',	'Frédéric',	'fdudouit',	'44im8',	'18 rue de l église',	'23120',	'GrandBourg',	'2000-08-01'),
('e49',	'Duncombe',	'Claude',	'cduncombe',	'qf77j',	'19 rue de la tour',	'23100',	'La souteraine',	'1987-10-10'),
('e5',	'Enault-Pascreau',	'Céline',	'cenault',	'y2qdu',	'25 place de la gare',	'23200',	'Gueret',	'1995-09-01'),
('e52',	'Eynde',	'Valérie',	'veynde',	'i7sn3',	'3 Grand Place',	'13015',	'Marseille',	'1999-11-01'),
('f21',	'Finck',	'Jacques',	'jfinck',	'mpb3t',	'10 avenue du Prado',	'13002',	'Marseille',	'2001-11-10'),
('f39',	'Frémont',	'Fernande',	'ffremont',	'xs5tq',	'4 route de la mer',	'13012',	'Allauh',	'1998-10-01'),
('f4',	'Gest',	'Alain',	'agest',	'dywvt',	'30 avenue de la mer',	'13025',	'Berre',	'1985-11-01');

CREATE TABLE `visiter` (
  `id_visiteur` char(4) NOT NULL,
  `id_presentation` int(11) NOT NULL,
  PRIMARY KEY (`id_visiteur`,`id_presentation`),
  KEY `id_presentation` (`id_presentation`),
  CONSTRAINT `visiter_ibfk_1` FOREIGN KEY (`id_visiteur`) REFERENCES `visiteur` (`id`),
  CONSTRAINT `visiter_ibfk_2` FOREIGN KEY (`id_presentation`) REFERENCES `presentation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- 2023-12-07 11:57:00
