drop database cafe;
create database cafe;
use cafe;

create table discounts
(
    discount_id   int auto_increment primary key,
    discount_type varchar(15) not null,
    discount_size double      not null
);

create table franchises
(
    franchise_id int auto_increment primary key,
    name         varchar(20) not null
);

create table cafe_addresses
(
    address_id    int auto_increment primary key,
    street_name   varchar(255) not null,
    building_num  int          not null,
    city          varchar(25)  not null,
    franchises_id int          not null,
    country       varchar(25)  not null,
    constraint fk_cafe_addresses_franchises1 foreign key (franchises_id) references franchises (franchise_id)
);

create index fk_cafe_addresses_franchises1_idx on cafe_addresses (franchises_id);

create table menu
(
    menu_id   int auto_increment primary key,
    menu_type varchar(15) not null
);

create table cafes
(
    cafe_id           int auto_increment primary key,
    cafe_addresses_id int         not null,
    menu_id           int         not null,
    cafe_name         varchar(15) not null,
    constraint fk_cafes_cafe_addresses1 foreign key (cafe_addresses_id) references cafe_addresses (address_id),
    constraint fk_cafes_menu1 foreign key (menu_id) references menu (menu_id)
);

create index fk_cafes_cafe_addresses1_idx
    on cafes (cafe_addresses_id);

create index fk_cafes_menu1_idx
    on cafes (menu_id);

create table employees
(
    employee_id int auto_increment primary key,
    name        varchar(25) not null,
    last_name   varchar(25) not null,
    phone_num   varchar(15) not null,
    position    varchar(20) not null,
    cafes_id    int         not null,
    constraint empl_id_UNIQUE unique (employee_id),
    constraint fk_employees_cafes1 foreign key (cafes_id) references cafes (cafe_id)
);

create index fk_employees_cafes1_idx
    on employees (cafes_id);

create table menu_item
(
    item_id          int auto_increment primary key,
    position_name    varchar(45)   not null,
    serving_portion  int           not null,
    measurement_unit varchar(10)   not null,
    price            decimal(6, 2) not null,
    menu_id          int           not null,
    constraint fk_menu_item_menu1 foreign key (menu_id) references menu (menu_id)
);

create index fk_menu_item_menu1_idx
    on menu_item (menu_id);

create table services
(
    service_id int auto_increment  primary key,
    type varchar(25) not null
);

create table tables
(
    table_id     int auto_increment primary key,
    seating_size int not null,
    cafes_id     int not null,
    constraint fk_tables_cafes1 foreign key (cafes_id) references cafes (cafe_id)
);

create table bookings
(
    booking_id int auto_increment primary key,
    time       timestamp(3) null,
    tables_id  int not null,
    constraint booking_id_UNIQUE
        unique (booking_id),
    constraint booking_time_UNIQUE
        unique (time),
    constraint fk_bookings_tables1
        foreign key (tables_id) references tables (table_id)
);

create index fk_bookings_tables1_idx
    on bookings (tables_id);

create table guests
(
    guest_id    int auto_increment primary key,
    name        varchar(20) not null,
    last_name   varchar(20) not null,
    bookings_id int         not null,
    constraint id_quest_UNIQUE unique (guest_id),
    constraint fk_guests_bookings1 foreign key (bookings_id) references bookings (booking_id)
);

create index fk_guests_bookings1_idx
    on guests (bookings_id);

create table orders
(
    order_id  int auto_increment primary key,
    guests_id int not null,
    constraint order_id_UNIQUE unique (order_id),
    constraint fk_orders_guests1 foreign key (guests_id) references guests (guest_id)
);

create table order_details
(
    details_id     int auto_increment primary key,
    menu_items_qty int not null,
    menu_item_id   int not null,
    orders_id      int not null,
    constraint fk_order_details_menu_item1
        foreign key (menu_item_id) references menu_item (item_id),
    constraint fk_order_details_orders1
        foreign key (orders_id) references orders (order_id)
);

create index fk_order_details_menu_item1_idx
    on order_details (menu_item_id);

create index fk_order_details_orders1_idx
    on order_details (orders_id);

create index fk_orders_guests1_idx
    on orders (guests_id);

create table orders_has_services
(
    ordserv_id  int not null primary key,
    orders_id   int not null,
    services_id int not null,
    constraint fk_orders_has_services_orders1
        foreign key (orders_id) references orders (order_id),
    constraint fk_orders_has_services_services1
        foreign key (services_id) references services (service_id)
);

create index fk_orders_has_services_orders1_idx
    on orders_has_services (orders_id);

create index fk_orders_has_services_services1_idx
    on orders_has_services (services_id);

create table payments
(
    payment_id   int auto_increment primary key,
    total_price  decimal(6, 2) not null,
    orders_id    int           not null,
    discounts_id int           not null,
    constraint fk_payments_discounts1
        foreign key (discounts_id) references discounts (discount_id),
    constraint fk_payments_orders1
        foreign key (orders_id) references orders (order_id)
);

create index fk_payments_discounts1_idx
    on payments (discounts_id);

create index fk_payments_orders1_idx
    on payments (orders_id);

create index fk_tables_cafes1_idx
    on tables (cafes_id);

