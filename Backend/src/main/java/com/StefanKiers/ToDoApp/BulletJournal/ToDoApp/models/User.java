package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @JsonIgnore
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

//    @OneToMany(mappedBy = "user")
//    List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<TaskList> taskList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<Task> tasks = new ArrayList<>();

    public User() {}
    public User(String firstName, String lastName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}
