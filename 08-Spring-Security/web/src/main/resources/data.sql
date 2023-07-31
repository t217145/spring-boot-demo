INSERT INTO myusers (
    name,
    usrcode, 
    password, 
    isactive
) values (
    'student',
    'student@cyrus822.com',
    '$2a$12$819BcFYb/dUKgmEtEkWZpOJQazLkuj6QkwgybCQ0qvhHTvX8vp9B6', --abc
    1
);

INSERT INTO myusers (
    name,
    usrcode, 
    password, 
    isactive
) values (
    'teacher',
    'teacher@cyrus822.com',
    '$2a$12$819BcFYb/dUKgmEtEkWZpOJQazLkuj6QkwgybCQ0qvhHTvX8vp9B6', --abc
    1
);

INSERT INTO myusers (
    name,
    usrcode, 
    password, 
    isactive
) values (
    'principle',
    'principle@cyrus822.com',
    '$2a$12$819BcFYb/dUKgmEtEkWZpOJQazLkuj6QkwgybCQ0qvhHTvX8vp9B6', --abc
    1
);

INSERT INTO myroles (
    usrcode, 
    roles
) values (
    'students@cyrus822.com', 
    'ROLE_STUDENTS'
);

INSERT INTO myroles (
    usrcode, 
    roles
) values (
    'teacher@cyrus822.com', 
    'ROLE_TEACHER'
);

INSERT INTO myroles (
    usrcode, 
    roles
) values (
    'principle@cyrus822.com', 
    'ROLE_ADMIN'
);