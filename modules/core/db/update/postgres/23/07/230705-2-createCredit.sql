alter table CREDIT_CREDIT add constraint FK_CREDIT_CREDIT_ON_CREDI_KIND foreign key (CREDI_KIND_ID) references CREDIT_CREDIT_KIND(ID);
alter table CREDIT_CREDIT add constraint FK_CREDIT_CREDIT_ON_BANK foreign key (BANK_ID) references DF_BANK(ID);
alter table CREDIT_CREDIT add constraint FK_CREDIT_CREDIT_ON_CARD foreign key (CARD_ID) references WF_CARD(ID) on delete CASCADE;
create index IDX_CREDIT_CREDIT_ON_CREDI_KIND on CREDIT_CREDIT (CREDI_KIND_ID);
create index IDX_CREDIT_CREDIT_ON_BANK on CREDIT_CREDIT (BANK_ID);
