package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.exceptions;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.globalerrordto.GlobalErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.access.AccessDeniedException;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<GlobalErrorResponseDTO> handleNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new GlobalErrorResponseDTO(ex.getMessage(), List.of()));
    }
    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<GlobalErrorResponseDTO> handleIncorrectPassword(IncorrectPasswordException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new GlobalErrorResponseDTO(ex.getMessage(), List.of()));
    }
    @ExceptionHandler(SamePasswordException.class)
    public ResponseEntity<GlobalErrorResponseDTO> handleSamePassword(SamePasswordException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new GlobalErrorResponseDTO(ex.getMessage(), List.of()));
    }
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<GlobalErrorResponseDTO> handleDuplicateEmail(DuplicateEmailException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new GlobalErrorResponseDTO(ex.getMessage(), List.of()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalErrorResponseDTO> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> details = ex.getBindingResult().getFieldErrors().stream().map(fieldError -> fieldError.getField() +
                ": " + fieldError.getDefaultMessage()).toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GlobalErrorResponseDTO("Validation failed", details));
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<GlobalErrorResponseDTO> handleAccessDenied(AccessDeniedException accessDeniedException){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GlobalErrorResponseDTO("Access denied.", List.of()));
    }
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<GlobalErrorResponseDTO> handleIllegalState(IllegalStateException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new GlobalErrorResponseDTO(ex.getMessage(), List.of()));
    }
}