-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2008014052369514496, '录取数据源', '2000', '1', 'admissionFullRecord', 'admit/admissionFullRecord/index', 1, 0, 'C', '0', '0', 'admit:admissionFullRecord:list', '#', 103, 1, sysdate(), null, null, '录取数据源菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2008014052369514497, '录取数据源查询', 2008014052369514496, '1',  '#', '', 1, 0, 'F', '0', '0', 'admit:admissionFullRecord:query',        '#', 103, 1, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2008014052369514498, '录取数据源新增', 2008014052369514496, '2',  '#', '', 1, 0, 'F', '0', '0', 'admit:admissionFullRecord:add',          '#', 103, 1, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2008014052373708800, '录取数据源修改', 2008014052369514496, '3',  '#', '', 1, 0, 'F', '0', '0', 'admit:admissionFullRecord:edit',         '#', 103, 1, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2008014052373708801, '录取数据源删除', 2008014052369514496, '4',  '#', '', 1, 0, 'F', '0', '0', 'admit:admissionFullRecord:remove',       '#', 103, 1, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2008014052373708802, '录取数据源导出', 2008014052369514496, '5',  '#', '', 1, 0, 'F', '0', '0', 'admit:admissionFullRecord:export',       '#', 103, 1, sysdate(), null, null, '');
