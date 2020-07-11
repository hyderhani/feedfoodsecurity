package org.hani.feedfood.security.exceptions;

public class InvalidDataException extends RuntimeException{
 InvalidDataException(String errorMessage) {
     super(errorMessage);
 }
}
