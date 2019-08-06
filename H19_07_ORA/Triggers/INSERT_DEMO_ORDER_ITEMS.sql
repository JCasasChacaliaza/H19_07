CREATE OR REPLACE TRIGGER  h19_07_ora."INSERT_DEMO_ORDER_ITEMS"
BEFORE
insert on h19_07_ora."DEMO_ORDER_ITEMS"
for each row
begin
declare
  order_item_id number;
begin
select demo_order_items_seq.nextval into order_item_id from dual;
:new.ORDER_ITEM_ID := order_item_id;
end;
end;
/