-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 05, 2014 at 04:59 PM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `tetris`
--

-- --------------------------------------------------------

--
-- Table structure for table `high score`
--

CREATE TABLE IF NOT EXISTS `high score` (
  `Nom` varchar(15) NOT NULL,
  `Temps` int(5) NOT NULL,
  `Lignes` int(4) NOT NULL,
  `Niv` int(2) NOT NULL,
  `Score` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `high score`
--

INSERT INTO `high score` (`Nom`, `Temps`, `Lignes`, `Niv`, `Score`) VALUES
('Azfar', 10, 12, 2, 85),
('Donavan', 100, 152, 52, 585),
('Azfar', 150, 152, 25, 8515465);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
