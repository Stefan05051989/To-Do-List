package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",  nullable = false,  length = 100, unique = false)
    private Long id;
    @Column(name = "FIRST_NAME",  nullable = false,  length = 100, unique = false)
    private String firstName;
    @Column(name = "LAST_NAME",  nullable = false,  length = 100, unique = false)
    private String lastName;
    @JsonIgnore
    @Column(name = "PASSWORD", nullable = false,  length = 64)
    private String password;
    @Column(name = "EMAIL",  nullable = false,  length = 100, unique = false)
    private String email;
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth;
    @Column(name = "IS_ADMIN", nullable = false,  length = 50)
    private boolean isAdmin = false;


//    @OneToMany(mappedBy = "user")
//    List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<TaskList> taskList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<Task> tasks = new ArrayList<>();

    public User() {}
    public User(String firstName, String lastName, String password, String email, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
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
    public boolean isAdmin() {
        return isAdmin;
    }
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Transient
    public int getAge(){
        if (dateOfBirth == null){ // <- zo crasht app niet als er geen dob is.
            return 0;
        }
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
