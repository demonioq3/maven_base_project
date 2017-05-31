--------------------------------------------------------
-- Archivo creado  - miércoles-mayo-31-2017   
--------------------------------------------------------
DROP TABLE "BPFW_CONFIGURACION" cascade constraints;
DROP TABLE "BPFW_GRUPO" cascade constraints;
DROP TABLE "BPFW_USUARIO" cascade constraints;
DROP TABLE "BPFW_USUARIOS_GRUPOS" cascade constraints;
DROP SEQUENCE "BPFW_CONFIGURACION_SEQ";
DROP SEQUENCE "BPFW_GRUPO_SEQ";
--------------------------------------------------------
--  DDL for Sequence BPFW_CONFIGURACION_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "BPFW_CONFIGURACION_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence BPFW_GRUPO_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "BPFW_GRUPO_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Table BPFW_CONFIGURACION
--------------------------------------------------------

  CREATE TABLE "BPFW_CONFIGURACION" 
   (	"ID_CONFIGURACION" NUMBER(*,0), 
	"LLAVE" VARCHAR2(255), 
	"VALOR" VARCHAR2(255), 
	"DESCRIPCION" VARCHAR2(1000)
   ) ;
--------------------------------------------------------
--  DDL for Table BPFW_GRUPO
--------------------------------------------------------

  CREATE TABLE "BPFW_GRUPO" 
   (	"ID" NUMBER, 
	"NOMBRE" VARCHAR2(255), 
	"DESCRIPCION" VARCHAR2(4000)
   ) ;
--------------------------------------------------------
--  DDL for Table BPFW_USUARIO
--------------------------------------------------------

  CREATE TABLE "BPFW_USUARIO" 
   (	"USERNAME" VARCHAR2(256), 
	"NOMBRE" VARCHAR2(1024), 
	"RUT" VARCHAR2(20), 
	"EMAIL" VARCHAR2(256), 
	"CREADO" TIMESTAMP (6) DEFAULT sysdate, 
	"ULTIMO_INGRESO" TIMESTAMP (6), 
	"BLOQUEADO" NUMBER(*,0) DEFAULT 0, 
	"INGRESOS_FALLIDOS" NUMBER(*,0) DEFAULT 0, 
	"PASS_MESSAGE_ENC" BLOB, 
	"PASS_MESSAGE" VARCHAR2(255), 
	"ULTIMO_CAMBIO_PASS" TIMESTAMP (6)
   ) ;
--------------------------------------------------------
--  DDL for Table BPFW_USUARIOS_GRUPOS
--------------------------------------------------------

  CREATE TABLE "BPFW_USUARIOS_GRUPOS" 
   (	"USERNAME" VARCHAR2(255), 
	"GRUPO_ID" NUMBER
   ) ;
REM INSERTING into BPFW_CONFIGURACION
SET DEFINE OFF;
Insert into BPFW_CONFIGURACION (ID_CONFIGURACION,LLAVE,VALOR,DESCRIPCION) values ('1','asadas','asdasd','asdasd');
Insert into BPFW_CONFIGURACION (ID_CONFIGURACION,LLAVE,VALOR,DESCRIPCION) values ('2','111','asdasda','11');
REM INSERTING into BPFW_GRUPO
SET DEFINE OFF;
Insert into BPFW_GRUPO (ID,NOMBRE,DESCRIPCION) values ('1','Superadmin','Usuario con maximos privilegios');
Insert into BPFW_GRUPO (ID,NOMBRE,DESCRIPCION) values ('2','Admin','Administrador');
Insert into BPFW_GRUPO (ID,NOMBRE,DESCRIPCION) values ('4','Invitado','Usuarion q no puede ver cosas');
REM INSERTING into BPFW_USUARIO
SET DEFINE OFF;
Insert into BPFW_USUARIO (USERNAME,NOMBRE,RUT,EMAIL,CREADO,ULTIMO_INGRESO,BLOQUEADO,INGRESOS_FALLIDOS,PASS_MESSAGE,ULTIMO_CAMBIO_PASS) values ('frmendez','fco','15.662.226-5','francisco.mendez@blueprintsit.cl',to_timestamp('24/05/17 18:35:04,979000000','DD/MM/RR HH24:MI:SSXFF'),to_timestamp('24/05/17 21:08:04,063000000','DD/MM/RR HH24:MI:SSXFF'),'0','0','6FMRMVSJSWNMEPJY',to_timestamp('24/05/17 19:09:41,200000000','DD/MM/RR HH24:MI:SSXFF'));
Insert into BPFW_USUARIO (USERNAME,NOMBRE,RUT,EMAIL,CREADO,ULTIMO_INGRESO,BLOQUEADO,INGRESOS_FALLIDOS,PASS_MESSAGE,ULTIMO_CAMBIO_PASS) values ('bpadmin','Admin BP','1-9','francisco.mendez@blueprintsit.cl',to_timestamp('24/05/17 18:59:26,016000000','DD/MM/RR HH24:MI:SSXFF'),to_timestamp('25/05/17 13:36:57,490000000','DD/MM/RR HH24:MI:SSXFF'),'0','0','YZNO4307C78RGXJ2',to_timestamp('24/05/17 21:20:19,515000000','DD/MM/RR HH24:MI:SSXFF'));
Insert into BPFW_USUARIO (USERNAME,NOMBRE,RUT,EMAIL,CREADO,ULTIMO_INGRESO,BLOQUEADO,INGRESOS_FALLIDOS,PASS_MESSAGE,ULTIMO_CAMBIO_PASS) values ('asantiago','Agustin Santiago','1-9','agustin.santiago@blueprintsit.cl',to_timestamp('25/05/17 13:20:37,633000000','DD/MM/RR HH24:MI:SSXFF'),to_timestamp('25/05/17 18:37:59,257000000','DD/MM/RR HH24:MI:SSXFF'),'0','0','o5jbCQ2hmas0ZpYY',to_timestamp('25/05/17 13:20:37,654000000','DD/MM/RR HH24:MI:SSXFF'));
REM INSERTING into BPFW_USUARIOS_GRUPOS
SET DEFINE OFF;
Insert into BPFW_USUARIOS_GRUPOS (USERNAME,GRUPO_ID) values ('asantiago','1');
Insert into BPFW_USUARIOS_GRUPOS (USERNAME,GRUPO_ID) values ('asantiago','2');
Insert into BPFW_USUARIOS_GRUPOS (USERNAME,GRUPO_ID) values ('bpadmin','1');
--------------------------------------------------------
--  DDL for Index CONFIGURACION_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "CONFIGURACION_PK" ON "BPFW_CONFIGURACION" ("ID_CONFIGURACION") 
  ;
