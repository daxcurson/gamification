ALTER TABLE `curso_ofertas` CHANGE `curso_id` `curso_oferta_id` INT(11) NOT NULL;
CREATE TABLE IF NOT EXISTS `preguntas` (
  `id` int(11) NOT NULL,
  `tipo_pregunta_id` int(11) NOT NULL,
  `texto_pregunta` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE IF NOT EXISTS `preguntas_formuladas` (
  `id` int(11) NOT NULL,
  `evaluacion_tomada_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE IF NOT EXISTS `respuestas` (
  `id` int(11) NOT NULL,
  `pregunta_id` int(11) NOT NULL,
  `valor_respuesta` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE IF NOT EXISTS `respuestas_formuladas` (
  `id` int(11) NOT NULL,
  `evaluacion_tomada_id` int(11) NOT NULL,
  `valor_respuesta` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE IF NOT EXISTS `calificaciones` (
  `id` int(11) NOT NULL,
  `evaluacion_tomada_id` int(11) NOT NULL,
  `capacitador_id` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `nota` double NOT NULL,
  `comentarios` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `preguntas`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `preguntas_formuladas`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `respuestas`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `respuestas_formuladas`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `calificaciones`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `preguntas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `preguntas_formuladas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `respuestas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `respuestas_formuladas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `calificaciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
