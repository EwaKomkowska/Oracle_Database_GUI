create sequence LOGMNR_EVOLVE_SEQ$
    order
    nocache
/

create sequence LOGMNR_SEQ$
    order
    nocache
/

create sequence LOGMNR_DIDS$
    order
    nocache
/

create sequence LOGMNR_UIDS$
    minvalue 100
    maxvalue 99999
    order
    cycle
    nocache
/

create sequence ROLLING_EVENT_SEQ$
    order
    nocache
/

create sequence MVIEW$_ADVSEQ_GENERIC
    maxvalue 4294967295
    cache 50
/

create sequence MVIEW$_ADVSEQ_ID
    maxvalue 4294967295
/

create sequence FESTYN_SEQ
    nocache
/

create sequence OPLATA_SEQ
    nocache
/

create sequence POMOCDYD_SEQ
    nocache
/

create sequence PRACOWNIK_SEQ
    nocache
/

create sequence PRZEDSZKOLANKA_SEQ
    nocache
/

create sequence SEKRETARKA_SEQ
    nocache
/

create sequence ZAJDOD_SEQ
    nocache
/

create sequence ZEBRANIE_SEQ
    nocache
/

create sequence DZIECKO_SEQ
    nocache
/

create sequence HOSPITACJA_SEQ
    nocache
/

create sequence GRUPA_SEQ
    nocache
/

create sequence POSILEK_SEQ
    nocache
/

create or replace type LOGMNR$TAB_GG_REC as object
(
    LOGMNR_UID              NUMBER,
    OBJ#                    NUMBER,
    BASEOBJV#               NUMBER,
    MD_TAB_USERID           NUMBER, /* owner# */
    MD_TAB_COLCOUNT         NUMBER, /* user column count */
    MD_TAB_TOTAL_COL_NUM    NUMBER, /* kernal column count */
    MD_TAB_LOG_GROUP_EXISTS NUMBER, /* Any log group exists for this table */
    MD_TAB_IOT              VARCHAR2(3), /* 'YES'/'NO' IS IOT? */
    MD_TAB_IOT_OVERFLOW     VARCHAR2(3), /* 'YES'/'NO' IOT with overflow ? */
    MD_TAB_PARTITION        VARCHAR2(3), /* 'YES'/'NO' is partitioned ? */
    MD_TAB_SUBPARTITION     VARCHAR2(3), /* 'YES'/'NO' is sub partitioned? */
    MD_TAB_XMLTYPETABLE     VARCHAR2(3), /* 'YES'/'NO' is xmltype table? */
    MD_TAB_OBJECTID         NUMBER, /* object id if table object */
    MD_TAB_OWNER            VARCHAR2(384), /* owner name */
    MD_TAB_NAME             VARCHAR2(384), /* table name */
    MD_TAB_OBJTYPE          VARCHAR2(384), /* Object type name */
    MD_TAB_SCN              NUMBER, /* COMMIT_SCN of this table version */
    TAB_FLAGS               NUMBER,
    TRIGFLAG                NUMBER,
    OBJ_FLAGS               NUMBER,
    PROPERTY                NUMBER,
    PARTTYPE                NUMBER,
    SUBPARTTYPE             NUMBER,
    SPARE1                  NUMBER,
    SPARE2                  NUMBER,
    SPARE3                  NUMBER,
    SPARE4                  VARCHAR2(4000),
    SPARE5                  VARCHAR2(4000),
    SPARE6                  VARCHAR2(4000),
/* Following fields added in 12.1.0.2 */
    LVLCNT                  NUMBER,
    LVL1OBJ#                NUMBER,
    LVL2OBJ#                NUMBER,
    LVL1TYPE#               NUMBER,
    LVL2TYPE#               NUMBER,
    LVL1NAME                VARCHAR2(384),
    LVL2NAME                VARCHAR2(384),
    INTCOLS                 NUMBER,
    ASSOC#                  NUMBER,
    XIDUSN                  NUMBER,
    XIDSLT                  NUMBER,
    XIDSQN                  NUMBER,
    DROP_SCN                NUMBER,
    FLAGS                   NUMBER,
    LOGMNR_SPARE1           NUMBER,
    LOGMNR_SPARE2           NUMBER,
    LOGMNR_SPARE3           VARCHAR2(1000),
    LOGMNR_SPARE4           DATE,
    LOGMNR_SPARE5           NUMBER,
    LOGMNR_SPARE6           NUMBER,
    LOGMNR_SPARE7           NUMBER,
    LOGMNR_SPARE8           NUMBER,
    LOGMNR_SPARE9           NUMBER,
    UNSUPPORTEDCOLS         NUMBER,
    COMPLEXTYPECOLS         NUMBER,
    NTPARENTOBJNUM          NUMBER,
    NTPARENTOBJVERSION      NUMBER,
    NTPARENTINTCOLNUM       NUMBER,
    LOGMNRTLOFLAGS          NUMBER,
    LOGMNRMCV               VARCHAR2(30),
/* Following fields added in 12.2 */
    ACDRFLAGS               NUMBER, /* automatic CDR */
    ACDRTSOBJ#              NUMBER, /* automatic CDR */
    ACDRROWTSINTCOL#        NUMBER /* automatic CDR */
);
/

create or replace type LOGMNR$COL_GG_REC as object
(
    LOGMNR_UID                  NUMBER,
    OBJ#                        NUMBER,
    MD_COL_NAME                 VARCHAR2(384),
    MD_COL_NUM                  NUMBER, /* col# */
    MD_COL_SEGCOL               NUMBER, /* segcol# */
    MD_COL_TYPE                 NUMBER, /* type# */
    MD_COL_LEN                  NUMBER,
    MD_COL_PREC                 NUMBER, /* precision */
    MD_COL_SCALE                NUMBER,
    MD_COL_CHARSETID            NUMBER, /* character set id */
    MD_COL_CHARSETFORM          NUMBER, /* character set form */
    MD_COL_ALT_TYPE             VARCHAR2(4000), /* adt type if any */
    MD_COL_ALT_PREC             NUMBER, /* precision of the adt attribute */
    MD_COL_ALT_CHAR_USED        VARCHAR2(2), /* charset used by the adt attribute */
    MD_COL_ALT_LENGTH           NUMBER, /* length of the adt attribute */
    MD_COL_ALT_XML_TYPE         NUMBER, /* 0/1. is xml_type column */
    MD_COL_ALT_BINARYXML_TYPE   NUMBER, /* 0/1. is xml_type stored as binary */
    MD_COL_ENC_ISENC            VARCHAR2(3), /* 'YES'/'NO' */
    MD_COL_ENC_NOSALT           VARCHAR2(3), /* 'YES'/'NO' */
    MD_COL_ENC_ISLOB            VARCHAR2(3), /* 'YES'/'NO' */
    MD_COL_ALT_OBJECTXML_TYPE   NUMBER, /* 0/1 xml_type stored as object */
    MD_COL_HASNOTNULLDEFAULT    VARCHAR2(3), /* 'YES'/'NO' */
    MD_COL_ALT_TYPE_OWNER       VARCHAR2(384), /* owner of the adt type if any */
    PROPERTY                    NUMBER,
    XCOLTYPEFLAGS               NUMBER,
    XOPQTYPEFLAGS               NUMBER,
    EAFLAGS                     NUMBER,
    XFQCOLNAME                  VARCHAR2(4000),
    SPARE1                      NUMBER,
    SPARE2                      NUMBER,
    SPARE3                      NUMBER,
    SPARE4                      VARCHAR2(4000),
    SPARE5                      VARCHAR2(4000),
    SPARE6                      VARCHAR2(4000),
/* Following fields added in 12.1.0.2 */
    OBJV#                       NUMBER,
    INTCOL#                     NUMBER,
    INTERVAL_LEADING_PRECISION  NUMBER,
    INTERVAL_TRAILING_PRECISION NUMBER,
    TOID                        RAW(16),
    TYPENAME                    VARCHAR2(384),
    NUMINTCOLS                  NUMBER,
    NUMATTRS                    NUMBER,
    ADTORDER                    NUMBER,
    LOGMNR_SPARE1               NUMBER,
    LOGMNR_SPARE2               NUMBER,
    LOGMNR_SPARE3               VARCHAR2(1000),
    LOGMNR_SPARE4               DATE,
    LOGMNR_SPARE5               NUMBER,
    LOGMNR_SPARE6               NUMBER,
    LOGMNR_SPARE7               NUMBER,
    LOGMNR_SPARE8               NUMBER,
    LOGMNR_SPARE9               NUMBER,
    XTYPENAME                   VARCHAR2(4000),
    XTOPINTCOL                  NUMBER,
    XREFFEDTABLEOBJN            NUMBER,
    XREFFEDTABLEOBJV            NUMBER,
    XOPQTYPETYPE                NUMBER,
    XOPQLOBINTCOL               NUMBER,
    XOPQOBJINTCOL               NUMBER,
    XXMLINTCOL                  NUMBER,
    LOGMNRDERIVEDFLAGS          NUMBER,
/* Following fields added in 12.2 */
    COLLID                      NUMBER,
    COLLINTCOL#                 NUMBER,
    ACDRRESCOL#                 NUMBER
);
/

create or replace type LOGMNR$SEQ_GG_REC as object
(
    LOGMNR_UID            NUMBER,
    OBJ#                  NUMBER,
    NAME                  VARCHAR2(384),
    OWNER#                NUMBER,
    OWNERNAME             VARCHAR2(384),
    FLAGS                 NUMBER,
    MD_TAB_SEQCACHE       NUMBER,
    MD_TAB_SEQINCREMENTBY NUMBER,
    SPARE1                NUMBER,
    SPARE2                NUMBER,
    SPARE3                NUMBER,
    SPARE4                VARCHAR2(4000),
    SPARE5                VARCHAR2(4000),
    SPARE6                VARCHAR2(4000)
);
/

create or replace type LOGMNR$KEY_GG_REC as object
(
    LOGMNR_UID      NUMBER,
    KEY#            NUMBER, /* index obj# or con# */
    KEY_FLAGS       NUMBER, /* index or constraint */
    KEY_NAME        VARCHAR2(384), /* index name or constraint name */
    INDEX_OWNER#    NUMBER,
    INDEX_OWNERNAME VARCHAR2(384),
    COLNAME         VARCHAR2(384),
    INTCOL#         NUMBER,
    which           number,
    KEY_ORDER       VARCHAR2(10), /* asc or desc */
    KEYCOL_FLAGS    NUMBER, /* Column properties such as is_null */
    SPARE1          NUMBER,
    SPARE2          NUMBER,
    SPARE3          NUMBER,
    SPARE4          VARCHAR2(4000),
    SPARE5          VARCHAR2(4000),
    SPARE6          VARCHAR2(4000)
);
/

create or replace TYPE LOGMNR$GSBA_GG_REC AS OBJECT
(
    LOGMNR_UID    NUMBER,
    NAME          VARCHAR2(384),
    VALUE         VARCHAR2(4000),
    LOGMNR_SPARE1 NUMBER,
    LOGMNR_SPARE2 NUMBER,
    LOGMNR_SPARE3 VARCHAR2(4000),
    LOGMNR_SPARE4 DATE
);
/

create or replace TYPE LOGMNR$USER_GG_REC AS OBJECT
(
    LOGMNR_UID    NUMBER,
    USERNAME      VARCHAR2(384),
    USERID        NUMBER,
    LOGMNR_SPARE1 NUMBER,
    LOGMNR_SPARE2 NUMBER,
    LOGMNR_SPARE3 VARCHAR2(4000),
    LOGMNR_SPARE4 DATE
);
/

create type LOGMNR$TAB_GG_RECS as table of LOGMNR$TAB_GG_REC
/

create type LOGMNR$COL_GG_RECS as table of LOGMNR$COL_GG_REC
/

create type LOGMNR$SEQ_GG_RECS as table of LOGMNR$SEQ_GG_REC
/

create type LOGMNR$KEY_GG_RECS as table of LOGMNR$KEY_GG_REC
/

create type LOGMNR$GSBA_GG_RECS as table of LOGMNR$GSBA_GG_REC
/

create type LOGMNR$USER_GG_RECS as table of LOGMNR$USER_GG_REC
/

