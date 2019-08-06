CREATE TABLE h19_07_ora.demo_order_items (
  order_item_id NUMBER(3) NOT NULL,
  order_id NUMBER NOT NULL,
  product_id NUMBER NOT NULL,
  unit_price NUMBER(8,2) NOT NULL,
  quantity NUMBER(8) NOT NULL,
  CONSTRAINT demo_order_items_pk PRIMARY KEY (order_item_id),
  CONSTRAINT demo_order_items_fk FOREIGN KEY (order_id) REFERENCES h19_07_ora.demo_orders (order_id) ON DELETE CASCADE,
  CONSTRAINT demo_order_items_product_id_fk FOREIGN KEY (product_id) REFERENCES h19_07_ora.demo_product_info (product_id) ON DELETE CASCADE
);