ALTER TABLE `inscripciones` ADD `aprobada` INT NOT NULL AFTER `activa`, 
	ADD `puntos` INT NOT NULL AFTER `aprobada`;
ALTER TABLE `cursos` ADD `puntos` INT NOT NULL AFTER `capacitador_id`;
