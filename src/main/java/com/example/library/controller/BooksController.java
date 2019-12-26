package com.example.library.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.library.model.Books;
import com.example.library.repository.BooksRepository;
import com.example.library.service.BooksService;

@RestController
@RequestMapping("/Books")
public class BooksController
{

   
    @Autowired
    private BooksService booksService;

    BooksController()
    {
        this.booksService = booksService;
    }

    @PostMapping
    public ResponseEntity<Books> addNewBook(@RequestBody Books book, UriComponentsBuilder uriComponentsBuilder)
        throws URISyntaxException
    {
        Books         newBook       = this.booksService.createNewBook(book);
        UriComponents uriComponents = uriComponentsBuilder.path("/{id}").buildAndExpand(newBook.getBookId());
        return ResponseEntity.created(new URI(uriComponents.getPath())).body(newBook);
    }

    @GetMapping
    public List<Books> getAllBooks()
    {
        return this.booksService.getAllBooks();
       
    }
}
