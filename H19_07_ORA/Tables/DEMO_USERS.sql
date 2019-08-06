CREATE TABLE h19_07_ora.demo_users (
  user_id NUMBER NOT NULL,
  user_name VARCHAR2(100 BYTE),
  "PASSWORD" VARCHAR2(4000 BYTE),
  created_on DATE,
  "QUOTA" NUMBER,
  products CHAR,
  expires_on DATE,
  admin_user CHAR,
  CONSTRAINT demo_users_pk PRIMARY KEY (user_id)
);