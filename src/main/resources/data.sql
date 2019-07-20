create table if not exists authorities (username varchar(256), authority varchar(256))

insert into organization (name, description) VALUES ('Bury Mis', 'Orgnizacja zbiera dary dla dzieci')
insert into organization (name, description) VALUES ('Promyk słonca', 'Fundacja zbiera dary dla biednych rodzin')


insert into category (name, description) VALUES ('clothes-to-use', 'ubrania, które nadają się do ponownego użycia')
insert into category (name, description) VALUES ('clothes-useless', 'ubrania, do wyrzucenia')
insert into category (name, description) VALUES ('toys', 'zabawki')
insert into category (name, description) VALUES ('books', 'książki')
insert into category (name, description) VALUES ('other', 'inne')


