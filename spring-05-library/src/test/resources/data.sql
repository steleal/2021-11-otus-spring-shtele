INSERT INTO AUTHORS (ID, FULL_NAME)
VALUES (1, 'Толстой Л.Н.'),
       (2, 'Толстой А.Н.');

INSERT INTO GENRES (ID, NAME)
VALUES (1, 'Роман'),
       (2, 'Сказка');

INSERT INTO BOOKS (ID, TITLE, AUTHOR_ID, GENRE_ID)
VALUES (1, 'Война и Мир', 1, 1);
