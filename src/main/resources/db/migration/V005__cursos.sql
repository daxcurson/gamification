ALTER TABLE `cursos` CHANGE `horario` `codigo_curso` VARCHAR(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL;
ALTER TABLE `temas_cursos` ADD `curso_id` INT NOT NULL AFTER `descripcion`;
