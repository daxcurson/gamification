DROP TABLE `respuestas_formuladas`;
CREATE TABLE `gamification`.`correcciones` (
	`id` INT NOT NULL AUTO_INCREMENT , 
	`evaluacion_tomada_id` INT NOT NULL , 
	`capacitador_id` INT NOT NULL , 
	`fecha` DATE NOT NULL , 
	`nota` int not null,
	PRIMARY KEY (`id`)
) ENGINE = InnoDB;
CREATE TABLE `gamification`.`correcciones_preguntas` ( 
	`id` INT NOT NULL AUTO_INCREMENT , 
	`correccion_id` INT NOT NULL , 
	`respuesta_id` INT NOT NULL , 
	`pregunta_id` INT NOT NULL , 
	`comentarios` TEXT NULL , 
	`nota` ENUM('BIEN','MAL','REGULAR','') NOT NULL , 
	PRIMARY KEY (`id`)
) ENGINE = InnoDB;
