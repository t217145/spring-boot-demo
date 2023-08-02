insert into items (item_id, item_name, item_code, unit_price) values 
(1, 'Item A', 'A123', 12.3),
(2, 'Item B', 'B456', 45.6),
(3, 'Item C', 'C789', 78.9);

insert into orders (order_id, cus_name, over_all_discount) values 
(1, 'Cyrus', 0.05),
(2, 'Mandy', 0.0),
(3, 'Cyrus', 0.02);

insert into order_details (order_dtl_id, order_id, item_code, qty) values
(1, 1, 'A123', 5),
(2, 1, 'C789', 2),
(3, 2, 'B456', 4),
(4, 3, 'B456', 1),
(5, 3, 'C789', 6),
(6, 3, 'A123', 3);