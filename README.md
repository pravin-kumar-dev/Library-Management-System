# Library Management System

A console-based Library Management System developed using **Java, JDBC, and Microsoft SQL Server**.

The application manages books, students, and book issue/return operations while maintaining database records through JDBC.

## Features

- Add new books
- View books
- Search books
- Add students
- View students
- Issue books
- Return books
- Check book availability
- Prevent duplicate active book issues
- Input validation and edge-case handling
- CRUD operations using JDBC
- PreparedStatement for parameterized SQL queries
- ResultSet for reading database records
- JDBC exception handling
- Database transactions using commit and rollback

## Technologies Used

- Java
- JDBC
- Microsoft SQL Server
- SQL
- IntelliJ IDEA
- Git & GitHub

## Architecture

The project follows a simple **DAO (Data Access Object) architecture**.

```text
Main
  |
  ├── Book
  ├── Student
  |
  ├── BookDAO
  ├── StudentDAO
  └── IssueDAO
          |
          ↓
     DBConnection
          |
          ↓
   Microsoft SQL Server
```

## Project Structure

```text
LibraryManagementSystem/
│
├── .gitignore
│
└── src/
    │
    ├── Book.java
    ├── BookDAO.java
    ├── IssueDAO.java
    ├── Main.java
    ├── Student.java
    ├── StudentDAO.java
    │
    └── com/
        └── pravin/
            └── library/
                └── DBConnection.java
```

## Database Design

The application uses **Microsoft SQL Server** as the database.

### Books

Stores information about library books.

Typical fields include:

- Book ID
- Book title
- Author
- Availability status

### Students

Stores information about students.

Typical fields include:

- Student ID
- Student name
- Student details

### IssueRecords

Stores book issue and return information.

Typical fields include:

- Issue ID
- Book ID
- Student ID
- Issue date
- Return date

A `NULL` return date represents a book that is currently issued.

## JDBC Concepts Used

This project demonstrates practical JDBC concepts including:

- `Connection`
- `DriverManager`
- `PreparedStatement`
- `Statement`
- `ResultSet`
- `SQLException`
- Try-with-resources
- `executeQuery()`
- `executeUpdate()`
- Transactions
- `commit()`
- `rollback()`

## Security

The project uses `PreparedStatement` for database operations instead of directly concatenating user input into SQL queries.

This helps prevent SQL injection and provides safer parameterized database operations.

Database credentials are kept as local configuration values and should not be shared publicly.

## Setup and Run

### 1. Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/LibraryManagementSystem.git
```

### 2. Open the project

Open the project in IntelliJ IDEA.

### 3. Configure Microsoft SQL Server

Create the required database and tables in Microsoft SQL Server.

### 4. Configure JDBC Connection

Open:

```text
src/com/pravin/library/DBConnection.java
```

Update the following values with your local SQL Server credentials:

```java
private static final String USERNAME = "YOUR_USERNAME";
private static final String PASSWORD = "YOUR_PASSWORD";
```

Also make sure the database name and SQL Server connection settings match your local environment.

### 5. Add Microsoft SQL Server JDBC Driver

Make sure the Microsoft SQL Server JDBC driver is available in the project classpath.

### 6. Run the application

Run:

```text
Main.java
```

The application will start in the console and provide options for managing books, students, and issue/return operations.

## Key Learning Outcomes

Through this project, I practiced:

- Core Java and OOP concepts
- JDBC database connectivity
- SQL queries
- CRUD operations
- DAO design pattern
- PreparedStatement
- Exception handling
- Transactions
- Input validation
- Database-driven application development

## Future Improvements

- GUI-based interface
- User authentication
- Admin and student roles
- Fine calculation for late returns
- Book search and filtering improvements
- Reports and statistics
- REST API using Spring Boot

## Author

**Pravin Kumar**

BSc IT | Java Developer Aspirant
