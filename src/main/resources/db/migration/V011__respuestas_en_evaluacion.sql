drop table preguntas_formuladas;
ALTER TABLE `respuestas` ADD `evaluacion_tomada_id` INT NOT NULL AFTER `pregunta_id`;
