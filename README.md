# LMS-mySQL
There are three main parts of this assignment Librarian, Borrower, Admin and a UI.
Each of these parts has 3 layers, the DAO connects to the database, the service connects to the DAO and the UI will call the service.

Librarian is designed for librarian admins who chooses which branch they work at, after that they do one of two actions:
They update the details of the Librarian( name or author) or they add more copies of a book to there branch they work at.

Borrower is designed for the the people who borrower books at the library.They enter their card number to the database and there they can choose to check out or return a book they borrowed.

Admin is designed for the people in charge of administratoring every library branch for an area. they can do CRUD operations for Book, Author, Publishers, Library Branch and borrowers. They can also over-ride due dates for certain books being borrowed
