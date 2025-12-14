package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.exceptions;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException(String message){
        super(message);
    }
}
