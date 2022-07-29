-- phpMyAdmin SQL Dump
-- version 4.1.4
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 26, 2022 at 11:03 PM
-- Server version: 5.6.15-log
-- PHP Version: 5.4.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `pcshop`
--

-- --------------------------------------------------------

--
-- Table structure for table `bills`
--

CREATE TABLE IF NOT EXISTS `bills` (
  `id` int(11) NOT NULL DEFAULT '0',
  `date` varchar(120) DEFAULT NULL,
  `Name` varchar(120) DEFAULT NULL,
  `Quantity` varchar(120) DEFAULT NULL,
  `Price` varchar(120) DEFAULT NULL,
  `Description` varchar(120) DEFAULT NULL,
  `Total` varchar(120) DEFAULT NULL,
  `Warranty` varchar(120) DEFAULT NULL,
  `Employee` varchar(120) DEFAULT NULL,
  `client` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bills`
--

INSERT INTO `bills` (`id`, `date`, `Name`, `Quantity`, `Price`, `Description`, `Total`, `Warranty`, `Employee`, `client`) VALUES
(1, '2022-07-12', 'OTG\nLENOVO', '1\n1', '20.0\n120.0', 'OTG Andoird\n16 GB USB', '140.0', '', 'AbdeLhalim', 'Simo'),
(2, '2022-07-21', 'LENOVO', '1', '120.0', '16 GB USB', '120.0', '', 'AbdeLhalim', 'Lotfi'),
(3, '2022-07-25', 'LENOVO\nThe SN25\nOTG\nGT 750 TI\nThe SN25\nHP Elite Book\nOTG\nGT 710', '10\n10\n10\n5\n1\n5\n5\n5', '120.0\n12.0\n20.0\n950.0\n12.0\n1500.0\n20.0\n700.0', '16 GB USB\nAMD Water Cooling\nOTG Andoird\nNVIDIA\nAMD Water Cooling\n16 GB RAM I5\nOTG Andoird\nNIDIA', '17382.0', '', 'AbdeLhalim', 'Simo'),
(0, '2022-07-26', 'Ryzen\nRazer GS35\nI7\nHK2004\nS3562\nLENOVO', '12\n3\n8\n13\n8\n15', '14.0\n1500.0\n1100.0\n250.0\n600.0\n120.0', 'Ryzen GDJ2 Case\nRazer Motherboard\ni7 5 core 10 Gen\nHP PS\nSmasung Battrey \n16 GB USB', '23318.0', '', 'AbdeLhalim', 'Simo');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE IF NOT EXISTS `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(120) DEFAULT NULL,
  `Quantity` int(11) DEFAULT NULL,
  `Price` int(11) DEFAULT NULL,
  `Description` varchar(120) DEFAULT NULL,
  `Type` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `Name`, `Quantity`, `Price`, `Description`, `Type`) VALUES
(12, 'HP RAM', 4, 200, '2.5 GHs RAMS', 'RAM'),
(2, 'GT 750 TI', 4, 950, 'NVIDIA', 'GPU'),
(3, 'HP Elite Book', 161, 1500, '16 GB RAM I5', 'PC'),
(4, 'I5', 12, 400, 'i5 3 core 3 Gen', 'CPU'),
(5, 'I7', 1, 1100, 'i7 5 core 10 Gen', 'CPU'),
(10, 'GT 710', 5, 700, 'NIDIA', 'GPU'),
(11, 'HP', 3, 750, 'HP Compaq 1600', 'Monitor'),
(13, 'Razer GS35', 1, 1500, 'Razer Motherboard', 'Motherboard'),
(14, 'Ryzen', 3, 14, 'Ryzen GDJ2 Case', 'Case'),
(15, 'HK2004', 2, 250, 'HP PS', 'Power Supplie'),
(16, 'LENOVO', 26, 120, '16 GB USB', 'Storage'),
(17, 'The SN25', 5, 12, 'AMD Water Cooling', 'Coolings'),
(18, 'S3562', 2, 600, 'Smasung Battrey ', 'Battery'),
(19, 'HP DVD250', 10, 50, 'DVD & CD HP', 'Optical Drives'),
(20, 'OTG', 71, 20, 'OTG Andoird', 'Cable'),
(21, 'M530', 6, 300, 'Marvel Keyboard', 'Accessories');

-- --------------------------------------------------------

--
-- Table structure for table `settings`
--

CREATE TABLE IF NOT EXISTS `settings` (
  `PC` varchar(60) DEFAULT NULL,
  `GPU` varchar(60) DEFAULT NULL,
  `CPU` varchar(60) DEFAULT NULL,
  `RAM` varchar(60) DEFAULT NULL,
  `Motherboard` varchar(60) DEFAULT NULL,
  `Battery` varchar(60) DEFAULT NULL,
  `Monitor` varchar(60) DEFAULT NULL,
  `Cas` varchar(60) DEFAULT NULL,
  `PS` varchar(60) DEFAULT NULL,
  `Storag` varchar(60) DEFAULT NULL,
  `Coolings` varchar(60) DEFAULT NULL,
  `OD` varchar(60) DEFAULT NULL,
  `Cable` varchar(60) DEFAULT NULL,
  `Accessories` varchar(60) DEFAULT NULL,
  `id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `settings`
--

INSERT INTO `settings` (`PC`, `GPU`, `CPU`, `RAM`, `Motherboard`, `Battery`, `Monitor`, `Cas`, `PS`, `Storag`, `Coolings`, `OD`, `Cable`, `Accessories`, `id`) VALUES
('1500', '700', '500', '200', '300', '200', '250', '500', '200', '50', '400', '40', '10', '10', 1);

-- --------------------------------------------------------

--
-- Table structure for table `statistic`
--

CREATE TABLE IF NOT EXISTS `statistic` (
  `totalsells` varchar(60) DEFAULT NULL,
  `pcs` varchar(60) DEFAULT NULL,
  `cpus` varchar(60) DEFAULT NULL,
  `gpus` varchar(60) DEFAULT NULL,
  `ram` varchar(60) DEFAULT NULL,
  `md` varchar(60) DEFAULT NULL,
  `b` varchar(60) DEFAULT NULL,
  `m` varchar(60) DEFAULT NULL,
  `c` varchar(60) DEFAULT NULL,
  `psss` varchar(60) DEFAULT NULL,
  `s` varchar(60) DEFAULT NULL,
  `pco` varchar(60) DEFAULT NULL,
  `od` varchar(60) DEFAULT NULL,
  `cable` varchar(60) DEFAULT NULL,
  `ac` varchar(60) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `statistic`
--

INSERT INTO `statistic` (`totalsells`, `pcs`, `cpus`, `gpus`, `ram`, `md`, `b`, `m`, `c`, `psss`, `s`, `pco`, `od`, `cable`, `ac`) VALUES
('114', '7', '9', '10', '0', '3', '8', '0', '12', '13', '26', '11', '0', '15', '0');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(120) DEFAULT NULL,
  `Email` varchar(200) DEFAULT NULL,
  `Password` varchar(120) DEFAULT NULL,
  `perms` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `Name`, `Email`, `Password`, `perms`) VALUES
(1, 'AbdeLhalim', 'admin', 'admin', 'Employee'),
(6, 'AbdeL', 'AbdeL', 'admin', 'Admin');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
