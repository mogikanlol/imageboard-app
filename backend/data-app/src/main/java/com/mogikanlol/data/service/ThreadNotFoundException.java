package com.mogikanlol.data.service;

public class ThreadNotFoundException extends RuntimeException {
    public ThreadNotFoundException(Long id) {
        super(String.format("Thread with id = %s is not found", id));
    }
}
