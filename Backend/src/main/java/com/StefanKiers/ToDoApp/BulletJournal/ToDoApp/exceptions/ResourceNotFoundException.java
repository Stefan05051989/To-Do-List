package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message){
        super(message);
    }
}
