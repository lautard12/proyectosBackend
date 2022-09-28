insert into serie(name, genre) values('Gotham', 'acción')
insert into serie(name, genre) values('3 por ciento', 'drama')
insert into serie(name, genre) values('The Flash', 'ciencia ficción')

insert into season(season_number, serie_id ) values(1, 1)

insert into chapter(name, number, url_stream, season_id) values('Pilot', 1, 'www.neflix.com/GothamTemp1Cap1', 1)
insert into chapter(name, number, url_stream, season_id) values('Selina Kyle', 2, 'www.neflix.com/GothamTemp1Cap2', 1)

