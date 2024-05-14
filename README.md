# Library Management System

## Library component:
### Assuming that we have the following parts of the library:
#### 1- Admin: which represent the owner of the library.
#### 2- Librarian: which represent the person who working on the library.
#### 2- Patron: which represent the person who can borrow books and returning it to the library after reading
#### 2- User: which represent the person who can use the System of the library
#### 2- Book: which represent the books in the library which can be borrowed or returned

##### each part of the library represented as an entity in system code 
<hr>

### Important notes:
##### 1- To borrow a book we need at least 1 patron - 1 librarian - 1 system user - 1 book.
##### 2- Patron cannot borrow the same book before retrun it first.
##### 3- Borrowing period assumed to be 1 Month.
##### 4- To save the shape of borrowing api url we assumed that there is one librarian with id 1 in DB but it can be modified to be choosen.
##### 5- Status of the book represented in code by (Returned - Not Returned).

### System Architecture:
#### 1- Model layer which contains (Repository - Entity) : the layer that connect to DB.
#### 2- Service layer: which contains the business logic of the System.
#### 3- Controller layer: which handle the Http requests and responses.
#### 4- Security components: which handle the authentication and the autherizations between each api and users.
#### 5- Somer helper classes which handle: Exceptions - logging - configurations.

