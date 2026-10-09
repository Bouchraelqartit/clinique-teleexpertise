USE clinique_teleexpertise;

-- Test accounts. Password for both accounts: test1234 (BCrypt, cost 10).
INSERT INTO utilisateur (username, password, role)
VALUES ('infirmier', '$2a$10$By1KpU25gmiWve199ZlZ6.oPNaLBRMn4LAOqsZOj4VGcb2tZGQlaC', 'INFIRMIER')
ON DUPLICATE KEY UPDATE
    password = VALUES(password),
    role = VALUES(role);

INSERT INTO utilisateur (username, password, role)
VALUES ('generaliste', '$2a$10$kgnHwRNYheP.oySkIpqC1eFCw7HrQJAKbuE5veIbUhECkt2QUVZiK', 'GENERALISTE')
ON DUPLICATE KEY UPDATE
    password = VALUES(password),
    role = VALUES(role);
