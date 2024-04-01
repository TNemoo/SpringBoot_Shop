-- DELETE FROM users;

INSERT INTO users (id, active, address, email, name, password, phone_number, role)
VALUES (1, true, 'New York City, First street', 'John@gmail.com', 'John'
, '$2a$08$FQ0Kx/h4qqDT796MjzMDvuNgmIeLMdfzKYctG2l5d3GqLDQ2BN9Fy', '+1-125-265-25-25', 'ADMIN');
ALTER SEQUENCE user_seq RESTART WITH 2;
-- password pass


INSERT INTO users (id, active, address, email, name, password, phone_number, role)
 VALUES (2, true, 'New York City, Second street', 'Lola@gmail.com', 'Lola'
        , '$2a$08$O0mzfWWafKGMCWuPymPc4OaIssS4P0gwGu5yaFFzkVlXCknUqz7kW', '+11-215-525-26-80', 'USER');
ALTER SEQUENCE user_seq RESTART WITH 3;
-- password 123


INSERT INTO users (id, active, address, email, name, password, phone_number, role)
VALUES (3, true, 'New York City, Third street', 'Nikolas@gmail.com', 'Nikolas'
       , '$2a$08$guhplgUnASCD9jxs3I4IJ.W7zaqyxbYDbRKkdEn0gyu6E94ngm/MK', '+11-235-561-56-89', 'USER');
ALTER SEQUENCE user_seq RESTART WITH 4;
-- password pass

-- INSERT INTO buckets(id, user_id) VALUES (1,2);
-- INSERT INTO buckets(id, user_id) VALUES (2,3);

-- DELETE FROM products;

INSERT INTO products (id, active, price, reduced_price, title)
VALUES (1, true, 12.5, 12.8, 'banana [Nicaragua]'),
       (2, true, 5.5, 5.2, 'apple [Krasnodar]'),
       (3, true, 7.5, 7.5, 'melon [Papua Ney Guinea]'),
       (4, true, 2.5, 2.5, 'banana [Papua Ney Guinea]'),
       (5, true, 1.8, 1.9, 'corn [Nicaragua]'),
       (6, true, 0.5, 0.5, 'cucumber [Krasnodar]'),
       (7, true, 0.8, 0.9, 'tomato [Krasnodar]'),
       (8, true, 0.3, 0.3, 'rice [Krasnodar]');
ALTER SEQUENCE product_seq RESTART WITH 9;

select * from users;
select * from buckets;
select * from categories;
select * from orders;
select * from products;
select * from orders_details;
select * from products_categories;
select * from buckets_products;