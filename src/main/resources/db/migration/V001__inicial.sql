CREATE TABLE `groups` (
  `id` int(11) NOT NULL,
  `created` datetime DEFAULT NULL,
  `group_name` varchar(255) DEFAULT NULL,
  `modified` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `group_permission` (
  `id` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `personas` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `usuario_sistema` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `habilitada` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `enabled` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `groups`
  ADD PRIMARY KEY (`id`);
ALTER TABLE `group_permission`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_14fbne1or7jokuvpatksjhlcv` (`group_id`);
ALTER TABLE `persistent_logins`
  ADD PRIMARY KEY (`series`);
ALTER TABLE `personas`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username_fc` (`username`),
  ADD KEY `FK_fm4cfgdt24toh89yw4rbnu1lb` (`group_id`);

ALTER TABLE `groups`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `group_permission`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `personas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `group_permission`
  ADD CONSTRAINT `FK_14fbne1or7jokuvpatksjhlcv` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`);

