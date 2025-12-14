package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.exceptions;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String message){
        super(message);
    }
}
