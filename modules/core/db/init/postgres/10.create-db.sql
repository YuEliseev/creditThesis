-- begin CREDIT_CREDIT_KIND
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
)^
-- end CREDIT_CREDIT_KIND
-- begin CREDIT_CREDIT
create table CREDIT_CREDIT (
    CARD_ID uuid,
    --
    NUMBER_ varchar(50),
    BANK_MANAGER_ID uuid,
    CREDI_KIND_ID uuid,
    BANK_ID uuid,
    DATE_ date,
    AMOUNT decimal(19, 2),
    GUARANTOR_ID uuid,
    --
    primary key (CARD_ID)
)^
-- end CREDIT_CREDIT
-- begin CREDIT_CREDIT_APPLICATION
create table CREDIT_CREDIT_APPLICATION (
    CARD_ID uuid,
    --
    CREDIT_ID uuid,
    BORROWER_ID uuid,
    --
    primary key (CARD_ID)
)^
-- end CREDIT_CREDIT_APPLICATION
