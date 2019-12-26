package com.example.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.library.model.Books;
import com.example.library.model.UserBook;

public interface UserBookRepository extends JpaRepository<UserBook, Integer>
{
    @Query("Select d from UserBook d where d.isbn = :isbn")
    List<UserBook> getBorrowedBooksWithIsbn(@Param("isbn") String isbn);

    @Query("Select d from UserBook d where d.user.customerId = :custId")
    List<UserBook> findBooksByUserId(Integer custId);
}
