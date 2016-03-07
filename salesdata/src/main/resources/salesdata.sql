-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 04, 2016 at 03:15 PM
-- Server version: 5.5.44-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `huaweitest1`
--

-- --------------------------------------------------------

--
-- Table structure for table `CountryCodes`
--

CREATE TABLE IF NOT EXISTS `CountryCodes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(3) NOT NULL,
  `name` varchar(15) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `code` (`code`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=178 ;

--
-- Dumping data for table `CountryCodes`
--

INSERT INTO `CountryCodes` (`id`, `code`, `name`) VALUES
(1, 'AE', 'United Arab Emi'),
(2, 'AF', 'Afghanistan'),
(3, 'AL', 'Albania'),
(4, 'AM', 'Armenia'),
(5, 'AO', 'Angola'),
(6, 'AR', 'Argentina'),
(7, 'AT', 'Austria'),
(8, 'AU', 'Australia'),
(9, 'AZ', 'Azerbaijan'),
(10, 'BA', 'Bosnia and Herz'),
(11, 'BD', 'Bangladesh'),
(12, 'BE', 'Belgium'),
(13, 'BF', 'Burkina Faso'),
(14, 'BG', 'Bulgaria'),
(15, 'BI', 'Burundi'),
(16, 'BJ', 'Benin'),
(17, 'BN', 'Brunei'),
(18, 'BO', 'Bolivia'),
(19, 'BR', 'Brazil'),
(20, 'BS', 'Bahamas'),
(21, 'BT', 'Bhutan'),
(22, 'BW', 'Botswana'),
(23, 'BY', 'Belarus'),
(24, 'BZ', 'Belize'),
(25, 'CA', 'Canada'),
(26, 'CD', 'Dem. Rep. Congo'),
(27, 'CF', 'Central African'),
(28, 'CG', 'Congo'),
(29, 'CH', 'Switzerland'),
(30, 'CI', 'Côte d''Ivoire'),
(31, 'CL', 'Chile'),
(32, 'CM', 'Cameroon'),
(33, 'CN', 'China'),
(34, 'CO', 'Colombia'),
(35, 'cod', 'name'),
(36, 'CR', 'Costa Rica'),
(37, 'CU', 'Cuba'),
(38, 'CY', 'Cyprus'),
(39, 'CZ', 'Czech Rep.'),
(40, 'DE', 'Germany'),
(41, 'DJ', 'Djibouti'),
(42, 'DK', 'Denmark'),
(43, 'DO', 'Dominican Rep.'),
(44, 'DZ', 'Algeria'),
(45, 'EC', 'Ecuador'),
(46, 'EE', 'Estonia'),
(47, 'EG', 'Egypt'),
(48, 'EH', 'W. Sahara'),
(49, 'ER', 'Eritrea'),
(50, 'ES', 'Spain'),
(51, 'ET', 'Ethiopia'),
(52, 'FI', 'Finland'),
(53, 'FJ', 'Fiji'),
(54, 'FK', 'Falkland Is.'),
(55, 'FR', 'France'),
(56, 'GA', 'Gabon'),
(57, 'GB', 'United Kingdom'),
(58, 'GE', 'Georgia'),
(59, 'GH', 'Ghana'),
(60, 'GL', 'Greenland'),
(61, 'GM', 'Gambia'),
(62, 'GN', 'Guinea'),
(63, 'GQ', 'Eq. Guinea'),
(64, 'GR', 'Greece'),
(65, 'GT', 'Guatemala'),
(66, 'GW', 'Guinea-Bissau'),
(67, 'GY', 'Guyana'),
(68, 'HN', 'Honduras'),
(69, 'HR', 'Croatia'),
(70, 'HT', 'Haiti'),
(71, 'HU', 'Hungary'),
(72, 'ID', 'Indonesia'),
(73, 'IE', 'Ireland'),
(74, 'IL', 'Israel'),
(75, 'IN', 'India'),
(76, 'IQ', 'Iraq'),
(77, 'IR', 'Iran'),
(78, 'IS', 'Iceland'),
(79, 'IT', 'Italy'),
(80, 'JM', 'Jamaica'),
(81, 'JO', 'Jordan'),
(82, 'JP', 'Japan'),
(83, 'KE', 'Kenya'),
(84, 'KG', 'Kyrgyzstan'),
(85, 'KH', 'Cambodia'),
(86, 'KP', 'Dem. Rep. Korea'),
(87, 'KR', 'Korea'),
(88, 'KW', 'Kuwait'),
(89, 'KZ', 'Kazakhstan'),
(90, 'LA', 'Lao PDR'),
(91, 'LB', 'Lebanon'),
(92, 'LK', 'Sri Lanka'),
(93, 'LR', 'Liberia'),
(94, 'LS', 'Lesotho'),
(95, 'LT', 'Lithuania'),
(96, 'LU', 'Luxembourg'),
(97, 'LV', 'Latvia'),
(98, 'LY', 'Libya'),
(99, 'MA', 'Morocco'),
(100, 'MD', 'Moldova'),
(101, 'ME', 'Montenegro'),
(102, 'MG', 'Madagascar'),
(103, 'MK', 'Macedonia'),
(104, 'ML', 'Mali'),
(105, 'MM', 'Myanmar'),
(106, 'MN', 'Mongolia'),
(107, 'MR', 'Mauritania'),
(108, 'MW', 'Malawi'),
(109, 'MX', 'Mexico'),
(110, 'MY', 'Malaysia'),
(111, 'MZ', 'Mozambique'),
(112, 'NA', 'Namibia'),
(113, 'NC', 'New Caledonia'),
(114, 'NE', 'Niger'),
(115, 'NG', 'Nigeria'),
(116, 'NI', 'Nicaragua'),
(117, 'NL', 'Netherlands'),
(118, 'NO', 'Norway'),
(119, 'NP', 'Nepal'),
(120, 'NZ', 'New Zealand'),
(121, 'OM', 'Oman'),
(122, 'PA', 'Panama'),
(123, 'PE', 'Peru'),
(124, 'PG', 'Papua New Guine'),
(125, 'PH', 'Philippines'),
(126, 'PK', 'Pakistan'),
(127, 'PL', 'Poland'),
(128, 'PR', 'Puerto Rico'),
(129, 'PS', 'Palestine'),
(130, 'PT', 'Portugal'),
(131, 'PY', 'Paraguay'),
(132, 'QA', 'Qatar'),
(133, 'RO', 'Romania'),
(134, 'RS', 'Serbia'),
(135, 'RU', 'Russia'),
(136, 'RW', 'Rwanda'),
(137, 'SA', 'Saudi Arabia'),
(138, 'SB', 'Solomon Is.'),
(139, 'SD', 'Sudan'),
(140, 'SE', 'Sweden'),
(141, 'SI', 'Slovenia'),
(142, 'SK', 'Slovakia'),
(143, 'SL', 'Sierra Leone'),
(144, 'SN', 'Senegal'),
(145, 'SO', 'Somalia'),
(146, 'SR', 'Suriname'),
(147, 'SS', 'S. Sudan'),
(148, 'SV', 'El Salvador'),
(149, 'SY', 'Syria'),
(150, 'SZ', 'Swaziland'),
(151, 'TD', 'Chad'),
(152, 'TF', 'Fr. S. Antarcti'),
(153, 'TG', 'Togo'),
(154, 'TH', 'Thailand'),
(155, 'TJ', 'Tajikistan'),
(156, 'TL', 'Timor-Leste'),
(157, 'TM', 'Turkmenistan'),
(158, 'TN', 'Tunisia'),
(159, 'TR', 'Turkey'),
(160, 'TT', 'Trinidad and To'),
(161, 'TW', 'Taiwan'),
(162, 'TZ', 'Tanzania'),
(163, 'UA', 'Ukraine'),
(164, 'UG', 'Uganda'),
(165, 'US', 'United States'),
(166, 'UY', 'Uruguay'),
(167, 'UZ', 'Uzbekistan'),
(168, 'VE', 'Venezuela'),
(169, 'VN', 'Vietnam'),
(170, 'VU', 'Vanuatu'),
(171, 'XC', 'N. Cyprus'),
(172, 'XK', 'Kosovo'),
(173, 'XS', 'Somaliland'),
(174, 'YE', 'Yemen'),
(175, 'ZA', 'South Africa'),
(176, 'ZM', 'Zambia'),
(177, 'ZW', 'Zimbabwe');

-- --------------------------------------------------------

--
-- Table structure for table `CountryData`
--

CREATE TABLE IF NOT EXISTS `CountryData` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(3) NOT NULL,
  `targetSales` int(11) NOT NULL,
  `currentSales` int(11) NOT NULL,
  `marketShare` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `CountryData`
--

INSERT INTO `CountryData` (`id`, `code`, `targetSales`, `currentSales`, `marketShare`) VALUES
(1, 'AE', 121, 323, 65),
(2, 'AF', 2323, 12, 23),
(3, 'BI', 6564, 343, 13),
(6, 'BJ', 123213, 1212, 54),
(7, 'RU', 3355, 123, 78),
(8, 'US', 45465, 3232, 12);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `CountryData`
--
ALTER TABLE `CountryData`
  ADD CONSTRAINT `fk_CountryData_Country` FOREIGN KEY (`code`) REFERENCES `CountryCodes` (`code`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
