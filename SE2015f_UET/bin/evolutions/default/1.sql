# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table tag (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_tag primary key (id))
;

create table user_account (
  id                        bigint not null,
  email                     varchar(255),
  password                  varchar(255),
  description               varchar(255),
  name                      varchar(255),
  tag_id                    bigint,
  chucvu                    bigint,
  constraint pk_user_account primary key (id))
;

create sequence tag_seq;

create sequence user_account_seq;

alter table user_account add constraint fk_user_account_tag_1 foreign key (tag_id) references tag (id) on delete restrict on update restrict;
create index ix_user_account_tag_1 on user_account (tag_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists tag;

drop table if exists user_account;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists tag_seq;

drop sequence if exists user_account_seq;

