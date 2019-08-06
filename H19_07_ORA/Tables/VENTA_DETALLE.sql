CREATE TABLE h19_07_ora.venta_detalle (
  idtel NUMBER(11) NOT NULL,
  cantventdet NUMBER(11),
  idpro NUMBER(11),
  idvent NUMBER(11),
  estvend CHAR,
  CONSTRAINT "VENTA_DETALLE_pk" PRIMARY KEY (idtel)
);