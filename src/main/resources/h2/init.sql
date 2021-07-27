create table users (
  id bigint auto_increment,
  name varchar(50),
  balance decimal(20, 2),
  primary key (id)
);

create table transactions (
  id bigint auto_increment,
  user_id bigint,
  time timestamp,
  status varchar(50),
  amount decimal(20, 2),
  reference varchar(50),
  primary key (id),
  foreign key (user_id) references users(id)
);