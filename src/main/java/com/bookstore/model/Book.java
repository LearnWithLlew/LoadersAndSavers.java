package com.bookstore.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String subtitle;
    private String isbn13;
    private String isbn10;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;
    
    private LocalDate publicationDate;
    private String edition;
    private Integer pageCount;
    private String language;
    private String description;
    private String coverImageUrl;
    private BigDecimal price;
    
    public Book() {
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        assert id != null : "id cannot be null";
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        assert title != null : "title cannot be null";
        this.title = title;
    }
    
    public String getSubtitle() {
        return subtitle;
    }
    
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
    
    public String getIsbn13() {
        return isbn13;
    }
    
    public void setIsbn13(String isbn13) {
        assert isbn13 != null : "isbn13 cannot be null";
        this.isbn13 = isbn13;
    }
    
    public String getIsbn10() {
        return isbn10;
    }
    
    public void setIsbn10(String isbn10) {
        assert isbn10 != null : "isbn10 cannot be null";
        this.isbn10 = isbn10;
    }
    
    public Publisher getPublisher() {
        return publisher;
    }
    
    public void setPublisher(Publisher publisher) {
        assert publisher != null : "publisher cannot be null";
        this.publisher = publisher;
    }
    
    public LocalDate getPublicationDate() {
        return publicationDate;
    }
    
    public void setPublicationDate(LocalDate publicationDate) {
        assert publicationDate != null : "publicationDate cannot be null";
        this.publicationDate = publicationDate;
    }
    
    public String getEdition() {
        return edition;
    }
    
    public void setEdition(String edition) {
        assert edition != null : "edition cannot be null";
        this.edition = edition;
    }
    
    public Integer getPageCount() {
        return pageCount;
    }
    
    public void setPageCount(Integer pageCount) {
        assert pageCount != null : "pageCount cannot be null";
        this.pageCount = pageCount;
    }
    
    public String getLanguage() {
        return language;
    }
    
    public void setLanguage(String language) {
        assert language != null : "language cannot be null";
        this.language = language;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getCoverImageUrl() {
        return coverImageUrl;
    }
    
    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
