DROP TABLE buckets_products, orders_details, products_categories, users, products, categories, buckets, orders;
DROP SEQUENCE bucket_seq;
DROP SEQUENCE category_seq;
DROP SEQUENCE order_details_seq;
DROP SEQUENCE order_seq;
DROP SEQUENCE product_seq;
DROP SEQUENCE user_seq;

create sequence bucket_seq start with 1 increment by 1;
create sequence category_seq start with 1 increment by 1;
create sequence order_details_seq start with 1 increment by 1;
create sequence order_seq start with 1 increment by 1;
create sequence product_seq start with 1 increment by 1;
create sequence user_seq start with 1 increment by 1;

create table buckets
(
    id      bigint not null,
    user_id bigint unique,
    primary key (id)
);
create table buckets_products
(
    bucket_id  bigint not null,
    product_id bigint not null
);
create table categories
(
    id    bigint not null,
    title varchar(255),
    primary key (id)
);
create table orders
(
    id           bigint not null,
    date_created timestamp(6),
    date_updated timestamp(6),
    user_id      bigint,
    shop_address varchar(255),
    status       varchar(255) check (status in ('NEW', 'CANCEL', 'PAID', 'CLOSED', 'RETURNED')),
    primary key (id)
);
create table orders_details
(
    id         bigint not null,
    amount     numeric(38, 2),
    price      numeric(38, 2),
    details_id bigint not null unique,
    order_id   bigint,
    product_id bigint,
    primary key (id)
);
create table products
(
    id            bigint  not null,
    active        boolean not null,
    price         numeric(38, 2),
    reduced_price numeric(38, 2),
    title         varchar(255),
    primary key (id)
);
create table products_categories
(
    category_id bigint not null,
    product_id  bigint not null
);
create table users
(
    id           bigint  not null,
    active       boolean not null,
    bucket_id    bigint unique,
    address      varchar(255),
    email        varchar(255),
    name         varchar(255),
    password     varchar(255),
    phone_number varchar(255),
    role         varchar(255) check (role in ('GUEST', 'USER', 'MANAGER', 'ADMIN')),
    primary key (id)
);
alter table if exists buckets
    add constraint constraint_54424 foreign key (user_id) references users;
alter table if exists buckets_products
    add constraint constraint_45242 foreign key (bucket_id) references buckets;
alter table if exists buckets_products
    add constraint constraint_48245 foreign key (product_id) references products;
alter table if exists orders
    add constraint constraint_78242 foreign key (user_id) references users;
alter table if exists orders_details
    add constraint constraint_24527 foreign key (order_id) references orders;
alter table if exists orders_details
    add constraint constraint_48787 foreign key (product_id) references products;
alter table if exists orders_details
    add constraint constraint_78571 foreign key (details_id) references orders_details;
alter table if exists products_categories
    add constraint constraint_78241 foreign key (category_id) references categories;
alter table if exists products_categories
    add constraint constraint_9752 foreign key (product_id) references products;
alter table if exists users
    add constraint constraint_8765 foreign key (bucket_id) references buckets;