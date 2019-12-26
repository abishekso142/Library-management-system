package com.example.library.exceptions;


public class newMaxLimitReachedException extends Exception
{
  private final static String message = "You have reached maximum limit for your membership, please renew your membership";
  public newMaxLimitReachedException() {
      super(message);
  }
}
