package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.ArrayList;

/**
 * Account
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models
 *
 * @author Stefan Kiers
 * @since 11-1-2026
 */
@Entity
@Data
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    private String username;

    private String password;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "creation_date")
    private OffsetDateTime creationDate;

//    @OneToMany(mappedBy = "author")
//    private List<Post> posts = new ArrayList<>();
//
//    @ManyToMany(mappedBy = "likes")
//    private List<Post> likes = new ArrayList<>();
}
