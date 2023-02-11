CREATE TABLE `events` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) DEFAULT NULL,
  `event_date` datetime(6) DEFAULT NULL,
  `partner_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpartnerid` (`partner_id`),
  CONSTRAINT `FKpartnerid` FOREIGN KEY (`partner_id`) REFERENCES `partners` (`id`)
)