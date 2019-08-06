CREATE TABLE h19_07_ora.demo_product_info (
  product_id NUMBER NOT NULL,
  product_name VARCHAR2(50 BYTE),
  product_description VARCHAR2(2000 BYTE),
  "CATEGORY" VARCHAR2(30 BYTE),
  product_avail VARCHAR2(1 BYTE),
  list_price NUMBER(8,2),
  product_image BLOB,
  mimetype VARCHAR2(255 BYTE),
  filename VARCHAR2(400 BYTE),
  image_last_update DATE,
  CONSTRAINT demo_product_info_pk PRIMARY KEY (product_id)
);