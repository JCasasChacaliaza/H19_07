CREATE TABLE h19_07_ora.demo_orders (
  order_id NUMBER NOT NULL,
  customer_id NUMBER NOT NULL,
  order_total NUMBER(8,2) CONSTRAINT demo_order_total_min CHECK (order_total >= 0),
  order_timestamp DATE,
  user_id NUMBER,
  CONSTRAINT demo_order_pk PRIMARY KEY (order_id),
  CONSTRAINT demo_orders_customer_id_fk FOREIGN KEY (customer_id) REFERENCES h19_07_ora.demo_customers (customer_id),
  CONSTRAINT demo_orders_user_id_fk FOREIGN KEY (user_id) REFERENCES h19_07_ora.demo_users (user_id)
);