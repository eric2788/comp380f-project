CREATE TABLE "User"(
                       username varchar(15) primary key not null,
                       password long varchar not null,
                       phone decimal(8, 0) not null,
                       fullname varchar(20) not null,
                       address long varchar not null,
                       admin boolean default false
);

CREATE TABLE Item(
                     id int primary key generated always as identity,
                     name varchar(30) not null,
                     description long varchar not null,
                     price double default 0.0,
                     available boolean default true
);

CREATE TABLE Comment(
                        id int primary key generated always as identity,
                        username varchar(15) references "User"(username),
                        content long varchar not null,
                        item int references Item(id)
);

CREATE TABLE "Order"(
                        username varchar(15) references "User"(username),
                        item int references Item(id),
                        amount int not null
);