create table LOGMNR_SESSION_EVOLVE$
(
    BRANCH_LEVEL         NUMBER,
    SESSION#             NUMBER not null,
    DB_ID                NUMBER not null,
    RESET_SCN            NUMBER not null,
    RESET_TIMESTAMP      NUMBER not null,
    PREV_RESET_SCN       NUMBER,
    PREV_RESET_TIMESTAMP NUMBER,
    STATUS               NUMBER,
    SPARE1               NUMBER,
    SPARE2               NUMBER,
    SPARE3               NUMBER,
    SPARE4               DATE,
    constraint LOGMNR_SESSION_EVOLVE$_PK
        primary key (SESSION#, DB_ID, RESET_SCN, RESET_TIMESTAMP)
)
/

create table LOGMNR_GLOBAL$
(
    HIGH_RECID_FOREIGN    NUMBER,
    HIGH_RECID_DELETED    NUMBER,
    LOCAL_RESET_SCN       NUMBER,
    LOCAL_RESET_TIMESTAMP NUMBER,
    VERSION_TIMESTAMP     NUMBER,
    SPARE1                NUMBER,
    SPARE2                NUMBER,
    SPARE3                NUMBER,
    SPARE4                VARCHAR2(2000),
    SPARE5                DATE,
    SESSION#              NUMBER
)
/

create global temporary table LOGMNR_GT_TAB_INCLUDE$
(
    SCHEMA_NAME VARCHAR2(390),
    TABLE_NAME  VARCHAR2(390),
    PDB_NAME    VARCHAR2(390),
    SPARE1      NUMBER,
    SPARE2      NUMBER,
    SPARE3      VARCHAR2(4000)
)
    on commit preserve rows
/

create global temporary table LOGMNR_GT_USER_INCLUDE$
(
    USER_NAME VARCHAR2(390),
    USER_TYPE NUMBER,
    PDB_NAME  VARCHAR2(390),
    SPARE1    NUMBER,
    SPARE2    NUMBER,
    SPARE3    VARCHAR2(4000)
)
    on commit preserve rows
/

create global temporary table LOGMNR_GT_XID_INCLUDE$
(
    XIDUSN   NUMBER,
    XIDSLT   NUMBER,
    XIDSQN   NUMBER,
    SPARE1   NUMBER,
    SPARE2   NUMBER,
    SPARE3   VARCHAR2(4000),
    PDBID    NUMBER,
    PDB_NAME VARCHAR2(390)
)
    on commit preserve rows
/

create table LOGMNR_PDB_INFO$
(
    LOGMNR_DID      NUMBER not null,
    LOGMNR_MDH      NUMBER not null,
    PDB_NAME        VARCHAR2(384),
    PDB_ID          NUMBER,
    PDB_UID         NUMBER,
    PLUGIN_SCN      NUMBER not null,
    UNPLUG_SCN      NUMBER,
    FLAGS           NUMBER,
    SPARE1          NUMBER,
    SPARE2          NUMBER,
    SPARE3          VARCHAR2(4000),
    SPARE4          TIMESTAMP(6),
    PDB_GLOBAL_NAME VARCHAR2(384),
    constraint LOGMNR_PDB_INFO$_PK
        primary key (LOGMNR_DID, LOGMNR_MDH, PLUGIN_SCN)
)
/

create table LOGMNR_DID$
(
    SESSION#   NUMBER not null
        constraint LOGMNR_DID$_PK
            primary key,
    LOGMNR_DID NUMBER,
    FLAGS      NUMBER default 0,
    SPARE1     NUMBER,
    SPARE2     NUMBER,
    SPARE3     VARCHAR2(4000),
    SPARE4     TIMESTAMP(6)
)
/

create table LOGMNR_UID$
(
    LOGMNR_UID NUMBER(22) not null
        constraint LOGMNR_UID$_PK
            primary key,
    LOGMNR_DID NUMBER,
    LOGMNR_MDH NUMBER,
    PDB_NAME   VARCHAR2(384),
    PDB_ID     NUMBER,
    PDB_UID    NUMBER,
    START_SCN  NUMBER,
    END_SCN    NUMBER,
    FLAGS      NUMBER,
    SPARE1     NUMBER,
    SPARE2     NUMBER,
    SPARE3     VARCHAR2(4000),
    SPARE4     TIMESTAMP(6)
)
/

create table LOGMNRGGC_GTLO
(
    LOGMNR_UID         NUMBER        not null,
    KEYOBJ#            NUMBER        not null,
    LVLCNT             NUMBER        not null,
    BASEOBJ#           NUMBER        not null,
    BASEOBJV#          NUMBER        not null,
    LVL1OBJ#           NUMBER,
    LVL2OBJ#           NUMBER,
    LVL0TYPE#          NUMBER        not null,
    LVL1TYPE#          NUMBER,
    LVL2TYPE#          NUMBER,
    OWNER#             NUMBER,
    OWNERNAME          VARCHAR2(384) not null,
    LVL0NAME           VARCHAR2(384) not null,
    LVL1NAME           VARCHAR2(384),
    LVL2NAME           VARCHAR2(384),
    INTCOLS            NUMBER        not null,
    COLS               NUMBER,
    KERNELCOLS         NUMBER,
    TAB_FLAGS          NUMBER,
    TRIGFLAG           NUMBER,
    ASSOC#             NUMBER,
    OBJ_FLAGS          NUMBER,
    TS#                NUMBER,
    TSNAME             VARCHAR2(90),
    PROPERTY           NUMBER,
    START_SCN          NUMBER        not null,
    DROP_SCN           NUMBER,
    XIDUSN             NUMBER,
    XIDSLT             NUMBER,
    XIDSQN             NUMBER,
    FLAGS              NUMBER,
    LOGMNR_SPARE1      NUMBER,
    LOGMNR_SPARE2      NUMBER,
    LOGMNR_SPARE3      VARCHAR2(1000),
    LOGMNR_SPARE4      DATE,
    LOGMNR_SPARE5      NUMBER,
    LOGMNR_SPARE6      NUMBER,
    LOGMNR_SPARE7      NUMBER,
    LOGMNR_SPARE8      NUMBER,
    LOGMNR_SPARE9      NUMBER,
    PARTTYPE           NUMBER,
    SUBPARTTYPE        NUMBER,
    UNSUPPORTEDCOLS    NUMBER,
    COMPLEXTYPECOLS    NUMBER,
    NTPARENTOBJNUM     NUMBER,
    NTPARENTOBJVERSION NUMBER,
    NTPARENTINTCOLNUM  NUMBER,
    LOGMNRTLOFLAGS     NUMBER,
    LOGMNRMCV          VARCHAR2(30),
    ACDRFLAGS          NUMBER,
    ACDRTSOBJ#         NUMBER,
    ACDRROWTSINTCOL#   NUMBER,
    constraint LOGMNRGGC_GTLO_PK
        primary key (LOGMNR_UID, KEYOBJ#, BASEOBJV#)
)
/

create index LOGMNRGGC_I2GTLO
    on LOGMNRGGC_GTLO (LOGMNR_UID, BASEOBJ#, BASEOBJV#)
/

create index LOGMNRGGC_I3GTLO
    on LOGMNRGGC_GTLO (LOGMNR_UID, DROP_SCN)
/

create table LOGMNRGGC_GTCS
(
    LOGMNR_UID                  NUMBER        not null,
    OBJ#                        NUMBER        not null,
    OBJV#                       NUMBER        not null,
    SEGCOL#                     NUMBER        not null,
    INTCOL#                     NUMBER        not null,
    COLNAME                     VARCHAR2(384) not null,
    TYPE#                       NUMBER        not null,
    LENGTH                      NUMBER,
    PRECISION                   NUMBER,
    SCALE                       NUMBER,
    INTERVAL_LEADING_PRECISION  NUMBER,
    INTERVAL_TRAILING_PRECISION NUMBER,
    PROPERTY                    NUMBER,
    TOID                        RAW(16),
    CHARSETID                   NUMBER,
    CHARSETFORM                 NUMBER,
    TYPENAME                    VARCHAR2(384),
    FQCOLNAME                   VARCHAR2(4000),
    NUMINTCOLS                  NUMBER,
    NUMATTRS                    NUMBER,
    ADTORDER                    NUMBER,
    LOGMNR_SPARE1               NUMBER,
    LOGMNR_SPARE2               NUMBER,
    LOGMNR_SPARE3               VARCHAR2(1000),
    LOGMNR_SPARE4               DATE,
    LOGMNR_SPARE5               NUMBER,
    LOGMNR_SPARE6               NUMBER,
    LOGMNR_SPARE7               NUMBER,
    LOGMNR_SPARE8               NUMBER,
    LOGMNR_SPARE9               NUMBER,
    COL#                        NUMBER,
    XTYPESCHEMANAME             VARCHAR2(384),
    XTYPENAME                   VARCHAR2(4000),
    XFQCOLNAME                  VARCHAR2(4000),
    XTOPINTCOL                  NUMBER,
    XREFFEDTABLEOBJN            NUMBER,
    XREFFEDTABLEOBJV            NUMBER,
    XCOLTYPEFLAGS               NUMBER,
    XOPQTYPETYPE                NUMBER,
    XOPQTYPEFLAGS               NUMBER,
    XOPQLOBINTCOL               NUMBER,
    XOPQOBJINTCOL               NUMBER,
    XXMLINTCOL                  NUMBER,
    EAOWNER#                    NUMBER,
    EAMKEYID                    VARCHAR2(192),
    EAENCALG                    NUMBER,
    EAINTALG                    NUMBER,
    EACOLKLC                    RAW(2000),
    EAKLCLEN                    NUMBER,
    EAFLAGS                     NUMBER,
    LOGMNRDERIVEDFLAGS          NUMBER,
    COLLID                      NUMBER,
    COLLINTCOL#                 NUMBER,
    ACDRRESCOL#                 NUMBER,
    constraint LOGMNRGGC_GTCS_PK
        primary key (LOGMNR_UID, OBJ#, OBJV#, INTCOL#)
)
/

create index LOGMNRGGC_I2GTCS
    on LOGMNRGGC_GTCS (LOGMNR_UID, OBJ#, OBJV#, SEGCOL#, INTCOL#)
/

create table LOGMNRC_DBNAME_UID_MAP
(
    GLOBAL_NAME VARCHAR2(384) not null,
    LOGMNR_UID  NUMBER,
    FLAGS       NUMBER,
    PDB_NAME    VARCHAR2(384),
    LOGMNR_MDH  NUMBER        not null,
    constraint LOGMNRC_DBNAME_UID_MAP_PK
        primary key (GLOBAL_NAME, LOGMNR_MDH)
)
/

create table LOGMNR_LOG$
(
    SESSION#               NUMBER not null,
    THREAD#                NUMBER not null,
    SEQUENCE#              NUMBER not null,
    FIRST_CHANGE#          NUMBER not null,
    NEXT_CHANGE#           NUMBER,
    FIRST_TIME             DATE,
    NEXT_TIME              DATE,
    FILE_NAME              VARCHAR2(513),
    STATUS                 NUMBER,
    INFO                   VARCHAR2(32),
    TIMESTAMP              DATE,
    DICT_BEGIN             VARCHAR2(3),
    DICT_END               VARCHAR2(3),
    STATUS_INFO            VARCHAR2(32),
    DB_ID                  NUMBER not null,
    RESETLOGS_CHANGE#      NUMBER not null,
    RESET_TIMESTAMP        NUMBER not null,
    PREV_RESETLOGS_CHANGE# NUMBER,
    PREV_RESET_TIMESTAMP   NUMBER,
    BLOCKS                 NUMBER,
    BLOCK_SIZE             NUMBER,
    FLAGS                  NUMBER,
    CONTENTS               NUMBER,
    RECID                  NUMBER,
    RECSTAMP               NUMBER,
    MARK_DELETE_TIMESTAMP  NUMBER,
    SPARE1                 NUMBER,
    SPARE2                 NUMBER,
    SPARE3                 NUMBER,
    SPARE4                 NUMBER,
    SPARE5                 NUMBER,
    constraint LOGMNR_LOG$_PK
        primary key (SESSION#, THREAD#, SEQUENCE#, FIRST_CHANGE#, DB_ID, RESETLOGS_CHANGE#, RESET_TIMESTAMP)
)
/

create index LOGMNR_LOG$_FLAGS
    on LOGMNR_LOG$ (FLAGS)
/

create index LOGMNR_LOG$_FIRST_CHANGE#
    on LOGMNR_LOG$ (FIRST_CHANGE#)
/

create index LOGMNR_LOG$_RECID
    on LOGMNR_LOG$ (RECID)
/

create index LOGMNR_LOG$_PURGE_IDX1
    on LOGMNR_LOG$ (FILE_NAME, STATUS)
/

create index LOGMNR_LOG$_PURGE_IDX2
    on LOGMNR_LOG$ (SESSION#, RESET_TIMESTAMP, NEXT_CHANGE#, STATUS)
/

create table LOGMNR_PROCESSED_LOG$
(
    SESSION#      NUMBER not null,
    THREAD#       NUMBER not null,
    SEQUENCE#     NUMBER,
    FIRST_CHANGE# NUMBER,
    NEXT_CHANGE#  NUMBER,
    FIRST_TIME    DATE,
    NEXT_TIME     DATE,
    FILE_NAME     VARCHAR2(513),
    STATUS        NUMBER,
    INFO          VARCHAR2(32),
    TIMESTAMP     DATE,
    constraint LOGMNR_PROCESSED_LOG$_PK
        primary key (SESSION#, THREAD#)
)
/

create table LOGMNR_SPILL$
(
    SESSION#   NUMBER not null,
    XIDUSN     NUMBER not null,
    XIDSLT     NUMBER not null,
    XIDSQN     NUMBER not null,
    CHUNK      NUMBER not null,
    STARTIDX   NUMBER not null,
    ENDIDX     NUMBER not null,
    FLAG       NUMBER not null,
    SEQUENCE#  NUMBER not null,
    SPILL_DATA BLOB,
    SPARE1     NUMBER,
    SPARE2     NUMBER,
    PDBID      NUMBER not null,
    constraint LOGMNR_SPILL$_PK
        primary key (SESSION#, PDBID, XIDUSN, XIDSLT, XIDSQN, CHUNK, STARTIDX, ENDIDX, FLAG, SEQUENCE#)
)
/

create table LOGMNR_AGE_SPILL$
(
    SESSION#   NUMBER not null,
    XIDUSN     NUMBER not null,
    XIDSLT     NUMBER not null,
    XIDSQN     NUMBER not null,
    CHUNK      NUMBER not null,
    SEQUENCE#  NUMBER not null,
    OFFSET     NUMBER,
    SPILL_DATA BLOB,
    SPARE1     NUMBER,
    SPARE2     NUMBER,
    PDBID      NUMBER not null,
    constraint LOGMNR_AGE_SPILL$_PK
        primary key (SESSION#, PDBID, XIDUSN, XIDSLT, XIDSQN, CHUNK, SEQUENCE#)
)
/

create table LOGMNR_RESTART_CKPT_TXINFO$
(
    SESSION#      NUMBER not null,
    XIDUSN        NUMBER not null,
    XIDSLT        NUMBER not null,
    XIDSQN        NUMBER not null,
    SESSION_NUM   NUMBER not null,
    SERIAL_NUM    NUMBER not null,
    FLAG          NUMBER,
    START_SCN     NUMBER,
    EFFECTIVE_SCN NUMBER not null,
    OFFSET        NUMBER,
    TX_DATA       BLOB,
    constraint LOGMNR_RESTART_CKPT_TXINFO$_PK
        primary key (SESSION#, XIDUSN, XIDSLT, XIDSQN, SESSION_NUM, SERIAL_NUM, EFFECTIVE_SCN)
)
/

create table LOGMNR_ERROR$
(
    SESSION#      NUMBER,
    TIME_OF_ERROR DATE,
    CODE          NUMBER,
    MESSAGE       VARCHAR2(4000),
    SPARE1        NUMBER,
    SPARE2        NUMBER,
    SPARE3        NUMBER,
    SPARE4        VARCHAR2(4000),
    SPARE5        VARCHAR2(4000)
)
/

create table LOGMNR_RESTART_CKPT$
(
    SESSION#    NUMBER not null,
    VALID       NUMBER,
    CKPT_SCN    NUMBER not null,
    XIDUSN      NUMBER not null,
    XIDSLT      NUMBER not null,
    XIDSQN      NUMBER not null,
    SESSION_NUM NUMBER not null,
    SERIAL_NUM  NUMBER not null,
    CKPT_INFO   BLOB,
    FLAG        NUMBER,
    OFFSET      NUMBER,
    CLIENT_DATA BLOB,
    SPARE1      NUMBER,
    SPARE2      NUMBER,
    PDBID       NUMBER not null,
    constraint LOGMNR_RESTART_CKPT$_PK
        primary key (SESSION#, CKPT_SCN, XIDUSN, XIDSLT, XIDSQN, PDBID, SESSION_NUM, SERIAL_NUM)
)
/

create table LOGMNR_FILTER$
(
    SESSION#    NUMBER,
    FILTER_TYPE VARCHAR2(30),
    ATTR1       NUMBER,
    ATTR2       NUMBER,
    ATTR3       NUMBER,
    ATTR4       NUMBER,
    ATTR5       NUMBER,
    ATTR6       NUMBER,
    FILTER_SCN  NUMBER,
    SPARE1      NUMBER,
    SPARE2      NUMBER,
    SPARE3      DATE,
    ATTR7       VARCHAR2(128),
    ATTR8       VARCHAR2(128),
    ATTR9       VARCHAR2(128),
    ATTR10      VARCHAR2(128),
    ATTR11      VARCHAR2(128)
)
/

create table LOGMNR_SESSION_ACTIONS$
(
    FLAGSRUNTIME    NUMBER default 0,
    DROPSCN         NUMBER,
    MODIFYTIME      TIMESTAMP(6),
    DISPATCHTIME    TIMESTAMP(6),
    DROPTIME        TIMESTAMP(6),
    LCRCOUNT        NUMBER default 0,
    ACTIONNAME      VARCHAR2(30) not null,
    LOGMNRSESSION#  NUMBER       not null,
    PROCESSROLE#    NUMBER       not null,
    ACTIONTYPE#     NUMBER       not null,
    FLAGSDEFINETIME NUMBER,
    CREATETIME      TIMESTAMP(6),
    XIDUSN          NUMBER,
    XIDSLT          NUMBER,
    XIDSQN          NUMBER,
    THREAD#         NUMBER,
    STARTSCN        NUMBER,
    STARTSUBSCN     NUMBER,
    ENDSCN          NUMBER,
    ENDSUBSCN       NUMBER,
    RBASQN          NUMBER,
    RBABLK          NUMBER,
    RBABYTE         NUMBER,
    SESSION#        NUMBER,
    OBJ#            NUMBER,
    ATTR1           NUMBER,
    ATTR2           NUMBER,
    ATTR3           NUMBER,
    SPARE1          NUMBER,
    SPARE2          NUMBER,
    SPARE3          TIMESTAMP(6),
    SPARE4          VARCHAR2(2000),
    PDBID           NUMBER,
    PDB_NAME        VARCHAR2(390),
    constraint LOGMNR_SESSION_ACTION$_PK
        primary key (LOGMNRSESSION#, ACTIONNAME)
)
/

create table LOGMNR_PARAMETER$
(
    SESSION# NUMBER        not null,
    NAME     VARCHAR2(384) not null,
    VALUE    VARCHAR2(2000),
    TYPE     NUMBER,
    SCN      NUMBER,
    SPARE1   NUMBER,
    SPARE2   NUMBER,
    SPARE3   VARCHAR2(2000)
)
/

create index LOGMNR_PARAMETER_INDX
    on LOGMNR_PARAMETER$ (SESSION#, NAME)
/

create table LOGMNR_SESSION$
(
    SESSION#             NUMBER        not null
        constraint LOGMNR_SESSION_PK
            primary key,
    CLIENT#              NUMBER,
    SESSION_NAME         VARCHAR2(128) not null
        constraint LOGMNR_SESSION_UK1
            unique,
    DB_ID                NUMBER,
    RESETLOGS_CHANGE#    NUMBER,
    SESSION_ATTR         NUMBER,
    SESSION_ATTR_VERBOSE VARCHAR2(400),
    START_SCN            NUMBER,
    END_SCN              NUMBER,
    SPILL_SCN            NUMBER,
    SPILL_TIME           DATE,
    OLDEST_SCN           NUMBER,
    RESUME_SCN           NUMBER,
    GLOBAL_DB_NAME       VARCHAR2(384) default null,
    RESET_TIMESTAMP      NUMBER,
    BRANCH_SCN           NUMBER,
    VERSION              VARCHAR2(64),
    REDO_COMPAT          VARCHAR2(20),
    SPARE1               NUMBER,
    PURGE_SCN            NUMBER,
    SPARE3               NUMBER,
    SPARE4               NUMBER,
    SPARE5               NUMBER,
    SPARE6               DATE,
    SPARE7               VARCHAR2(1000),
    SPARE8               VARCHAR2(1000),
    SPARE9               NUMBER
)
/

create global temporary table LOGMNRT_MDDL$
(
    SOURCE_OBJ#  NUMBER not null,
    SOURCE_ROWID ROWID  not null,
    DEST_ROWID   ROWID  not null,
    constraint LOGMNRT_MDDL$_PK
        primary key (SOURCE_OBJ#, SOURCE_ROWID)
)
    on commit delete rows
/

create table REDO_DB
(
    DBID               NUMBER not null,
    GLOBAL_DBNAME      VARCHAR2(129),
    DBUNAME            VARCHAR2(32),
    VERSION            VARCHAR2(32),
    THREAD#            NUMBER not null,
    RESETLOGS_SCN_BAS  NUMBER,
    RESETLOGS_SCN_WRP  NUMBER,
    RESETLOGS_TIME     NUMBER not null,
    PRESETLOGS_SCN_BAS NUMBER,
    PRESETLOGS_SCN_WRP NUMBER,
    PRESETLOGS_TIME    NUMBER not null,
    SEQNO_RCV_CUR      NUMBER,
    SEQNO_RCV_LO       NUMBER,
    SEQNO_RCV_HI       NUMBER,
    SEQNO_DONE_CUR     NUMBER,
    SEQNO_DONE_LO      NUMBER,
    SEQNO_DONE_HI      NUMBER,
    GAP_SEQNO          NUMBER,
    GAP_RET            NUMBER,
    GAP_DONE           NUMBER,
    APPLY_SEQNO        NUMBER,
    APPLY_DONE         NUMBER,
    PURGE_DONE         NUMBER,
    HAS_CHILD          NUMBER,
    ERROR1             NUMBER,
    STATUS             NUMBER,
    CREATE_DATE        DATE,
    TS1                NUMBER,
    TS2                NUMBER,
    GAP_NEXT_SCN       NUMBER,
    GAP_NEXT_TIME      NUMBER,
    CURSCN_TIME        NUMBER,
    RESETLOGS_SCN      NUMBER not null,
    PRESETLOGS_SCN     NUMBER not null,
    GAP_RET2           NUMBER,
    CURLOG             NUMBER,
    ENDIAN             NUMBER,
    ENQIDX             NUMBER,
    SPARE4             NUMBER,
    SPARE5             DATE,
    SPARE6             VARCHAR2(65),
    SPARE7             VARCHAR2(129),
    TS3                NUMBER,
    CURBLKNO           NUMBER,
    SPARE8             NUMBER,
    SPARE9             NUMBER,
    SPARE10            NUMBER,
    SPARE11            NUMBER,
    SPARE12            NUMBER
)
/

create index REDO_DB_IDX
    on REDO_DB (DBID, THREAD#, RESETLOGS_SCN, RESETLOGS_TIME)
/

create table REDO_LOG
(
    DBID               NUMBER not null,
    GLOBAL_DBNAME      VARCHAR2(129),
    DBUNAME            VARCHAR2(32),
    VERSION            VARCHAR2(32),
    THREAD#            NUMBER not null,
    RESETLOGS_SCN_BAS  NUMBER,
    RESETLOGS_SCN_WRP  NUMBER,
    RESETLOGS_TIME     NUMBER not null,
    PRESETLOGS_SCN_BAS NUMBER,
    PRESETLOGS_SCN_WRP NUMBER,
    PRESETLOGS_TIME    NUMBER not null,
    SEQUENCE#          NUMBER not null,
    DUPID              NUMBER,
    STATUS1            NUMBER,
    STATUS2            NUMBER,
    CREATE_TIME        VARCHAR2(32),
    CLOSE_TIME         VARCHAR2(32),
    DONE_TIME          VARCHAR2(32),
    FIRST_SCN_BAS      NUMBER,
    FIRST_SCN_WRP      NUMBER,
    FIRST_TIME         NUMBER,
    NEXT_SCN_BAS       NUMBER,
    NEXT_SCN_WRP       NUMBER,
    NEXT_TIME          NUMBER,
    FIRST_SCN          NUMBER,
    NEXT_SCN           NUMBER,
    RESETLOGS_SCN      NUMBER not null,
    BLOCKS             NUMBER,
    BLOCK_SIZE         NUMBER,
    OLD_BLOCKS         NUMBER,
    CREATE_DATE        DATE,
    ERROR1             NUMBER,
    ERROR2             NUMBER,
    FILENAME           VARCHAR2(513),
    TS1                NUMBER,
    TS2                NUMBER,
    ENDIAN             NUMBER,
    SPARE2             NUMBER,
    SPARE3             NUMBER,
    SPARE4             NUMBER,
    SPARE5             DATE,
    SPARE6             VARCHAR2(65),
    SPARE7             VARCHAR2(129),
    TS3                NUMBER,
    PRESETLOGS_SCN     NUMBER not null,
    SPARE8             NUMBER,
    SPARE9             NUMBER,
    SPARE10            NUMBER,
    OLD_STATUS1        NUMBER,
    OLD_STATUS2        NUMBER,
    OLD_FILENAME       VARCHAR2(513)
)
/

create index REDO_LOG_IDX
    on REDO_LOG (DBID, THREAD#, RESETLOGS_SCN, RESETLOGS_TIME)
/

create table ROLLING$CONNECTIONS
(
    SOURCE_RDBID    NUMBER,
    DEST_RDBID      NUMBER,
    ATTRIBUTES      NUMBER,
    SERVICE_NAME    VARCHAR2(256),
    CONN_HANDLE     NUMBER,
    CONNECT_TIME    TIMESTAMP(6),
    SEND_TIME       TIMESTAMP(6),
    DISCONNECT_TIME TIMESTAMP(6),
    UPDATE_TIME     TIMESTAMP(6),
    SOURCE_PID      NUMBER,
    DEST_PID        NUMBER,
    SPARE1          NUMBER,
    SPARE2          NUMBER,
    SPARE3          VARCHAR2(128)
)
/

create table ROLLING$DATABASES
(
    RDBID         NUMBER,
    ATTRIBUTES    NUMBER,
    ATTRIBUTES2   NUMBER,
    DBUN          VARCHAR2(128),
    DBID          NUMBER,
    PROD_RSCN     NUMBER,
    PROD_RID      NUMBER,
    PROD_SCN      NUMBER,
    CONS_RSCN     NUMBER,
    CONS_RID      NUMBER,
    CONS_SCN      NUMBER,
    ENGINE_STATUS NUMBER,
    VERSION       VARCHAR2(128),
    REDO_SOURCE   NUMBER,
    UPDATE_TIME   TIMESTAMP(6),
    REVISION      NUMBER,
    SPARE1        NUMBER,
    SPARE2        NUMBER,
    SPARE3        VARCHAR2(128)
)
/

create table ROLLING$DIRECTIVES
(
    DIRECTID    NUMBER,
    PHASE       NUMBER,
    TASKID      NUMBER,
    FEATURE     VARCHAR2(32),
    DESCRIPTION VARCHAR2(256),
    TARGET      NUMBER,
    FLAGS       VARCHAR2(64),
    OPCODE      NUMBER,
    P1          VARCHAR2(256),
    P2          VARCHAR2(256),
    P3          VARCHAR2(256),
    SPARE1      NUMBER,
    SPARE2      NUMBER,
    SPARE3      VARCHAR2(256)
)
/

create table ROLLING$EVENTS
(
    EVENTID    NUMBER,
    INSTID     NUMBER,
    REVISION   NUMBER,
    EVENT_TIME TIMESTAMP(6),
    TYPE       VARCHAR2(16),
    STATUS     NUMBER,
    MESSAGE    VARCHAR2(256),
    SPARE1     NUMBER,
    SPARE2     NUMBER,
    SPARE3     VARCHAR2(128)
)
/

create table ROLLING$PARAMETERS
(
    SCOPE      NUMBER,
    NAME       VARCHAR2(32),
    ID         NUMBER,
    DESCRIP    VARCHAR2(256),
    TYPE       NUMBER,
    DATATYPE   NUMBER,
    ATTRIBUTES NUMBER,
    CURVAL     VARCHAR2(1024),
    LSTVAL     VARCHAR2(1024),
    DEFVAL     VARCHAR2(1024),
    MINVAL     NUMBER,
    MAXVAL     NUMBER,
    SPARE1     NUMBER,
    SPARE2     NUMBER,
    SPARE3     VARCHAR2(128)
)
/

create table ROLLING$PLAN
(
    INSTID      NUMBER,
    BATCHID     NUMBER,
    DIRECTID    NUMBER,
    TASKID      NUMBER,
    REVISION    NUMBER,
    PHASE       NUMBER,
    STATUS      NUMBER,
    PROGRESS    NUMBER,
    SOURCE      NUMBER,
    TARGET      NUMBER,
    RFLAGS      NUMBER,
    OPCODE      NUMBER,
    P1          VARCHAR2(256),
    P2          VARCHAR2(256),
    P3          VARCHAR2(256),
    P4          VARCHAR2(256),
    DESCRIPTION VARCHAR2(256),
    EXEC_STATUS NUMBER,
    EXEC_INFO   VARCHAR2(256),
    EXEC_TIME   TIMESTAMP(6),
    FINISH_TIME TIMESTAMP(6),
    POST_STATUS NUMBER,
    SPARE1      NUMBER,
    SPARE2      NUMBER,
    SPARE3      VARCHAR2(256)
)
/

create table ROLLING$STATISTICS
(
    STATID      NUMBER,
    RDBID       NUMBER,
    ATTRIBUTES  NUMBER,
    TYPE        NUMBER,
    NAME        VARCHAR2(256),
    VALUESTR    VARCHAR2(256),
    VALUENUM    NUMBER,
    VALUETS     TIMESTAMP(6),
    VALUEINT    INTERVAL DAY (3) TO SECOND (2),
    UPDATE_TIME TIMESTAMP(6),
    SPARE1      NUMBER,
    SPARE2      NUMBER,
    SPARE3      VARCHAR2(128)
)
/

create table ROLLING$STATUS
(
    REVISION     NUMBER,
    PHASE        NUMBER,
    BATCHID      NUMBER,
    STATUS       NUMBER,
    COORDID      NUMBER,
    OPRIMARY     NUMBER,
    FPRIMARY     NUMBER,
    PID          NUMBER,
    INSTANCE     NUMBER,
    DBTOTAL      NUMBER,
    DBACTIVE     NUMBER,
    LOCATION     VARCHAR2(128),
    INIT_TIME    TIMESTAMP(6),
    BUILD_TIME   TIMESTAMP(6),
    START_TIME   TIMESTAMP(6),
    SWITCH_TIME  TIMESTAMP(6),
    FINISH_TIME  TIMESTAMP(6),
    LAST_INSTID  NUMBER,
    LAST_BATCHID NUMBER,
    SPARE1       NUMBER,
    SPARE2       NUMBER,
    SPARE3       VARCHAR2(128)
)
/

create table MVIEW$_ADV_WORKLOAD
(
    QUERYID#      NUMBER        not null
        constraint MVIEW$_ADV_WORKLOAD_PK
            primary key,
    COLLECTIONID# NUMBER        not null,
    COLLECTTIME   DATE          not null,
    APPLICATION   VARCHAR2(128),
    CARDINALITY   NUMBER,
    RESULTSIZE    NUMBER,
    UNAME         VARCHAR2(128) not null,
    QDATE         DATE,
    PRIORITY      NUMBER,
    EXEC_TIME     NUMBER,
    SQL_TEXT      LONG          not null,
    SQL_TEXTLEN   NUMBER        not null,
    SQL_HASH      NUMBER,
    SQL_ADDR      RAW(16),
    FREQUENCY     NUMBER
)
/

comment on table MVIEW$_ADV_WORKLOAD is 'Shared workload repository for DBA users of summary advisor'
/

create index MVIEW$_ADV_WORKLOAD_IDX_01
    on MVIEW$_ADV_WORKLOAD (COLLECTIONID#, QUERYID#)
/

create table MVIEW$_ADV_BASETABLE
(
    COLLECTIONID# NUMBER not null,
    QUERYID#      NUMBER not null
        constraint MVIEW$_ADV_BASETABLE_FK
            references MVIEW$_ADV_WORKLOAD,
    OWNER         VARCHAR2(128),
    TABLE_NAME    VARCHAR2(128),
    TABLE_TYPE    NUMBER
)
/

comment on table MVIEW$_ADV_BASETABLE is 'Base tables refered by a query'
/

create index MVIEW$_ADV_BASETABLE_IDX_01
    on MVIEW$_ADV_BASETABLE (QUERYID#)
/

create table MVIEW$_ADV_SQLDEPEND
(
    COLLECTIONID# NUMBER,
    INST_ID       NUMBER,
    FROM_ADDRESS  RAW(16),
    FROM_HASH     NUMBER,
    TO_OWNER      VARCHAR2(128),
    TO_NAME       VARCHAR2(1000),
    TO_TYPE       NUMBER,
    CARDINALITY   NUMBER
)
/

comment on table MVIEW$_ADV_SQLDEPEND is 'Temporary table for workload collections'
/

create index MVIEW$_ADV_SQLDEPEND_IDX_01
    on MVIEW$_ADV_SQLDEPEND (COLLECTIONID#, FROM_ADDRESS, FROM_HASH, INST_ID)
/

create table MVIEW$_ADV_PRETTY
(
    QUERYID# NUMBER,
    SQL_TEXT LONG
)
/

comment on table MVIEW$_ADV_PRETTY is 'Table for sql parsing'
/

create index MVIEW$_ADV_PRETTY_IDX_01
    on MVIEW$_ADV_PRETTY (QUERYID#)
/

create table MVIEW$_ADV_TEMP
(
    ID#  NUMBER,
    SEQ# NUMBER,
    TEXT LONG
)
/

comment on table MVIEW$_ADV_TEMP is 'Table for temporary data'
/

create index MVIEW$_ADV_TEMP_IDX_01
    on MVIEW$_ADV_TEMP (ID#, SEQ#)
/

create table MVIEW$_ADV_FILTER
(
    FILTERID#     NUMBER not null,
    SUBFILTERNUM# NUMBER not null,
    SUBFILTERTYPE NUMBER not null,
    STR_VALUE     VARCHAR2(1028),
    NUM_VALUE1    NUMBER,
    NUM_VALUE2    NUMBER,
    DATE_VALUE1   DATE,
    DATE_VALUE2   DATE,
    constraint MVIEW$_ADV_FILTER_PK
        primary key (FILTERID#, SUBFILTERNUM#)
)
/

comment on table MVIEW$_ADV_FILTER is 'Table for workload filter definition'
/

create table MVIEW$_ADV_LOG
(
    RUNID#     NUMBER not null
        constraint MVIEW$_ADV_LOG_PK
            primary key,
    FILTERID#  NUMBER,
    RUN_BEGIN  DATE,
    RUN_END    DATE,
    RUN_TYPE   NUMBER,
    UNAME      VARCHAR2(128),
    STATUS     NUMBER not null,
    MESSAGE    VARCHAR2(2000),
    COMPLETED  NUMBER,
    TOTAL      NUMBER,
    ERROR_CODE VARCHAR2(20)
)
/

comment on table MVIEW$_ADV_LOG is 'Log all calls to summary advisory functions'
/

create table MVIEW$_ADV_FILTERINSTANCE
(
    RUNID#        NUMBER not null
        constraint MVIEW$_ADV_FILTERINSTANCE_FK
            references MVIEW$_ADV_LOG,
    FILTERID#     NUMBER,
    SUBFILTERNUM# NUMBER,
    SUBFILTERTYPE NUMBER,
    STR_VALUE     VARCHAR2(1028),
    NUM_VALUE1    NUMBER,
    NUM_VALUE2    NUMBER,
    DATE_VALUE1   DATE,
    DATE_VALUE2   DATE
)
/

comment on table MVIEW$_ADV_FILTERINSTANCE is 'Table for workload filter instance definition'
/

create table MVIEW$_ADV_LEVEL
(
    RUNID#     NUMBER  not null
        constraint MVIEW$_ADV_LEVEL_FK
            references MVIEW$_ADV_LOG,
    LEVELID#   NUMBER  not null,
    DIMOBJ#    NUMBER,
    FLAGS      NUMBER  not null,
    TBLOBJ#    NUMBER  not null,
    COLUMNLIST RAW(70) not null,
    LEVELNAME  VARCHAR2(128),
    constraint MVIEW$_ADV_LEVEL_PK
        primary key (RUNID#, LEVELID#)
)
/

comment on table MVIEW$_ADV_LEVEL is 'Level definition'
/

create table MVIEW$_ADV_ROLLUP
(
    RUNID#    NUMBER not null
        constraint MVIEW$_ADV_ROLLUP_FK
            references MVIEW$_ADV_LOG,
    CLEVELID# NUMBER not null,
    PLEVELID# NUMBER not null,
    FLAGS     NUMBER not null,
    constraint MVIEW$_ADV_ROLLUP_PK
        primary key (RUNID#, CLEVELID#, PLEVELID#),
    constraint MVIEW$_ADV_ROLLUP_CFK
        foreign key (RUNID#, CLEVELID#) references MVIEW$_ADV_LEVEL,
    constraint MVIEW$_ADV_ROLLUP_PFK
        foreign key (RUNID#, PLEVELID#) references MVIEW$_ADV_LEVEL
)
/

comment on table MVIEW$_ADV_ROLLUP is 'Each row repesents either a functional dependency or join-key relationship'
/

create table MVIEW$_ADV_AJG
(
    AJGID#    NUMBER   not null
        constraint MVIEW$_ADV_AJG_PK
            primary key,
    RUNID#    NUMBER   not null
        constraint MVIEW$_ADV_AJG_FK
            references MVIEW$_ADV_LOG,
    AJGDESLEN NUMBER   not null,
    AJGDES    LONG RAW not null,
    HASHVALUE NUMBER   not null,
    FREQUENCY NUMBER
)
/

comment on table MVIEW$_ADV_AJG is 'Anchor-join graph representation'
/

create table MVIEW$_ADV_FJG
(
    FJGID#    NUMBER   not null
        constraint MVIEW$_ADV_FJG_PK
            primary key,
    AJGID#    NUMBER   not null
        constraint MVIEW$_ADV_FJG_FK
            references MVIEW$_ADV_AJG,
    FJGDESLEN NUMBER   not null,
    FJGDES    LONG RAW not null,
    HASHVALUE NUMBER   not null,
    FREQUENCY NUMBER
)
/

comment on table MVIEW$_ADV_FJG is 'Representation for query join sub-graph not in AJG '
/

create table MVIEW$_ADV_GC
(
    GCID#     NUMBER   not null
        constraint MVIEW$_ADV_GC_PK
            primary key,
    FJGID#    NUMBER   not null
        constraint MVIEW$_ADV_GC_FK
            references MVIEW$_ADV_FJG,
    GCDESLEN  NUMBER   not null,
    GCDES     LONG RAW not null,
    HASHVALUE NUMBER   not null,
    FREQUENCY NUMBER
)
/

comment on table MVIEW$_ADV_GC is 'Group-by columns of a query'
/

create table MVIEW$_ADV_CLIQUE
(
    CLIQUEID#    NUMBER   not null
        constraint MVIEW$_ADV_CLIQUE_PK
            primary key,
    RUNID#       NUMBER   not null
        constraint MVIEW$_ADV_CLIQUE_FK
            references MVIEW$_ADV_LOG,
    CLIQUEDESLEN NUMBER   not null,
    CLIQUEDES    LONG RAW not null,
    HASHVALUE    NUMBER   not null,
    FREQUENCY    NUMBER   not null,
    BYTECOST     NUMBER   not null,
    ROWSIZE      NUMBER   not null,
    NUMROWS      NUMBER   not null
)
/

comment on table MVIEW$_ADV_CLIQUE is 'Table for storing canonical form of Clique queries'
/

create table MVIEW$_ADV_ELIGIBLE
(
    SUMOBJN#  NUMBER not null,
    RUNID#    NUMBER not null
        constraint MVIEW$_ADV_ELIGIBLE_FK
            references MVIEW$_ADV_LOG,
    BYTECOST  NUMBER not null,
    FLAGS     NUMBER not null,
    FREQUENCY NUMBER not null,
    constraint MVIEW$_ADV_ELIGIBLE_PK
        primary key (SUMOBJN#, RUNID#)
)
/

comment on table MVIEW$_ADV_ELIGIBLE is 'Summary management rewrite eligibility information'
/

create table MVIEW$_ADV_OUTPUT
(
    RUNID#                NUMBER not null
        constraint MVIEW$_ADV_OUTPUT_FK
            references MVIEW$_ADV_LOG,
    OUTPUT_TYPE           NUMBER not null,
    RANK#                 NUMBER not null,
    ACTION_TYPE           VARCHAR2(6),
    SUMMARY_OWNER         VARCHAR2(128),
    SUMMARY_NAME          VARCHAR2(128),
    GROUP_BY_COLUMNS      VARCHAR2(2000),
    WHERE_CLAUSE          VARCHAR2(2000),
    FROM_CLAUSE           VARCHAR2(2000),
    MEASURES_LIST         VARCHAR2(2000),
    FACT_TABLES           VARCHAR2(1000),
    GROUPING_LEVELS       VARCHAR2(2000),
    QUERYLEN              NUMBER,
    QUERY_TEXT            LONG,
    STORAGE_IN_BYTES      NUMBER,
    PCT_PERFORMANCE_GAIN  NUMBER,
    FREQUENCY             NUMBER,
    CUMULATIVE_BENEFIT    NUMBER,
    BENEFIT_TO_COST_RATIO NUMBER not null,
    VALIDATED             NUMBER,
    constraint MVIEW$_ADV_OUTPUT_PK
        primary key (RUNID#, RANK#)
)
/

comment on table MVIEW$_ADV_OUTPUT is 'Output table for summary recommendations and evaluations'
/

create table MVIEW$_ADV_EXCEPTIONS
(
    RUNID#         NUMBER
        constraint MVIEW$_ADV_EXCEPTION_FK
            references MVIEW$_ADV_LOG,
    OWNER          VARCHAR2(128),
    TABLE_NAME     VARCHAR2(128),
    DIMENSION_NAME VARCHAR2(128),
    RELATIONSHIP   VARCHAR2(11),
    BAD_ROWID      ROWID
)
/

comment on table MVIEW$_ADV_EXCEPTIONS is 'Output table for dimension validations'
/

create table MVIEW$_ADV_PARAMETERS
(
    PARAMETER_NAME  VARCHAR2(128) not null
        constraint MVIEW$_ADV_PARAMETERS_PK
            primary key,
    PARAMETER_TYPE  NUMBER        not null,
    STRING_VALUE    VARCHAR2(30),
    DATE_VALUE      DATE,
    NUMERICAL_VALUE NUMBER
)
/

comment on table MVIEW$_ADV_PARAMETERS is 'Summary advisor tuning parameters'
/

create table MVIEW$_ADV_INFO
(
    RUNID#  NUMBER not null
        constraint MVIEW$_ADV_INFO_FK
            references MVIEW$_ADV_LOG,
    SEQ#    NUMBER not null,
    TYPE    NUMBER not null,
    INFOLEN NUMBER not null,
    INFO    LONG RAW,
    STATUS  NUMBER,
    FLAG    NUMBER,
    constraint MVIEW$_ADV_INFO_PK
        primary key (RUNID#, SEQ#)
)
/

comment on table MVIEW$_ADV_INFO is 'Internal table for passing information from the SQL analyzer'
/

create table MVIEW$_ADV_JOURNAL
(
    RUNID#    NUMBER not null
        constraint MVIEW$_ADV_JOURNAL_FK
            references MVIEW$_ADV_LOG,
    SEQ#      NUMBER not null,
    TIMESTAMP DATE   not null,
    FLAGS     NUMBER not null,
    NUM       NUMBER,
    TEXT      LONG,
    TEXTLEN   NUMBER,
    constraint MVIEW$_ADV_JOURNAL_PK
        primary key (RUNID#, SEQ#)
)
/

comment on table MVIEW$_ADV_JOURNAL is 'Summary advisor journal table for debugging and information'
/

create table MVIEW$_ADV_PLAN
(
    STATEMENT_ID    VARCHAR2(30),
    TIMESTAMP       DATE,
    REMARKS         VARCHAR2(80),
    OPERATION       VARCHAR2(30),
    OPTIONS         VARCHAR2(255),
    OBJECT_NODE     VARCHAR2(128),
    OBJECT_OWNER    VARCHAR2(128),
    OBJECT_NAME     VARCHAR2(128),
    OBJECT_INSTANCE NUMBER,
    OBJECT_TYPE     VARCHAR2(30),
    OPTIMIZER       VARCHAR2(255),
    SEARCH_COLUMNS  NUMBER,
    ID              NUMBER,
    PARENT_ID       NUMBER,
    POSITION        NUMBER,
    COST            NUMBER,
    CARDINALITY     NUMBER,
    BYTES           NUMBER,
    OTHER_TAG       VARCHAR2(255),
    PARTITION_START VARCHAR2(255),
    PARTITION_STOP  VARCHAR2(255),
    PARTITION_ID    NUMBER,
    OTHER           LONG,
    DISTRIBUTION    VARCHAR2(30),
    CPU_COST        NUMBER,
    IO_COST         NUMBER,
    TEMP_SPACE      NUMBER
)
/

comment on table MVIEW$_ADV_PLAN is 'Private plan table for estimate_mview_size operations'
/

create table AQ$_QUEUE_TABLES
(
    SCHEMA        VARCHAR2(128) not null,
    NAME          VARCHAR2(128) not null,
    UDATA_TYPE    NUMBER        not null,
    OBJNO         NUMBER        not null
        constraint AQ$_QUEUE_TABLES_PRIMARY
            primary key,
    FLAGS         NUMBER        not null,
    SORT_COLS     NUMBER        not null,
    TIMEZONE      VARCHAR2(64),
    TABLE_COMMENT VARCHAR2(2000)
)
/

create index I1_QUEUE_TABLES
    on AQ$_QUEUE_TABLES (OBJNO, SCHEMA, FLAGS)
/

create table AQ$_QUEUES
(
    OID              RAW(16)       not null
        constraint AQ$_QUEUES_PRIMARY
            primary key,
    EVENTID          NUMBER        not null,
    NAME             VARCHAR2(128) not null,
    TABLE_OBJNO      NUMBER        not null,
    USAGE            NUMBER        not null,
    ENABLE_FLAG      NUMBER        not null,
    MAX_RETRIES      NUMBER,
    RETRY_DELAY      NUMBER,
    PROPERTIES       NUMBER,
    RET_TIME         NUMBER,
    QUEUE_COMMENT    VARCHAR2(2000),
    SUBSCRIBERS      SYS.AQ$_SUBSCRIBERS,
    MEMORY_THRESHOLD NUMBER,
    SERVICE_NAME     VARCHAR2(64),
    NETWORK_NAME     VARCHAR2(256),
    SUB_OID          RAW(16),
    SHARDED          NUMBER,
    BASE_QUEUE       NUMBER default 0,
    constraint AQ$_QUEUES_CHECK
        unique (NAME, TABLE_OBJNO)
)
/

create index I1_QUEUES
    on AQ$_QUEUES (NAME, EVENTID, TABLE_OBJNO)
/

create table AQ$_SCHEDULES
(
    OID         RAW(16)       not null,
    DESTINATION VARCHAR2(390) not null,
    START_TIME  DATE,
    DURATION    VARCHAR2(8),
    NEXT_TIME   VARCHAR2(128),
    LATENCY     VARCHAR2(8),
    LAST_TIME   DATE,
    JOBNO       NUMBER
        constraint AQ$_SCHEDULES_CHECK
            unique,
    constraint AQ$_SCHEDULES_PRIMARY
        primary key (OID, DESTINATION)
)
/

create table AQ$_INTERNET_AGENTS
(
    AGENT_NAME VARCHAR2(512) not null
        primary key,
    PROTOCOL   NUMBER        not null,
    SPARE1     VARCHAR2(128)
)
/

create table AQ$_INTERNET_AGENT_PRIVS
(
    AGENT_NAME  VARCHAR2(512) not null
        constraint AGENT_MUST_BE_CREATED
            references AQ$_INTERNET_AGENTS
                on delete cascade,
    DB_USERNAME VARCHAR2(128) not null,
    constraint UNQ_PAIRS
        unique (AGENT_NAME, DB_USERNAME)
)
/

create global temporary table OL$
(
    OL_NAME     VARCHAR2(128),
    SQL_TEXT    LONG,
    TEXTLEN     NUMBER,
    SIGNATURE   RAW(16),
    HASH_VALUE  NUMBER,
    HASH_VALUE2 NUMBER,
    CATEGORY    VARCHAR2(128),
    VERSION     VARCHAR2(64),
    CREATOR     VARCHAR2(128),
    TIMESTAMP   DATE,
    FLAGS       NUMBER,
    HINTCOUNT   NUMBER,
    SPARE1      NUMBER,
    SPARE2      VARCHAR2(1000)
)
    on commit preserve rows
/

create unique index OL$NAME
    on OL$ (OL_NAME)
/

create unique index OL$SIGNATURE
    on OL$ (SIGNATURE, CATEGORY)
/

create global temporary table OL$HINTS
(
    OL_NAME         VARCHAR2(128),
    HINT#           NUMBER,
    CATEGORY        VARCHAR2(128),
    HINT_TYPE       NUMBER,
    HINT_TEXT       VARCHAR2(512),
    STAGE#          NUMBER,
    NODE#           NUMBER,
    TABLE_NAME      VARCHAR2(128),
    TABLE_TIN       NUMBER,
    TABLE_POS       NUMBER,
    REF_ID          NUMBER,
    USER_TABLE_NAME VARCHAR2(260),
    COST            FLOAT,
    CARDINALITY     FLOAT,
    BYTES           FLOAT,
    HINT_TEXTOFF    NUMBER,
    HINT_TEXTLEN    NUMBER,
    JOIN_PRED       VARCHAR2(2000),
    SPARE1          NUMBER,
    SPARE2          NUMBER,
    HINT_STRING     CLOB
)
    on commit preserve rows
/

create unique index OL$HNT_NUM
    on OL$HINTS (OL_NAME, HINT#)
/

create global temporary table OL$NODES
(
    OL_NAME      VARCHAR2(128),
    CATEGORY     VARCHAR2(128),
    NODE_ID      NUMBER,
    PARENT_ID    NUMBER,
    NODE_TYPE    NUMBER,
    NODE_TEXTLEN NUMBER,
    NODE_TEXTOFF NUMBER,
    NODE_NAME    VARCHAR2(64)
)
    on commit preserve rows
/

create table LOGMNR_DICTSTATE$
(
    LOGMNR_UID   NUMBER(22)
        constraint LOGMNR_DICTSTATE$_PK
            primary key,
    START_SCNBAS NUMBER,
    START_SCNWRP NUMBER,
    END_SCNBAS   NUMBER,
    END_SCNWRP   NUMBER,
    REDO_THREAD  NUMBER,
    RBASQN       NUMBER,
    RBABLK       NUMBER,
    RBABYTE      NUMBER,
    LOGMNR_FLAGS NUMBER(22)
)
/

create table LOGMNRC_GTLO
(
    LOGMNR_UID         NUMBER        not null,
    KEYOBJ#            NUMBER        not null,
    LVLCNT             NUMBER        not null,
    BASEOBJ#           NUMBER        not null,
    BASEOBJV#          NUMBER        not null,
    LVL1OBJ#           NUMBER,
    LVL2OBJ#           NUMBER,
    LVL0TYPE#          NUMBER        not null,
    LVL1TYPE#          NUMBER,
    LVL2TYPE#          NUMBER,
    OWNER#             NUMBER,
    OWNERNAME          VARCHAR2(384) not null,
    LVL0NAME           VARCHAR2(384) not null,
    LVL1NAME           VARCHAR2(384),
    LVL2NAME           VARCHAR2(384),
    INTCOLS            NUMBER        not null,
    COLS               NUMBER,
    KERNELCOLS         NUMBER,
    TAB_FLAGS          NUMBER,
    TRIGFLAG           NUMBER,
    ASSOC#             NUMBER,
    OBJ_FLAGS          NUMBER,
    TS#                NUMBER,
    TSNAME             VARCHAR2(90),
    PROPERTY           NUMBER,
    START_SCN          NUMBER        not null,
    DROP_SCN           NUMBER,
    XIDUSN             NUMBER,
    XIDSLT             NUMBER,
    XIDSQN             NUMBER,
    FLAGS              NUMBER,
    LOGMNR_SPARE1      NUMBER,
    LOGMNR_SPARE2      NUMBER,
    LOGMNR_SPARE3      VARCHAR2(1000),
    LOGMNR_SPARE4      DATE,
    LOGMNR_SPARE5      NUMBER,
    LOGMNR_SPARE6      NUMBER,
    LOGMNR_SPARE7      NUMBER,
    LOGMNR_SPARE8      NUMBER,
    LOGMNR_SPARE9      NUMBER,
    PARTTYPE           NUMBER,
    SUBPARTTYPE        NUMBER,
    UNSUPPORTEDCOLS    NUMBER,
    COMPLEXTYPECOLS    NUMBER,
    NTPARENTOBJNUM     NUMBER,
    NTPARENTOBJVERSION NUMBER,
    NTPARENTINTCOLNUM  NUMBER,
    LOGMNRTLOFLAGS     NUMBER,
    LOGMNRMCV          VARCHAR2(30),
    ACDRFLAGS          NUMBER,
    ACDRTSOBJ#         NUMBER,
    ACDRROWTSINTCOL#   NUMBER,
    constraint LOGMNRC_GTLO_PK
        primary key (LOGMNR_UID, KEYOBJ#, BASEOBJV#)
)
/

create index LOGMNRC_I2GTLO
    on LOGMNRC_GTLO (LOGMNR_UID, BASEOBJ#, BASEOBJV#)
/

create index LOGMNRC_I3GTLO
    on LOGMNRC_GTLO (LOGMNR_UID, DROP_SCN)
/

create table LOGMNRC_GTCS
(
    LOGMNR_UID                  NUMBER        not null,
    OBJ#                        NUMBER        not null,
    OBJV#                       NUMBER        not null,
    SEGCOL#                     NUMBER        not null,
    INTCOL#                     NUMBER        not null,
    COLNAME                     VARCHAR2(384) not null,
    TYPE#                       NUMBER        not null,
    LENGTH                      NUMBER,
    PRECISION                   NUMBER,
    SCALE                       NUMBER,
    INTERVAL_LEADING_PRECISION  NUMBER,
    INTERVAL_TRAILING_PRECISION NUMBER,
    PROPERTY                    NUMBER,
    TOID                        RAW(16),
    CHARSETID                   NUMBER,
    CHARSETFORM                 NUMBER,
    TYPENAME                    VARCHAR2(384),
    FQCOLNAME                   VARCHAR2(4000),
    NUMINTCOLS                  NUMBER,
    NUMATTRS                    NUMBER,
    ADTORDER                    NUMBER,
    LOGMNR_SPARE1               NUMBER,
    LOGMNR_SPARE2               NUMBER,
    LOGMNR_SPARE3               VARCHAR2(1000),
    LOGMNR_SPARE4               DATE,
    LOGMNR_SPARE5               NUMBER,
    LOGMNR_SPARE6               NUMBER,
    LOGMNR_SPARE7               NUMBER,
    LOGMNR_SPARE8               NUMBER,
    LOGMNR_SPARE9               NUMBER,
    COL#                        NUMBER,
    XTYPESCHEMANAME             VARCHAR2(384),
    XTYPENAME                   VARCHAR2(4000),
    XFQCOLNAME                  VARCHAR2(4000),
    XTOPINTCOL                  NUMBER,
    XREFFEDTABLEOBJN            NUMBER,
    XREFFEDTABLEOBJV            NUMBER,
    XCOLTYPEFLAGS               NUMBER,
    XOPQTYPETYPE                NUMBER,
    XOPQTYPEFLAGS               NUMBER,
    XOPQLOBINTCOL               NUMBER,
    XOPQOBJINTCOL               NUMBER,
    XXMLINTCOL                  NUMBER,
    EAOWNER#                    NUMBER,
    EAMKEYID                    VARCHAR2(192),
    EAENCALG                    NUMBER,
    EAINTALG                    NUMBER,
    EACOLKLC                    RAW(2000),
    EAKLCLEN                    NUMBER,
    EAFLAGS                     NUMBER,
    LOGMNRDERIVEDFLAGS          NUMBER,
    COLLID                      NUMBER,
    COLLINTCOL#                 NUMBER,
    ACDRRESCOL#                 NUMBER,
    constraint LOGMNRC_GTCS_PK
        primary key (LOGMNR_UID, OBJ#, OBJV#, INTCOL#)
)
/

create index LOGMNRC_I2GTCS
    on LOGMNRC_GTCS (LOGMNR_UID, OBJ#, OBJV#, SEGCOL#, INTCOL#)
/

create table LOGMNRC_SEQ_GG
(
    LOGMNR_UID NUMBER        not null,
    OBJ#       NUMBER        not null,
    COMMIT_SCN NUMBER        not null,
    DROP_SCN   NUMBER,
    SEQ_FLAGS  NUMBER        not null,
    OWNER#     NUMBER        not null,
    OWNERNAME  VARCHAR2(384) not null,
    OBJNAME    VARCHAR2(384) not null,
    SEQCACHE   NUMBER,
    SEQINC     NUMBER,
    SPARE1     NUMBER,
    SPARE2     NUMBER,
    SPARE3     VARCHAR2(4000),
    SPARE4     VARCHAR2(4000),
    constraint LOGMNRC_SEQ_GG_PK
        primary key (LOGMNR_UID, OBJ#, COMMIT_SCN)
)
/

create index LOGMNRC_I2SEQGG
    on LOGMNRC_SEQ_GG (LOGMNR_UID, DROP_SCN)
/

create table LOGMNRC_CON_GG
(
    LOGMNR_UID NUMBER        not null,
    CON#       NUMBER        not null,
    NAME       VARCHAR2(384) not null,
    COMMIT_SCN NUMBER        not null,
    DROP_SCN   NUMBER,
    BASEOBJ#   NUMBER        not null,
    BASEOBJV#  NUMBER        not null,
    FLAGS      NUMBER        not null,
    INDEXOBJ#  NUMBER,
    SPARE1     NUMBER,
    SPARE2     NUMBER,
    SPARE3     NUMBER,
    SPARE4     VARCHAR2(4000),
    SPARE5     VARCHAR2(4000),
    SPARE6     VARCHAR2(4000),
    constraint LOGMNRC_CON_GG_PK
        primary key (LOGMNR_UID, CON#, COMMIT_SCN)
)
/

create index LOGMNRC_I1CONGG
    on LOGMNRC_CON_GG (LOGMNR_UID, BASEOBJ#, BASEOBJV#, COMMIT_SCN)
/

create index LOGMNRC_I2CONGG
    on LOGMNRC_CON_GG (LOGMNR_UID, DROP_SCN)
/

create table LOGMNRC_CONCOL_GG
(
    LOGMNR_UID NUMBER not null,
    CON#       NUMBER not null,
    COMMIT_SCN NUMBER not null,
    INTCOL#    NUMBER not null,
    POS#       NUMBER,
    SPARE1     NUMBER,
    SPARE2     NUMBER,
    SPARE3     VARCHAR2(4000),
    constraint LOGMNRC_CONCOL_GG_PK
        primary key (LOGMNR_UID, CON#, COMMIT_SCN, INTCOL#)
)
/

create table LOGMNRC_IND_GG
(
    LOGMNR_UID NUMBER        not null,
    OBJ#       NUMBER        not null,
    NAME       VARCHAR2(384) not null,
    COMMIT_SCN NUMBER        not null,
    DROP_SCN   NUMBER,
    BASEOBJ#   NUMBER        not null,
    BASEOBJV#  NUMBER        not null,
    FLAGS      NUMBER        not null,
    OWNER#     NUMBER        not null,
    OWNERNAME  VARCHAR2(384) not null,
    SPARE1     NUMBER,
    SPARE2     NUMBER,
    SPARE3     NUMBER,
    SPARE4     VARCHAR2(4000),
    SPARE5     VARCHAR2(4000),
    SPARE6     VARCHAR2(4000),
    constraint LOGMNRC_IND_GG_PK
        primary key (LOGMNR_UID, OBJ#, COMMIT_SCN)
)
/

create index LOGMNRC_I1INDGG
    on LOGMNRC_IND_GG (LOGMNR_UID, BASEOBJ#, BASEOBJV#, COMMIT_SCN)
/

create index LOGMNRC_I2INDGG
    on LOGMNRC_IND_GG (LOGMNR_UID, DROP_SCN)
/

create table LOGMNRC_INDCOL_GG
(
    LOGMNR_UID NUMBER not null,
    OBJ#       NUMBER not null,
    COMMIT_SCN NUMBER not null,
    INTCOL#    NUMBER not null,
    POS#       NUMBER not null,
    SPARE1     NUMBER,
    SPARE2     NUMBER,
    SPARE3     VARCHAR2(4000),
    constraint LOGMNRC_INDCOL_GG_PK
        primary key (LOGMNR_UID, OBJ#, COMMIT_SCN, INTCOL#)
)
/

create table LOGMNRC_SHARD_TS
(
    LOGMNR_UID      NUMBER       not null,
    TABLESPACE_NAME VARCHAR2(90) not null,
    CHUNK_NUMBER    NUMBER       not null,
    START_SCN       NUMBER       not null,
    DROP_SCN        NUMBER,
    SPARE1          NUMBER,
    SPARE2          NUMBER,
    SPARE3          VARCHAR2(4000),
    constraint LOGMNRC_SHARD_TS_PK
        primary key (LOGMNR_UID, TABLESPACE_NAME, START_SCN)
)
/

create index LOGMNRC_I1SHARD_TS
    on LOGMNRC_SHARD_TS (LOGMNR_UID, DROP_SCN)
/

create table LOGMNRC_TSPART
(
    LOGMNR_UID NUMBER not null,
    OBJ#       NUMBER not null,
    TS#        NUMBER not null,
    START_SCN  NUMBER not null,
    DROP_SCN   NUMBER,
    SPARE1     NUMBER,
    SPARE2     NUMBER,
    SPARE3     VARCHAR2(4000),
    constraint LOGMNRC_TSPART_PK
        primary key (LOGMNR_UID, OBJ#, START_SCN)
)
/

create index LOGMNRC_I1TSPART
    on LOGMNRC_TSPART (LOGMNR_UID, DROP_SCN)
/

create table LOGMNRC_TS
(
    LOGMNR_UID NUMBER     not null,
    TS#        NUMBER(22) not null,
    NAME       VARCHAR2(90),
    START_SCN  NUMBER     not null,
    DROP_SCN   NUMBER,
    SPARE1     NUMBER,
    SPARE2     NUMBER,
    SPARE3     VARCHAR2(4000),
    constraint LOGMNRC_TS_PK
        primary key (LOGMNR_UID, TS#, START_SCN)
)
/

create index LOGMNRC_I1TS
    on LOGMNRC_TS (LOGMNR_UID, DROP_SCN)
/

create table LOGMNRC_GSII
(
    LOGMNR_UID    NUMBER not null,
    OBJ#          NUMBER not null,
    BO#           NUMBER not null,
    INDTYPE#      NUMBER not null,
    DROP_SCN      NUMBER,
    LOGMNR_SPARE1 NUMBER,
    LOGMNR_SPARE2 NUMBER,
    LOGMNR_SPARE3 VARCHAR2(1000),
    LOGMNR_SPARE4 DATE,
    constraint LOGMNRC_GSII_PK
        primary key (LOGMNR_UID, OBJ#)
)
/

create table LOGMNRC_GSBA
(
    LOGMNR_UID       NUMBER not null,
    AS_OF_SCN        NUMBER not null,
    FDO_LENGTH       NUMBER,
    FDO_VALUE        RAW(255),
    CHARSETID        NUMBER,
    NCHARSETID       NUMBER,
    DBTIMEZONE_LEN   NUMBER,
    DBTIMEZONE_VALUE VARCHAR2(192),
    LOGMNR_SPARE1    NUMBER,
    LOGMNR_SPARE2    NUMBER,
    LOGMNR_SPARE3    VARCHAR2(1000),
    LOGMNR_SPARE4    DATE,
    DB_GLOBAL_NAME   VARCHAR2(384),
    constraint LOGMNRC_GSBA_PK
        primary key (LOGMNR_UID, AS_OF_SCN)
)
/

create table LOGMNR_SEED$
(
    SEED_VERSION   NUMBER(22),
    GATHER_VERSION NUMBER(22),
    SCHEMANAME     VARCHAR2(384),
    OBJ#           NUMBER,
    OBJV#          NUMBER(22),
    TABLE_NAME     VARCHAR2(384),
    COL_NAME       VARCHAR2(384),
    COL#           NUMBER,
    INTCOL#        NUMBER,
    SEGCOL#        NUMBER,
    TYPE#          NUMBER,
    LENGTH         NUMBER,
    PRECISION#     NUMBER,
    SCALE          NUMBER,
    NULL$          NUMBER not null,
    LOGMNR_UID     NUMBER(22),
    LOGMNR_FLAGS   NUMBER(22),
    constraint LOGMNR_SEED$_PK
        primary key (LOGMNR_UID, OBJ#, INTCOL#)
)
/

create index LOGMNR_I1SEED$
    on LOGMNR_SEED$ (LOGMNR_UID, OBJ#, INTCOL#)
/

create index LOGMNR_I2SEED$
    on LOGMNR_SEED$ (LOGMNR_UID, SCHEMANAME, TABLE_NAME, COL_NAME, OBJ#, INTCOL#)
/

create table LOGMNR_DICTIONARY$
(
    DB_NAME              VARCHAR2(27),
    DB_ID                NUMBER(20),
    DB_CREATED           VARCHAR2(20),
    DB_DICT_CREATED      VARCHAR2(20),
    DB_DICT_SCN          NUMBER(22),
    DB_THREAD_MAP        RAW(8),
    DB_TXN_SCNBAS        NUMBER(22),
    DB_TXN_SCNWRP        NUMBER(22),
    DB_RESETLOGS_CHANGE# NUMBER(22),
    DB_RESETLOGS_TIME    VARCHAR2(20),
    DB_VERSION_TIME      VARCHAR2(20),
    DB_REDO_TYPE_ID      VARCHAR2(8),
    DB_REDO_RELEASE      VARCHAR2(60),
    DB_CHARACTER_SET     VARCHAR2(192),
    DB_VERSION           VARCHAR2(240),
    DB_STATUS            VARCHAR2(240),
    DB_GLOBAL_NAME       VARCHAR2(384),
    DB_DICT_MAXOBJECTS   NUMBER(22),
    DB_DICT_OBJECTCOUNT  NUMBER(22) not null,
    LOGMNR_UID           NUMBER(22)
        constraint LOGMNR_DICTIONARY$_PK
            primary key,
    LOGMNR_FLAGS         NUMBER(22),
    PDB_NAME             VARCHAR2(384),
    PDB_ID               NUMBER,
    PDB_UID              NUMBER,
    PDB_DBID             NUMBER,
    PDB_GUID             RAW(16),
    PDB_CREATE_SCN       NUMBER,
    PDB_COUNT            NUMBER,
    PDB_GLOBAL_NAME      VARCHAR2(384),
    FED_ROOT_CON_ID#     NUMBER
)
/

create index LOGMNR_I1DICTIONARY$
    on LOGMNR_DICTIONARY$ (LOGMNR_UID)
/

create table LOGMNR_OBJ$
(
    OBJV#        NUMBER(22),
    OWNER#       NUMBER(22),
    NAME         VARCHAR2(384),
    NAMESPACE    NUMBER(22),
    SUBNAME      VARCHAR2(384),
    TYPE#        NUMBER(22),
    OID$         RAW(16),
    REMOTEOWNER  VARCHAR2(384),
    LINKNAME     VARCHAR2(384),
    FLAGS        NUMBER(22),
    SPARE3       NUMBER(22),
    STIME        DATE,
    OBJ#         NUMBER(22) not null,
    LOGMNR_UID   NUMBER(22),
    LOGMNR_FLAGS NUMBER(22),
    START_SCNBAS NUMBER,
    START_SCNWRP NUMBER,
    constraint LOGMNR_OBJ$_PK
        primary key (LOGMNR_UID, OBJ#)
)
/

create index LOGMNR_I1OBJ$
    on LOGMNR_OBJ$ (LOGMNR_UID, OBJ#)
/

create index LOGMNR_I2OBJ$
    on LOGMNR_OBJ$ (LOGMNR_UID, OID$)
/

create index LOGMNR_I3OBJ$
    on LOGMNR_OBJ$ (LOGMNR_UID, NAME)
/

create table LOGMNR_TAB$
(
    TS#              NUMBER(22),
    COLS             NUMBER(22),
    PROPERTY         NUMBER,
    INTCOLS          NUMBER(22),
    KERNELCOLS       NUMBER(22),
    BOBJ#            NUMBER(22),
    TRIGFLAG         NUMBER(22),
    FLAGS            NUMBER(22),
    OBJ#             NUMBER(22) not null,
    LOGMNR_UID       NUMBER(22),
    LOGMNR_FLAGS     NUMBER(22),
    ACDRFLAGS        NUMBER,
    ACDRTSOBJ#       NUMBER,
    ACDRROWTSINTCOL# NUMBER,
    constraint LOGMNR_TAB$_PK
        primary key (LOGMNR_UID, OBJ#)
)
/

create index LOGMNR_I1TAB$
    on LOGMNR_TAB$ (LOGMNR_UID, OBJ#)
/

create index LOGMNR_I2TAB$
    on LOGMNR_TAB$ (LOGMNR_UID, BOBJ#)
/

create table LOGMNR_COL$
(
    COL#         NUMBER(22),
    SEGCOL#      NUMBER(22),
    NAME         VARCHAR2(384),
    TYPE#        NUMBER(22),
    LENGTH       NUMBER(22),
    PRECISION#   NUMBER(22),
    SCALE        NUMBER(22),
    NULL$        NUMBER(22),
    INTCOL#      NUMBER(22),
    PROPERTY     NUMBER(22),
    CHARSETID    NUMBER(22),
    CHARSETFORM  NUMBER(22),
    SPARE1       NUMBER(22),
    SPARE2       NUMBER(22),
    OBJ#         NUMBER(22) not null,
    LOGMNR_UID   NUMBER(22),
    LOGMNR_FLAGS NUMBER(22),
    COLLID       NUMBER,
    COLLINTCOL#  NUMBER,
    ACDRRESCOL#  NUMBER,
    constraint LOGMNR_COL$_PK
        primary key (LOGMNR_UID, OBJ#, INTCOL#)
)
/

create index LOGMNR_I1COL$
    on LOGMNR_COL$ (LOGMNR_UID, OBJ#, INTCOL#)
/

create index LOGMNR_I2COL$
    on LOGMNR_COL$ (LOGMNR_UID, OBJ#, NAME)
/

create index LOGMNR_I3COL$
    on LOGMNR_COL$ (LOGMNR_UID, OBJ#, COL#)
/

create table LOGMNR_ATTRCOL$
(
    INTCOL#      NUMBER,
    NAME         VARCHAR2(4000),
    OBJ#         NUMBER not null,
    LOGMNR_UID   NUMBER(22),
    LOGMNR_FLAGS NUMBER(22),
    constraint LOGMNR_ATTRCOL$_PK
        primary key (LOGMNR_UID, OBJ#, INTCOL#)
)
/

create index LOGMNR_I1ATTRCOL$
    on LOGMNR_ATTRCOL$ (LOGMNR_UID, OBJ#, INTCOL#)
/

create table LOGMNR_TS$
(
    TS#          NUMBER(22),
    NAME         VARCHAR2(90),
    OWNER#       NUMBER(22),
    BLOCKSIZE    NUMBER(22) not null,
    LOGMNR_UID   NUMBER(22),
    LOGMNR_FLAGS NUMBER(22),
    START_SCNBAS NUMBER,
    START_SCNWRP NUMBER,
    constraint LOGMNR_TS$_PK
        primary key (LOGMNR_UID, TS#)
)
/

create index LOGMNR_I1TS$
    on LOGMNR_TS$ (LOGMNR_UID, TS#)
/

create table LOGMNR_IND$
(
    BO#          NUMBER(22),
    COLS         NUMBER(22),
    TYPE#        NUMBER(22),
    FLAGS        NUMBER,
    PROPERTY     NUMBER,
    OBJ#         NUMBER(22) not null,
    LOGMNR_UID   NUMBER(22),
    LOGMNR_FLAGS NUMBER(22),
    constraint LOGMNR_IND$_PK
        primary key (LOGMNR_UID, OBJ#)
)
/

create index LOGMNR_I1IND$
    on LOGMNR_IND$ (LOGMNR_UID, OBJ#)
/

create index LOGMNR_I2IND$
    on LOGMNR_IND$ (LOGMNR_UID, BO#)
/

create table LOGMNR_USER$
(
    USER#        NUMBER(22),
    NAME         VARCHAR2(384) not null,
    LOGMNR_UID   NUMBER(22),
    LOGMNR_FLAGS NUMBER(22),
    constraint LOGMNR_USER$_PK
        primary key (LOGMNR_UID, USER#)
)
/

create index LOGMNR_I1USER$
    on LOGMNR_USER$ (LOGMNR_UID, USER#)
/

create index LOGMNR_I2USER$
    on LOGMNR_USER$ (LOGMNR_UID, NAME)
/

create table LOGMNR_TABPART$
(
    OBJ#         NUMBER(22),
    TS#          NUMBER(22),
    PART#        NUMBER,
    BO#          NUMBER(22) not null,
    LOGMNR_UID   NUMBER(22),
    LOGMNR_FLAGS NUMBER(22),
    constraint LOGMNR_TABPART$_PK
        primary key (LOGMNR_UID, OBJ#, BO#)
)
/

create index LOGMNR_I1TABPART$
    on LOGMNR_TABPART$ (LOGMNR_UID, OBJ#, BO#)
/

create index LOGMNR_I2TABPART$
    on LOGMNR_TABPART$ (LOGMNR_UID, BO#)
/

create table LOGMNR_TABSUBPART$
(
    OBJ#         NUMBER(22),
    DATAOBJ#     NUMBER(22),
    POBJ#        NUMBER(22),
    SUBPART#     NUMBER(22),
    TS#          NUMBER(22) not null,
    LOGMNR_UID   NUMBER(22),
    LOGMNR_FLAGS NUMBER(22),
    constraint LOGMNR_TABSUBPART$_PK
        primary key (LOGMNR_UID, OBJ#, POBJ#)
)
/

create index LOGMNR_I1TABSUBPART$
    on LOGMNR_TABSUBPART$ (LOGMNR_UID, OBJ#, POBJ#)
/

create index LOGMNR_I2TABSUBPART$
    on LOGMNR_TABSUBPART$ (LOGMNR_UID, POBJ#)
/

create table LOGMNR_TABCOMPART$
(
    OBJ#         NUMBER(22),
    BO#          NUMBER(22),
    PART#        NUMBER(22) not null,
    LOGMNR_UID   NUMBER(22),
    LOGMNR_FLAGS NUMBER(22),
    constraint LOGMNR_TABCOMPART$_PK
        primary key (LOGMNR_UID, OBJ#)
)
/

create index LOGMNR_I1TABCOMPART$
    on LOGMNR_TABCOMPART$ (LOGMNR_UID, OBJ#)
/

create index LOGMNR_I2TABCOMPART$
    on LOGMNR_TABCOMPART$ (LOGMNR_UID, BO#)
/

create table LOGMNR_TYPE$
(
    VERSION#        NUMBER,
    VERSION         VARCHAR2(384),
    TVOID           RAW(16),
    TYPECODE        NUMBER,
    PROPERTIES      NUMBER,
    ATTRIBUTES      NUMBER,
    METHODS         NUMBER,
    HIDDENMETHODS   NUMBER,
    SUPERTYPES      NUMBER,
    SUBTYPES        NUMBER,
    EXTERNTYPE      NUMBER,
    EXTERNNAME      VARCHAR2(4000),
    HELPERCLASSNAME VARCHAR2(4000),
    LOCAL_ATTRS     NUMBER,
    LOCAL_METHODS   NUMBER,
    TYPEID          RAW(16),
    ROOTTOID        RAW(16),
    SPARE1          NUMBER,
    SPARE2          NUMBER,
    SPARE3          NUMBER,
    SUPERTOID       RAW(16),
    HASHCODE        RAW(17),
    TOID            RAW(16) not null,
    LOGMNR_UID      NUMBER(22),
    LOGMNR_FLAGS    NUMBER(22),
    constraint LOGMNR_TYPE$_PK
        primary key (LOGMNR_UID, TOID, VERSION#)
)
/

create index LOGMNR_I1TYPE$
    on LOGMNR_TYPE$ (LOGMNR_UID, TOID, VERSION#)
/

create table LOGMNR_COLTYPE$
(
    COL#         NUMBER,
    INTCOL#      NUMBER,
    TOID         RAW(16),
    VERSION#     NUMBER,
    PACKED       NUMBER,
    INTCOLS      NUMBER,
    INTCOL#S     RAW(2000),
    FLAGS        NUMBER,
    TYPIDCOL#    NUMBER,
    SYNOBJ#      NUMBER,
    OBJ#         NUMBER not null,
    LOGMNR_UID   NUMBER(22),
    LOGMNR_FLAGS NUMBER(22),
    constraint LOGMNR_COLTYPE$_PK
        primary key (LOGMNR_UID, OBJ#, INTCOL#)
)
/

create index LOGMNR_I1COLTYPE$
    on LOGMNR_COLTYPE$ (LOGMNR_UID, OBJ#, INTCOL#)
/

create table LOGMNR_ATTRIBUTE$
(
    VERSION#      NUMBER,
    NAME          VARCHAR2(384),
    ATTRIBUTE#    NUMBER,
    ATTR_TOID     RAW(16),
    ATTR_VERSION# NUMBER,
    SYNOBJ#       NUMBER,
    PROPERTIES    NUMBER,
    CHARSETID     NUMBER,
    CHARSETFORM   NUMBER,
    LENGTH        NUMBER,
    PRECISION#    NUMBER,
    SCALE         NUMBER,
    EXTERNNAME    VARCHAR2(4000),
    XFLAGS        NUMBER,
    SPARE1        NUMBER,
    SPARE2        NUMBER,
    SPARE3        NUMBER,
    SPARE4        NUMBER,
    SPARE5        NUMBER,
    SETTER        NUMBER,
    GETTER        NUMBER,
    TOID          RAW(16) not null,
    LOGMNR_UID    NUMBER(22),
    LOGMNR_FLAGS  NUMBER(22),
    constraint LOGMNR_ATTRIBUTE$_PK
        primary key (LOGMNR_UID, TOID, VERSION#, ATTRIBUTE#)
)
/

create index LOGMNR_I1ATTRIBUTE$
    on LOGMNR_ATTRIBUTE$ (LOGMNR_UID, TOID, VERSION#, ATTRIBUTE#)
/

create table LOGMNR_LOB$
(
    OBJ#         NUMBER,
    INTCOL#      NUMBER,
    COL#         NUMBER,
    LOBJ#        NUMBER,
    CHUNK        NUMBER not null,
    LOGMNR_UID   NUMBER(22),
    LOGMNR_FLAGS NUMBER(22),
    constraint LOGMNR_LOB$_PK
        primary key (LOGMNR_UID, OBJ#, INTCOL#)
)
/

create index LOGMNR_I1LOB$
    on LOGMNR_LOB$ (LOGMNR_UID, OBJ#, INTCOL#)
/

create table LOGMNR_CON$
(
    OWNER#       NUMBER        not null,
    NAME         VARCHAR2(384) not null,
    CON#         NUMBER        not null,
    LOGMNR_UID   NUMBER(22),
    LOGMNR_FLAGS NUMBER(22),
    START_SCNBAS NUMBER,
    START_SCNWRP NUMBER,
    constraint LOGMNR_CON$_PK
        primary key (LOGMNR_UID, CON#)
)
/

create index LOGMNR_I1CON$
    on LOGMNR_CON$ (LOGMNR_UID, CON#)
/

create table LOGMNR_CONTAINER$
(
    OBJ#             NUMBER not null,
    CON_ID#          NUMBER not null,
    DBID             NUMBER not null,
    CON_UID          NUMBER not null,
    CREATE_SCNWRP    NUMBER not null,
    CREATE_SCNBAS    NUMBER not null,
    FLAGS            NUMBER,
    STATUS           NUMBER not null,
    LOGMNR_UID       NUMBER(22),
    LOGMNR_FLAGS     NUMBER(22),
    VSN              NUMBER,
    FED_ROOT_CON_ID# NUMBER,
    constraint LOGMNR_CONTAINER$_PK
        primary key (LOGMNR_UID, OBJ#)
)
/

create index LOGMNR_I1CONTAINER$
    on LOGMNR_CONTAINER$ (LOGMNR_UID, CON_ID#)
/

create table LOGMNR_CDEF$
(
    CON#         NUMBER,
    COLS         NUMBER,
    TYPE#        NUMBER,
    ROBJ#        NUMBER,
    RCON#        NUMBER,
    ENABLED      NUMBER,
    DEFER        NUMBER,
    OBJ#         NUMBER not null,
    LOGMNR_UID   NUMBER(22),
    LOGMNR_FLAGS NUMBER(22),
    constraint LOGMNR_CDEF$_PK
        primary key (LOGMNR_UID, CON#)
)
/

create index LOGMNR_I1CDEF$
    on LOGMNR_CDEF$ (LOGMNR_UID, CON#)
/

create index LOGMNR_I2CDEF$
    on LOGMNR_CDEF$ (LOGMNR_UID, ROBJ#)
/

create index LOGMNR_I3CDEF$
    on LOGMNR_CDEF$ (LOGMNR_UID, OBJ#)
/

create table LOGMNR_CCOL$
(
    CON#         NUMBER,
    OBJ#         NUMBER,
    COL#         NUMBER,
    POS#         NUMBER,
    INTCOL#      NUMBER not null,
    LOGMNR_UID   NUMBER(22),
    LOGMNR_FLAGS NUMBER(22),
    constraint LOGMNR_CCOL$_PK
        primary key (LOGMNR_UID, CON#, INTCOL#)
)
/

create index LOGMNR_I1CCOL$
    on LOGMNR_CCOL$ (LOGMNR_UID, CON#, INTCOL#)
/

create table LOGMNR_ICOL$
(
    OBJ#         NUMBER,
    BO#          NUMBER,
    COL#         NUMBER,
    POS#         NUMBER,
    SEGCOL#      NUMBER,
    INTCOL#      NUMBER not null,
    LOGMNR_UID   NUMBER(22),
    LOGMNR_FLAGS NUMBER(22),
    constraint LOGMNR_ICOL$_PK
        primary key (LOGMNR_UID, OBJ#, INTCOL#)
)
/

create index LOGMNR_I1ICOL$
    on LOGMNR_ICOL$ (LOGMNR_UID, OBJ#, INTCOL#)
/

create table LOGMNR_LOBFRAG$
(
    FRAGOBJ#     NUMBER,
    PARENTOBJ#   NUMBER,
    TABFRAGOBJ#  NUMBER,
    INDFRAGOBJ#  NUMBER,
    FRAG#        NUMBER not null,
    LOGMNR_UID   NUMBER(22),
    LOGMNR_FLAGS NUMBER(22),
    constraint LOGMNR_LOBFRAG$_PK
        primary key (LOGMNR_UID, FRAGOBJ#)
)
/

create index LOGMNR_I1LOBFRAG$
    on LOGMNR_LOBFRAG$ (LOGMNR_UID, FRAGOBJ#)
/

create table LOGMNR_INDPART$
(
    OBJ#         NUMBER,
    BO#          NUMBER,
    PART#        NUMBER,
    TS#          NUMBER not null,
    LOGMNR_UID   NUMBER(22),
    LOGMNR_FLAGS NUMBER(22),
    constraint LOGMNR_INDPART$_PK
        primary key (LOGMNR_UID, OBJ#, BO#)
)
/

create index LOGMNR_I1INDPART$
    on LOGMNR_INDPART$ (LOGMNR_UID, OBJ#, BO#)
/

create index LOGMNR_I2INDPART$
    on LOGMNR_INDPART$ (LOGMNR_UID, BO#)
/

create table LOGMNR_INDSUBPART$
(
    OBJ#         NUMBER(22),
    DATAOBJ#     NUMBER(22),
    POBJ#        NUMBER(22),
    SUBPART#     NUMBER(22),
    TS#          NUMBER(22) not null,
    LOGMNR_UID   NUMBER(22),
    LOGMNR_FLAGS NUMBER(22),
    constraint LOGMNR_INDSUBPART$_PK
        primary key (LOGMNR_UID, OBJ#, POBJ#)
)
/

create index LOGMNR_I1INDSUBPART$
    on LOGMNR_INDSUBPART$ (LOGMNR_UID, OBJ#, POBJ#)
/

create table LOGMNR_INDCOMPART$
(
    OBJ#         NUMBER,
    DATAOBJ#     NUMBER,
    BO#          NUMBER,
    PART#        NUMBER not null,
    LOGMNR_UID   NUMBER(22),
    LOGMNR_FLAGS NUMBER(22),
    constraint LOGMNR_INDCOMPART$_PK
        primary key (LOGMNR_UID, OBJ#)
)
/

create index LOGMNR_I1INDCOMPART$
    on LOGMNR_INDCOMPART$ (LOGMNR_UID, OBJ#)
/

create table LOGMNR_LOGMNR_BUILDLOG
(
    BUILD_DATE              VARCHAR2(20),
    DB_TXN_SCNBAS           NUMBER,
    DB_TXN_SCNWRP           NUMBER,
    CURRENT_BUILD_STATE     NUMBER,
    COMPLETION_STATUS       NUMBER,
    MARKED_LOG_FILE_LOW_SCN NUMBER,
    INITIAL_XID             VARCHAR2(22) not null,
    LOGMNR_UID              NUMBER(22),
    LOGMNR_FLAGS            NUMBER(22),
    CDB_XID                 VARCHAR2(22),
    SPARE1                  NUMBER,
    SPARE2                  VARCHAR2(4000),
    constraint LOGMNR_LOGMNR_BUILDLOG_PK
        primary key (LOGMNR_UID, INITIAL_XID)
)
/

create index LOGMNR_I1LOGMNR_BUILDLOG
    on LOGMNR_LOGMNR_BUILDLOG (LOGMNR_UID, INITIAL_XID)
/

create table LOGMNR_NTAB$
(
    COL#         NUMBER,
    INTCOL#      NUMBER,
    NTAB#        NUMBER,
    NAME         VARCHAR2(4000),
    OBJ#         NUMBER not null,
    LOGMNR_UID   NUMBER(22),
    LOGMNR_FLAGS NUMBER(22),
    constraint LOGMNR_NTAB$_PK
        primary key (LOGMNR_UID, OBJ#, INTCOL#)
)
/

create index LOGMNR_I1NTAB$
    on LOGMNR_NTAB$ (LOGMNR_UID, OBJ#, INTCOL#)
/

create index LOGMNR_I2NTAB$
    on LOGMNR_NTAB$ (LOGMNR_UID, NTAB#)
/

create table LOGMNR_OPQTYPE$
(
    INTCOL#      NUMBER not null,
    TYPE         NUMBER,
    FLAGS        NUMBER,
    LOBCOL       NUMBER,
    OBJCOL       NUMBER,
    EXTRACOL     NUMBER,
    SCHEMAOID    RAW(16),
    ELEMNUM      NUMBER,
    SCHEMAURL    VARCHAR2(4000),
    OBJ#         NUMBER not null,
    LOGMNR_UID   NUMBER(22),
    LOGMNR_FLAGS NUMBER(22),
    constraint LOGMNR_OPQTYPE$_PK
        primary key (LOGMNR_UID, OBJ#, INTCOL#)
)
/

create index LOGMNR_I1OPQTYPE$
    on LOGMNR_OPQTYPE$ (LOGMNR_UID, OBJ#, INTCOL#)
/

create table LOGMNR_SUBCOLTYPE$
(
    INTCOL#      NUMBER  not null,
    TOID         RAW(16) not null,
    VERSION#     NUMBER  not null,
    INTCOLS      NUMBER,
    INTCOL#S     RAW(2000),
    FLAGS        NUMBER,
    SYNOBJ#      NUMBER,
    OBJ#         NUMBER  not null,
    LOGMNR_UID   NUMBER(22),
    LOGMNR_FLAGS NUMBER(22),
    constraint LOGMNR_SUBCOLTYPE$_PK
        primary key (LOGMNR_UID, OBJ#, INTCOL#, TOID)
)
/

create index LOGMNR_I1SUBCOLTYPE$
    on LOGMNR_SUBCOLTYPE$ (LOGMNR_UID, OBJ#, INTCOL#, TOID)
/

create table LOGMNR_KOPM$
(
    LENGTH       NUMBER,
    METADATA     RAW(255),
    NAME         VARCHAR2(384) not null,
    LOGMNR_UID   NUMBER(22),
    LOGMNR_FLAGS NUMBER(22),
    constraint LOGMNR_KOPM$_PK
        primary key (LOGMNR_UID, NAME)
)
/

create index LOGMNR_I1KOPM$
    on LOGMNR_KOPM$ (LOGMNR_UID, NAME)
/

create table LOGMNR_PROPS$
(
    VALUE$       VARCHAR2(4000),
    COMMENT$     VARCHAR2(4000),
    NAME         VARCHAR2(384) not null,
    LOGMNR_UID   NUMBER(22),
    LOGMNR_FLAGS NUMBER(22),
    constraint LOGMNR_PROPS$_PK
        primary key (LOGMNR_UID, NAME)
)
/

create index LOGMNR_I1PROPS$
    on LOGMNR_PROPS$ (LOGMNR_UID, NAME)
/

create table LOGMNR_ENC$
(
    OBJ#         NUMBER,
    OWNER#       NUMBER,
    ENCALG       NUMBER,
    INTALG       NUMBER,
    COLKLC       RAW(2000),
    KLCLEN       NUMBER,
    FLAG         NUMBER,
    MKEYID       VARCHAR2(192) not null,
    LOGMNR_UID   NUMBER(22),
    LOGMNR_FLAGS NUMBER(22),
    constraint LOGMNR_ENC$_PK
        primary key (LOGMNR_UID, OBJ#, OWNER#)
)
/

create index LOGMNR_I1ENC$
    on LOGMNR_ENC$ (LOGMNR_UID, OBJ#, OWNER#)
/

create table LOGMNR_REFCON$
(
    COL#         NUMBER,
    INTCOL#      NUMBER,
    REFTYP       NUMBER,
    STABID       RAW(16),
    EXPCTOID     RAW(16),
    OBJ#         NUMBER not null,
    LOGMNR_UID   NUMBER(22),
    LOGMNR_FLAGS NUMBER(22),
    constraint LOGMNR_REFCON$_PK
        primary key (LOGMNR_UID, OBJ#, INTCOL#)
)
/

create index LOGMNR_I1REFCON$
    on LOGMNR_REFCON$ (LOGMNR_UID, OBJ#, INTCOL#)
/

create table LOGMNR_IDNSEQ$
(
    OBJ#         NUMBER not null,
    INTCOL#      NUMBER not null,
    SEQOBJ#      NUMBER not null,
    STARTWITH    NUMBER not null,
    LOGMNR_UID   NUMBER(22),
    LOGMNR_FLAGS NUMBER(22),
    constraint LOGMNR_IDNSEQ$_PK
        primary key (LOGMNR_UID, OBJ#, INTCOL#)
)
/

create index LOGMNR_I1IDNSEQ$
    on LOGMNR_IDNSEQ$ (LOGMNR_UID, OBJ#, INTCOL#)
/

create index LOGMNR_I2IDNSEQ$
    on LOGMNR_IDNSEQ$ (LOGMNR_UID, SEQOBJ#)
/

create table LOGMNR_PARTOBJ$
(
    PARTTYPE     NUMBER,
    PARTCNT      NUMBER,
    PARTKEYCOLS  NUMBER,
    FLAGS        NUMBER,
    DEFTS#       NUMBER,
    DEFPCTFREE   NUMBER,
    DEFPCTUSED   NUMBER,
    DEFPCTTHRES  NUMBER,
    DEFINITRANS  NUMBER,
    DEFMAXTRANS  NUMBER,
    DEFTINIEXTS  NUMBER,
    DEFEXTSIZE   NUMBER,
    DEFMINEXTS   NUMBER,
    DEFMAXEXTS   NUMBER,
    DEFEXTPCT    NUMBER,
    DEFLISTS     NUMBER,
    DEFGROUPS    NUMBER,
    DEFLOGGING   NUMBER,
    SPARE1       NUMBER,
    SPARE2       NUMBER,
    SPARE3       NUMBER,
    DEFINCLCOL   NUMBER,
    PARAMETERS   VARCHAR2(3000),
    OBJ#         NUMBER not null,
    LOGMNR_UID   NUMBER(22),
    LOGMNR_FLAGS NUMBER(22),
    constraint LOGMNR_PARTOBJ$_PK
        primary key (LOGMNR_UID, OBJ#)
)
/

create index LOGMNR_I1PARTOBJ$
    on LOGMNR_PARTOBJ$ (LOGMNR_UID, OBJ#)
/

create table LOGMNRP_CTAS_PART_MAP
(
    LOGMNR_UID NUMBER not null,
    BASEOBJ#   NUMBER not null,
    BASEOBJV#  NUMBER not null,
    KEYOBJ#    NUMBER not null,
    PART#      NUMBER not null,
    SPARE1     NUMBER,
    SPARE2     NUMBER,
    SPARE3     VARCHAR2(1000),
    constraint LOGMNRP_CTAS_PART_MAP_PK
        primary key (LOGMNR_UID, BASEOBJV#, KEYOBJ#)
)
/

create index LOGMNRP_CTAS_PART_MAP_I
    on LOGMNRP_CTAS_PART_MAP (LOGMNR_UID, BASEOBJ#, BASEOBJV#, PART#)
/

create table LOGMNR_SHARD_TS
(
    LOGMNR_UID      NUMBER       not null,
    TABLESPACE_NAME VARCHAR2(90) not null,
    CHUNK_NUMBER    NUMBER       not null,
    START_SCNBAS    NUMBER,
    START_SCNWRP    NUMBER,
    constraint LOGMNR_SHARD_TS_PK
        primary key (LOGMNR_UID, TABLESPACE_NAME)
)
/

create index LOGMNR_I1SHARD_TS
    on LOGMNR_SHARD_TS (LOGMNR_UID, TABLESPACE_NAME)
/

create table SCHEDULER_PROGRAM_ARGS_TBL
(
    OWNER                 VARCHAR2(128) not null,
    PROGRAM_NAME          VARCHAR2(128) not null,
    ARGUMENT_NAME         VARCHAR2(128),
    ARGUMENT_POSITION     NUMBER        not null,
    ARGUMENT_TYPE         VARCHAR2(257),
    METADATA_ATTRIBUTE    VARCHAR2(19),
    DEFAULT_VALUE         VARCHAR2(4000),
    DEFAULT_ANYDATA_VALUE SYS.ANYDATA,
    OUT_ARGUMENT          VARCHAR2(5)
)
/

create table SCHEDULER_JOB_ARGS_TBL
(
    OWNER             VARCHAR2(128),
    JOB_NAME          VARCHAR2(128),
    ARGUMENT_NAME     VARCHAR2(128),
    ARGUMENT_POSITION NUMBER,
    ARGUMENT_TYPE     VARCHAR2(257),
    VALUE             VARCHAR2(4000),
    ANYDATA_VALUE     SYS.ANYDATA,
    OUT_ARGUMENT      VARCHAR2(5)
)
/

create table LOGSTDBY$PARAMETERS
(
    NAME   VARCHAR2(30),
    VALUE  VARCHAR2(2000),
    TYPE   NUMBER,
    SCN    NUMBER,
    SPARE1 NUMBER,
    SPARE2 NUMBER,
    SPARE3 VARCHAR2(2000)
)
/

create table LOGSTDBY$EVENTS
(
    EVENT_TIME  TIMESTAMP(6) not null,
    CURRENT_SCN NUMBER,
    COMMIT_SCN  NUMBER,
    XIDUSN      NUMBER,
    XIDSLT      NUMBER,
    XIDSQN      NUMBER,
    ERRVAL      NUMBER,
    EVENT       VARCHAR2(2000),
    FULL_EVENT  CLOB,
    ERROR       VARCHAR2(2000),
    SPARE1      NUMBER,
    SPARE2      NUMBER,
    SPARE3      VARCHAR2(2000),
    CON_NAME    VARCHAR2(30),
    CON_ID      NUMBER
)
/

create index LOGSTDBY$EVENTS_IND
    on LOGSTDBY$EVENTS (EVENT_TIME)
/

create index LOGSTDBY$EVENTS_IND_SCN
    on LOGSTDBY$EVENTS (COMMIT_SCN)
/

create index LOGSTDBY$EVENTS_IND_XID
    on LOGSTDBY$EVENTS (XIDUSN, XIDSLT, XIDSQN)
/

create table LOGSTDBY$APPLY_PROGRESS
(
    XIDUSN      NUMBER,
    XIDSLT      NUMBER,
    XIDSQN      NUMBER,
    COMMIT_SCN  NUMBER,
    COMMIT_TIME DATE,
    SPARE1      NUMBER,
    SPARE2      NUMBER,
    SPARE3      VARCHAR2(2000)
)
/

create table LOGSTDBY$APPLY_MILESTONE
(
    SESSION_ID               NUMBER             not null,
    COMMIT_SCN               NUMBER             not null,
    COMMIT_TIME              DATE,
    SYNCH_SCN                NUMBER             not null,
    EPOCH                    NUMBER             not null,
    PROCESSED_SCN            NUMBER             not null,
    PROCESSED_TIME           DATE,
    FETCHLWM_SCN             NUMBER default (0) not null,
    SPARE1                   NUMBER,
    SPARE2                   NUMBER,
    SPARE3                   VARCHAR2(2000),
    FLAGS                    NUMBER,
    LWM_UPD_TIME             DATE,
    SPARE4                   NUMBER,
    SPARE5                   NUMBER,
    SPARE6                   NUMBER,
    SPARE7                   DATE,
    PTO_RECOVERY_SCN         NUMBER,
    PTO_RECOVERY_INCARNATION NUMBER
)
/

create table LOGSTDBY$SCN
(
    OBJ#    NUMBER,
    OBJNAME VARCHAR2(4000),
    SCHEMA  VARCHAR2(128),
    TYPE    VARCHAR2(20),
    SCN     NUMBER,
    SPARE1  NUMBER,
    SPARE2  NUMBER,
    SPARE3  VARCHAR2(2000)
)
/

create table LOGSTDBY$FLASHBACK_SCN
(
    PRIMARY_SCN  NUMBER not null
        primary key,
    PRIMARY_TIME DATE,
    STANDBY_SCN  NUMBER,
    STANDBY_TIME DATE,
    SPARE1       NUMBER,
    SPARE2       NUMBER,
    SPARE3       DATE
)
/

create table LOGSTDBY$PLSQL
(
    SESSION_ID   NUMBER,
    START_FINISH NUMBER,
    CALL_TEXT    CLOB,
    SPARE1       NUMBER,
    SPARE2       NUMBER,
    SPARE3       VARCHAR2(2000)
)
/

create table LOGSTDBY$SKIP_TRANSACTION
(
    XIDUSN     NUMBER,
    XIDSLT     NUMBER,
    XIDSQN     NUMBER,
    ACTIVE     NUMBER,
    COMMIT_SCN NUMBER,
    SPARE2     NUMBER,
    SPARE3     VARCHAR2(2000),
    CON_NAME   VARCHAR2(384)
)
/

create table LOGSTDBY$SKIP
(
    ERROR         NUMBER,
    STATEMENT_OPT VARCHAR2(128),
    SCHEMA        VARCHAR2(128),
    NAME          VARCHAR2(261),
    USE_LIKE      NUMBER,
    ESC           VARCHAR2(1),
    PROC          VARCHAR2(392),
    ACTIVE        NUMBER,
    SPARE1        NUMBER,
    SPARE2        NUMBER,
    SPARE3        VARCHAR2(2000)
)
/

create index LOGSTDBY$SKIP_IDX1
    on LOGSTDBY$SKIP (USE_LIKE, SCHEMA, NAME)
/

create index LOGSTDBY$SKIP_IDX2
    on LOGSTDBY$SKIP (STATEMENT_OPT)
/

create table LOGSTDBY$SKIP_SUPPORT
(
    ACTION NUMBER        not null,
    NAME   VARCHAR2(128) not null,
    NAME2  VARCHAR2(128),
    NAME3  VARCHAR2(128),
    NAME4  VARCHAR2(128),
    NAME5  VARCHAR2(128),
    REG    NUMBER,
    SPARE1 NUMBER,
    SPARE2 NUMBER,
    SPARE3 VARCHAR2(2000)
)
/

create index LOGSTDBY$SKIP_IND
    on LOGSTDBY$SKIP_SUPPORT (NAME, ACTION)
/

create table LOGSTDBY$HISTORY
(
    STREAM_SEQUENCE# NUMBER,
    LMNR_SID         NUMBER,
    DBID             NUMBER,
    FIRST_CHANGE#    NUMBER,
    LAST_CHANGE#     NUMBER,
    SOURCE           NUMBER,
    STATUS           NUMBER,
    FIRST_TIME       DATE,
    LAST_TIME        DATE,
    DGNAME           VARCHAR2(255),
    SPARE1           NUMBER,
    SPARE2           NUMBER,
    SPARE3           VARCHAR2(2000)
)
/

create table LOGSTDBY$EDS_TABLES
(
    OWNER               VARCHAR2(128) not null,
    TABLE_NAME          VARCHAR2(128) not null,
    SHADOW_TABLE_NAME   VARCHAR2(128),
    BASE_TRIGGER_NAME   VARCHAR2(128),
    SHADOW_TRIGGER_NAME VARCHAR2(128),
    DBLINK              VARCHAR2(255),
    FLAGS               NUMBER,
    STATE               VARCHAR2(255),
    OBJV                NUMBER,
    OBJ#                NUMBER,
    SOBJ#               NUMBER,
    CTIME               TIMESTAMP(6),
    SPARE1              NUMBER,
    SPARE2              VARCHAR2(255),
    SPARE3              NUMBER,
    MVIEW_NAME          VARCHAR2(128),
    MVIEW_LOG_NAME      VARCHAR2(128),
    MVIEW_TRIGGER_NAME  VARCHAR2(128),
    constraint LOGSTDBY$EDS_TABLES_PKEY
        primary key (OWNER, TABLE_NAME)
)
/

create table SQLPLUS_PRODUCT_PROFILE
(
    PRODUCT       VARCHAR2(30) not null,
    USERID        VARCHAR2(128),
    ATTRIBUTE     VARCHAR2(240),
    SCOPE         VARCHAR2(240),
    NUMERIC_VALUE NUMBER(15, 2),
    CHAR_VALUE    VARCHAR2(240),
    DATE_VALUE    DATE,
    LONG_VALUE    LONG
)
/

create table HELP
(
    TOPIC VARCHAR2(50) not null,
    SEQ   NUMBER       not null,
    INFO  VARCHAR2(80),
    constraint HELP_TOPIC_SEQ
        primary key (TOPIC, SEQ)
)
/

create table PRZEDSZKOLANKA
(
    IDPRAC       NUMBER default SYSTEM.PRZEDSZKOLANKA_SEQ.nextval not null
        constraint PRZEDSZKOLANKA_PK
            primary key,
    NAZWAGRUPY   NUMBER,
    HOS_IDHOS    NUMBER                                           not null,
    IMIE         VARCHAR2(25),
    NAZWISKO     VARCHAR2(50),
    KWALIFIKACJE VARCHAR2(100),
    PLACA        FLOAT
)
/

create table FESTYN
(
    IDFESTYNU           NUMBER default "SYSTEM"."FESTYN_SEQ"."NEXTVAL" not null
        constraint FESTYN_PK
            primary key,
    GRUPAWYSTEPUJACA    NUMBER,
    OSOBAODPOWIEDZIALNA NUMBER,
    TERMINWYDARZENA     DATE                                           not null,
    HASLO               VARCHAR2(25 char)
)
/

create table GRUPAPRZEDSZKOLNA
(
    IDGRUPY    NUMBER default "SYSTEM"."GRUPA_SEQ"."NEXTVAL" not null
        constraint GRUPAPRZEDSZKOLNA_PK
            primary key,
    SALA       NUMBER,
    NAZWA      VARCHAR2(25 char),
    WIEKDZIECI NUMBER,
    IDPRAC     NUMBER                                        not null
)
/

create table HOSPITACJA
(
    IDHOSPITACJI   NUMBER default "SYSTEM"."HOSPITACJA_SEQ"."NEXTVAL" not null
        constraint HOSPITACJA_PK
            primary key,
    TERMIN         DATE,
    KTONADZOROWANY NUMBER,
    KTONADZORUJE   BLOB
)
/

create table POSILEK
(
    IDPOSILKU      NUMBER default "SYSTEM"."POSILEK_SEQ"."NEXTVAL" not null
        constraint POSILEK_PK
            primary key,
    NAZWA          VARCHAR2(50 char),
    GODZROZWOZENIA DATE,
    DIETA          VARCHAR2(20 char)
)
/

create table SEKRETARKA
(
    IDPRAC               NUMBER default "SYSTEM"."SEKRETARKA_SEQ"."NEXTVAL" not null
        constraint SEKRETARKA_PK
            primary key,
    GODZROZPOCZECIAPRACY DATE,
    GODZZAKONCZENIAPRACY DATE,
    IMIE                 VARCHAR2(25),
    NAZWISKO             VARCHAR2(50),
    KWALIFIKACJE         VARCHAR2(100),
    PLACA                FLOAT
)
/

create table ZAJECIADODATKOWE
(
    IDZAJECIA       NUMBER default "SYSTEM"."ZAJDOD_SEQ"."NEXTVAL" not null
        constraint ZAJECIADODATKOWE_PK
            primary key,
    RODZAJ          VARCHAR2(50 char),
    DATAPROWADZENIA DATE,
    OPLATY          NUMBER,
    CZASTYGODNIOWO  NUMBER,
    DLAKOGO         NUMBER
)
/

create table OPLATA
(
    IDOPLATY                   NUMBER default system.OPLATA_SEQ.nextval not null
        constraint OPLATA_PK
            primary key,
    WIELKOSC                   NUMBER,
    PRZEDMIOTOPLATY            VARCHAR2(100 char),
    CZESTOSC                   VARCHAR2(25 char),
    ZAJECIADODATKOWE_IDZAJECIA NUMBER                                   not null
        constraint OPLATA_ZAJECIA_FK
            references ZAJECIADODATKOWE,
    POMOCDYDAKTYCZNA_IDPOMOCY  NUMBER,
    POMOCDYDAKTYCZNA_IDPRAC    NUMBER,
    POMOCDYDAKTYCZNA_IDGRUPY   NUMBER
)
/

create unique index OPLATA__IDX
    on OPLATA (POMOCDYDAKTYCZNA_IDPOMOCY, POMOCDYDAKTYCZNA_IDPRAC, POMOCDYDAKTYCZNA_IDGRUPY)
/

create unique index OPLATA__IDXV1
    on OPLATA (ZAJECIADODATKOWE_IDZAJECIA)
/

create table POMOCDYDAKTYCZNA
(
    IDPOMOCY                  NUMBER default system.POMOCDYD_SEQ.nextval not null,
    RODZAJ                    VARCHAR2(50 char),
    DODATKOWEOPLATY           NUMBER,
    GRUPADOCELOWA             NUMBER,
    OSOBAODPOWIEDZIALNA       NUMBER,
    OPLATA_IDOPLATY           NUMBER
        constraint POMOC_OPLATA_FK
            references OPLATA,
    PRZEDSZKOLANKA_IDPRAC     NUMBER                                     not null
        constraint POMOC_PRZEDSZKOLANKA_FK
            references PRZEDSZKOLANKA,
    GRUPAPRZEDSZKOLNA_IDGRUPY NUMBER                                     not null
        constraint POMOC_GRUPA_FK
            references GRUPAPRZEDSZKOLNA,
    constraint POMOCDYDAKTYCZNA_PK
        primary key (IDPOMOCY, PRZEDSZKOLANKA_IDPRAC, GRUPAPRZEDSZKOLNA_IDGRUPY)
)
/

alter table OPLATA
    add constraint OPLATA_POMOC_FK
        foreign key (POMOCDYDAKTYCZNA_IDPOMOCY, POMOCDYDAKTYCZNA_IDPRAC,
                     POMOCDYDAKTYCZNA_IDGRUPY) references POMOCDYDAKTYCZNA
/

create unique index POMOCDYDAKTYCZNA__IDX
    on POMOCDYDAKTYCZNA (OPLATA_IDOPLATY)
/

create table ZEBRANIEZRODZICAMI
(
    IDZEBRANIA                  NUMBER default "SYSTEM"."ZEBRANIE_SEQ"."NEXTVAL" not null,
    DATA                        DATE,
    GRUPA                       NUMBER,
    MIEJSCA                     NUMBER,
    PROWADZACYZEBRANIE          NUMBER,
    CZYOBOWIAZKOWE              CHAR(5),
    PRZEDSZKOLANKA_IDHOSPITACJI NUMBER                                           not null,
    constraint ZEBRANIEZRODZICAMI_PK
        primary key (IDZEBRANIA, PRZEDSZKOLANKA_IDHOSPITACJI)
)
/

create table DZIECKO
(
    IDDZIECKA                 NUMBER default "SYSTEM"."DZIECKO_SEQ"."NEXTVAL" not null,
    IMIE                      VARCHAR2(15 char)                               not null,
    NAZWISKO                  VARCHAR2(25 char)                               not null,
    DATAURODZENIA             DATE,
    GRUPAPRZEDSZKOLNA_IDGRUPY NUMBER                                          not null,
    POSILEK_IDPOSILKU         NUMBER                                          not null,
    constraint DZIECKO_PK
        primary key (IDDZIECKA, GRUPAPRZEDSZKOLNA_IDGRUPY, POSILEK_IDPOSILKU)
)
/

create table ZESPOLY
(
    ID_ZESP     NUMBER(19) not null
        primary key,
    ADRES       VARCHAR2(255 char),
    LICZBA_PRAC NUMBER(19),
    NAZWA       VARCHAR2(255 char)
)
/

create or replace view MVIEW_WORKLOAD as
select a.collectionid# as workloadid,
       a.collecttime   as import_time,
       a.queryid#      as queryid,
       a.application,
       a.cardinality,
       a.resultsize,
       a.qdate         as lastuse,
       a.frequency,
       a.uname         as owner,
       a.priority,
       a.sql_text      as query,
       a.exec_time     as responsetime
from SYSTEM.MVIEW$_ADV_WORKLOAD A,
     SYSTEM.MVIEW$_ADV_LOG B,
     ALL_USERS D
WHERE a.collectionid# = b.runid#
  AND b.uname = d.username
  AND d.user_id = userenv('SCHEMAID')
/

comment on table MVIEW_WORKLOAD is 'This view gives DBA access to shared workload'
/

create or replace view MVIEW_FILTER as
select a.filterid#                                               as filterid,
       a.subfilternum#                                           as subfilternum,
       decode(a.subfiltertype, 1, 'APPLICATION', 2, 'CARDINALITY', 3, 'LASTUSE',
              4, 'FREQUENCY', 5, 'USER', 6, 'PRIORITY', 7, 'BASETABLE',
              8, 'RESPONSETIME', 9, 'COLLECTIONID', 10, 'TRACENAME',
              11, 'SCHEMA', 'UNKNOWN')                           AS subfiltertype,
       a.str_value,
       to_number(decode(a.num_value1, -999, NULL, a.num_value1)) AS num_value1,
       to_number(decode(a.num_value2, -999, NULL, a.num_value2)) AS num_value2,
       a.date_value1,
       a.date_value2
from system.mview$_adv_filter a,
     system.mview$_adv_log b,
     ALL_USERS u
WHERE a.filterid# = b.runid#
  AND b.uname = u.username
  AND u.user_id = userenv('SCHEMAID')
/

comment on table MVIEW_FILTER is 'Workload filter records'
/

create or replace view MVIEW_LOG as
select m.runid#                       as id,
       m.filterid#                    as filterid,
       m.run_begin,
       m.run_end,
       decode(m.run_type, 1, 'EVALUATE', 2, 'EVALUATE_W', 3, 'RECOMMEND',
              4, 'RECOMMEND_W', 5, 'VALIDATE', 6, 'WORKLOAD',
              7, 'FILTER', 'UNKNOWN') AS type,
       decode(m.status, 0, 'UNUSED', 1, 'CANCELLED', 2, 'IN_PROGRESS', 3, 'COMPLETED',
              4, 'ERROR', 'UNKNOWN')  AS status,
       m.message,
       m.completed,
       m.total,
       m.error_code
from system.mview$_adv_log m,
     all_users u
where m.uname = u.username
  and u.user_id = userenv('SCHEMAID')
/

comment on table MVIEW_LOG is 'Advisor session log'
/

create or replace view MVIEW_FILTERINSTANCE as
select a.runid#                                                  as runid,
       a.filterid#                                               as filterid,
       a.subfilternum#                                           as subfilternum,
       decode(a.subfiltertype, 1, 'APPLICATION', 2, 'CARDINALITY', 3, 'LASTUSE',
              4, 'FREQUENCY', 5, 'USER', 6, 'PRIORITY', 7, 'BASETABLE',
              8, 'RESPONSETIME', 9, 'COLLECTIONID', 10, 'TRACENAME',
              11, 'SCHEMA', 'UNKNOWN')                           AS subfiltertype,
       a.str_value,
       to_number(decode(a.num_value1, -999, NULL, a.num_value1)) AS num_value1,
       to_number(decode(a.num_value2, -999, NULL, a.num_value2)) AS num_value2,
       a.date_value1,
       a.date_value2
from system.mview$_adv_filterinstance a
/

comment on table MVIEW_FILTERINSTANCE is 'Workload filter instance records'
/

create or replace view MVIEW_RECOMMENDATIONS as
select t1.runid#      as runid,
       t1.from_clause as all_tables,
       fact_tables,
       grouping_levels,
       query_text,
       rank#          as recommendation_number,
       action_type    as recommended_action,
       summary_owner  as mview_owner,
       summary_name   as mview_name,
       storage_in_bytes,
       pct_performance_gain,
       benefit_to_cost_ratio
from SYSTEM.MVIEW$_ADV_OUTPUT t1,
     SYSTEM.MVIEW$_ADV_LOG t2,
     ALL_USERS u
where t1.runid# = t2.runid#
  and u.username = t2.uname
  and u.user_id = userenv('SCHEMAID')
  and t1.output_type = 0
order by t1.rank#
/

comment on table MVIEW_RECOMMENDATIONS is 'This view gives DBA access to summary recommendations'
/

create or replace view MVIEW_EVALUATIONS as
select t1.runid#     as runid,
       summary_owner AS mview_owner,
       summary_name  AS mview_name,
       rank#         as rank,
       storage_in_bytes,
       frequency,
       cumulative_benefit,
       benefit_to_cost_ratio
from SYSTEM.MVIEW$_ADV_OUTPUT t1,
     SYSTEM.MVIEW$_ADV_LOG t2,
     ALL_USERS u
where t1.runid# = t2.runid#
  and u.username = t2.uname
  and u.user_id = userenv('SCHEMAID')
  and t1.output_type = 1
order by t1.rank#
/

comment on table MVIEW_EVALUATIONS is 'This view gives DBA access to summary evaluation output'
/

create or replace view MVIEW_EXCEPTIONS as
select t1.runid# as runid,
       owner,
       table_name,
       dimension_name,
       relationship,
       bad_rowid
from SYSTEM.MVIEW$_ADV_EXCEPTIONS t1,
     SYSTEM.MVIEW$_ADV_LOG t2,
     ALL_USERS u
where t1.runid# = t2.runid#
  and u.username = t2.uname
  and u.user_id = userenv('SCHEMAID')
/

comment on table MVIEW_EXCEPTIONS is 'This view gives DBA access to dimension validation results'
/

create or replace view SCHEDULER_PROGRAM_ARGS as
SELECT "OWNER",
       "PROGRAM_NAME",
       "ARGUMENT_NAME",
       "ARGUMENT_POSITION",
       "ARGUMENT_TYPE",
       "METADATA_ATTRIBUTE",
       "DEFAULT_VALUE",
       "DEFAULT_ANYDATA_VALUE",
       "OUT_ARGUMENT"
FROM sys.all_scheduler_program_args
/

create or replace view SCHEDULER_JOB_ARGS as
SELECT "OWNER",
       "JOB_NAME",
       "ARGUMENT_NAME",
       "ARGUMENT_POSITION",
       "ARGUMENT_TYPE",
       "VALUE",
       "ANYDATA_VALUE",
       "OUT_ARGUMENT"
FROM sys.all_scheduler_job_args
/

create or replace view PRODUCT_PRIVS as
SELECT PRODUCT,
       USERID,
       ATTRIBUTE,
       SCOPE,
       NUMERIC_VALUE,
       CHAR_VALUE,
       DATE_VALUE,
       LONG_VALUE
FROM SQLPLUS_PRODUCT_PROFILE
WHERE USERID = 'PUBLIC'
   OR USERID LIKE SYS_CONTEXT('USERENV', 'CURRENT_USER')
/

create or replace PACKAGE Zmienne IS
    vLicznik NUMBER;
    PROCEDURE ZwiekszLicznik;
    PROCEDURE ZmniejszLicznik;
    FUNCTION PokazLicznik RETURN NUMBER;
END Zmienne;
/

create or replace PACKAGE BODY Zmienne IS
    PROCEDURE ZwiekszLicznik IS
    BEGIN
        vLicznik := vLicznik + 1;
        DBMS_OUTPUT.PUT_LINE('Zwiekszono');
    END ZwiekszLicznik;

    PROCEDURE ZmniejszLicznik IS
    BEGIN
        vLicznik := vLicznik - 1;
        DBMS_OUTPUT.PUT_LINE('Zmniejszono');
    END ZmniejszLicznik;

    FUNCTION PokazLicznik
        RETURN NUMBER IS
        licznik NUMBER;
    BEGIN
        RETURN vLicznik;
    END PokazLicznik;
    BEGIN
    vLicznik := 1;
    DBMS_OUTPUT.PUT_LINE('Zainicjalizowano');
END Zmienne;
/

create or replace PACKAGE IntZespoly IS
    PROCEDURE DodajZespol(idZespolu NUMBER);
    PROCEDURE UsunZespolID(idZespolu NUMBER);
    PROCEDURE UsunZespolNazwa(nazwaZespolu VARCHAR);
    PROCEDURE Modyfikuj(idZespolu NUMBER, nazwaZespolu VARCHAR, adresZespolu VARCHAR);
    FUNCTION PodajID(nazwaZespolu VARCHAR) RETURN NUMBER;
    FUNCTION PodajNazwe(numerZespolu NUMBER) RETURN VARCHAR2;
    FUNCTION PodajAdres(idZespolu NUMBER) RETURN VARCHAR2;
END IntZespoly;
/

create or replace PACKAGE BODY IntZespoly IS

    PROCEDURE DodajZespol(idZespolu NUMBER) IS
        exPowielenieID EXCEPTION;
        PRAGMA EXCEPTION_INIT (exPowielenieID, -00001);
    BEGIN
        INSERT INTO Zespoly (id_zesp) VALUES (idZespolu);

        IF SQL%FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Dodano zespol ' || idZespolu);
        ELSE
            DBMS_OUTPUT.PUT_LINE('Nie mozna dodac zespolu');
        END IF;

    EXCEPTION
        WHEN exPowielenieId THEN
            DBMS_OUTPUT.PUT_LINE('Zespol o takim ID JUŻ ISTNIEJE');
    END DodajZespol;

    PROCEDURE UsunZespolID(idZespolu NUMBER) IS
        exNiepoprawnyZespolId EXCEPTION;
    BEGIN
        DELETE FROM Zespoly WHERE id_zesp = idZespolu;

        IF SQL%FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Ususnieto zespol ' || idZespolu);
        ELSE
            RAISE exNiepoprawnyZespolID;
        END IF;

    EXCEPTION
        WHEN exNiepoprawnyZespolId THEN
            DBMS_OUTPUT.PUT_LINE('Zespol o takim ID JUŻ NIE ISTNIEJE!');
    END UsunZespolID;

    PROCEDURE UsunZespolNazwa(nazwaZespolu VARCHAR) IS
        exNiepoprawnyZespolNazwa EXCEPTION;
    BEGIN
        DELETE FROM Zespoly WHERE nazwa = nazwaZespolu;

        IF SQL%FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Ususnieto zespol ' || nazwaZespolu);
        ELSE
            RAISE exNiepoprawnyZespolNazwa;
        END IF;

    EXCEPTION
        WHEN exNiepoprawnyZespolNazwa THEN
            DBMS_OUTPUT.PUT_LINE('Zespol o takiej nazwie NIE ISTNIEJE!');
    END UsunZespolNazwa;

    PROCEDURE Modyfikuj(idZespolu NUMBER, nazwaZespolu VARCHAR, adresZespolu VARCHAR) IS
        exNiepoprawnyZespolNazwaAdres EXCEPTION;
    BEGIN
        UPDATE Zespoly
        SET nazwa = nazwaZespolu,
            adres = adresZespolu
        WHERE id_zesp = idZespolu;

        IF SQL%FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Zmodyfikowano zespol ' || idZespolu);
        ELSE
            RAISE exNiepoprawnyZespolNazwaAdres;
        END IF;
    EXCEPTION
        WHEN exNiepoprawnyZespolNazwaAdres THEN
            DBMS_OUTPUT.PUT_LINE('Podano niepoprawny parametr: id, adres lub nazwe!');
    END Modyfikuj;

    FUNCTION PodajID(nazwaZespolu VARCHAR)
        RETURN NUMBER IS
        idZespolu NUMBER;
    BEGIN
        SELECT id_zesp
        INTO idZespolu
        FROM zespoly
        WHERE nazwa = nazwaZespolu;
        RETURN idZespolu;

    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Nieznana nazwa zespolu!');
            RETURN NULL;
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Nieznany wyjatek!');
    END PodajID;

    FUNCTION PodajNazwe(numerZespolu NUMBER)
        RETURN VARCHAR2 IS
        nazwaZespolu VARCHAR2(1000);
    BEGIN
        SELECT nazwa
        INTO nazwaZespolu
        FROM zespoly
        WHERE id_zesp = numerZespolu;
        RETURN nazwaZespolu;

    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Nieznane ID!');
            RETURN NULL;
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Nieznany wyjatek!');
    END PodajNazwe;

    FUNCTION PodajAdres(idZespolu NUMBER)
        RETURN VARCHAR2 IS
        adresZespolu VARCHAR2(1000);
    BEGIN
        SELECT adres
        INTO adresZespolu
        FROM zespoly
        WHERE id_zesp = idZespolu;
        RETURN adresZespolu;

    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Nieznane ID!');
            RETURN NULL;
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Nieznany wyjatek!');
    END PodajAdres;
END IntZespoly;
/

create or replace FUNCTION LOGMNR$TAB_GG_TABF_PUBLIC wrapped
    a000000
    1
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    8
    22 e 181
    PRVt0FiLRPgjIhZlCZcQhnOHXQ4wgzJp2UhGfHSKrQ843hKm3gKD9swoFAS4jVcW0CsSl7W1
    6 banVWdjktOVM18XyRjY4ZM5eRdquSp0ZdfHl3KJPBYqPi9LXIwum30Qh7ymgO+zTKj+R1N2
    nSs/
TPnknfYLwcUdAfBryDQGEIMisMuE9XT5ix3sudhHa5tLJRjsBIDKlqL7zk2CH0C1NRAZ
XrP7WgavVdNS3Yikz88VupZG21hTuAGspJBgCagmNWIwi9pgCIWP3rxF4p+uMps/
ABEg+MBP
6Iykm62kO6hWhVHJXkfKF/
jrQFjYBTTzatr1VTcOXt/
AFuagDR7isNtb//
lnXh8TXyAFWyCT
ubv6GXL0aM0PP/
t+DfSl
/

create or replace FUNCTION LOGMNR$COL_GG_TABF_PUBLIC wrapped
    a000000
    1
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    8
    237 185
    nkvJV1w6wH1y7mRApzf9mGuebNUwgxDILkhGfHQCmP8+Vi4fyqh3SG1Fyq+pCts1OlgnK761
    YuzKBA4JE5DNwZzBIF/
Y4ZM5eUlquVyTkOg+AodK3vQJt9NLvPITXbP42O37gO+zKr4BQEJk
ypwrP1U/
Pf6MLZONN8LUaVqHCN87T14HqHs5taX7LhLXQ2lCVBE1Ll8dyB9CDOlbyvQS/
lrb
+0n1pQi9IJAWySL85ChAqnTaqFJm0YeToD4lZ8UUPQqIZNoX0x73WK9OzsmdBrvEC97iduxe
PEXVkxF6xklPod6yOGBvW7DAFMBgf+LajDLVKOAwB2EAiKCXYMuTUTtMYYkCFFf4sj1rCpsj
TLth6TSru530aM2HP6bEbm3m
/

create or replace FUNCTION LOGMNR$SEQ_GG_TABF_PUBLIC wrapped
    a000000
    1
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    8
    249 181
    ++dDv/
cHZatK7/
vHG9lvR8DQCpYwg/
D3AEhqfHQC2h6ONoOvOeHvTNX1S5GDyajM4j8vkSVz
IMw+LbYS3goujprvmrB/
LUpdBF8TVvjEqZpC7MCKPXWcGnTeL7ja8C2tcOdjOpRXkwL5NmPJ
B0KqMvwepdiQDY7HUDQrBddQC1lBqHGEogWkwRJ3+2+jUC+Gpo5GTazIWS0V551NkSl3+h0W
BhkPglLbvQDPzxWTnmu4ZuJIlTiNwTf1R0WxghyyKFjES9CJsCrGT8Fn7prlF4Mr5kx1YBGf
5xaODtRnmVJgb65RlKbAN9+Xxf2QnQjKQL99RZAgsEwGVKNfx9lFKwHLGUwzjOxmIAXXYd/
Z
+L9osPQJjZYkNrD0pQ==
/

create or replace FUNCTION LOGMNR$KEY_GG_TABF_PUBLIC wrapped
    a000000
    1
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    8
    2 a0 1 a1
    3 O4I5hDO715d8A2tqbxMCflFW0owg/
D319xqfC9Grfg+K6yE71zMvtNS45AyRXBk77WpI5v4
nUSfE2lbvUYgk3JHySIe28XxC3xIYYpPGQjxwa3GzPw0FN5aN6kerQQTHUBp29Dd+vLSgBaC
2pAFrq059ZvN0ZPN11XG/
2RuDY7HaTQu/
QffhnY8rVlNxpFmbkVidwtZQahx5qIFu9Uww/
tv
o1AvhjaORi898/
KiPtOqv7LpsPFbyNuMnZEG48cxtZuesMBJFP/
bKtgU2DN69xiT8Pxf+N2n
g0D2ximYzZqwY/
4dBQj9dyQDuXRFo40hdqtWw0L96zV6723aQ8Xp0cqBaZj2wWTI4+6Ikry9
zY0Mdm3bV8TYqsOa+zT4fnikGO0eYbTFHEiW9QUbl/
UwzuERwk8p
/

create or replace FUNCTION LOGMNR$GSBA_GG_TABF_PUBLIC wrapped
    a000000
    1
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    8
    206 171
    6 gxi/
CQwK1I5Rfwuw/
SXrOGpVRYwgwLI1yfbfHRGEjNe54OE4QwRZCoA20oG536tzgcBrj+1
xE3tE8jIhAoTlUdUmkdYMmZycO1SdiJZwwt/
6BrM1wHXl/
E5+3Ip2NXzC9j8v4+KjkD9d5AT
p05eEsEWjU1CBTMSpjZZrXzbgFl9QNnQ+zJGjSug21f76ajs78m6anxz7vFcTcem6XpAgKjc
EXzd/
ijP8qiOqwblTfnXcRslJn3MljD02u+5fh9NBctOmnaw/
tOjRCFPUhY8I9gCoMptjG7U
rHEIFzHOFyxBEdulRGq4ngSgcm7l2yOdSHgNM8rO2vUH4gozvJoLE1S8GBBzG/
wrvHPhACQ/
2w==
/

create or replace FUNCTION LOGMNR$USER_GG_TABF_PUBLIC wrapped
    a000000
    1
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    abcd
    8
    272 191
    Vc+i+Mga8m7/
BSlvXl0J+GmParYwg/
D319wCfC8CTE6Ot30G8QRR0WzR8ohw8Z/
y6DuL4pL5
IDjZApnCaZgeKa1OTjLj2B2tOYZg2ZuOAPKrxo7DeBqtn0Ahw0tubS36jP4xc+d2eQebdJMD
c/
U0JxdiAl0qIPzsltBjeoGAEsxUk0aubCMJmysgc2d8ojil6ixQ37D7RA0HWMkh27QdOuXF
vSwuufDunMMT8Hue9dvy4vRXj+PhuyylJSukStsxyIb234EahCXyrjDlnzbC91eoU7v5sb4D
OvtDmggQCEViyhFXwspP9P0dOObin8JENsDJeFZYr/
oVAgSHa97LRKvNZgd+f//
XHlpEAOP4
rNm5mF0wTCABbb7tc5c7uo09M+79i7en8g==
/

create or replace PROCEDURE NowyPracownik(nazwisko IN VARCHAR,
                                          nazwa_zespolu IN VARCHAR,
                                          nazwisko_szefa IN VARCHAR,
                                          placa_pod IN NUMBER,
                                          data_zatrudnienia IN DATE DEFAULT CURRENT_DATE,
                                          etat IN VARCHAR := 'STAZYSTA') IS
BEGIN
    INSERT INTO Pracownicy (id_prac, nazwisko, etat, id_szefa, zatrudniony, placa_pod, id_zesp)
    VALUES (300, nazwisko, etat, 110, data_zatrudnienia, placa_pod, 40);
END NowyPracownik;

EXEC NowyPracownik('DYNDALSKI', 'ALGORYTMY', 'BLAZEWICZ', 250);
SELECT *
FROM Pracownicy
WHERE nazwisko = 'DYNDALSKI';


/*zadanie 2*/
CREATE OR REPLACE FUNCTION PlacaNetto(placaBrutto IN NUMBER,
                                      podatek IN NUMBER := 20)
    RETURN NUMBER IS
    netto NUMBER;
BEGIN
    netto := (100 - podatek) / 100 * placaBrutto;
    RETURN netto;
END PlacaNetto;

SELECT nazwisko, placa_pod AS BRUTTO, PlacaNetto(placa_pod, 35) AS NETTO
FROM Pracownicy
WHERE etat = 'PROFESOR'
ORDER BY nazwisko;


/*zadanie 3*/
CREATE OR REPLACE FUNCTION Silnia(n IN POSITIVE)
    RETURN NUMBER IS
    wynik NUMBER := 1;
BEGIN
    FOR i IN 1 .. n
        LOOP
            wynik := wynik * i;
        END LOOP;
    RETURN wynik;
END;

SELECT Silnia(8)
FROM Dual;


/*zadanie 4*/
CREATE OR REPLACE FUNCTION SilniaRek(n IN POSITIVE)
    RETURN NUMBER IS
    wynik NUMBER := 1;
BEGIN
    IF n = 1 THEN
        wynik := 1;
    END IF;
    wynik := n * SILNIA(n - 1);
    RETURN wynik;
END;

SELECT SilniaRek(10)
FROM DUAL;


/*zadanie 5*/
CREATE OR REPLACE FUNCTION IleLat(data_poczatkowa IN DATE)
    RETURN NUMBER IS
    lata NUMBER;
BEGIN
    lata := EXTRACT(YEAR FROM CURRENT_DATE) - EXTRACT(YEAR FROM data_poczatkowa);
    RETURN lata;
END;

SELECT nazwisko, zatrudniony, IleLat(zatrudniony) AS staz
FROM Pracownicy
WHERE placa_pod > 800
ORDER BY nazwisko;


/*zadanie 6*/
    CREATE OR REPLACE PACKAGE Konwersja IS
    FUNCTION Cels_To_Fahr(stopnie NUMBER)
        RETURN NUMBER;
    FUNCTION Fahr_To_Cels(stopnie NUMBER)
        RETURN NUMBER;
END Konwersja;

CREATE OR REPLACE PACKAGE BODY Konwersja IS
    FUNCTION Cels_To_Fahr(stopnie NUMBER)
        RETURN NUMBER IS
        wynik NUMBER;
    BEGIN
        wynik := 9 / 5 * stopnie + 32;
        RETURN wynik;
    END Cels_To_Fahr;

    FUNCTION Fahr_To_Cels(stopnie NUMBER)
        RETURN NUMBER IS
        wynik NUMBER;
    BEGIN
        wynik := 5 / 9 * (stopnie - 32);
        RETURN wynik;
    END Fahr_To_Cels;
END Konwersja;

SELECT Konwersja.Fahr_To_Cels(212) AS CELSJUSZ
FROM Dual;
SELECT Konwersja.Cels_To_Fahr(0) AS FAHRENHEIT
FROM Dual;


/*zadanie 7*/
    CREATE OR REPLACE PACKAGE Zmienne IS
    vLicznik NUMBER;
    PROCEDURE ZwiekszLicznik;
    PROCEDURE ZmniejszLicznik;
    FUNCTION PokazLicznik RETURN NUMBER;
END Zmienne;

CREATE OR REPLACE PACKAGE BODY Zmienne IS
    PROCEDURE ZwiekszLicznik IS
    BEGIN
        vLicznik := vLicznik + 1;
        DBMS_OUTPUT.PUT_LINE('Zwiekszono');
    END ZwiekszLicznik;

    PROCEDURE ZmniejszLicznik IS
    BEGIN
        vLicznik := vLicznik - 1;
        DBMS_OUTPUT.PUT_LINE('Zmniejszono');
    END ZmniejszLicznik;

    FUNCTION PokazLicznik
        RETURN NUMBER IS
        licznik NUMBER;
    BEGIN
        RETURN vLicznik;
    END PokazLicznik;
    BEGIN
    vLicznik := 1;
    DBMS_OUTPUT.PUT_LINE('Zainicjalizowano');
END Zmienne;

BEGIN
    dbms_output.put_line(Zmienne.PokazLicznik);
END;

BEGIN
    Zmienne.ZwiekszLicznik;
    DBMS_OUTPUT.PUT_LINE(Zmienne.PokazLicznik);
    Zmienne.ZwiekszLicznik;
    DBMS_OUTPUT.PUT_LINE(Zmienne.PokazLicznik);
END;

BEGIN
    DBMS_OUTPUT.PUT_LINE(Zmienne.PokazLicznik);
    Zmienne.ZmniejszLicznik;
    DBMS_OUTPUT.PUT_LINE(Zmienne.PokazLicznik);
END;


/*zadanie 8*/
    CREATE OR REPLACE PACKAGE IntZespoly IS
    PROCEDURE DodajZespol(idZespolu NUMBER);
    PROCEDURE UsunZespolID(idZespolu NUMBER);
    PROCEDURE UsunZespolNazwa(nazwaZespolu VARCHAR);
    PROCEDURE Modyfikuj(idZespolu NUMBER, nazwaZespolu VARCHAR, adresZespolu VARCHAR);
    FUNCTION PodajID(nazwaZespolu VARCHAR) RETURN NUMBER;
    FUNCTION PodajNazwe(numerZespolu NUMBER) RETURN VARCHAR2;
    FUNCTION PodajAdres(idZespolu NUMBER) RETURN VARCHAR2;
END IntZespoly;

CREATE OR REPLACE PACKAGE BODY IntZespoly IS
    PROCEDURE DodajZespol(idZespolu NUMBER) IS
    BEGIN
        INSERT INTO Zespoly (id_zesp) VALUES (idZespolu);
        DBMS_OUTPUT.PUT_LINE('Dodano zespol ' || idZespolu);
    END DodajZespol;

    PROCEDURE UsunZespolID(idZespolu NUMBER) IS
    BEGIN
        DELETE FROM Zespoly WHERE id_zesp = idZespolu;
        DBMS_OUTPUT.PUT_LINE('Ususnieto zespol ' || idZespolu);
    END UsunZespolID;

    PROCEDURE UsunZespolNazwa(nazwaZespolu VARCHAR) IS
    BEGIN
        DELETE FROM Zespoly WHERE nazwa = nazwaZespolu;
        DBMS_OUTPUT.PUT_LINE('Ususnieto zespol ' || nazwaZespolu);
    END UsunZespolNazwa;

    PROCEDURE Modyfikuj(idZespolu NUMBER, nazwaZespolu VARCHAR, adresZespolu VARCHAR) IS
    BEGIN
        UPDATE Zespoly
        SET nazwa = nazwaZespolu,
            adres = adresZespolu
        WHERE id_zesp = idZespolu;
        DBMS_OUTPUT.PUT_LINE('Zmodyfikowano zespol ' || idZespolu);
    END Modyfikuj;

    FUNCTION PodajID(nazwaZespolu VARCHAR)
        RETURN NUMBER IS
        idZespolu NUMBER;
    BEGIN
        SELECT id_zesp
        INTO idZespolu
        FROM zespoly
        WHERE nazwa = nazwaZespolu;
        RETURN idZespolu;
    END PodajID;

    FUNCTION PodajNazwe(numerZespolu NUMBER)
        RETURN VARCHAR2 IS
        nazwaZespolu VARCHAR2(1000);
    BEGIN
        SELECT nazwa
        INTO nazwaZespolu
        FROM zespoly
        WHERE id_zesp = numerZespolu;
        RETURN nazwaZespolu;
    END PodajNazwe;

    FUNCTION PodajAdres(idZespolu NUMBER)
        RETURN VARCHAR2 IS
        adresZespolu VARCHAR2(1000);
    BEGIN
        SELECT adres
        INTO adresZespolu
        FROM zespoly
        WHERE id_zesp = idZespolu;
        RETURN adresZespolu;
    END PodajAdres;
END IntZespoly;


/*zadanie 9*/ --w systemie nazwy przechowujemy dużymi literami zapisane!!!
--lista procedur
SELECT object_name
FROM User_Procedures
WHERE object_type = 'PROCEDURE'
ORDER BY object_name;

--lista funkcji 
SELECT object_name, status
FROM User_Objects
WHERE object_type = 'FUNCTION'
ORDER BY object_name;

--lista pakietow (bez ich funkcji)
SELECT DISTINCT object_name
FROM User_Procedures
WHERE object_type = 'PACKAGE'
ORDER BY object_name;

--sprawdzenie statusu procedury NowyPracownik
SELECT object_name, status
FROM User_Objects
WHERE object_name = 'NOWYPRACOWNIK'
  AND object_type = 'PROCEDURE';

--wyświetlenie kodu źródowego ciala pakietu Zmienne
SELECT text
FROM User_Source
WHERE name = 'ZMIENNE'
  AND type = 'PACKAGE BODY'
ORDER BY line;


/*zadanie 10*/
DROP PROCEDURE SILNIA;
DROP PROCEDURE SilniaRek;
DROP FUNCTION IleLat;


SET SERVEROUTPUT OFF
/

create or replace FUNCTION PlacaNetto(placaBrutto IN NUMBER,
                                      podatek IN NUMBER := 20)
    RETURN NUMBER IS
    netto NUMBER;
BEGIN
    netto := (100 - podatek) / 100 * placaBrutto;
    RETURN netto;
END PlacaNetto;
/

create or replace PROCEDURE PokazPracownikowEtatu(
    etatPracowniczy IN VARCHAR) IS

    CURSOR etatowiPracownicy IS
        SELECT *
        FROM Pracownicy
        WHERE etat = etatPracowniczy
        ORDER BY nazwisko;

BEGIN
    FOR ePracownik IN etatowiPracownicy
        LOOP
            DBMS_OUTPUT.PUT_LINE(ePracownik.nazwisko);
        END LOOP;
END;
/

create or replace PROCEDURE PokazPracownikowEtatu2(
    etatPracowniczy IN VARCHAR) IS

    CURSOR etatowiPracownicy IS
        SELECT *
        FROM Pracownicy
        WHERE etat = etatPracowniczy
        ORDER BY nazwisko;

BEGIN
    FOR ePracownik IN etatowiPracownicy
        LOOP
            DBMS_OUTPUT.PUT_LINE(etatowiPracownicy%ROWCOUNT || '. ' || ePracownik.nazwisko || ', pensja ' ||
                                 (ePracownik.placa_pod + nvl(ePracownik.placa_dod, 0)));
        END LOOP;
END;
/

create or replace PROCEDURE RaportKadrowy IS
--pogrupowac wedlug etatu i petla wyswietlic dla poszczegolnych etatow
    CURSOR etaty IS
        SELECT etat, AVG(placa_pod + nvl(placa_dod, 0)) as srednia, COUNT(*) as liczbaPracownikow
        FROM Pracownicy
        GROUP BY etat;

BEGIN
    FOR nazwaEtatu IN etaty
        LOOP
            DBMS_OUTPUT.PUT_LINE('Etat: ' || nazwaEtatu.etat);
            PokazPracownikowEtatu2(nazwaEtatu.etat);
            DBMS_OUTPUT.PUT_LINE('Liczba pracownikow: ' || nazwaEtatu.liczbaPracownikow);
            DBMS_OUTPUT.PUT_LINE('Srednia placa na etacie: ' || nazwaEtatu.srednia);
            DBMS_OUTPUT.PUT_LINE(' ');
        END LOOP;
END;
/

create or replace FUNCTION SilniaRek(n IN POSITIVE)
    RETURN NUMBER IS
    wynik NUMBER := 1;
BEGIN
    IF n = 1 THEN
        wynik := 1;
    ELSE
        wynik := n * SilniaRek(n - 1);
    END IF;
    RETURN wynik;
END;
/

create or replace FUNCTION NoweID(tabela VARCHAR)
    RETURN NUMBER IS
    NoweDziecko NUMBER;
BEGIN
    SELECT MAX(iddziecka) + 1
    INTO NoweDziecko
    FROM tabela;
    RETURN NoweDziecko;
END NoweId;
/

create or replace FUNCTION Urodziny(szukane number)
    RETURN NUMBER IS
    ile_Lat NUMBER DEFAULT 0;

BEGIN
    SELECT EXTRACT(YEAR FROM current_date) - EXTRACT(YEAR FROM dataurodzenia)
    INTO ile_Lat
    FROM dziecko
    WHERE iddziecka = szukane;
    RETURN ile_Lat;

END Urodziny;
/

create or replace PROCEDURE NoweDziecko(imie IN VARCHAR,
                                        nazwisko IN VARCHAR,
                                        dataurodzenia IN DATE,
                                        grupa IN NUMBER,
                                        posilek IN NUMBER DEFAULT 1) IS
BEGIN
    INSERT INTO dziecko (iddziecka, imie, nazwisko, dataurodzenia, grupaprzedszkolna, posilek)
    VALUES (NoweID, imie, nazwisko, dataurodzenia, grupa, posilek);
END NoweDziecko;
/

create synonym SYSCATALOG for SYS.SYSCATALOG
/

create synonym CATALOG for SYS.CATALOG
/

create synonym TAB for SYS.TAB
/

create synonym COL for SYS.COL
/

create synonym TABQUOTAS for SYS.TABQUOTAS
/

create synonym SYSFILES for SYS.SYSFILES
/

create synonym PUBLICSYN for SYS.PUBLICSYN
/

create synonym PRODUCT_USER_PROFILE for SQLPLUS_PRODUCT_PROFILE
/


