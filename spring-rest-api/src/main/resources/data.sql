INSERT INTO AUTHORS (AUTHOR_ID, FIRST_NAME, LAST_NAME) VALUES (101, 'George', 'Orwell');

INSERT INTO BOOKS (BOOK_ID, TITLE, NUM_PAGES) VALUES (201, 'Animal Farm', 112);
INSERT INTO BOOKS (BOOK_ID, TITLE, NUM_PAGES) VALUES (202, '1984', 328);

INSERT INTO AUTHORS_BOOKS VALUES (101, 201);
INSERT INTO AUTHORS_BOOKS VALUES (101, 202);