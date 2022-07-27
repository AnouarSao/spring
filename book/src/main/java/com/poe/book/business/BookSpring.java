package com.poe.book.business;

import javax.persistence.*;

@Entity
@Table(name ="books")
public class BookSpring {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer nbPages;
    private String category;

    public BookSpring() {
    }

    public BookSpring(String title, Integer nbPages, String category) {
        this.title = title;
        this.nbPages = nbPages;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNbPages() {
        return nbPages;
    }

    public void setNbPages(Integer nbPages) {
        this.nbPages = nbPages;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", nbPages=" + nbPages +
                ", category='" + category + '\'' +
                '}';
    }
}
