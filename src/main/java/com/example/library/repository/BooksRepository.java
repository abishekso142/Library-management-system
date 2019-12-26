package com.example.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.library.model.Books;

public interface BooksRepository extends JpaRepository<Books,Integer>
{
   @Query("Select d from Books d where d.isbn = :isbn")
   List<Books> getAllBooksWithIsbn(@Param("isbn") String isbn);
}
