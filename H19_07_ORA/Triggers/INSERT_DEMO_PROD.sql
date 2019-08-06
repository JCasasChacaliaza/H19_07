CREATE OR REPLACE TRIGGER  h19_07_ora."INSERT_DEMO_PROD"
BEFORE INSERT ON h19_07_ora.demo_product_info
FOR EACH ROW
DECLARE
  prod_id number;
BEGIN
  SELECT demo_prod_seq.nextval
    INTO prod_id
    FROM dual;
  :new.PRODUCT_ID := prod_id;
END;
/