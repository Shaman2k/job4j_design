insert into roles (role_name) values ('Admin');

insert into users (username, role_id) values ('Nikolay', 1);

insert into rules (rule_name) values ('edit');

insert into roles_rules (role_id, rule_id) values (1, 1);

insert into states (state_name) values ('state1');

insert into categories (category_name) values ('category1');

insert into items (item_name, user_id, category_id, state_id) values ('item1', 1, 1, 1);

insert into comments (comment_name, item_id) values ('First comment', 1);

insert into attachs (attach_name, item_id) values ('attach', 1);