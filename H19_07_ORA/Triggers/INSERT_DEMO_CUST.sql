CREATE OR REPLACE TRIGGER  h19_07_ora."INSERT_DEMO_CUST"
BEFORE INSERT ON h19_07_ora.demo_customers
FOR EACH ROW
DECLARE
  cust_id number;
BEGIN
  SELECT demo_cust_seq.nextval
    INTO cust_id
    FROM dual;
  :new.CUSTOMER_ID := cust_id;
END;
/