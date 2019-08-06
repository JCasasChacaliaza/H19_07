CREATE TABLE h19_07_ora.demo_customers (
  customer_id NUMBER NOT NULL,
  cust_first_name VARCHAR2(20 BYTE) NOT NULL,
  cust_last_name VARCHAR2(20 BYTE) NOT NULL,
  cust_street_address1 VARCHAR2(60 BYTE),
  cust_street_address2 VARCHAR2(60 BYTE),
  cust_city VARCHAR2(30 BYTE),
  cust_state VARCHAR2(2 BYTE),
  cust_postal_code VARCHAR2(10 BYTE),
  phone_number1 VARCHAR2(25 BYTE),
  phone_number2 VARCHAR2(25 BYTE),
  credit_limit NUMBER(9,2) CONSTRAINT demo_cust_credit_limit_max CHECK (credit_limit <= 5000),
  cust_email VARCHAR2(30 BYTE),
  CONSTRAINT demo_customers_pk PRIMARY KEY (customer_id)
);