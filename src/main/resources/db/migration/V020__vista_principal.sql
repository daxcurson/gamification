ALTER TABLE `groups` ADD `vista_principal` VARCHAR(100) NOT NULL AFTER `modified`;
update groups set vista_principal="menu" where group_name="Administradores";
