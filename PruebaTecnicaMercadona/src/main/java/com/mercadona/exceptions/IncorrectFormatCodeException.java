package com.mercadona.exceptions;

public class IncorrectFormatCodeException extends Exception{
	
    public IncorrectFormatCodeException() {
        super("El formato del código pasado como parámetro no es correcto.");
    }
}
