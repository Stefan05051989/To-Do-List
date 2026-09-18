-- Users
INSERT INTO users (first_name, last_name, email, password, is_admin)
VALUES ('Stefan', 'Kiers', 'stefan@test.nl', '$2b$12$FFAQXFVUroAUirZjORR.nOddUzqAUkhvGypIomBHYVGJYVYYRJ3rO', true),
       ('Jan', 'Jansen', 'jan@test.nl', '$2b$12$FFAQXFVUroAUirZjORR.nOddUzqAUkhvGypIomBHYVGJYVYYRJ3rO', false),
       ('Piet', 'Pietersen', 'piet@test.nl', '$2b$12$FFAQXFVUroAUirZjORR.nOddUzqAUkhvGypIomBHYVGJYVYYRJ3rO', false),
       ('Emma', 'de Vries', 'emma@test.nl', '$2b$12$FFAQXFVUroAUirZjORR.nOddUzqAUkhvGypIomBHYVGJYVYYRJ3rO', false),
       ('Lisa', 'Bakker', 'lisa@test.nl', '$2b$12$FFAQXFVUroAUirZjORR.nOddUzqAUkhvGypIomBHYVGJYVYYRJ3rO', false);

-- Task Lists
INSERT INTO task_list (title, user_id)
VALUES ('Werk', 1),
       ('Studie', 1),
       ('Persoonlijk', 2),
       ('Boodschappen', 3);

-- Tasks
INSERT INTO task (title, content, task_list_id, status, user_id, migration_count, created_at)
VALUES ('Backend afmaken', 'Spring Boot API compleet maken', 1, 'IN_PROGRESS', 1, 0, CURRENT_DATE),
       ('Frontend bouwen', 'React components maken', 1, 'CREATED', 1, 0, CURRENT_DATE),
       ('TypeScript leren', 'Cursus volgen', 2, 'IN_PROGRESS', 1, 0, CURRENT_DATE),
       ('React Query oefenen', 'useMutation implementeren', 2, 'CREATED', 1, 0, CURRENT_DATE),
       ('Boodschappen doen', 'Melk, brood, kaas', 4, 'CREATED', 3, 0, CURRENT_DATE),
       ('Sportschool', 'Maandag en woensdag', 3, 'DONE', 2, 0, CURRENT_DATE);

-- Projects
INSERT INTO project (title, deadline)
VALUES ('Website Redesign', '2026-12-31'),
       ('Interne Tool', '2027-03-31');

-- Project Members (Jan=Scrum Master, Lisa=Product Owner, Piet=Developer, Emma=Client — allemaal op project 1)
INSERT INTO project_member (project_id, user_id, role)
VALUES (1, 2, 'SCRUM_MASTER'),
       (1, 5, 'PRODUCT_OWNER'),
       (1, 3, 'DEVELOPER'),
       (1, 4, 'CLIENT');

-- Sprints
INSERT INTO sprint (title, description, start_date, end_date, sprint_status, project_id)
VALUES ('Sprint 1', 'Eerste sprint: basisopzet', '2026-09-01', '2026-09-14', 'ACTIVE', 1),
       ('Sprint 2', 'Tweede sprint: uitbreiding', '2026-09-15', '2026-09-28', 'PLANNED', 1);