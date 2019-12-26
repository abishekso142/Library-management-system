package com.example.library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.model.Books;
import com.example.library.repository.BooksRepository;

@Service
public class BooksService
{

    @Autowired
    private BooksRepository booksRepo;

    BooksService()
    {
        this.booksRepo = booksRepo;
    }

    public List<Books> getAllBooks()
    {

        List<Books> books = new ArrayList<Books>();
        this.booksRepo.findAll().forEach(new Consumer<Books>()
        {

            @Override
            public void accept(Books t)
            {
                books.add(t);
            }
        });
        return books;

    }

    public Books createNewBook(Books book)
    {
       return this.booksRepo.save(book);
    }
}
