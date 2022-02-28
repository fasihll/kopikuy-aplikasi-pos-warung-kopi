-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 22, 2021 at 12:20 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kopikuy`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `categoryID` int(3) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`categoryID`, `name`, `description`) VALUES
(1, 'minuman', NULL),
(2, 'maknan', NULL),
(3, 'lainnya', NULL),
(6, 'Makanan Ringan', 'Makanan Kemasan Plastik');

-- --------------------------------------------------------

--
-- Table structure for table `detile_transaction`
--

CREATE TABLE `detile_transaction` (
  `orderID` int(10) NOT NULL,
  `transactionID` int(11) NOT NULL,
  `foodID` int(3) NOT NULL,
  `qty` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `detile_transaction`
--

INSERT INTO `detile_transaction` (`orderID`, `transactionID`, `foodID`, `qty`, `created_at`, `updated_at`) VALUES
(1, 1, 30, 12, '2021-12-03 16:11:18', '2021-12-03 16:11:18'),
(2, 1, 32, 2, '2021-12-03 16:11:18', '2021-12-03 16:11:18'),
(3, 1, 38, 1, '2021-12-03 16:11:18', '2021-12-03 16:11:18'),
(4, 2, 35, 3, '2021-12-03 17:19:24', '2021-12-03 17:19:24'),
(5, 2, 37, 41, '2021-12-03 17:19:24', '2021-12-03 17:19:24'),
(6, 2, 22, 2, '2021-12-03 17:19:24', '2021-12-03 17:19:24'),
(7, 3, 19, 2, '2021-12-05 17:34:03', '2021-12-05 17:34:03'),
(8, 3, 29, 2, '2021-12-05 17:34:03', '2021-12-05 17:34:03'),
(9, 4, 19, 1, '2021-12-06 04:57:09', '2021-12-06 04:57:09'),
(10, 4, 27, 1, '2021-12-06 04:57:09', '2021-12-06 04:57:09'),
(11, 4, 30, 1, '2021-12-06 04:57:09', '2021-12-06 04:57:09'),
(12, 4, 35, 1, '2021-12-06 04:57:09', '2021-12-06 04:57:09'),
(13, 5, 19, 1, '2021-12-06 04:57:54', '2021-12-06 04:57:54'),
(14, 5, 27, 1, '2021-12-06 04:57:54', '2021-12-06 04:57:54'),
(15, 5, 30, 1, '2021-12-06 04:57:54', '2021-12-06 04:57:54'),
(16, 5, 35, 1, '2021-12-06 04:57:54', '2021-12-06 04:57:54'),
(17, 6, 37, 5, '2021-12-06 05:09:04', '2021-12-06 05:09:04'),
(18, 6, 33, 1, '2021-12-06 05:09:04', '2021-12-06 05:09:04'),
(19, 7, 30, 1, '2021-12-06 05:09:44', '2021-12-06 05:09:44'),
(20, 7, 33, 1, '2021-12-06 05:09:44', '2021-12-06 05:09:44'),
(21, 7, 39, 1, '2021-12-06 05:09:44', '2021-12-06 05:09:44'),
(23, 8, 35, 3, '2021-12-06 05:10:23', '2021-12-06 05:10:23'),
(24, 9, 22, 1, '2021-12-06 05:11:05', '2021-12-06 05:11:05'),
(25, 9, 35, 3, '2021-12-06 05:11:05', '2021-12-06 05:11:05'),
(26, 10, 22, 1, '2021-12-06 05:11:44', '2021-12-06 05:11:44'),
(27, 10, 35, 3, '2021-12-06 05:11:44', '2021-12-06 05:11:44'),
(29, 11, 22, 1, '2021-12-06 05:12:25', '2021-12-06 05:12:25'),
(30, 11, 35, 3, '2021-12-06 05:12:25', '2021-12-06 05:12:25'),
(32, 11, 38, 2, '2021-12-06 05:12:25', '2021-12-06 05:12:25'),
(33, 12, 22, 1, '2021-12-06 05:12:58', '2021-12-06 05:12:58'),
(34, 12, 35, 3, '2021-12-06 05:12:58', '2021-12-06 05:12:58'),
(36, 13, 24, 1, '2021-12-07 08:35:58', '2021-12-07 08:35:58'),
(38, 13, 32, 3, '2021-12-07 08:35:58', '2021-12-07 08:35:58'),
(39, 14, 24, 3, '2021-12-09 08:06:05', '2021-12-09 08:06:05'),
(40, 14, 29, 5, '2021-12-09 08:06:05', '2021-12-09 08:06:05'),
(42, 15, 24, 4, '2021-12-09 09:17:56', '2021-12-09 09:17:56'),
(43, 15, 25, 2, '2021-12-09 09:17:56', '2021-12-09 09:17:56'),
(44, 16, 19, 1, '2021-12-09 13:20:09', '2021-12-09 13:20:09'),
(45, 16, 1, 1, '2021-12-09 13:20:09', '2021-12-09 13:20:09'),
(47, 17, 1, 1, '2021-12-11 17:01:15', '2021-12-11 17:01:15'),
(48, 17, 19, 1, '2021-12-11 17:01:15', '2021-12-11 17:01:15'),
(50, 18, 19, 1, '2021-12-14 10:04:21', '2021-12-14 10:04:21'),
(52, 19, 19, 2, '2021-12-21 06:27:01', '2021-12-21 06:27:01'),
(53, 19, 27, 1, '2021-12-21 06:27:01', '2021-12-21 06:27:01'),
(54, 20, 24, 2, '2021-12-22 11:14:08', '2021-12-22 11:14:08');

--
-- Triggers `detile_transaction`
--
DELIMITER $$
CREATE TRIGGER `kurangiStock` AFTER INSERT ON `detile_transaction` FOR EACH ROW UPDATE foods SET food_stock=food_stock-NEW.qty WHERE foodID=NEW.foodID
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `foods`
--

CREATE TABLE `foods` (
  `foodID` int(3) NOT NULL,
  `name` varchar(50) NOT NULL,
  `categoryID` int(3) NOT NULL,
  `price` double NOT NULL,
  `food_stock` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `foods`
--

INSERT INTO `foods` (`foodID`, `name`, `categoryID`, `price`, `food_stock`, `created_at`, `updated_at`) VALUES
(1, 'torabika susu', 1, 5000, 10, '2021-11-04 16:44:26', '2021-12-11 17:01:15'),
(19, 'Top coffe', 1, 3000, 10, '2021-11-09 03:51:10', '2021-12-21 06:27:01'),
(21, 'Beng beng coklat', 1, 4000, 12, '2021-11-09 03:51:10', '2021-11-09 15:21:54'),
(22, 'Kopi hitam biasa', 1, 3000, 22, '2021-11-09 03:51:10', '2021-12-06 05:12:58'),
(24, 'Kopi specialty arabika', 1, 8000, 7, '2021-11-09 03:51:10', '2021-12-22 11:14:08'),
(25, 'Kopi speacialty robusta', 1, 6000, 1, '2021-11-09 03:51:10', '2021-12-09 09:17:56'),
(27, 'Mi goreng', 2, 4000, 0, '2021-11-09 03:51:10', '2021-12-21 06:27:01'),
(29, 'Pop ice', 1, 3000, 19, '2021-11-09 03:51:10', '2021-12-09 08:06:05'),
(30, 'Telur goreng dadar', 2, 2000, 7, '2021-11-09 03:51:10', '2021-12-21 06:16:48'),
(32, 'Kentang goreng', 2, 2500, 0, '2021-11-09 03:51:10', '2021-12-14 02:54:42'),
(33, 'kerupuk', 2, 500, 28, '2021-11-09 03:51:10', '2021-12-06 05:09:44'),
(35, 'Sosis bakar', 2, 1000, 0, '2021-11-09 03:51:10', '2021-12-14 02:54:46'),
(36, 'gorengan', 2, 1000, 18, '2021-11-09 03:54:53', '2021-11-09 14:15:04'),
(37, 'Susu indomilk', 1, 4000, 0, '2021-11-09 03:54:53', '2021-12-14 02:54:31'),
(38, 'rokok gudang garam', 3, 12000, 22, '2021-11-24 14:59:28', '2021-12-06 05:12:25'),
(39, 'rokok umild', 3, 8000, 24, '2021-11-24 15:00:45', '2021-12-06 05:09:44'),
(42, 'Nasi putih', 2, 3000, 50, '2021-12-20 14:34:12', '2021-12-20 14:34:12'),
(43, 'Kopi Kapal Api', 1, 2500, 13, '2021-12-21 06:16:12', '2021-12-21 06:16:12');

-- --------------------------------------------------------

--
-- Table structure for table `karyawan`
--

CREATE TABLE `karyawan` (
  `karyawanID` int(3) NOT NULL,
  `name` varchar(50) NOT NULL,
  `jenis_kelamin` varchar(15) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `alamat` varchar(35) NOT NULL,
  `no_telp` varchar(17) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `karyawan`
--

INSERT INTO `karyawan` (`karyawanID`, `name`, `jenis_kelamin`, `tgl_lahir`, `alamat`, `no_telp`) VALUES
(3, 'sueb', 'laki-laki', '2021-11-04', 'jl kh moch kholil', '823423423'),
(4, 'Musthofa', 'laki-laki', '2000-01-14', 'jl kh moch kholil', '823423423'),
(5, 'Ahmad', 'laki-laki', '2001-11-16', 'jl. Merdeka', '87532467'),
(6, 'Bagus', 'laki-laki', '1999-11-01', 'jl. A.Yani', '8769645'),
(7, 'Bella', 'perempuan', '2001-12-21', 'Jl. Raden wijaya', '812121212'),
(8, 'asep', 'laki-laki', '2002-11-08', 'Jl. Raden wijaya', '81242342'),
(9, 'sohibul liwak', 'laki-laki', '2021-11-10', 'jl suekarno', '853232323'),
(10, 'farah', 'perempuan', '2002-11-08', 'jl bangbuta no 2', '8643423423'),
(11, 'wulan', 'perempuan', '2002-11-07', 'jl poter', '853423232'),
(12, 'desi', 'perempuan', '2002-11-13', 'jl poter', '864322323'),
(13, 'fiyana', 'perempuan', '2021-11-10', 'jl tanah merah', '8564434344'),
(14, 'owen', 'laki-laki', '2002-11-14', 'jl sidokere', '853423432'),
(15, 'ayyub', 'laki-laki', '2002-11-06', 'jl kirian sidoarjo', '865443432'),
(16, 'riyan', 'laki-laki', '2002-11-13', 'jl kh moch kholil', '832423532'),
(18, 'Aldi', 'laki-laki', '2001-04-17', 'jl.Sukoharjo', '0831546783');

-- --------------------------------------------------------

--
-- Table structure for table `level`
--

CREATE TABLE `level` (
  `levelID` int(3) NOT NULL,
  `name` varchar(15) NOT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `level`
--

INSERT INTO `level` (`levelID`, `name`, `description`) VALUES
(1, 'Admin', 'full access'),
(2, 'Owner', 'Read a Report'),
(3, 'Kasir', 'Melayani proses transaksi');

-- --------------------------------------------------------

--
-- Table structure for table `shift`
--

CREATE TABLE `shift` (
  `shiftID` int(3) NOT NULL,
  `name` varchar(25) NOT NULL,
  `startTime` time NOT NULL,
  `endTime` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `shift`
--

INSERT INTO `shift` (`shiftID`, `name`, `startTime`, `endTime`) VALUES
(1, 'shif malam', '18:30:00', '23:00:00'),
(2, 'shift sore', '13:00:00', '17:00:00'),
(3, 'shift pagi', '07:00:00', '11:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `shift_karyawan`
--

CREATE TABLE `shift_karyawan` (
  `id_shift_karyawan` int(3) NOT NULL,
  `karyawanID` int(3) NOT NULL,
  `shiftID` int(3) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `shift_karyawan`
--

INSERT INTO `shift_karyawan` (`id_shift_karyawan`, `karyawanID`, `shiftID`, `created_at`, `updated_at`) VALUES
(1, 5, 1, '2021-11-10 04:35:09', '2021-11-10 04:35:09'),
(5, 3, 1, '2021-11-10 04:35:09', '2021-11-10 04:35:09'),
(6, 6, 1, '2021-12-04 23:25:03', '2021-12-04 23:25:03'),
(8, 4, 2, '2021-12-21 06:29:33', '2021-12-21 06:29:33');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `transactionID` int(11) NOT NULL,
  `no_faktur` varchar(255) NOT NULL DEFAULT 'TI',
  `buyer_name` varchar(20) NOT NULL,
  `total` double NOT NULL,
  `total_item` int(11) NOT NULL,
  `bayar` double NOT NULL,
  `kembalian` double NOT NULL,
  `karyawanID` int(3) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`transactionID`, `no_faktur`, `buyer_name`, `total`, `total_item`, `bayar`, `kembalian`, `karyawanID`, `created_at`, `updated_at`) VALUES
(1, 'TI1', '', 41000, 15, 50000, -9000, 3, '2020-01-01 16:11:18', '2021-12-17 07:09:32'),
(2, 'TI2', 'ach fasihul lisan', 173000, 46, 180000, -7000, 3, '2020-02-03 17:19:24', '2021-12-17 07:09:37'),
(3, 'TI3', 'fasih', 12000, 4, 15000, -3000, 5, '2020-03-05 17:34:03', '2021-12-17 07:09:47'),
(4, 'TI4', 'niecola', 10000, 4, 10000, 0, 5, '2020-04-06 04:57:09', '2021-12-17 07:09:51'),
(5, 'TI5', 'niecola', 10000, 4, 10000, 0, 5, '2021-05-06 04:57:54', '2021-12-06 05:15:45'),
(6, 'TI6', 'sukirman', 20000, 5, 100000, -80000, 3, '2021-06-06 05:09:04', '2021-12-06 05:15:45'),
(7, 'TI7', 'sudirman', 10500, 3, 15000, -4500, 3, '2021-07-06 05:09:44', '2021-12-06 05:15:46'),
(8, 'TI8', 'sulaiman', 6000, 4, 10000, -4000, 3, '2021-08-06 05:10:23', '2021-12-06 05:15:46'),
(9, 'TI9', 'asep', 6000, 4, 10000, -4000, 3, '2021-09-06 05:11:05', '2021-12-06 05:15:46'),
(10, 'TI10', 'deny', 12000, 6, 15000, -3000, 3, '2021-10-06 05:11:44', '2021-12-06 05:15:46'),
(11, 'TI11', 'adi', 36000, 8, 50000, -14000, 3, '2021-11-06 05:12:25', '2021-12-06 05:15:46'),
(12, 'TI12', 'wulan', 12000, 6, 50000, -38000, 3, '2021-12-06 05:12:58', '2021-12-06 05:12:58'),
(13, 'TI13', 'ach fasihul lisan', 21500, 6, 22000, -500, 3, '2021-12-07 08:35:58', '2021-12-07 08:35:58'),
(14, 'TI14', '', 39000, 8, 50000, -11000, 3, '2021-12-09 08:06:05', '2021-12-09 08:06:05'),
(15, 'TI15', 'ahmad', 59000, 11, 60000, -1000, 3, '2021-12-09 09:17:56', '2021-12-09 09:17:56'),
(16, 'TI16', 'siti', 11000, 3, 12000, 1000, 3, '2021-12-09 13:20:09', '2021-12-09 13:20:09'),
(17, 'TI17', 'fasih', 11000, 3, 11000, 0, 3, '2021-12-11 17:01:15', '2021-12-11 17:01:15'),
(18, 'TI18', 'fasih', 9000, 3, 10000, 1000, 3, '2021-12-14 10:04:21', '2021-12-14 10:04:21'),
(19, 'TI19', 'fasih', 10000, 3, 15000, 5000, 3, '2021-12-21 06:27:01', '2021-12-21 06:27:01'),
(20, 'TI20', 'deny', 16000, 2, 20000, 4000, 3, '2021-12-22 11:14:08', '2021-12-22 11:14:08');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `usersID` int(3) NOT NULL,
  `karyawanID` int(3) DEFAULT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL,
  `levelID` int(3) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`usersID`, `karyawanID`, `username`, `password`, `levelID`, `created_at`, `updated_at`) VALUES
(2, 3, 'admin', 'admin123', 1, '2021-12-06 13:32:34', '2021-11-09 04:25:55'),
(3, 6, 'owner', 'owner123', 2, '2021-12-06 13:32:34', '2021-11-09 04:17:13'),
(6, 3, 'kasir', 'kasir123', 3, '2021-12-06 13:32:34', '2021-11-21 23:50:54'),
(7, 5, 'ahmad', 'ahmad123', 3, '2021-12-06 13:32:34', '2021-11-23 14:32:05');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`categoryID`),
  ADD KEY `name` (`name`);

--
-- Indexes for table `detile_transaction`
--
ALTER TABLE `detile_transaction`
  ADD PRIMARY KEY (`orderID`),
  ADD KEY `foodID` (`foodID`),
  ADD KEY `transactionID` (`transactionID`);

--
-- Indexes for table `foods`
--
ALTER TABLE `foods`
  ADD PRIMARY KEY (`foodID`),
  ADD KEY `categoryID` (`categoryID`),
  ADD KEY `name` (`name`);

--
-- Indexes for table `karyawan`
--
ALTER TABLE `karyawan`
  ADD PRIMARY KEY (`karyawanID`),
  ADD KEY `name` (`name`),
  ADD KEY `name_2` (`name`);

--
-- Indexes for table `level`
--
ALTER TABLE `level`
  ADD PRIMARY KEY (`levelID`),
  ADD KEY `name` (`name`);

--
-- Indexes for table `shift`
--
ALTER TABLE `shift`
  ADD PRIMARY KEY (`shiftID`),
  ADD KEY `name` (`name`);

--
-- Indexes for table `shift_karyawan`
--
ALTER TABLE `shift_karyawan`
  ADD PRIMARY KEY (`id_shift_karyawan`),
  ADD KEY `shift_karyawan_ibfk_1` (`karyawanID`),
  ADD KEY `shift_karyawan_ibfk_2` (`shiftID`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`transactionID`),
  ADD KEY `karyawanID` (`karyawanID`),
  ADD KEY `no_faktur` (`no_faktur`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`usersID`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `id_level` (`levelID`),
  ADD KEY `name` (`karyawanID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `categoryID` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `detile_transaction`
