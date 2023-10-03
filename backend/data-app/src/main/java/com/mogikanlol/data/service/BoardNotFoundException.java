package com.mogikanlol.data.service;

public class BoardNotFoundException extends RuntimeException {
    public BoardNotFoundException(String id) {
        super(String.format("Board with id = %s is not found", id));
    }
}
