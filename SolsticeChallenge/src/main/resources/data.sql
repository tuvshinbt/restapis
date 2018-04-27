--- 1 ---
INSERT INTO address(address1, address2, city, state, zip_code) VALUES('4915 West Belle Plaine Avenue', null, 'Chicago', 'IL', 60641);
INSERT INTO company(`name`, phone) VALUES ('Solstice', 987654322);
INSERT INTO contact(email, phone_number, profile_image) VALUES ('battuvshin.badarch@gmail.com', 3128419455, 'images\Battuvshin_Badarch.jpg');
INSERT INTO person (birth_date, full_name, address_id, company_id, contact_id) VALUES (SYSDATE(), 'Battuvshin Badarch', 1, 1, 1); 
--- 2 ---
INSERT INTO address(address1, address2, city, state, zip_code) VALUES('1000 West Belle Plaine Avenue', null, 'Chicago', 'IL', 60641);
INSERT INTO company(`name`, phone) VALUES ('Tek', 987654311);
INSERT INTO contact(email, phone_number, profile_image) VALUES ('john.james@gmail.com', 2145249786, null);
INSERT INTO person (birth_date, full_name, address_id, company_id, contact_id) VALUES (SYSDATE(), 'John James', 2, 2, 2); 
--- 3 ---
INSERT INTO address(address1, address2, city, state, zip_code) VALUES('4th Street', null, 'Fairfield', 'IO', 60641);
INSERT INTO company(`name`, phone) VALUES ('Bank', 1254789600);
INSERT INTO contact(email, phone_number, profile_image) VALUES ('sue.jim@gmail.com', 5218479632, 'PIC');
INSERT INTO person (birth_date, full_name, address_id, company_id, contact_id) VALUES (SYSDATE(), 'Sue Jim', 3, 3, 3); 