# Book Management System

## Overview

A backend application built with Java Spring Boot and MS SQL for managing books. Users can register, borrow, return, and manage books securely.

## Features

- **User Management**: Register users with hashed passwords.
- **Book Management**: Add, view, search, update, and delete books.
- **Borrow/Return**: Borrow multiple books and return them.
- **Security**: JWT-based authentication.

## Technologies

- Java 17
- Spring Boot
- Spring Data JPA
- MS SQL Server
- JWT

## Setup

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/book-management-system.git
   cd book-management-system
   ```

2. **Configure Database**:
   Update `src/main/resources/application.properties` with your MS SQL credentials.

3. **Build and Run**:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## API Endpoints

- **User Registration**: `POST /api/users/register`
- **Book Management**: 
  - `POST /api/books` (Add)
  - `GET /api/books/search` (Search)
  - `PUT /api/books/{id}` (Update)
  - `DELETE /api/books/{id}` (Delete)
- **Borrow/Return**:
  - `POST /api/borrow/books` (Borrow)
  - `POST /api/borrow/return/{bookId}` (Return)
  - `GET /api/borrow/my-books` (View borrowed books)
