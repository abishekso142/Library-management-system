package com.example.library.exceptions;

public class BookNotFound extends RuntimeException
{
   private static final String message = "We dont have that book yet, try again later";
   
   public BookNotFound() {
       super(message);
   }
}
