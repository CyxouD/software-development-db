-- MySQL dump 10.13  Distrib 5.7.11, for Win64 (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	5.7.11-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `версия`
--

DROP TABLE IF EXISTS `версия`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `версия` (
  `Дата выпуска` datetime NOT NULL,
  `Номер` varchar(255) DEFAULT NULL,
  `Программа` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Дата выпуска`),
  KEY `ПрограммаВерсия` (`Программа`),
  CONSTRAINT `ПрограммаВерсия` FOREIGN KEY (`Программа`) REFERENCES `программа` (`Название`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `версия`
--

LOCK TABLES `версия` WRITE;
/*!40000 ALTER TABLE `версия` DISABLE KEYS */;
INSERT INTO `версия` VALUES ('1966-12-08 00:00:00',NULL,NULL),('1966-12-09 00:00:00',NULL,NULL),('1996-08-12 00:00:00','1.0','Download Master'),('1996-08-12 09:00:00','99.0','Dima'),('1996-12-08 00:00:00',NULL,NULL),('1998-08-12 00:00:00','2.0','Download Master'),('2005-08-12 00:00:00','3.0','Download Master'),('2010-04-05 00:00:00','Final','Hard Disk Sentinel'),('2012-04-05 00:00:00','Final','Crysis3'),('2015-12-02 00:00:00','666',NULL),('2016-02-05 00:00:00','2016.1.1','IntelIJ Idea');
/*!40000 ALTER TABLE `версия` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `издание`
--

DROP TABLE IF EXISTS `издание`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `издание` (
  `Название` varchar(255) NOT NULL,
  `Особенности` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Название`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `издание`
--

LOCK TABLES `издание` WRITE;
/*!40000 ALTER TABLE `издание` DISABLE KEYS */;
INSERT INTO `издание` VALUES ('Business','Fine'),('hoho',NULL),('Home','Good'),('Professional','Excelent');
/*!40000 ALTER TABLE `издание` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `издание операционной системы`
--

DROP TABLE IF EXISTS `издание операционной системы`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `издание операционной системы` (
  `Операционная система` varchar(255) NOT NULL,
  `Издание операционная система` varchar(255) NOT NULL,
  PRIMARY KEY (`Операционная система`,`Издание операционная система`),
  KEY `ИзданиеОперационнойСистемы-Издание` (`Издание операционная система`),
  CONSTRAINT `ИзданиеОперационная система-ОперационнаяСистема` FOREIGN KEY (`Операционная система`) REFERENCES `операционная система` (`Название`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ИзданиеОперационнойСистемы-Издание` FOREIGN KEY (`Издание операционная система`) REFERENCES `издание` (`Название`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `издание операционной системы`
--

LOCK TABLES `издание операционной системы` WRITE;
/*!40000 ALTER TABLE `издание операционной системы` DISABLE KEYS */;
INSERT INTO `издание операционной системы` VALUES ('Windows','Home'),('Windows','Professional');
/*!40000 ALTER TABLE `издание операционной системы` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `клиент`
--

DROP TABLE IF EXISTS `клиент`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `клиент` (
  `Название фирмы` varchar(255) NOT NULL,
  `Оплата` decimal(19,4) DEFAULT NULL,
  PRIMARY KEY (`Название фирмы`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `клиент`
--

LOCK TABLES `клиент` WRITE;
/*!40000 ALTER TABLE `клиент` DISABLE KEYS */;
INSERT INTO `клиент` VALUES ('Global company',99999.0000),('Softserve',500.0000),('Папа',66.0000);
/*!40000 ALTER TABLE `клиент` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `Оплата_before_insert` BEFORE INSERT ON `Клиент`
FOR EACH ROW
BEGIN
    declare msg varchar(128);
    IF new.`Оплата` < 0.0 THEN
        set msg = concat('MyTriggerError: Trying to insert a negative value in `Оплата`: ', cast(new.`Оплата` as char));
        signal sqlstate '45000' set message_text = msg;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `Оплата_before_update` BEFORE UPDATE ON Клиент
FOR EACH ROW
BEGIN
	declare msg varchar(128);
    IF new.`Оплата` < 0.0 THEN
        set msg = concat('MyTriggerError: Trying to update a negative value in `Оплата`: ', cast(new.`Оплата` as char));
        signal sqlstate '45000' set message_text = msg;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `команда`
--

DROP TABLE IF EXISTS `команда`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `команда` (
  `Название` varchar(255) NOT NULL,
  `Глава команды` varchar(255) DEFAULT NULL,
  `Компания` int(11) DEFAULT NULL,
  PRIMARY KEY (`Название`),
  UNIQUE KEY `Глава команды` (`Глава команды`),
  UNIQUE KEY `Глава команды_2` (`Глава команды`),
  KEY `КомпанияКоманда` (`Компания`),
  CONSTRAINT `КомпанияКоманда` FOREIGN KEY (`Компания`) REFERENCES `компания` (`id`),
  CONSTRAINT `Программный инженерКоманда` FOREIGN KEY (`Глава команды`) REFERENCES `программный инженер` (`ФИО`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `команда`
--

LOCK TABLES `команда` WRITE;
/*!40000 ALTER TABLE `команда` DISABLE KEYS */;
INSERT INTO `команда` VALUES ('Heyhey','Сигизмунд Василий Павлович',1),('Nanana','Пупкина Маргарита Владимировна',2),('Ololo','Хо хо хо',2),('Shona',NULL,NULL),('SyperTeam',NULL,NULL),('Родители','Засуха Дмитрий Александрович',3);
/*!40000 ALTER TABLE `команда` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `компания`
--

DROP TABLE IF EXISTS `компания`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `компания` (
  `id` int(11) NOT NULL,
  `Название` varchar(255) DEFAULT NULL,
  `Полный адрес` varchar(255) DEFAULT NULL,
  `Штат` varchar(255) DEFAULT NULL,
  `Ежегодный доход` varchar(255) DEFAULT NULL,
  `Пароль` varchar(255) NOT NULL,
  `Уровень привелегий` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uc_Name` (`Название`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `компания`
--

LOCK TABLES `компания` WRITE;
/*!40000 ALTER TABLE `компания` DISABLE KEYS */;
INSERT INTO `компания` VALUES (1,'SoftServe','ул.Пушкина, дом Калатушкина','100000','550000','','Пользователь'),(2,'DataArt','ул.Московская','500','100000','','Пользователь'),(3,'Example','ул.WhoKnows','5000','143543','','Пользователь'),(4,'TestCompany',NULL,NULL,NULL,'98f6bcd4621d373cade4e832627b4f6','Пользователь'),(6,'НИИПО','Америка, штат Масачусетс, Колорадо 57','100','0.0','6b7ed98642e8a8c01ad9c46c5bce87e','Администратор БД');
/*!40000 ALTER TABLE `компания` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `Ежегодный_доход_before_insert` BEFORE INSERT ON `Компания`
FOR EACH ROW
BEGIN
    declare msg varchar(128);
    IF new.`Ежегодный доход` < 0.0 THEN
        set msg = concat('MyTriggerError: Trying to insert a negative value in `Ежегодный доход`: ', cast(new.`Ежегодный доход` as char));
        signal sqlstate '45000' set message_text = msg;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `Штат_before_insert` BEFORE INSERT ON `Компания`
FOR EACH ROW
BEGIN
    declare msg varchar(128);
    IF new.`Штат` NOT REGEXP '^-?[0-9]+$' THEN
        set msg = concat('MyTriggerError: Trying to insert not integer value in `Штат`: ', cast(new.`Штат` as char));
        signal sqlstate '45000' set message_text = msg;
	ELSEIF new.`Штат` < 0 THEN
        set msg = concat('MyTriggerError: Trying to insert a negative value in `Штат`: ', cast(new.`Штат` as char));
        signal sqlstate '45000' set message_text = msg;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `Ежегодный_доход_before_update` BEFORE UPDATE ON Компания
FOR EACH ROW
BEGIN
	declare msg varchar(128);
    IF new.`Ежегодный доход` < 0.0 THEN
        set msg = concat('MyTriggerError: Trying to update a negative value in `Ежегодный доход`: ', cast(new.`Ежегодный доход` as char));
        signal sqlstate '45000' set message_text = msg;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `Штат_before_update` BEFORE UPDATE ON Компания
FOR EACH ROW
BEGIN
	declare msg varchar(128);
    IF new.`Штат` NOT REGEXP '^-?[0-9]+$' THEN
        set msg = concat('MyTriggerError: Trying to update not integer value in `Штат`: ', cast(new.`Штат` as char));
        signal sqlstate '45000' set message_text = msg;
	ELSEIF new.`Штат` < 0 THEN
        set msg = concat('MyTriggerError: Trying to update a negative value in `Штат`: ', cast(new.`Штат` as char));
        signal sqlstate '45000' set message_text = msg;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `язык программирования`
--

DROP TABLE IF EXISTS `язык программирования`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `язык программирования` (
  `Название` varchar(255) NOT NULL,
  `Предназначение` varchar(255) DEFAULT NULL,
  `Версия` varchar(255) DEFAULT NULL,
  `Наиболее частая ОС` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Название`),
  KEY `Операционная системаЯзык программирования` (`Наиболее частая ОС`),
  CONSTRAINT `Операционная системаЯзык программирования` FOREIGN KEY (`Наиболее частая ОС`) REFERENCES `операционная система` (`Название`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `язык программирования`
--

LOCK TABLES `язык программирования` WRITE;
/*!40000 ALTER TABLE `язык программирования` DISABLE KEYS */;
INSERT INTO `язык программирования` VALUES ('C#','Back-end','5.0','Windows'),('C++','Разработка игр','11','Linux'),('Java','Backend',NULL,'Windows'),('Java EE','Создание веб приложений','8.2','Windows'),('Javascript',NULL,NULL,'Ios'),('Sex','Производство детей','1.0','МамаПапаДНК');
/*!40000 ALTER TABLE `язык программирования` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `методология программирования`
--

DROP TABLE IF EXISTS `методология программирования`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `методология программирования` (
  `Название` varchar(255) NOT NULL,
  `Количество человек в команде` varchar(255) DEFAULT NULL,
  `Особенности` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Название`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `методология программирования`
--

LOCK TABLES `методология программирования` WRITE;
/*!40000 ALTER TABLE `методология программирования` DISABLE KEYS */;
INSERT INTO `методология программирования` VALUES ('KANBAN ','До 10','Весь проект делится на итерации (спринты) продолжительностью 30 дней каждый. Выбирается список функций системы, которые планируется реализовать в течение следующего спринта.'),('SCRUM','Не больше 5','Визуализация разработки: разделение работы на задачи, использование отметок о положение задачи в разработке;ограничение работ, выполняющихся одновременно, на каждом этапе разработки');
/*!40000 ALTER TABLE `методология программирования` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `операционная система`
--

DROP TABLE IF EXISTS `операционная система`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `операционная система` (
  `Дата выпуска` datetime DEFAULT NULL,
  `Название` varchar(255) NOT NULL,
  `Предназначение` varchar(255) DEFAULT NULL,
  `Разрядность` int(11) DEFAULT NULL,
  `Тип интерфейса` varchar(255) DEFAULT NULL,
  `Тип задачности` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Название`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `операционная система`
--

LOCK TABLES `операционная система` WRITE;
/*!40000 ALTER TABLE `операционная система` DISABLE KEYS */;
INSERT INTO `операционная система` VALUES (NULL,'Ios','операционная система для смартфонов, электронных планшетов и носимых проигрывателей, разрабатываемая и выпускаемая американской компанией Apple.',64,'графический интерфейс','Многозадачная'),(NULL,'Linux',' общее название Unix-подобных операционных систем, основанных на одноимённом ядре. Ядро Linux создаётся и распространяется в соответствии с моделью разработки свободного и открытого программного обеспечения. ',64,'графический интерфейс','Многозадачная'),(NULL,'Windows','Microsoft Windows — семейство проприетарных операционных систем корпорации Microsoft, ориентированных на применение графического интерфейса при управлении. Изначально Windows была всего лишь графической надстройкой для MS-DOS.',64,'графический интерфейс','Многозадачная'),(NULL,'Кококо',NULL,NULL,NULL,NULL),(NULL,'МамаПапаДНК','Передание генетического материала',64,'графический интерфейс','Многозадачная');
/*!40000 ALTER TABLE `операционная система` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `программа`
--

DROP TABLE IF EXISTS `программа`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `программа` (
  `Название` varchar(255) NOT NULL,
  `Проект` varchar(255) DEFAULT NULL,
  `Предназначение` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Название`),
  KEY `ПроектПрограмма` (`Проект`),
  CONSTRAINT `ПроектПрограмма` FOREIGN KEY (`Проект`) REFERENCES `проект` (`Название`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `программа`
--

LOCK TABLES `программа` WRITE;
/*!40000 ALTER TABLE `программа` DISABLE KEYS */;
INSERT INTO `программа` VALUES ('Crysis3','CryEngine','Полезное времяпровождение'),('Dima','Мама','Дарить добро'),('Download Master','Download Technologies','Скачивание файлов'),('Google Chrome','Google','Просмотр вебсайтов'),('Hard Disk Sentinel','Hard Disk Sentinel Developers','Контроль за состоянием жесткого диска'),('Hello man','Download Technologies',NULL),('Hi',NULL,NULL),('IntelIJ Idea',' NetBeans','Среда программирования'),('MicrosoftExcel','Microsoft Office','Создание таблиц'),('OK','Мама','Test'),('Test1','Download Technologies',NULL),('Проект','Download Technologies','Фор фан'),('Проект22','Мама','Ок');
/*!40000 ALTER TABLE `программа` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `программа-операционная система`
--

DROP TABLE IF EXISTS `программа-операционная система`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `программа-операционная система` (
  `Программа` varchar(255) NOT NULL,
  `Операционная система` varchar(255) NOT NULL,
  PRIMARY KEY (`Программа`,`Операционная система`),
  KEY `Операционная система-Операционная система` (`Операционная система`),
  CONSTRAINT `Операционная система-Операционная система` FOREIGN KEY (`Операционная система`) REFERENCES `операционная система` (`Название`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ПрограммаПрограмма` FOREIGN KEY (`Программа`) REFERENCES `программа` (`Название`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `программа-операционная система`
--

LOCK TABLES `программа-операционная система` WRITE;
/*!40000 ALTER TABLE `программа-операционная система` DISABLE KEYS */;
INSERT INTO `программа-операционная система` VALUES ('Crysis3','Ios'),('Google Chrome','Ios'),('IntelIJ Idea','Ios'),('Crysis3','Linux'),('Google Chrome','Linux'),('Hard Disk Sentinel','Linux'),('IntelIJ Idea','Linux'),('Crysis3','Windows'),('Download Master','Windows'),('Google Chrome','Windows'),('Hard Disk Sentinel','Windows'),('IntelIJ Idea','Windows'),('MicrosoftExcel','Windows'),('Dima','МамаПапаДНК'),('Проект22','МамаПапаДНК');
/*!40000 ALTER TABLE `программа-операционная система` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `программирование`
--

DROP TABLE IF EXISTS `программирование`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `программирование` (
  `Программный инженер` varchar(255) NOT NULL,
  `Его программа` varchar(255) NOT NULL,
  PRIMARY KEY (`Программный инженер`,`Его программа`),
  KEY `ПрограммаПрограммирование` (`Его программа`),
  CONSTRAINT `ПрограммаПрограммирование` FOREIGN KEY (`Его программа`) REFERENCES `программа` (`Название`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ПрограммныйИнженерПрограммирование` FOREIGN KEY (`Программный инженер`) REFERENCES `программный инженер` (`ФИО`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `программирование`
--

LOCK TABLES `программирование` WRITE;
/*!40000 ALTER TABLE `программирование` DISABLE KEYS */;
INSERT INTO `программирование` VALUES ('Засуха Дмитрий Александрович','Dima');
/*!40000 ALTER TABLE `программирование` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `программный инженер`
--

DROP TABLE IF EXISTS `программный инженер`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `программный инженер` (
  `ФИО` varchar(255) NOT NULL,
  `Принадлежность команде` varchar(255) DEFAULT NULL,
  `Звание` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ФИО`),
  KEY `КомандаПрограммный инженер` (`Принадлежность команде`),
  CONSTRAINT `КомандаПрограммный инженер` FOREIGN KEY (`Принадлежность команде`) REFERENCES `команда` (`Название`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `программный инженер`
--

LOCK TABLES `программный инженер` WRITE;
/*!40000 ALTER TABLE `программный инженер` DISABLE KEYS */;
INSERT INTO `программный инженер` VALUES ('Засуха Дмитрий Александрович','Родители','Senior Developer'),('Пупкин Вася Васильевич','Ololo','Junior Developer'),('Пупкина Маргарита Владимировна','Ololo','Senior Developer'),('Сигизмунд Василий Павлович','Ololo','Junior Developer'),('Хо хо хо','Ololo','Developer');
/*!40000 ALTER TABLE `программный инженер` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `проект`
--

DROP TABLE IF EXISTS `проект`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `проект` (
  `Название` varchar(255) NOT NULL,
  `Команда` varchar(255) NOT NULL,
  `Клиент` varchar(255) DEFAULT NULL,
  `Методология` varchar(255) DEFAULT NULL,
  `Бюджет` decimal(19,4) DEFAULT NULL,
  `Закончен` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`Название`),
  KEY `КлиентПроект` (`Клиент`),
  KEY `Методология программированияПроект1` (`Методология`),
  KEY `fk_Команда` (`Команда`),
  CONSTRAINT `fk_Команда` FOREIGN KEY (`Команда`) REFERENCES `команда` (`Название`),
  CONSTRAINT `КлиентПроект` FOREIGN KEY (`Клиент`) REFERENCES `клиент` (`Название фирмы`),
  CONSTRAINT `Методология программированияПроект` FOREIGN KEY (`Методология`) REFERENCES `методология программирования` (`Название`),
  CONSTRAINT `Методология программированияПроект1` FOREIGN KEY (`Методология`) REFERENCES `методология программирования` (`Название`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `проект`
--

LOCK TABLES `проект` WRITE;
/*!40000 ALTER TABLE `проект` DISABLE KEYS */;
INSERT INTO `проект` VALUES (' NetBeans','Shona',NULL,'KANBAN ',5000.0000,1),('CryEngine','Ololo',NULL,'KANBAN ',100.0000,0),('Download Technologies','SyperTeam',NULL,'SCRUM',30000.0000,1),('Google','Nanana',NULL,'SCRUM',55000.0000,1),('Hard Disk Sentinel Developers','Nanana',NULL,'KANBAN ',0.0000,0),('Microsoft Office','Heyhey',NULL,'SCRUM',6576575.0000,1),('Мама','Родители',NULL,'SCRUM',1.0000,0);
/*!40000 ALTER TABLE `проект` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `Бюджет_before_insert` BEFORE INSERT ON `Проект`
 FOR EACH ROW
 BEGIN
     declare msg varchar(128);
     IF new.`Бюджет` < 0.0 THEN
         set msg = concat('Trying to insert a negative value in `Бюджет`: ', cast(new.`Бюджет` as char));
         signal sqlstate '45000' set message_text = msg;
     END IF;
 END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `Бюджет_before_update` BEFORE UPDATE ON Проект
FOR EACH ROW
BEGIN
	declare msg varchar(128);
    IF new.Бюджет < 0.0 THEN
        set msg = concat('MyTriggerError: Trying to update a negative value in Бюджет: ', cast(new.Бюджет as char));
        signal sqlstate '45000' set message_text = msg;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `проект-требование`
--

DROP TABLE IF EXISTS `проект-требование`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `проект-требование` (
  `Проект` varchar(255) DEFAULT NULL,
  `Требование` int(11) DEFAULT NULL,
  `В срок` tinyint(1) DEFAULT '0',
  KEY `ПроектПроект-Требование` (`Проект`),
  KEY `ТребованиеПроект-Требование` (`Требование`),
  CONSTRAINT `ПроектПроект-Требование` FOREIGN KEY (`Проект`) REFERENCES `проект` (`Название`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ТребованиеПроект-Требование` FOREIGN KEY (`Требование`) REFERENCES `требования` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `проект-требование`
--

LOCK TABLES `проект-требование` WRITE;
/*!40000 ALTER TABLE `проект-требование` DISABLE KEYS */;
INSERT INTO `проект-требование` VALUES ('CryEngine',1,1),('CryEngine',2,1),('CryEngine',3,1),('CryEngine',4,1),('CryEngine',5,1),('Google',1,0),('Google',2,1),('Google',3,1),('Google',4,0),('Google',5,1),('Hard Disk Sentinel Developers',1,0),('Hard Disk Sentinel Developers',2,0),('Hard Disk Sentinel Developers',3,1),('Hard Disk Sentinel Developers',4,1),('Hard Disk Sentinel Developers',5,1),('Microsoft Office',1,0),('Microsoft Office',2,1),('Microsoft Office',3,1),('Microsoft Office',4,0),('Microsoft Office',5,0),('Мама',1,1),('Мама',2,1),('Мама',3,1),('Мама',4,1),('Мама',5,1),(' NetBeans',1,0),(' NetBeans',2,1),(' NetBeans',3,1),(' NetBeans',4,1),(' NetBeans',5,0),('Download Technologies',1,0),('Download Technologies',2,1),('Download Technologies',3,1),('Download Technologies',4,0),('Download Technologies',5,0);
/*!40000 ALTER TABLE `проект-требование` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `реализация`
--

DROP TABLE IF EXISTS `реализация`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `реализация` (
  `Программа` varchar(255) NOT NULL,
  `Пишется на языке программирования` varchar(255) NOT NULL,
  PRIMARY KEY (`Программа`,`Пишется на языке программирования`),
  KEY `Язык программированияРеализация` (`Пишется на языке программирования`),
  CONSTRAINT `ПрограммаРеализация` FOREIGN KEY (`Программа`) REFERENCES `программа` (`Название`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Язык программированияРеализация` FOREIGN KEY (`Пишется на языке программирования`) REFERENCES `язык программирования` (`Название`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `реализация`
--

LOCK TABLES `реализация` WRITE;
/*!40000 ALTER TABLE `реализация` DISABLE KEYS */;
INSERT INTO `реализация` VALUES ('Crysis3','C#'),('Download Master','C++'),('MicrosoftExcel','C++'),('IntelIJ Idea','Java'),('Google Chrome','Javascript'),('Dima','Sex');
/*!40000 ALTER TABLE `реализация` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `требования`
--

DROP TABLE IF EXISTS `требования`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `требования` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Требование` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `требования`
--

LOCK TABLES `требования` WRITE;
/*!40000 ALTER TABLE `требования` DISABLE KEYS */;
INSERT INTO `требования` VALUES (1,'Срок'),(2,'Сопровождение'),(3,'Доступность под мобильные устройства'),(4,'Доступность под ПК'),(5,'Особенности Заказа');
/*!40000 ALTER TABLE `требования` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-19  0:40:44
