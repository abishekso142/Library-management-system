package com.example.library.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    private String customerName;
    private int maxBooks;
    private int booksRemaining;
    
    Customer()
    {
        // default constructor
    }

    Customer(String Name, int limit, int rem)
    {
        this.customerName   = Name;
        this.maxBooks = limit;     
        this.booksRemaining = rem;
    }

    
    public int getCustomerId()
    {
        return customerId;
    }

    
    public void setCustomerId(int customerId)
    {
        this.customerId = customerId;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public int getmaxBooks()
    {
        return maxBooks;
    }

    public void setmaxBooks(int maxBooks)
    {
        this.maxBooks = maxBooks;
    }

    public int getBooksRemaining()
    {
        return booksRemaining;
    }

    public void setBooksRemaining(int booksRemaining)
    {
        this.booksRemaining = booksRemaining;
    }
}
