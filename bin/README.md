# gestionscolairespringboot
Bonjour à tous


INSERT INTO `role` (`name`) VALUES ('USER');
INSERT INTO `role` (`name`) VALUES ('CREATOR');
INSERT INTO `role` (`name`) VALUES ('EDITOR');
INSERT INTO `role` (`name`) VALUES ('ADMIN');

INSERT INTO `cours` (`id_cours`, `libelle`, `nb_heure`) VALUES (1, 'Cours de JAVA', 1);
INSERT INTO `cours` (`id_cours`, `libelle`, `nb_heure`) VALUES (2, 'Cours de SPRING', 2);
INSERT INTO `cours` (`id_cours`, `libelle`, `nb_heure`) VALUES (3, 'Cours de ANGULAR', 1);


INSERT INTO `personne` (`id`, `adresse`, `mail`, `nom`, `prenom`, `telephone`) VALUES (1, "241 rue DUGUESCLIN,
69003 Lyon","contact@objis.com", "objis", "objis", 012030405);
INSERT INTO `etudiant` (`date_naissance`, `id`) VALUES ("01/01/01", 1);

INSERT INTO `user` (`username`, `password`, `enabled`) VALUES ('patrick', '$2a$10$cTUErxQqYVyU2qmQGIktpup5chLEdhD2zpzNEyYqmxrHHJbSNDOG.', 1);
(patrick,patrick)

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (1, 1);

INSERT INTO `user` (`username`, `password`, `enabled`) VALUES ('alex', '$2a$10$.tP2OH3dEG0zms7vek4ated5AiQ.EGkncii0OpCcGq4bckS9NOULu', 1);

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (2, 2);


http://localhost:8080/gestion
