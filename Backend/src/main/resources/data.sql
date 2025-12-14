-- Users
INSERT INTO users (first_name, last_name, email, password) VALUES
('Stefan', 'Kiers', 'stefan@test.nl', 'password123'),
('Jan', 'Jansen', 'jan@test.nl', 'password123'),
('Piet', 'Pietersen', 'piet@test.nl', 'password123');

-- Task Lists
INSERT INTO task_list (title, user_id) VALUES
('Werk', 1),
('Studie', 1),
('Persoonlijk', 2),
('Boodschappen', 3);

-- Tasks
INSERT INTO task (title, content, task_list_id, status, user_id) VALUES
('Backend afmaken', 'Spring Boot API compleet maken', 1, 'IN_PROGRESS', 1),
('Frontend bouwen', 'React components maken', 1, 'CREATED', 1),
('TypeScript leren', 'Cursus volgen', 2, 'IN_PROGRESS', 1),
('React Query oefenen', 'useMutation implementeren', 2, 'CREATED', 1),
('Boodschappen doen', 'Melk, brood, kaas', 4, 'CREATED', 3),
('Sportschool', 'Maandag en woensdag', 3, 'DONE', 2);