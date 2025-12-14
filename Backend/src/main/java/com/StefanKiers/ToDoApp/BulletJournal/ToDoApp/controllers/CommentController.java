//package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.controllers;
//
////import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.comment.CommentCreateDTO;
////import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.comment.CommentSummaryDTO;
////import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.comment.CommentUpdateDTO;
////import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.services.CommentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//public class CommentController {
//    @Autowired
//    private final CommentService commentService;
//
//    public CommentController(CommentService commentService) {
//        this.commentService = commentService;
//    }
//
//    @PostMapping
//    public ResponseEntity<CommentSummaryDTO> create(@RequestBody CommentCreateDTO dto) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.create(dto));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<CommentSummaryDTO>> getAll() {
//        return ResponseEntity.ok(commentService.getAll());
//    }
//
//    @GetMapping("/task/{taskId}")
//    public ResponseEntity<List<CommentSummaryDTO>> getByTaskId(@PathVariable Long taskId) {
//        return ResponseEntity.ok(commentService.getByTaskId(taskId));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<CommentSummaryDTO> getById(@PathVariable Long id) {
//        return ResponseEntity.ok(commentService.getById(id));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<CommentSummaryDTO> update(@PathVariable Long id, @RequestBody CommentUpdateDTO dto) {
//        return ResponseEntity.ok(commentService.update(id, dto));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        commentService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
//}
