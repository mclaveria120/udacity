CREATE TABLE IF NOT EXISTS USER (
  id INT PRIMARY KEY auto_increment,
  username VARCHAR(20),
  salt VARCHAR,
  password VARCHAR,
  first_name VARCHAR(20),
  lastname VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS NOTE (
    id INT PRIMARY KEY auto_increment,
    tittle VARCHAR(20),
    description VARCHAR (1000),
    user_id INT,
    foreign key (user_id) references USER(id)
);

CREATE TABLE IF NOT EXISTS FILE (
    id INT PRIMARY KEY auto_increment,
    name VARCHAR,
    file_type VARCHAR,
    size VARCHAR,
    user_id INT,
    data BLOB,
    foreign key (user_id) references USER(id)
);

CREATE TABLE IF NOT EXISTS CREDENTIAL (
    id INT PRIMARY KEY auto_increment,
    url VARCHAR(100),
    username VARCHAR (30),
    key VARCHAR,
    password VARCHAR,
    user_id INT,
    foreign key (user_id) references USER(id)
);