package com.mercadona.exceptions;

public class IncorrectEANCode extends Exception{
    public IncorrectEANCode() {
        super("El código EAN introducido no es válido.");
    }
}
