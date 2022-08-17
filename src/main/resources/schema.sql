create table pressArticle(
                         id bigint not null auto_increment primary key,
                         title varchar(255) not null,
                         contents varchar(255) not null,
                         publicationDate date not null,
                         magazine varchar(255) not null,
                         authorFirstName varchar(255) not null,
                         authorLastName varchar(255) not null,
                         created date not null
);