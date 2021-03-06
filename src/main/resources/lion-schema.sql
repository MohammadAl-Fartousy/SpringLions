DROP TABLE IF EXISTS `lion` CASCADE;
CREATE TABLE `lion` (
	`id` BIGINT PRIMARY KEY AUTO_INCREMENT, 
	`age` INTEGER CHECK (age<=15 AND age>=2), 
	`gender` VARCHAR(255), 
	`habitat` VARCHAR(255), 
	`name` VARCHAR(255) NOT NULL UNIQUE
);