insert into genre(genre_name) values ('Horor')
insert into genre(genre_name) values ('Akcija')
insert into genre(genre_name) values ('Komedija')
insert into genre(genre_name) values ('Drama')
insert into genre(genre_name) values ('Triler')
insert into genre(genre_name) values ('Dokumentarni')
insert into genre(genre_name) values ('Misterija')

insert into actor(first_name, last_name) values ('Džim', 'Keri')
insert into actor(first_name, last_name) values ('Šon', 'Koneri')
insert into actor(first_name, last_name) values ('Dragan', 'Jovanović')
insert into actor(first_name, last_name) values ('Pavle', 'Vuisić')

insert into director(first_name, last_name) values ('Stiven', 'Spilberg')
insert into director(first_name, last_name) values ('Kventin', 'Tarantino')
insert into director(first_name, last_name) values ('Dejvid', 'Linc')
insert into director(first_name, last_name) values ('Stenli', 'Kjubrik')

insert into theatre_cinema(type, name, adress, description) values (1, 'Arena Cineplex', 'Bul. Mihajla pupina 3 21000 Novi Sad', 'Opis Arene Cinplex')
insert into theatre_cinema(type, name, adress, description) values (1, 'Sinestar Novi Sad', 'Sentandrejski put 11, BIG CEE Novi Sad', 'Opis sienstara big cee')
insert into theatre_cinema(type, name, adress, description) values (0, 'Srpsko Narodno Pozoriste', 'Pozorisni Trg 1 Novi Sad', 'SNP pozoriste ns opis neki')
insert into theatre_cinema(type, name, adress, description) values (0, 'Pozoriste na Terazijama', 'Terazije 29 Beograd', 'BG pozoriste terazije opis neki')

insert into hall(name, theatre_cinema_id) values ('glavna sala', 1)
insert into hall(name, theatre_cinema_id) values ('glavna sala', 1)
insert into hall(name, theatre_cinema_id) values ('glavna sala', 2)
insert into hall(name, theatre_cinema_id) values ('mala sala', 3)

insert into segment(name, hall_id) values ('prvi segment', 1)
insert into segment(name, hall_id) values ('drugi segment', 2)
insert into segment(name, hall_id) values ('prvi segment', 2)
insert into segment(name, hall_id) values ('prednji deo', 2)


insert into movie_performance(type, name, director_id, length, poster) values (1, "Film1", 1, 78978, "poster1")
insert into movie_performance(type, name, director_id, length, poster) values (1, "Film2", 2, 3215, "poster2")

insert into projection(name, description, projection_date_time, movie_performance_id, hall_id, theatre_cinema_id) values ("film 1 projekcija 1", "projekcija 1", "1523552094656", 1, 1, 1) 
insert into projection(name, description, projection_date_time, movie_performance_id, hall_id, theatre_cinema_id) values ("film 2 projekcija 2", "projekcija 2", "1523552094656", 1, 2, 1)
insert into projection(name, description, projection_date_time, movie_performance_id, hall_id, theatre_cinema_id) values ("projekcija2", "projekcija2", "1523", 1, 2, 1)
insert into projection(name, description, projection_date_time, movie_performance_id, hall_id, theatre_cinema_id) values ("film 3 projekcija 3", "projekcija 3", "1523552111", 1, 1, 3) 

insert into user(city, email, first_login, mobile_number, user_name,user_password, user_password_conf, user_role, user_status, user_surname) values ('Zvornik','sysAdmin@sysAdmin.com',0,'123','sysAdmin','sysAdmin','sysAdmin', 2,0,'sysAdmin')

insert into user(city, email, first_login, mobile_number, user_name,user_password, user_password_conf, user_role, user_status, user_surname) values ('Zvornik1','sysAdmin1@sysAdmin1.com',0,'1234','sysAdmin1','sysAdmin1','sysAdmin1', 3,0,'sysAdmin1')

insert into user(city,email,first_login,mobile_number,user_name,user_password,user_password_conf,user_role,user_status,user_surname) value ('Lopare','s@s.com',0,'123','s','s','s',0,0,'s')
insert into user(city,email,first_login,mobile_number,user_name,user_password,user_password_conf,user_role,user_status,user_surname) value ('Aaaa','a@a.com',0,'1234','a','a','a',0,0,'a')
insert into user(city,email,first_login,mobile_number,user_name,user_password,user_password_conf,user_role,user_status,user_surname) value ('bbbb','b@b.com',0,'12345','b','b','b',0,0,'b')
insert into user(city,email,first_login,mobile_number,user_name,user_password,user_password_conf,user_role,user_status,user_surname) value ('cccc','c@c.com',0,'12341','c','c','c',0,0,'c')

