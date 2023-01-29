INSERT INTO client (name) VALUES
   ('Mark Jakob'),
   	('Jim Ford'),
   	('Rosy Bafoun'),
   	('Bill Trast'),
   	('Karl Magic'),
   	('Naomi Salar'),
   	('Natan Browen'),
   	('Ruf Rimes'),
   	('Richi Foster'),
   	('Cristin Malta');

INSERT INTO planet (id, name) VALUES
  ('MER', 'Mercury'),
  ('VEN', 'Venus'),
  ('EAR', 'Earth'),
  ('MAR', 'Mars'),
  ('JUP', 'Jupiter'),
  ('SAT', 'Saturn'),
  ('URA', 'Uranus'),
  ('NEP', 'Neptune');

INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id) VALUES
   ( '2022-12-31 23:59:59', 1, 'MER', 'EAR'),
   ( '2023-01-01 13:09:05', 2, 'VEN', 'MAR'),
   ( '2023-01-01 20:19:09', 3, 'EAR', 'JUP'),
   ( '2023-01-02 12:50:59', 4, 'MAR', 'VEN'),
   ( '2023-01-02 22:20:39', 5, 'VEN', 'MER'),
   ( '2023-01-04 12:50:59', 6, 'EAR', 'VEN'),
   ( '2023-01-05 11:50:09', 7, 'MER', 'SAT'),
   ( '2023-01-08 22:50:59', 8, 'JUP', 'URA'),
   ( '2023-01-10 12:50:59', 9, 'MER', 'EAR'),
   ( '2023-01-12 20:50:00', 10, 'NEP', 'MER');