--
ALTER TABLE `detile_transaction`
  MODIFY `orderID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT for table `foods`
--
ALTER TABLE `foods`
  MODIFY `foodID` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT for table `karyawan`
--
ALTER TABLE `karyawan`
  MODIFY `karyawanID` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `level`
--
ALTER TABLE `level`
  MODIFY `levelID` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `shift`
--
ALTER TABLE `shift`
  MODIFY `shiftID` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `shift_karyawan`
--
ALTER TABLE `shift_karyawan`
  MODIFY `id_shift_karyawan` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `transactionID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `usersID` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `detile_transaction`
--
ALTER TABLE `detile_transaction`
  ADD CONSTRAINT `detile_transaction_ibfk_1` FOREIGN KEY (`foodID`) REFERENCES `foods` (`foodID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detile_transaction_ibfk_2` FOREIGN KEY (`transactionID`) REFERENCES `transaction` (`transactionID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `foods`
--
ALTER TABLE `foods`
  ADD CONSTRAINT `foods_ibfk_1` FOREIGN KEY (`categoryID`) REFERENCES `category` (`categoryID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `shift_karyawan`
--
ALTER TABLE `shift_karyawan`
  ADD CONSTRAINT `shift_karyawan_ibfk_1` FOREIGN KEY (`karyawanID`) REFERENCES `karyawan` (`karyawanID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `shift_karyawan_ibfk_2` FOREIGN KEY (`shiftID`) REFERENCES `shift` (`shiftID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`karyawanID`) REFERENCES `karyawan` (`karyawanID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`levelID`) REFERENCES `level` (`levelID`),
  ADD CONSTRAINT `users_ibfk_2` FOREIGN KEY (`karyawanID`) REFERENCES `karyawan` (`karyawanID`) ON DELETE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
