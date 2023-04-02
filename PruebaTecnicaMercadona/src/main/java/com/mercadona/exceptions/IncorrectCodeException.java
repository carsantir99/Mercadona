package com.mercadona.exceptions;

public class IncorrectCodeException extends Exception{
	
    public IncorrectCodeException() {
        super("El código pasado como parámetro no existe.");
    }
}
