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
    
    class PageViewController {
        -PageViewService pageViewService
        +PageViewController(PageViewService)
        +recordPageView(HttpServletRequest) void
    }
    
    class PageViewService {
        -PageViewRepository pageViewRepository
        -CountryRepository countryRepository
        +PageViewService(PageViewRepository, CountryRepository)
        +recordPageView(String ipAddress, String userAgent, String referrer, String pageUrl) void
        +getCountryFromIp(String ipAddress) Country
    }
    
    class PageViewRepository {
        <<interface>>
        +save(PageView) PageView
        +findAll() List~PageView~
    }
    
    class PageView {
        -Long id
        -String ipAddress
        -Country country
        -LocalDateTime visitTimestamp
        -String userAgent
        -String sessionId
        -String referrerUrl
        -String pageUrl
        +getters()
        +setters()
    }
    
    class Country {
        -Long id
        -String name
        -String code
        +getters()
        +setters()
    }

    BookController --> BookService : uses
    BookService --> BookRepository : uses
    BookRepository --> Book : manages
    BookController ..> Book : displays
    
    PageViewController --> PageViewService : uses
    PageViewService --> PageViewRepository : uses
    PageViewService --> Country : uses
    PageViewRepository --> PageView : manages
    PageView --> Country : references

    note for BookRepository "Extends JpaRepository~Book, Long~"
    note for BookController "Handles HTTP requests and returns views"
    note for BookService "Contains business logic"
    note for Book "JPA Entity mapped to books table"
    note for PageViewRepository "Extends JpaRepository~PageView, Long~"
    note for PageViewController "Records visitor information"
    note for PageViewService "Processes and stores page view data"
    note for PageView "JPA Entity mapped to page_views table"
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
            PageViewController["PageViewController"]
        end
        
        subgraph "Business Layer"
            Service["BookService"]
            PageViewService["PageViewService"]
        end
        
        subgraph "Data Access Layer"
            Repository["BookRepository"]
            Entity["Book Entity"]
            PageViewRepository["PageViewRepository"]
            PageViewEntity["PageView Entity"]
            CountryRepository["CountryRepository"]
            CountryEntity["Country Entity"]
        end
    end
    
    subgraph "Database"
        Derby["Apache Derby\n(Embedded)"]
    end
    
    Browser <--> Controller
    Browser <--> PageViewController
    Controller <--> View
    Controller --> Service
    PageViewController --> PageViewService
    Service --> Repository
    PageViewService --> PageViewRepository
    PageViewService --> CountryRepository
    Repository --> Entity
    PageViewRepository --> PageViewEntity
    CountryRepository --> CountryEntity
    Entity <--> Derby
    PageViewEntity <--> Derby
    CountryEntity <--> Derby
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

## Sequence Diagram for Recording Page Views

```mermaid
sequenceDiagram
    participant User
    participant PageViewController as PageViewController
    participant PageViewService as PageViewService
    participant PageViewRepository as PageViewRepository
    participant CountryRepository as CountryRepository
    participant Database as Derby Database
    
    User->>PageViewController: Access webpage
    PageViewController->>PageViewService: recordPageView(HttpServletRequest)
    PageViewService->>PageViewRepository: save(PageView)
    PageViewService->>CountryRepository: getCountryFromIp(String ipAddress)
    CountryRepository->>Database: Execute SQL query
    Database-->>CountryRepository: Return country record
    CountryRepository-->>PageViewService: Return Country
    PageViewService->>PageViewRepository: save(PageView) with country
    PageViewRepository->>Database: Execute SQL query
    Database-->>PageViewRepository: Return page view record
    PageViewRepository-->>PageViewService: Return PageView
    PageViewService-->>PageViewController: Return void
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
