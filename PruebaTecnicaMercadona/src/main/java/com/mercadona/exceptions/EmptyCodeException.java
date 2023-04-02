package com.mercadona.exceptions;

public class EmptyCodeException extends Exception{
    public EmptyCodeException() {
        super("El campo código no puede estar vacío.");
    }
}
