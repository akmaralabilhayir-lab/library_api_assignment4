# library_api_assignment4

Project Description

This project is a console-based Java application that demonstrates a clean layered architecture and the application of Ы  SOLID principles in practice.

The system manages Authors, Categories, and Books using JDBC and PostgreSQL.
The project is an improved version of Assignment 3, refactored to follow SOLID and advanced OOP concepts.

Project Structure

docs/

├──uml.png

└── screenshots

src/

├── controller/

│   ├── AuthorController.java

│   ├── BookController.java

│   └── CategoryController.java

├── service/

│   ├── AuthorService.java

│   ├── BookService.java

│   └── CategoryService.java

├── repository/

│   ├── AuthorRepository.java

│   ├── BookRepository.java

│   └── CategoryRepository.java

│   └── interfaces/

│       └── CrudRepository.java

├── model/
│   ├── BaseEntity.java

│   ├── Author.java

│   ├── Books.java

│   └── Category.java

├── exception/

│   ├── InvalidInputException.java

│   ├── DuplicateResourceException.java

│   ├── ResourceNotFoundException.java

│   └── DatabaseOperationException.java

├── utils/

│   ├── ReflectionUtils.java

│   ├── SortingUtils.java

    └── DatabaseConnection.java
    
└── Main.java
resources/

└── schema.sql

 A. SOLID Principles

Single Responsibility Principle (SRP)

Each class has one responsibility:

Controllers handle user interaction

 Services contain business logic and validation
 
Repositories work only with the database

Models represent domain entities


 Open–Closed Principle (OCP)

The abstract class `BaseEntity` allows adding new entity types without changing existing logic.

 Liskov Substitution Principle (LSP)

All entities (`Author`, `Books`, `Category`) can be used through the `BaseEntity` type.
This is demonstrated in `Main` using a polymorphic list.



Interface Segregation Principle (ISP)

Small interfaces such as `Validatable` and `Printable` are used instead of large, general ones.

Dependency Inversion Principle (DIP)

Services depend on repository interfaces, not concrete implementations.

 B. Advanced OOP Features

 Generics

A generic interface `CrudRepository<T>` is used for CRUD operations across all repositories.

---

Lambdas

Lambda expressions are used when iterating and sorting collections, for example when printing and sorting books.


 Reflection

The `ReflectionUtils` class demonstrates runtime type inspection:

 class name
 
fields

 methods
 

This is shown in `Main`.

---

### Interface Default / Static Methods

The `Validatable` interface includes default and static methods for reusable validation logic.


 C. OOP Design

Abstract Class and Subclasses

`BaseEntity` – abstract parent class

Author`, `Books`, `Category` – subclasses overriding base behavior

Composition

A `Book` has:

 an `Author`

a `Category`

These relationships are implemented in both Java objects and the database schema.

 Polymorphism

Different entities are stored in a `List<BaseEntity>` and processed using overridden methods.

D. Database Design

Tables

authors
category
books

 Constraints

Primary keys for all tables

Foreign keys:

books.author_id → authors.id

books.category_id → category.id

SAMPLE DATA

 -- AUTHORS
 
INSERT INTO authors (name) VALUES

('Sh. Murtaza'),

('I. Yesenberlin');

-- CATEGORIES

INSERT INTO category (name) VALUES

('Novel'),

('Historical Trilogy');

-- BOOKS

INSERT INTO books (name, author_id, category_id, available) VALUES

('Ai men Aisha', 1, 1, true),

('Koshpendiler', 2, 2, true),

('Qyzyl Zhebe', 1, 2, false),

('Khan Kene', 2, 2, false);


E. Architecture Overview


Controller → Service → Repository → Database

Controller delegates requests

Service validates and applies rules

Repository executes JDBC queries


F. How to Run

 Compile

javac -cp postgresql-42.x.x.jar src/**/*.java

 Run
 
java -cp .;postgresql-42.x.x.jar Main

G. Demonstration (Screenshots)

Creating authors, categories, and books

<img width="1022" height="609" alt="image" src="https://github.com/user-attachments/assets/f8122798-919e-4996-9205-e30b28b7dccc" />


 Sorted book list (lambda usage)


 <img width="999" height="145" alt="image" src="https://github.com/user-attachments/assets/d84cc9c7-444c-4948-9194-0575a867132f" />


 Reflection output


 <img width="185" height="341" alt="image" src="https://github.com/user-attachments/assets/df7f4a74-7033-4877-9528-71ee954ea5e6" />

uml


<img width="1656" height="834" alt="image" src="https://github.com/user-attachments/assets/bf7f5080-39b8-43ee-b210-6939d769126b" />


 H. Reflection

What I Learned

I learned how to refactor a project using SOLID principles and how layered architecture improves code clarity.

Challenges

The main challenges were:

strict separation of layers

handling validation correctly

debugging JDBC issues

 Value of SOLID

SOLID principles made the project easier to maintain, extend, and debug.

