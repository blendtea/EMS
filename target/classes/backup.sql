DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` int NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `users` WRITE;

INSERT INTO `users` VALUES (1,_binary '','`谷田','永遠','$2a$10$O9aKrEc5jChrEObtN5LTxusc/T3Q49cX6Nij04SX8.XH4p14WCWxm','NV242'),(2,_binary '','村井','俊介','$2a$10$GmR2EzFiuSjiQHO4hQ7AaeGNgRDPJIF3Q68vgKdPbG2nj0qXvtBZm','NV245');

UNLOCK TABLES;
