CREATE TABLE h19_07_ora.venta (
  idvent NUMBER(11) NOT NULL,
  nomcli VARCHAR2(150 BYTE),
  dnicli VARCHAR2(150 BYTE),
  fecvent DATE,
  idper NUMBER(11),
  estven CHAR,
  CONSTRAINT "VENTA_pk" PRIMARY KEY (idvent)
);