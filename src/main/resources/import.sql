-- Initialize AUTHORS
INSERT INTO AUTHOR(FIRSTNAME, LASTNAME) VALUES ( 'Joanne', 'Rowling' ), ( 'Stephen', 'King' );

-- Initialize BOOKS
INSERT INTO BOOK(TITLE) VALUES ('Harry Potter and the Philosopher''s Stone'), ( 'Harry Potter and the Half-Blood Prince' );
INSERT INTO BOOK(TITLE) VALUES ( 'It' ), ( 'Misery' );

-- Initialize AUTHOR_BOOK
INSERT INTO AUTHOR_BOOK(AUTHOR_ID, BOOK_ID) VALUES ( '1', '1' ), ( '1', '2' ), ( '2', '3' ), ( '2', '4' );