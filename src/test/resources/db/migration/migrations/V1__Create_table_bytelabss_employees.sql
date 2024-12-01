DROP TABLE IF EXISTS `bytelabss_employees`;
CREATE TABLE `bytelabss_employees` (
  `employee_id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `salary` int DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `hiring_date` date DEFAULT NULL,
  `salary_with_bonus` int DEFAULT NULL,
  `department_upper_case` varchar(255) DEFAULT NULL,
  `experience` double DEFAULT NULL,
  PRIMARY KEY (`employee_id`)
);
