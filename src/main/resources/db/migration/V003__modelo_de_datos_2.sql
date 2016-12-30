CREATE TABLE `capacitadores` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `usuario_sistema` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `habilitada` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `cursos` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `horario` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `curso_ofertas` (
  `id` int(11) NOT NULL,
  `curso_id` int(11) NOT NULL,
  `fecha_comienzo` date NOT NULL,
  `fecha_fin` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `evaluaciones` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(200) NOT NULL,
  `curso_oferta_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `evaluaciones_tomadas` (
  `id` int(11) NOT NULL,
  `curso_oferta_id` int(11) NOT NULL,
  `fecha_evaluacion` date NOT NULL,
  `estudiante_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `inscripciones` (
  `id` int(11) NOT NULL,
  `estudiante_id` int(11) NOT NULL,
  `curso_oferta_id` int(11) NOT NULL,
  `fecha_inscripcion` date NOT NULL,
  `activa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `temas_cursos` (
  `id` int(11) NOT NULL,
  `nombre` varchar(200) NOT NULL,
  `descripcion` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `capacitadores`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `cursos`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `curso_ofertas`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `evaluaciones`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `evaluaciones_tomadas`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `inscripciones`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `temas_cursos`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `capacitadores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `cursos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `curso_ofertas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `evaluaciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `evaluaciones_tomadas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `temas_cursos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
