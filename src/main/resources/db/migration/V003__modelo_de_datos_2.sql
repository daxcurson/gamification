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
CREATE TABLE `estudiantes` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `usuario_sistema` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `habilitada` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;
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
CREATE TABLE `groups` (
  `id` int(11) NOT NULL,
  `created` datetime DEFAULT NULL,
  `group_name` varchar(255) DEFAULT NULL,
  `modified` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
INSERT INTO `groups` (`id`, `created`, `group_name`, `modified`) VALUES
(2, NULL, 'Administradores', NULL);
CREATE TABLE `group_permission` (
  `id` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL
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
ALTER TABLE `estudiantes`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `evaluaciones`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `evaluaciones_tomadas`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `groups`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `group_permission`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_14fbne1or7jokuvpatksjhlcv` (`group_id`);
ALTER TABLE `inscripciones`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `persistent_logins`
  ADD PRIMARY KEY (`series`);
ALTER TABLE `schema_version`
  ADD PRIMARY KEY (`installed_rank`),
  ADD KEY `schema_version_s_idx` (`success`);
ALTER TABLE `temas_cursos`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username_fc` (`username`),
  ADD UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`),
  ADD KEY `FK_fm4cfgdt24toh89yw4rbnu1lb` (`group_id`);
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
