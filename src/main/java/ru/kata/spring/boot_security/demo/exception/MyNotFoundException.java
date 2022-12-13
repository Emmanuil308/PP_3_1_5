package ru.kata.spring.boot_security.demo.exception;

public class MyNotFoundException extends RuntimeException {

    public MyNotFoundException(String message) {
        super(message);
    }
}
