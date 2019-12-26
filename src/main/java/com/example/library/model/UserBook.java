package com.example.library.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UserBook
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int      userbookid;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    @JsonIgnore
    private Customer user;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bookId")
    @JsonIgnore
    private Books    book;
    private String   isbn;

    public UserBook()
    {

    }

    public UserBook(Customer user, Books book, String isbn)
    {
        this.user = user;
        this.book = book;
        this.isbn = isbn;
    }

    public int getUserbookid()
    {
        return userbookid;
    }

    public void setUserbookid(int userbookid)
    {
        this.userbookid = userbookid;
    }

    public Customer getUser()
    {
        return user;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    public void setUser(Customer user)
    {
        this.user = user;
    }

    public Books getBook()
    {
        return book;
    }

    public void setBook(Books book)
    {
        this.book = book;
    }

    @Override
    public String toString()
    {
        return this.getUser().getCustomerId() + " " + this.getUser().getCustomerName() + " " + this.getBook().getBookTitle();
    }

}
