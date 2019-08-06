CREATE TABLE h19_07_ora.apex$_ws_tags (
  "ID" NUMBER NOT NULL,
  ws_app_id NUMBER NOT NULL,
  data_grid_id NUMBER,
  row_id NUMBER,
  webpage_id NUMBER,
  component_level VARCHAR2(30 BYTE) CONSTRAINT apex$_ws_tags_cl_ck CHECK (component_level in ('WEBSHEET','ROW','WORKSPACE','WEBPAGE')),
  "TAG" VARCHAR2(4000 BYTE),
  created_on DATE NOT NULL,
  created_by VARCHAR2(255 BYTE) NOT NULL,
  updated_on DATE,
  updated_by VARCHAR2(255 BYTE),
  CONSTRAINT apex$_ws_tags_pk PRIMARY KEY ("ID"),
  CONSTRAINT apex$_ws_tags_fk FOREIGN KEY (row_id) REFERENCES h19_07_ora.apex$_ws_rows ("ID") ON DELETE CASCADE
);