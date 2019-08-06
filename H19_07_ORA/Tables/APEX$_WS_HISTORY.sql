CREATE TABLE h19_07_ora.apex$_ws_history (
  row_id NUMBER NOT NULL,
  ws_app_id NUMBER NOT NULL,
  data_grid_id NUMBER NOT NULL,
  column_name VARCHAR2(255 BYTE),
  old_value VARCHAR2(4000 BYTE),
  new_value VARCHAR2(4000 BYTE),
  application_user_id VARCHAR2(255 BYTE),
  change_date DATE
);