package ru.kata.spring.boot_security.demo.exception;

public class MyNotRoleException extends RuntimeException{

    public MyNotRoleException(String message) {
        super(message);
    }
}
