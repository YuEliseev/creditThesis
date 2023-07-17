-- insert new wf-proc
update WF_PROC set code='creditProcessing', card_types = ',credit$CreditApplication,', update_ts=current_timestamp, updated_by='system'
where name = 'creditProcessing'^
--end insert new wf-proc