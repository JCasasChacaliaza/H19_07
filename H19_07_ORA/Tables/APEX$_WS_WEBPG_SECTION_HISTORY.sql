CREATE TABLE h19_07_ora.apex$_ws_webpg_section_history (
  section_id NUMBER NOT NULL,
  ws_app_id NUMBER NOT NULL,
  webpage_id NUMBER NOT NULL,
  old_display_sequence NUMBER,
  new_display_sequence NUMBER,
  old_title VARCHAR2(4000 BYTE),
  new_title VARCHAR2(4000 BYTE),
  old_content CLOB,
  new_content CLOB,
  application_user_id VARCHAR2(255 BYTE) NOT NULL,
  change_date DATE NOT NULL
);