//package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.services;
////
////import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.comment.CommentCreateDTO;
////import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.comment.CommentDTO;
////import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.comment.CommentSummaryDTO;
////import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.comment.CommentUpdateDTO;
//import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.exceptions.ResourceNotFoundException;
////import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.Comment;
//import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models.User;
//import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.repository.CommentRepository;
//import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.repository.UserRepository;
//import jakarta.transaction.Transactional;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//
//import java.util.List;
//
////import static com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.comment.CommentDTO.toSummaryDTO;
//
//@Service
//@Transactional
//public class CommentService {
//    private final CommentRepository commentRepository;
//    private final UserRepository toDoUserRepository;
//
//    public CommentService(CommentRepository commentRepository, UserRepository toDoUserRepository) {
//        this.commentRepository = commentRepository;
//        this.toDoUserRepository = toDoUserRepository;
//    }
//
//
//    public CommentSummaryDTO create(CommentCreateDTO dto) {
//        // correcte manier! user ophalen zonder user te hardcoden.
//        User user = toDoUserRepository.findById(dto.userId()).orElseThrow(() -> new UsernameNotFoundException("User ID not found:" + dto.userId()));
//        Comment comment = new Comment();
////        comment.setContent(dto.content());
////        comment.setTaskId(dto.taskId());
////        comment.setUserId(dto.userId());
//        return toSummaryDTO(commentRepository.save(comment));
//    }
//
//    public List<CommentSummaryDTO> getAll() {
//        return commentRepository.findAll().stream()
//                .map(CommentDTO::toSummaryDTO)
//                .toList();
//    }
//
//    public List<CommentSummaryDTO> getByTaskId(Long taskId) {
//        return commentRepository.findByTaskId(taskId).stream()
//                .map(CommentDTO::toSummaryDTO)
//                .toList();
//    }
//
//    public CommentSummaryDTO getById(Long id) {
//        Comment comment = commentRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
//        return toSummaryDTO(comment);
//    }
//
//    public CommentSummaryDTO update(Long id, CommentUpdateDTO dto) {
//        Comment comment = commentRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
//        comment.setContent(dto.content());
//        return toSummaryDTO(commentRepository.save(comment));
//    }
//
//    public void delete(Long id) {
//        if (!commentRepository.existsById(id)) {
//            throw new ResourceNotFoundException("Comment not found");
//        }
//        commentRepository.deleteById(id);
//    }
//}
//
////    private CommentSummaryDTO toSummaryDTO(Comment comment) {
////        return new CommentSummaryDTO(
////                comment.getId(),
////                comment.getContent(),
////                comment.getTaskId(),
////                comment.getUserId()
//////        );
////    }
////}
//
//
