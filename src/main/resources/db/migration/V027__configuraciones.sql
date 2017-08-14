CREATE TABLE `gamification`.`configuraciones` 
(
	`id` INT NOT NULL AUTO_INCREMENT , 
	`config_nombre` VARCHAR(300) NOT NULL , 
	`config_valor` VARCHAR(300) NOT NULL , 
	PRIMARY KEY (`id`)
) ENGINE = InnoDB;

insert into configuraciones(config_nombre,config_valor) values ("nota_aprobacion","6");
