CREATE TABLE h19_07_ora.emp (
  empno NUMBER(4) NOT NULL,
  ename VARCHAR2(10 BYTE),
  "JOB" VARCHAR2(9 BYTE),
  mgr NUMBER(4),
  hiredate DATE,
  sal NUMBER(7,2),
  comm NUMBER(7,2),
  deptno NUMBER(2),
  PRIMARY KEY (empno),
  FOREIGN KEY (mgr) REFERENCES h19_07_ora.emp (empno),
  FOREIGN KEY (deptno) REFERENCES h19_07_ora.dept (deptno)
);