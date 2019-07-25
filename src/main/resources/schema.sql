drop table if exists oauth_client_details;
create table oauth_client_details (
    client_id varchar(256) primary key,
    resource_ids varchar(256),
    client_secret varchar(256),
    scope varchar(256),
    authorized_grant_types varchar(256),
    web_server_redirect_uri varchar(256),
    authorities varchar(256),
    access_token_validity integer,
    refresh_token_validity integer,
    additional_information varchar(4096),
    autoapprove varchar(256)
);

drop table if exists oauth_access_token;
create table oauth_access_token (
  token_id VARCHAR(256),
  token LONG VARBINARY,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication LONG VARBINARY,
  refresh_token VARCHAR(256)
);

drop table if exists oauth_refresh_token;
create table oauth_refresh_token (
  token_id VARCHAR(256),
  token LONG VARBINARY,
  authentication LONG VARBINARY
);

drop table if exists users;
create table users(
    username varchar(256) not null primary key,
    password varchar(256) not null,
    enabled boolean not null
);

drop table if exists groups;
create table groups (
    id bigint auto_increment primary key,
    group_name varchar(50) not null
);

drop table if exists group_authorities;
create table group_authorities (
    group_id bigint not null,
    authority varchar(50) not null,
    constraint fk_group_authorities_group foreign key(group_id) references groups(id)
);

drop table if exists group_members;
create table group_members (
    id bigint auto_increment primary key,
    username varchar(50) not null,
    group_id bigint not null,
    constraint fk_group_members_group foreign key(group_id) references groups(id)
);

drop table if exists tbl_role;
create table tbl_role (
	id int not null auto_increment,
	name varchar(255) not null,
    primary key(id),
    unique(name)
) engine=InnoDB;

insert into tbl_role(name) values ('USER'),('ADMIN');

drop table if exists tbl_authority;
create table tbl_authority (
	id int not null auto_increment,
	name varchar(255) not null,
    primary key(id),
    unique(name)
) engine=InnoDB;

insert into tbl_authority(name) values ('INVENTORY_CREATE'),('INVENTORY_READ'),('INVENTORY_UPDATE'),('INVENTORY_DELETE');

drop table if exists tbl_role_authority;
create table tbl_role_authority (
	id int not null auto_increment,
	role_id int not null,
	authority_id int not null,
    primary key(id),
    unique(role_id,authority_id),
    foreign key(role_id) references tbl_role(id) on update cascade on delete cascade,
    foreign key(authority_id) references tbl_authority(id) on update cascade on delete cascade
) engine=InnoDB;

insert into tbl_role_authority(role_id, authority_id) values (1, 2),(2,1),(2,2),(2,3),(2,4);

drop table if exists tbl_user;
create table tbl_user(
	id int not null auto_increment,
    username varchar(256) not null,
    password varchar(256) not null,
    account_expired boolean,
    account_locked boolean,
    credentials_expired boolean,
    enabled boolean not null,
    role_id int,
    primary key(id),
    unique(username),
    foreign key(role_id) references tbl_role(id) on update cascade on delete cascade
) engine=InnoDB;

insert into tbl_user (username,password,account_expired,account_locked,credentials_expired,enabled,role_id) values 
	('john', '$2a$04$Ts1ry6sOr1BXXie5Eez.j.bsvqC0u3x7xAwOInn2qrItwsUUIC9li',0,0,0,1,1),
	('kelly', '$2a$04$qkCGgz.e5dkTiZogvzxla.KXbIvWXrQzyf8wTPJOOJBKjtHAQhoBa',0,0,0,1,2);
	

	