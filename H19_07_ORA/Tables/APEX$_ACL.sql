CREATE TABLE h19_07_ora.apex$_acl (
  "ID" NUMBER NOT NULL,
  ws_app_id NUMBER NOT NULL,
  username VARCHAR2(255 BYTE) NOT NULL,
  priv VARCHAR2(1 BYTE) NOT NULL CONSTRAINT apex$_acl_priv_ck CHECK (priv in ('R','C','A')),
  created_on DATE NOT NULL,
  created_by VARCHAR2(255 BYTE) NOT NULL,
  updated_on DATE,
  updated_by VARCHAR2(255 BYTE),
  CONSTRAINT apex$_acl_pk PRIMARY KEY ("ID")
);