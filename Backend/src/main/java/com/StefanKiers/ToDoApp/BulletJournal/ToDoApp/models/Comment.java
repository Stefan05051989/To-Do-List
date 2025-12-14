//package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "Comments")
//public class Comment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String content;
////    private Long taskId;
////    private Long userId;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    @JsonIgnore // alleen aan many to one kant!
//    private User user;
//
//    public Comment(){}
//
//    public Long getId(){
//        return id;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
////    public Long getTaskId() {
////        return taskId;
////    }
////
////    public void setTaskId(Long taskId) {
////        this.taskId = taskId;
////    }
////
////    public Long getUserId() {
////        return userId;
////    }
////
////    public void setUserId(Long userId) {
////        this.userId = userId;
////    }
//}