--------------------------------------------------------
--  DDL for Index BPFW_USUARIOS_GRUPOS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BPFW_USUARIOS_GRUPOS_PK" ON "BPFW_USUARIOS_GRUPOS" ("USERNAME", "GRUPO_ID") 
  ;
--------------------------------------------------------
--  DDL for Index BPFW_GRUPO_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BPFW_GRUPO_PK" ON "BPFW_GRUPO" ("ID") 
  ;
--------------------------------------------------------
--  DDL for Index BP_USR_UNQ_IDX1
--------------------------------------------------------

  CREATE UNIQUE INDEX "BP_USR_UNQ_IDX1" ON "BPFW_USUARIO" ("USERNAME") 
  ;
--------------------------------------------------------
--  DDL for Index CONFIGURACION_UNIQUE_LL
--------------------------------------------------------

  CREATE UNIQUE INDEX "CONFIGURACION_UNIQUE_LL" ON "BPFW_CONFIGURACION" ("LLAVE") 
  ;
--------------------------------------------------------
--  Constraints for Table BPFW_GRUPO
--------------------------------------------------------

  ALTER TABLE "BPFW_GRUPO" ADD CONSTRAINT "BPFW_GRUPO_PK" PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "BPFW_GRUPO" MODIFY ("NOMBRE" NOT NULL ENABLE);
  ALTER TABLE "BPFW_GRUPO" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table BPFW_USUARIOS_GRUPOS
--------------------------------------------------------

  ALTER TABLE "BPFW_USUARIOS_GRUPOS" ADD CONSTRAINT "BPFW_USUARIOS_GRUPOS_PK" PRIMARY KEY ("USERNAME", "GRUPO_ID") ENABLE;
  ALTER TABLE "BPFW_USUARIOS_GRUPOS" MODIFY ("GRUPO_ID" NOT NULL ENABLE);
  ALTER TABLE "BPFW_USUARIOS_GRUPOS" MODIFY ("USERNAME" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table BPFW_CONFIGURACION
--------------------------------------------------------

  ALTER TABLE "BPFW_CONFIGURACION" ADD CONSTRAINT "CONFIGURACION_PK" PRIMARY KEY ("ID_CONFIGURACION") ENABLE;
  ALTER TABLE "BPFW_CONFIGURACION" MODIFY ("ID_CONFIGURACION" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table BPFW_USUARIO
--------------------------------------------------------

  ALTER TABLE "BPFW_USUARIO" MODIFY ("USERNAME" NOT NULL ENABLE);
  ALTER TABLE "BPFW_USUARIO" ADD PRIMARY KEY ("USERNAME") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BPFW_USUARIOS_GRUPOS
--------------------------------------------------------

  ALTER TABLE "BPFW_USUARIOS_GRUPOS" ADD CONSTRAINT "BPFW_USUARIOS_GRUPOS_FK1" FOREIGN KEY ("GRUPO_ID")
	  REFERENCES "BPFW_GRUPO" ("ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "BPFW_USUARIOS_GRUPOS" ADD CONSTRAINT "BPFW_USUARIOS_GRUPOS_FK2" FOREIGN KEY ("USERNAME")
	  REFERENCES "BPFW_USUARIO" ("USERNAME") ENABLE;
--------------------------------------------------------
--  DDL for Trigger BPFK_CONFIGURACION_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "BPFK_CONFIGURACION_TRG" 
BEFORE INSERT ON BPFW_CONFIGURACION
FOR EACH ROW
  BEGIN
    <<COLUMN_SEQUENCES>>
    BEGIN
      IF INSERTING AND :NEW.ID_CONFIGURACION IS NULL THEN
        SELECT BPFW_CONFIGURACION_SEQ.NEXTVAL INTO :NEW.ID_CONFIGURACION FROM SYS.DUAL;
      END IF;
    END COLUMN_SEQUENCES;
  END;
/
ALTER TRIGGER "BPFK_CONFIGURACION_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger BPFW_CONFIGURACION_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "BPFW_CONFIGURACION_TRG" 
BEFORE INSERT ON BPFW_CONFIGURACION
FOR EACH ROW
  BEGIN
    <<COLUMN_SEQUENCES>>
    BEGIN
      IF INSERTING AND :NEW.ID_CONFIGURACION IS NULL THEN
        SELECT BPFW_CONFIGURACION_SEQ.NEXTVAL INTO :NEW.ID_CONFIGURACION FROM SYS.DUAL;
      END IF;
    END COLUMN_SEQUENCES;
  END;
/
ALTER TRIGGER "BPFW_CONFIGURACION_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger BPFW_GRUPO_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "BPFW_GRUPO_TRG" 
BEFORE INSERT ON BPFW_GRUPO 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT BPFW_GRUPO_SEQ.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "BPFW_GRUPO_TRG" ENABLE;
