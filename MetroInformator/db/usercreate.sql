INSERT INTO testbase.user (id, firstName, lastName, birthDay, login, password) VALUES (1, 'Alexey', 'Bystrov', '1990-12-18', '13shut13@mail.ru', '$2a$10$R4AIlHDefOTUd.iLCHhxvuW87qY4dpV3vk8YMISom.Ke1F.HBEeAO');
INSERT INTO testbase.user (id, firstName, lastName, birthDay, login, password) VALUES (3, 'Misha', 'Lisechkin', '1990-12-18', 'volpert13@gmail.com', '$2a$10$R4AIlHDefOTUd.iLCHhxvuW87qY4dpV3vk8YMISom.Ke1F.HBEeAO');
INSERT INTO testbase.user (id, firstName, lastName, birthDay, login, password) VALUES (4, 'Andrey', 'Fetisov', '1990-12-18', 'fetisbs@mail.ru', '$2a$10$R4AIlHDefOTUd.iLCHhxvuW87qY4dpV3vk8YMISom.Ke1F.HBEeAO');
INSERT INTO testbase.user (id, firstName, lastName, birthDay, login, password) VALUES (5, 'Kostya', 'Kashkin', '1990-12-18', 'kash@mail.ru', '$2a$10$R4AIlHDefOTUd.iLCHhxvuW87qY4dpV3vk8YMISom.Ke1F.HBEeAO');
INSERT INTO testbase.user (id, firstName, lastName, birthDay, login, password) VALUES (6, 'aa', 'bb', 'aa@aa.ru', '1990-12-18', '$2a$10$R4AIlHDefOTUd.iLCHhxvuW87qY4dpV3vk8YMISom.Ke1F.HBEeAO');
INSERT INTO testbase.user (id, firstName, lastName, birthDay, login, password) VALUES (7, 'Misha', 'Lisechkin', '1990-12-18', 'dummymailmetro@gmail.com', '$2a$10$R4AIlHDefOTUd.iLCHhxvuW87qY4dpV3vk8YMISom.Ke1F.HBEeAO');
-- ===========================================
-- ============USER-ROLES=====================
-- ===========================================
INSERT INTO testbase.user_role (user_id, role_id) VALUES (1, 3);
INSERT INTO testbase.user_role (user_id, role_id) VALUES (3, 1);
INSERT INTO testbase.user_role (user_id, role_id) VALUES (4, 3);
INSERT INTO testbase.user_role (user_id, role_id) VALUES (5, 3);
INSERT INTO testbase.user_role (user_id, role_id) VALUES (6, 1);