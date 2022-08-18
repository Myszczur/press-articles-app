create table pressArticle(
                         id LONG not null auto_increment primary key unique,
                         title varchar(60) not null,
                         contents varchar(255) not null,
                         publicationdate date not null,
                         magazine varchar(60) not null,
                         authorfirstname varchar(20) not null,
                         authorlastname varchar(20) not null,
                         created date not null,
                         updated date
);