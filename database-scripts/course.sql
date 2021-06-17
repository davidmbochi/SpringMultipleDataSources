CREATE DATABASE IF NOT EXISTS `course_database`;


USE `course_database`;


DROP TABLE IF EXISTS `course`;

CREATE TABLE `course`(
`id` int NOT NULL AUTO_INCREMENT,
`course_name` varchar (50),
PRIMARY KEY (`id`)
);

LOCK TABLES `course` WRITE;

INSERT INTO `course` VALUES
(1,"Introduction to Java"),
(2,"Multithreading in Action"),
(3,"Concurrency control mechanisms"),
(4,"Data structures and algorithms");

UNLOCK TABLES;