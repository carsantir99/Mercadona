package com.mercadona.exceptions;

public class EmptyNameException extends Exception{
    public EmptyNameException() {
        super("El campo nombre no puede estar vacío.");
    }
}
