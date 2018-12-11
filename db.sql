create table client_table (id bigserial primary key,
                       firstname varchar(40) not null,
                       lastname varchar(40) not null,
                       fathername varchar(40),
                       phone varchar(15));

create table machinist_table (id bigserial primary key,
                       firstname varchar(40) not null,
                       lastname varchar(40) not null,
                       fathername varchar(40),
                       hour_cost double precision not null);

create table order_status_table (id bigint primary key,
                       status varchar(15) not null);

create table order_table (id bigserial primary key,
                       description varchar(400),
                       client_id bigint not null,
                       machinist_id bigint not null,
                       start_date date not null,
                       end_date date not null,
                       full_cost double precision not null,
                       order_status_id bigint not null,
                       constraint order_table_client_id_fk foreign key (client_id) references client_table (id),
                       constraint order_table_machinist_id_fk foreign key (machinist_id) references machinist_table (id),
                       constraint order_status_table_order_status_id_fk foreign key (order_status_id) references order_status_table (id));

insert into client_table(firstname, lastname, fathername, phone) values('Андрей', 'Ковалёв', 'Аркадьевич', '8988906977');
insert into client_table(firstname, lastname, fathername, phone) values('Алексей', 'Страйк', 'Александрович', '182225');
insert into client_table(firstname, lastname, fathername, phone) values('Александр', 'Карпухин', '', '287755');
insert into client_table(firstname, lastname, fathername, phone) values('Игорь', 'Моравский', '', '89487521456');

insert into machinist_table(firstname, lastname, fathername, hour_cost) values('Николай', 'Иванов', 'Петрович', 500);
insert into machinist_table(firstname, lastname, fathername, hour_cost) values('Иван', 'Петров', 'Петрович', 800);
insert into machinist_table(firstname, lastname, fathername, hour_cost) values('Пётр', 'Капустин', 'Петрович', 1000);
insert into machinist_table(firstname, lastname, fathername, hour_cost) values('Игорь', 'Прохин', 'Петрович', 1800);

insert into order_status_table values(0, 'Запланирован');
insert into order_status_table values(1, 'Выполнен');
insert into order_status_table values(2, 'Принят клиентом');

insert into order_table(description, client_id, machinist_id, start_date, end_date, full_cost, order_status_id) values('Замена покрышек', 1, 2, '2018-09-01', '2018-09-02', 1600, 2);
insert into order_table(description, client_id, machinist_id, start_date, end_date, full_cost, order_status_id) values('Замена тормозных колодок', 2, 2, '2018-09-10', '2018-09-12', 1200, 0);
insert into order_table(description, client_id, machinist_id, start_date, end_date, full_cost, order_status_id) values('Замена масла', 3, 1, '2018-09-09', '2018-09-12', 2000, 1);
insert into order_table(description, client_id, machinist_id, start_date, end_date, full_cost, order_status_id) values('Замена тормозных колодок', 4, 3, '2018-09-01', '2018-09-03', 2200, 1);
insert into order_table(description, client_id, machinist_id, start_date, end_date, full_cost, order_status_id) values('Ремонт правого поворотника', 3, 4, '2018-09-11', '2018-09-12', 1600, 0);
