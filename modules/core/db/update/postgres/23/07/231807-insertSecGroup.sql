--begin insert secGroup
INSERT INTO sec_group(
	id, create_ts, created_by, version, update_ts, updated_by, delete_ts, deleted_by, sys_tenant_id, name, parent_id, ad_group_name, loc_name, dtype)
	VALUES (newid(), current_timestamp, 'system', '1', null, null, null, null, null,  'Служба безопасности',
	(select ID from sec_group where NAME = 'Ограниченный доступ'), null,
	'{"captionWithLanguageList":[{"language":"ru","caption":"Служба безопасности"},{"language":"en","caption":"Security service"}]}', '10')^
--end insert secGroup
--begin update secConstraint
INSERT INTO public.sec_constraint(
	id, create_ts, created_by, version,
	update_ts, updated_by, delete_ts, deleted_by, sys_tenant_id, code,
	check_type, operation_type, entity_name, join_clause, where_clause,
	groovy_script, filter_xml, is_active, group_id)
	VALUES (newid(), current_timestamp, 'system', '1',
	null, null, null, null, null, null,
    'db', 'read', 'df$Doc', 'left outer join {E}.aclList acl join credit$CreditApplication ca',
    '{E}.id = ca.id or acl.user.id = :session$userId or acl.global = true',
    null, null, true, (select ID from sec_group where NAME = 'Служба безопасности'))^
--end update secConstraint