INSERT INTO category (id, name, description)
VALUES (
           '11111111-1111-1111-1111-111111111111',
           'Electronics',
           'Electronic devices'
       );

INSERT INTO product (id, name, price, available, category_id)
VALUES (
           '22222222-2222-2222-2222-222222222222',
           'iPhone 15',
           999.99,
           true,
           '11111111-1111-1111-1111-111111111111'
       );