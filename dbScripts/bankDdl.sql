create table customer(
customerId int not null auto_increment,
address varchar(30),
contactNo varchar(10),
primary key(customerId)
);

create table bank_account(
accountNo int not null auto_increment,
branch varchar(30),
accountHolder varchar(10),
bankCode varchar(10),
aType varchar(10),
accountBalance varchar(10),
customer_id int,
primary key(accountNo),
FOREIGN KEY (customer_id) REFERENCES customer (customerId) ON DELETE SET NULL
);

create table Transaction(
transacionId int not null auto_increment,
branch varchar(30),
accountHolder varchar(10),
tdate date,
tDescription varchar(10),
tReference varchar(10),
deposit double,
withdrawal double,
balance double,
account_id int,
primary key(transacionId),
FOREIGN KEY (account_id) REFERENCES bank_account (accountNo) ON DELETE SET NULL
);

