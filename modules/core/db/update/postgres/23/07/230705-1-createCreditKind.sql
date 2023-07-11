create table CREDIT_CREDIT_KIND (
    ID uuid,
    VERSION integer,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CODE varchar(50),
    NAME varchar(250),
    DESCRIPTION text,
    --
    primary key (ID)
);