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

### Project Technologies:
##### 1- java 17
##### 2- Spring boot 3.2.5
##### 3- Spring Web
##### 4- Spring data JPA
##### 5- spring Test
##### 6- Spring Validation
##### 7- Spring Security
##### 8- Spring AOP
##### 9- Spring Cach
##### 10- Maven
##### 11- MySQL for SQL DB.
<hr>

### How to run the project:
##### 1- locate to project path.
##### 2- Customize your configuration in application.yml file unde src/main/resources.
##### 3- Run (mvn spring-boot:run).
##### 4- To build jar file you can run command (mvn clean install) it will generate jar file under target folder after building.
##### 5- To run the generated jar file you can use command (java -jar jar-file-name.jar).
<hr>

### Important notes:
##### 1- To borrow a book we need at least 1 patron - 1 librarian - 1 system user - 1 book.
##### 2- Patron cannot borrow the same book before retrun it first.
##### 3- Borrowing period assumed to be 1 Month.
##### 4- To save the shape of borrowing api url we assumed that there is one librarian with id 1 in DB but it can be modified to be choosen.
##### 5- Status of the book represented in code by (Returned - Not Returned).
<hr>

### System Architecture:
#### 1- Model layer which contains (Repository - Entity) : the layer that connect to DB.
#### 2- Service layer: which contains the business logic of the System.
#### 3- Controller layer: which handle the Http requests and responses.
#### 4- Security components: which handle the authentication and the autherizations between each api and users.
#### 5- Somer helper classes which handle: Exceptions - logging - configurations.
<hr>

### Database design:
#### 1- books table:
#####   contains ((PK) id - title - author - publication_year - isbn)
#### 2- patrons table: 
#####   contains ((PK) id - first_name - last_name - email - phone - address - city - state)
#### 3- librarians  table:
#####   contains ((PK) id - first_name - last_name - emial - phone)
#### 4- borrowing_records table:
#####   contains ((PK) id - (FK) librarian_id - (FK) patron_id - (FK) book_id - borrowing_date - return_date - isReturned)
#### 5- users table: 
#####   contains ((pk) id- usename - password - role)
<hr>

### Authorizations:
<hr> 

#### (HTTP.GET - /api/books/**) not authorized.
#### (HTTP.POST - /api/books) allowed for AMDIN - LIBRARIAN roles.
#### (HTTP.PUT - /api/books) allowed for ADMIN - LIBRARIAN roles.
#### (HTTP.DELETE - /api/books) allowed for ADMIN - LIBRARIAN roles.
<hr>

#### (HTTP.GET - /api/patrons/**) allowed for AMDIN - LIBRARIAN roles.
#### (HTTP.POST - /api/patons) allowed for AMDIN - LIBRARIAN roles.
#### (HTTP.PUT - /api/patons) allowed for ADMIN - LIBRARIAN roles.
#### (HTTP.DELETE - /api/patrons) allowed for ADMIN - LIBRARIAN roles.
<hr>

#### (HTTP.GET - /api/librarians/**) not authorized.
#### (HTTP.POST - /api/librarians) allowed for AMDIN role.
#### (HTTP.PUT - /api/librarians) allowed for ADMIN role.
#### (HTTP.DELETE - /api/librarians) allowed for ADMIN role.
<hr>

#### (HTTP.GET - /api/users/**) allowed for AMDIN role.
#### (HTTP.POST - /api/users) allowed for AMDIN role.
#### (HTTP.PUT - /api/users) allowed for ADMIN role.
#### (HTTP.DELETE - /api/users) allowed for ADMIN role.
<hr>

#### (HTTP.GET - /api/borrow) allowed for ADMIN - LIBRARIAN roles.
#### (HTTP.POST - /api/borrow) allowed for ADMIN - LIBRARIAN roles.
#### (HTTP.PUT - /api/borrow) allowed for ADMIN - LIBRARIAN roles.
<hr>

## Code explanation is above each method of code.
## Refrences: Available upone requests.
