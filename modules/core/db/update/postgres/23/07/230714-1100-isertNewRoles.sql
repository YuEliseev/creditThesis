-- insert new Role
insert into sec_role(id, create_ts, created_by, version, name, loc_name, description, role_type, is_default_role) values
(newid(), current_timestamp, 'system', 1, 'secOfficer', 'Служба Безопасности', 'Сотрудник службы безопасности банка', 0, false),
(newid(), current_timestamp, 'system', 1, 'legalOfficer', 'Юридический Отдел', 'Сотрудник юридического отдела', 0, false);
-- end insert new Role