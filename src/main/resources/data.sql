INSERT INTO pet_types (name) VALUES
('dog'),
('cat'),
('bird'),
('fish');


INSERT INTO center (city, name, street, telephone, password, confirm_password, is_active, email) VALUES
('Bucharest', 'centerA', 'some Street, 55', '00998877', 'password', 'password', 'true', 'email@test.ro'),
('Oradea', 'centerB', 'some other street, 66', '08325272', 'password', 'password', 'true', 'centerB@test.com');

INSERT INTO pets (name, description, date_of_birth, type, CENTER_ID) VALUES
('Dory', ' a lovely dog', '2020-05-01','3', '1' );

INSERT INTO roles (name) VALUES
('center'),
('admin');

/*Setting center roles to centers created
*/
INSERT INTO CENTER_ROLE(CENTER_ID, ROLE_ID) VALUES
(1,1),
(2,1);
