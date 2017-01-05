ALTER TABLE `preguntas` ADD `evaluacion_id` INT NOT NULL AFTER `texto_pregunta`;
ALTER TABLE `preguntas` ADD `texto_ordenar` TEXT NOT NULL AFTER `evaluacion_id`;
CREATE TABLE `tipos_pregunta` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(200) NOT NULL,
  `codigo` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
ALTER TABLE `tipos_pregunta` ADD PRIMARY KEY(`id`);
ALTER TABLE `tipos_pregunta` CHANGE `id` `id` INT(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `tipos_pregunta` CHANGE `codigo` `codigo` VARCHAR(50) NOT NULL;
INSERT INTO `tipos_pregunta` (`id`, `descripcion`, `codigo`) VALUES (NULL, 'Code Magnet', 'CMAGNET');
