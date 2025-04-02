# Bookstore Application Architecture

This document provides an overview of the architecture of the Bookstore application, which displays the top 10 books.

## Architecture Overview

The application follows a standard Spring Boot architecture with the following layers:
- **Presentation Layer**: Controllers and Thymeleaf templates
- **Business Logic Layer**: Services
- **Data Access Layer**: Repositories and JPA entities
- **Database**: Apache Derby (embedded)

## Class Diagram

```mermaid
classDiagram
    class BookstoreApplication {
        +main(String[] args) void
    }

    class BookController {
        -BookService bookService
        +BookController(BookService)
        +listBooks(Model) String
    }

    class BookService {
        -BookRepository bookRepository
        +BookService(BookRepository)
        +getTop10Books() List~Book~
    }

    class BookRepository {
        <<interface>>
        +findTop10Books(Pageable) List~Book~
    }

    class Book {
        -Long id
        -String title
        -String subtitle
        -String isbn13
        -String isbn10
        -Long publisherId
        -LocalDate publicationDate
        -String edition
        -Integer pageCount
        -String language
        -String description
        -String coverImageUrl
        -BigDecimal price
        +getters()
        +setters()
    }

    BookController --> BookService : uses
    BookService --> BookRepository : uses
    BookRepository --> Book : manages
    BookController ..> Book : displays

    note for BookRepository "Extends JpaRepository~Book, Long~"
    note for BookController "Handles HTTP requests and returns views"
    note for BookService "Contains business logic"
    note for Book "JPA Entity mapped to books table"
```

## Component Diagram

```mermaid
flowchart TB
    subgraph Client
        Browser
    end

    subgraph "Spring Boot Application"
        subgraph "Presentation Layer"
            Controller["BookController"]
            View["Thymeleaf Templates"]
        end
        
        subgraph "Business Layer"
            Service["BookService"]
        end
        
        subgraph "Data Access Layer"
            Repository["BookRepository"]
            Entity["Book Entity"]
        end
    end
    
    subgraph "Database"
        Derby["Apache Derby\n(Embedded)"]
    end
    
    Browser <--> Controller
    Controller <--> View
    Controller --> Service
    Service --> Repository
    Repository --> Entity
    Entity <--> Derby
```

## Sequence Diagram for Retrieving Books

```mermaid
sequenceDiagram
    participant User
    participant Controller as BookController
    participant Service as BookService
    participant Repository as BookRepository
    participant Database as Derby Database
    
    User->>Controller: Access homepage (GET /)
    Controller->>Service: getTop10Books()
    Service->>Repository: findTop10Books(pageable)
    Repository->>Database: Execute SQL query
    Database-->>Repository: Return book records
    Repository-->>Service: Return List<Book>
    Service-->>Controller: Return List<Book>
    Controller-->>User: Render index.html with books
```

## Technology Stack

- **Java**: Programming language
- **Spring Boot**: Application framework
- **Spring Web**: Web layer
- **Spring Data JPA**: Data access layer
- **Hibernate**: JPA implementation
- **Apache Derby**: Embedded database
- **Thymeleaf**: View templating
- **Maven**: Dependency management and build tool
- **JUnit 5 & Spring Boot Test**: Testing framework
