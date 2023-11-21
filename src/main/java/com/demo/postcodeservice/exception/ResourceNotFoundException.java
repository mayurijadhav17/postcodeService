package com.demo.postcodeservice.exception;

public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String msg) {
  super(msg);
 }
}