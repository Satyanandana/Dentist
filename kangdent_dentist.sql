-- phpMyAdmin SQL Dump
-- version 4.4.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 02, 2016 at 05:53 AM
-- Server version: 5.6.25
-- PHP Version: 5.6.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kangdent_dentist`
--
CREATE DATABASE IF NOT EXISTS `kangdent_dentist` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `kangdent_dentist`;

-- --------------------------------------------------------

--
-- Table structure for table `appointmentrequests`
--

DROP TABLE IF EXISTS `appointmentrequests`;
CREATE TABLE IF NOT EXISTS `appointmentrequests` (
  `appointmentRequestID` bigint(20) NOT NULL,
  `appointmentStartTime` datetime NOT NULL,
  `note` longtext NOT NULL,
  `requestInsertedTime` datetime NOT NULL,
  `status` varchar(255) NOT NULL,
  `appointmentID` bigint(20) DEFAULT NULL,
  `patientID` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `appointmentrequests`:
--   `patientID`
--       `patient_details` -> `userID`
--   `appointmentID`
--       `appointments` -> `appointmentID`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointments`
--

DROP TABLE IF EXISTS `appointments`;
CREATE TABLE IF NOT EXISTS `appointments` (
  `appointmentID` bigint(20) NOT NULL,
  `actualCalEventID` varchar(255) NOT NULL,
  `amountPaid` decimal(19,2) DEFAULT NULL,
  `appointmentInsertedTime` datetime NOT NULL,
  `appointmentStartTime` datetime NOT NULL,
  `expectedAmount` decimal(19,2) DEFAULT NULL,
  `fakeCalEventID` varchar(255) NOT NULL,
  `note` longtext NOT NULL,
  `status` varchar(255) NOT NULL,
  `appointmentRequestID` bigint(20) NOT NULL,
  `patientID` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `appointments`:
--   `appointmentRequestID`
--       `appointmentrequests` -> `appointmentRequestID`
--   `patientID`
--       `patient_details` -> `userID`
--

-- --------------------------------------------------------

--
-- Table structure for table `documents_recieved`
--

DROP TABLE IF EXISTS `documents_recieved`;
CREATE TABLE IF NOT EXISTS `documents_recieved` (
  `docID` bigint(20) NOT NULL,
  `fileExt` varchar(255) NOT NULL,
  `fileName` varchar(255) NOT NULL,
  `path` varchar(255) NOT NULL,
  `receivedTime` datetime NOT NULL,
  `receiverID` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `documents_recieved`:
--   `receiverID`
--       `patient_details` -> `userID`
--

-- --------------------------------------------------------

--
-- Table structure for table `documents_sent`
--

DROP TABLE IF EXISTS `documents_sent`;
CREATE TABLE IF NOT EXISTS `documents_sent` (
  `docID` bigint(20) NOT NULL,
  `fileExt` varchar(255) NOT NULL,
  `fileName` varchar(255) NOT NULL,
  `path` varchar(255) NOT NULL,
  `sentTime` datetime NOT NULL,
  `senderID` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `documents_sent`:
--   `senderID`
--       `patient_details` -> `userID`
--

-- --------------------------------------------------------

--
-- Table structure for table `insurances`
--

DROP TABLE IF EXISTS `insurances`;
CREATE TABLE IF NOT EXISTS `insurances` (
  `insuranceID` bigint(20) NOT NULL,
  `dateOfBirth` date DEFAULT NULL,
  `insertedDate` datetime NOT NULL,
  `insuranceGroupID` varchar(255) NOT NULL,
  `insuranceGroupName` varchar(255) NOT NULL,
  `insuranceProviderName` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `subscriberFullName` longtext NOT NULL,
  `subscriberID` varchar(255) NOT NULL,
  `patientID` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `insurances`:
--   `patientID`
--       `patient_details` -> `userID`
--

-- --------------------------------------------------------

--
-- Table structure for table `patientteethstatus`
--

DROP TABLE IF EXISTS `patientteethstatus`;
CREATE TABLE IF NOT EXISTS `patientteethstatus` (
  `lastModified` datetime NOT NULL,
  `teethStatus` varchar(255) NOT NULL,
  `teethID` int(11) NOT NULL,
  `patientID` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `patientteethstatus`:
--   `teethID`
--       `teeth` -> `teethID`
--   `patientID`
--       `patient_details` -> `userID`
--

-- --------------------------------------------------------

--
-- Table structure for table `patient_details`
--

DROP TABLE IF EXISTS `patient_details`;
CREATE TABLE IF NOT EXISTS `patient_details` (
  `userID` bigint(20) NOT NULL,
  `emergencyContactName` varchar(255) DEFAULT NULL,
  `emergencyContactNumber` varchar(255) DEFAULT NULL,
  `emergencyContactRelation` varchar(255) DEFAULT NULL,
  `dateOfBirth` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `address1` longtext,
  `address2` longtext,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `zipcode` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) NOT NULL,
  `middleName` varchar(255) NOT NULL,
  `phoneNumber` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `patient_details`:
--   `userID`
--       `user_auth` -> `userID`
--

-- --------------------------------------------------------

--
-- Table structure for table `recieved_messages`
--

DROP TABLE IF EXISTS `recieved_messages`;
CREATE TABLE IF NOT EXISTS `recieved_messages` (
  `messageID` bigint(20) NOT NULL,
  `msg` longtext NOT NULL,
  `receivedTime` datetime NOT NULL,
  `receiverID` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `recieved_messages`:
--   `receiverID`
--       `patient_details` -> `userID`
--

-- --------------------------------------------------------

--
-- Table structure for table `sent_messages`
--

DROP TABLE IF EXISTS `sent_messages`;
CREATE TABLE IF NOT EXISTS `sent_messages` (
  `messageID` bigint(20) NOT NULL,
  `msg` longtext NOT NULL,
  `sentTime` datetime NOT NULL,
  `senderID` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `sent_messages`:
--   `senderID`
--       `patient_details` -> `userID`
--

-- --------------------------------------------------------

--
-- Table structure for table `teeth`
--

DROP TABLE IF EXISTS `teeth`;
CREATE TABLE IF NOT EXISTS `teeth` (
  `teethID` int(11) NOT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `teethName` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `teeth`:
--

-- --------------------------------------------------------

--
-- Table structure for table `treatments`
--

DROP TABLE IF EXISTS `treatments`;
CREATE TABLE IF NOT EXISTS `treatments` (
  `treatmentID` bigint(20) NOT NULL,
  `amountExpected` decimal(19,2) DEFAULT NULL,
  `amountPaid` decimal(19,2) DEFAULT NULL,
  `note` longtext NOT NULL,
  `status` varchar(255) NOT NULL,
  `treatmentDoneTime` date DEFAULT NULL,
  `treatmentExpectedTime` date DEFAULT NULL,
  `treatmentInsertedTime` datetime NOT NULL,
  `patientID` bigint(20) NOT NULL,
  `teethID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `treatments`:
--   `teethID`
--       `teeth` -> `teethID`
--   `patientID`
--       `patient_details` -> `userID`
--

-- --------------------------------------------------------

--
-- Table structure for table `user_auth`
--

DROP TABLE IF EXISTS `user_auth`;
CREATE TABLE IF NOT EXISTS `user_auth` (
  `userID` bigint(20) NOT NULL,
  `accountStatus` varchar(255) NOT NULL,
  `creationTime` datetime NOT NULL,
  `lastLoginTime` datetime NOT NULL,
  `prevSessionID` varchar(255) NOT NULL,
  `userEmail` varchar(255) NOT NULL,
  `userIp` varchar(255) NOT NULL,
  `userPwd` varchar(255) NOT NULL,
  `userRole` varchar(255) NOT NULL,
  `verifyKey` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `user_auth`:
--

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointmentrequests`
--
ALTER TABLE `appointmentrequests`
  ADD PRIMARY KEY (`appointmentRequestID`),
  ADD KEY `FK_lm9baolelkgfbpdvxcl24g8wi` (`appointmentID`),
  ADD KEY `FK_7dfat6fdrjav3uyncdayasal7` (`patientID`);

--
-- Indexes for table `appointments`
--
ALTER TABLE `appointments`
  ADD PRIMARY KEY (`appointmentID`),
  ADD KEY `FK_3la4q87n7889we2evohdvxc1k` (`appointmentRequestID`),
  ADD KEY `FK_knw49wdvc05u2lx5ugmscan3y` (`patientID`);

--
-- Indexes for table `documents_recieved`
--
ALTER TABLE `documents_recieved`
  ADD PRIMARY KEY (`docID`),
  ADD KEY `FK_r2lu4jmpdav8bdnrs1alw6x6g` (`receiverID`);

--
-- Indexes for table `documents_sent`
--
ALTER TABLE `documents_sent`
  ADD PRIMARY KEY (`docID`),
  ADD KEY `FK_k7xi5pumfj9v0myjghd2xmhg1` (`senderID`);

--
-- Indexes for table `insurances`
--
ALTER TABLE `insurances`
  ADD PRIMARY KEY (`insuranceID`),
  ADD KEY `FK_ocrnuy1o1noug5r7ugpw9s41a` (`patientID`);

--
-- Indexes for table `patientteethstatus`
--
ALTER TABLE `patientteethstatus`
  ADD PRIMARY KEY (`patientID`,`teethID`),
  ADD KEY `FK_2a7rrbk8728lpsd23whbjtcun` (`teethID`);

--
-- Indexes for table `patient_details`
--
ALTER TABLE `patient_details`
  ADD PRIMARY KEY (`userID`),
  ADD UNIQUE KEY `UK_7f8gsv5vjf0itjprr52howldn` (`email`);

--
-- Indexes for table `recieved_messages`
--
ALTER TABLE `recieved_messages`
  ADD PRIMARY KEY (`messageID`),
  ADD KEY `FK_leboco7a4nfv00tr5k0nqubsn` (`receiverID`);

--
-- Indexes for table `sent_messages`
--
ALTER TABLE `sent_messages`
  ADD PRIMARY KEY (`messageID`),
  ADD KEY `FK_mvpvhwf40jbskswx7t72u0t0k` (`senderID`);

--
-- Indexes for table `teeth`
--
ALTER TABLE `teeth`
  ADD PRIMARY KEY (`teethID`);

--
-- Indexes for table `treatments`
--
ALTER TABLE `treatments`
  ADD PRIMARY KEY (`treatmentID`),
  ADD KEY `FK_4hqow1qxgdnyqueiukpa91kgn` (`patientID`),
  ADD KEY `FK_2o824em1bj6oayk27ctfg0uf8` (`teethID`);

--
-- Indexes for table `user_auth`
--
ALTER TABLE `user_auth`
  ADD PRIMARY KEY (`userID`),
  ADD UNIQUE KEY `UK_1sjb17k8uijub1jwfj85urlof` (`userEmail`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appointmentrequests`
--
ALTER TABLE `appointmentrequests`
  MODIFY `appointmentRequestID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `appointments`
--
ALTER TABLE `appointments`
  MODIFY `appointmentID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `documents_recieved`
--
ALTER TABLE `documents_recieved`
  MODIFY `docID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `documents_sent`
--
ALTER TABLE `documents_sent`
  MODIFY `docID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `insurances`
--
ALTER TABLE `insurances`
  MODIFY `insuranceID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `recieved_messages`
--
ALTER TABLE `recieved_messages`
  MODIFY `messageID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `sent_messages`
--
ALTER TABLE `sent_messages`
  MODIFY `messageID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `treatments`
--
ALTER TABLE `treatments`
  MODIFY `treatmentID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user_auth`
--
ALTER TABLE `user_auth`
  MODIFY `userID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `appointmentrequests`
--
ALTER TABLE `appointmentrequests`
  ADD CONSTRAINT `FK_7dfat6fdrjav3uyncdayasal7` FOREIGN KEY (`patientID`) REFERENCES `patient_details` (`userID`),
  ADD CONSTRAINT `FK_lm9baolelkgfbpdvxcl24g8wi` FOREIGN KEY (`appointmentID`) REFERENCES `appointments` (`appointmentID`);

--
-- Constraints for table `appointments`
--
ALTER TABLE `appointments`
  ADD CONSTRAINT `FK_3la4q87n7889we2evohdvxc1k` FOREIGN KEY (`appointmentRequestID`) REFERENCES `appointmentrequests` (`appointmentRequestID`),
  ADD CONSTRAINT `FK_knw49wdvc05u2lx5ugmscan3y` FOREIGN KEY (`patientID`) REFERENCES `patient_details` (`userID`);

--
-- Constraints for table `documents_recieved`
--
ALTER TABLE `documents_recieved`
  ADD CONSTRAINT `FK_r2lu4jmpdav8bdnrs1alw6x6g` FOREIGN KEY (`receiverID`) REFERENCES `patient_details` (`userID`);

--
-- Constraints for table `documents_sent`
--
ALTER TABLE `documents_sent`
  ADD CONSTRAINT `FK_k7xi5pumfj9v0myjghd2xmhg1` FOREIGN KEY (`senderID`) REFERENCES `patient_details` (`userID`);

--
-- Constraints for table `insurances`
--
ALTER TABLE `insurances`
  ADD CONSTRAINT `FK_ocrnuy1o1noug5r7ugpw9s41a` FOREIGN KEY (`patientID`) REFERENCES `patient_details` (`userID`);

--
-- Constraints for table `patientteethstatus`
--
ALTER TABLE `patientteethstatus`
  ADD CONSTRAINT `FK_2a7rrbk8728lpsd23whbjtcun` FOREIGN KEY (`teethID`) REFERENCES `teeth` (`teethID`),
  ADD CONSTRAINT `FK_h66u02xln3ucwpe3ml484hgxv` FOREIGN KEY (`patientID`) REFERENCES `patient_details` (`userID`);

--
-- Constraints for table `patient_details`
--
ALTER TABLE `patient_details`
  ADD CONSTRAINT `FK_dwr85f82orpc35j0rkn044ttx` FOREIGN KEY (`userID`) REFERENCES `user_auth` (`userID`);

--
-- Constraints for table `recieved_messages`
--
ALTER TABLE `recieved_messages`
  ADD CONSTRAINT `FK_leboco7a4nfv00tr5k0nqubsn` FOREIGN KEY (`receiverID`) REFERENCES `patient_details` (`userID`);

--
-- Constraints for table `sent_messages`
--
ALTER TABLE `sent_messages`
  ADD CONSTRAINT `FK_mvpvhwf40jbskswx7t72u0t0k` FOREIGN KEY (`senderID`) REFERENCES `patient_details` (`userID`);

--
-- Constraints for table `treatments`
--
ALTER TABLE `treatments`
  ADD CONSTRAINT `FK_2o824em1bj6oayk27ctfg0uf8` FOREIGN KEY (`teethID`) REFERENCES `teeth` (`teethID`),
  ADD CONSTRAINT `FK_4hqow1qxgdnyqueiukpa91kgn` FOREIGN KEY (`patientID`) REFERENCES `patient_details` (`userID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
