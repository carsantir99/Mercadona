package com.mercadona.exceptions;

public class DuplicatedCodeException extends Exception{
    public DuplicatedCodeException() {
        super("El código introducido ya existe.");
    }
}
