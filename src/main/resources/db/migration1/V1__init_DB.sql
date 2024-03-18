DROP TABLE IF EXISTS
    products_categories,
    orders_details,
    products_categories,
    users,
    products,
    categories,
    buckets,
    orders;

drop sequence if exists bucket_seq;
drop sequence if exists category_seq;
drop sequence if exists order_details_seq;
drop sequence if exists order_seq;
drop sequence if exists product_seq;
drop sequence if exists user_seq;


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
    date_created timestamp(6),
    date_updated timestamp(6),
    id           bigint not null,
    user_id      bigint,
    shop_address varchar(255),
    status       varchar(255) check (status in ('NEW', 'CANCEL', 'PAID', 'CLOSED', 'RETURNED')),
    primary key (id)
);
create table orders_details
(
    amount     numeric(38, 2),
    price      numeric(38, 2),
    details_id bigint not null unique,
    id         bigint not null,
    order_id   bigint,
    product_id bigint,
    primary key (id)
);
create table products
(
    active        boolean not null,
    price         numeric(38, 2),
    reduced_price numeric(38, 2),
    id            bigint  not null,
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
    active       boolean not null,
    id           bigint  not null,
    address      varchar(255),
    email        varchar(255),
    name         varchar(255),
    password     varchar(255),
    phone_number varchar(255),
    role         varchar(255) check (role in ('GUEST', 'USER', 'MANAGER', 'ADMIN')),
    primary key (id)
);

alter table if exists buckets
    add constraint FKnl0ltaj67xhydcrfbq8401nvj foreign key (user_id) references users;
alter table if exists buckets_products
    add constraint FKloyxdc1uy11tayedf3dpu9lci foreign key (product_id) references products;
alter table if exists buckets_products
    add constraint FKc49ah45o66gy2f2f4c3os3149 foreign key (bucket_id) references buckets;
alter table if exists orders
    add constraint FK32ql8ubntj5uh44ph9659tiih foreign key (user_id) references users;
alter table if exists orders_details
    add constraint FK5o977kj2vptwo70fu7w7so9fe foreign key (order_id) references orders;
alter table if exists orders_details
    add constraint FKs0r9x49croribb4j6tah648gt foreign key (product_id) references products;
alter table if exists orders_details
    add constraint FKgvp1k7a3ubdboj3yhnawd5m1p foreign key (details_id) references orders_details;
alter table if exists products_categories
    add constraint FKqt6m2o5dly3luqcm00f5t4h2p foreign key (category_id) references categories;
alter table if exists products_categories
    add constraint FKtj1vdea8qwerbjqie4xldl1el foreign key (product_id) references products;