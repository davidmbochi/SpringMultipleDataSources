CREATE DATABASE IF NOT EXISTS `student_database`;


USE `student_database`;


DROP TABLE IF EXISTS `student`;

CREATE TABLE `student`(
`id` int NOT NULL AUTO_INCREMENT,
`first_name` varchar (50),
`last_name` varchar (50),
`email` varchar (50),
PRIMARY KEY (`id`)
);

LOCK TABLES `student` WRITE;

INSERT INTO `student` VALUES
(1,"john","doe","john@javadev.com"),
(2,"peter","anderson","peter@javadev.com"),
(3,"lucy","parker","lucy@javadev.com"),
(4,"mercy","williams","mercy@javadev.com");

UNLOCK TABLES;