package com.example.library.exceptions;

public class currentlyNoBookAvailableException extends RuntimeException
{
  private static final String message = "Book currently unavailable, please try later";
  
  public currentlyNoBookAvailableException() {
      super(message);
  }
}
