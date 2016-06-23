-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 23, 2016 at 08:57 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fixture`
--

-- --------------------------------------------------------

--
-- Table structure for table `data`
--

CREATE TABLE `data` (
  `node` varchar(16) NOT NULL,
  `uniqueid` int(16) NOT NULL,
  `db_uuid` int(11) NOT NULL,
  `id` varchar(48) NOT NULL,
  `val` varchar(48) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `data`
--

INSERT INTO `data` (`node`, `uniqueid`, `db_uuid`, `id`, `val`) VALUES
('dat_attendance_8', 1, 1, 'Anthony Menendez', 'Absent');

-- --------------------------------------------------------

--
-- Table structure for table `logins`
--

CREATE TABLE `logins` (
  `username` varchar(32) NOT NULL,
  `ip` varchar(24) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `logins`
--

INSERT INTO `logins` (`username`, `ip`, `time`) VALUES
('fixture', 'n/a', '2016-06-22 21:22:27'),
('fixture', 'n/a', '2016-06-22 22:50:49'),
('fixture', 'n/a', '2016-06-22 22:51:02'),
('fixture', 'n/a', '2016-06-22 22:51:13'),
('fixture', 'n/a', '2016-06-22 22:51:26'),
('fixture', 'n/a', '2016-06-22 22:51:42'),
('fixture', 'n/a', '2016-06-22 23:23:54'),
('fixture', 'n/a', '2016-06-22 23:27:28'),
('fixture', 'n/a', '2016-06-22 23:27:42'),
('fixture', 'n/a', '2016-06-22 23:31:47'),
('fixture', 'n/a', '2016-06-22 23:32:36'),
('fixture', 'n/a', '2016-06-23 00:05:01'),
('fixture', 'n/a', '2016-06-23 00:06:08'),
('fixture', 'n/a', '2016-06-23 00:06:26'),
('fixture', 'n/a', '2016-06-23 00:06:38'),
('fixture', 'n/a', '2016-06-23 00:07:23'),
('fixture', 'n/a', '2016-06-23 00:08:17'),
('fixture', 'n/a', '2016-06-23 18:46:21');

-- --------------------------------------------------------

--
-- Table structure for table `nodes`
--

CREATE TABLE `nodes` (
  `identifier` int(11) NOT NULL,
  `node` varchar(16) NOT NULL,
  `nice_name` varchar(48) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nodes`
--

INSERT INTO `nodes` (`identifier`, `node`, `nice_name`) VALUES
(1, 'dat_attendance_8', '8th Grade Attendance');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `data`
--
ALTER TABLE `data`
  ADD PRIMARY KEY (`uniqueid`);

--
-- Indexes for table `nodes`
--
ALTER TABLE `nodes`
  ADD PRIMARY KEY (`identifier`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `data`
--
ALTER TABLE `data`
  MODIFY `uniqueid` int(16) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `nodes`
--
ALTER TABLE `nodes`
  MODIFY `identifier` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
