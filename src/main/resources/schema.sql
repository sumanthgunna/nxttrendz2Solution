create table category (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    description VARCHAR(255)
);

create table product (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(255),
    description varchar(255),
    price double,
    categoryId int,
    FOREIGN KEY(categoryId) REFERENCES category(id)
);