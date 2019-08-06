CREATE TABLE h19_07_ora.sucursal (
  idsuc NUMBER(11) NOT NULL,
  telsuc CHAR(9 BYTE),
  diresuc VARCHAR2(150 BYTE),
  prosuc VARCHAR2(150 BYTE),
  estsuc CHAR,
  CONSTRAINT "SUCURSAL_pk" PRIMARY KEY (idsuc)
);