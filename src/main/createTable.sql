create table if not exists language(
     name text PRIMARY KEY,
     description text,
     rating INT UNIQUE
);

insert into language(name, description, rating) values
    ('Java', 'class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible', 5),
    ('C#', 'object-oriented (class-based) programming language developed by Microsoft', 4),
    ('Python', 'interpreted, high-level and general-purpose programming language', 1),
    ('JavaScript', 'programming language that conforms to the ECMAScript specification', 2);