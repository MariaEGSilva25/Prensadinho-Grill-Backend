package com.unasp.Prensadinho.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(){
        super("Not found");
    }
}
