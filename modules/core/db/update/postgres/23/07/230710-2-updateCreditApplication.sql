alter table CREDIT_CREDIT_APPLICATION rename column borrower_id to borrower_id__u79642 ;
alter table CREDIT_CREDIT_APPLICATION drop constraint FK_CREDIT_APPLICATION_ON_BORROWER ;
drop index IDX_CREDIT_APPLICATION_ON_BORROWER ;
alter table CREDIT_CREDIT_APPLICATION add column BORROWER_ID uuid ;
