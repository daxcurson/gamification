CREATE TABLE `gamification`.`personas` ( 
	`id` INT NOT NULL AUTO_INCREMENT , 
	`user_id` INT NOT NULL , 
	`usuario_sistema` INT NOT NULL , 
	`habilitada` INT NOT NULL , 
	`nombre` VARCHAR(300) NOT NULL , 
	PRIMARY KEY (`id`)
) ENGINE = InnoDB;
ALTER TABLE `estudiantes` CHANGE `id` `id` INT(11) NOT NULL;
ALTER TABLE `capacitadores` CHANGE `id` `id` INT(11) NOT NULL;
