package com.example.library.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Books
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bookId;

    private String isbn;
    private String bookTitle;
    private String author;
    private String publisher;
    private int    numofCopies;

    Books()
    {

    }

    Books(String isbn, String bookTitle, String author, String publisher, int num)
    {
        this.isbn        = isbn;
        this.bookTitle   = bookTitle;
        this.author      = author;
        this.publisher   = publisher;
        this.numofCopies = num;
    }

    public Integer getBookId()
    {
        return bookId;
    }

    public void setBookId(Integer bookId)
    {
        this.bookId = bookId;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    public String getBookTitle()
    {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle)
    {
        this.bookTitle = bookTitle;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getPublisher()
    {
        return publisher;
    }

    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }

    public int getNumofCopies()
    {
        return numofCopies;
    }

    public void setNumofCopies(int numofCopies)
    {
        this.numofCopies = numofCopies;
    }
    
    @Override
    public String toString() {
        return this.getBookId() + " " + this.getBookTitle() + " " + this.getAuthor();
    }
}
