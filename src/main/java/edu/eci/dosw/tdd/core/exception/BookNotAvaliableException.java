package edu.eci.dosw.tdd.core.exception;



public class BookNotAvaliableException extends RuntimeException {

    public BookNotAvaliableException(String message) {
        super(message);
    }
    public BookNotAvaliableException(String message, Throwable cause) {
        super(message, cause);
    }
}

