package com.LoopPop.LoopPop.LoopPop_User;
import com.LoopPop.LoopPop.Comment.Comment;
import jakarta.persistence.*;


import java.time.LocalDate;
import java.time.Period;
import java.util.List;

// Mark this class as a JPA entity (a class that is mapped to a database table)
@Entity
// Specify the details of the database table that this entity will map to
@Table(name = "looppop_user")
public class LoopPop_User {

    // Primary key field mapped to the 'id' column in the database
    @Id
    // Configure a sequence generator for generating unique IDs
    @SequenceGenerator(
            name = "mainUser_sequence",         // Name of the sequence generator
            sequenceName = "mainUser_sequence", // Name of the database sequence
            allocationSize = 1                  // Increment size for the sequence
    )
    // Specify the strategy for generating the primary key value
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE, // Use sequence-based ID generation
            generator =  "mainUser_sequence"    // Name of the sequence generator to use
    )
    private Long id;

    // Fields mapped to columns in the 'looppop_user' table
    private String name;
    private String email;
    private String hobby;
    private String favoriteMusic;

    // Column mapped to 'dob' in the database, not nullable, default value is current date
    @Column(nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate dob;

    // One-to-many relationship with Comment entities, mapped by the 'user' field in Comment class
    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    // Transient field not persisted in the database
    @Transient
    private Integer age;

    // Constructors

    // Constructor with all fields
    public LoopPop_User(Long id, String name, String email, String hobby, String favoriteMusic, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.hobby = hobby;
        this.favoriteMusic = favoriteMusic;
        this.dob = dob;
    }

    // Constructor without 'id' field (typically used when creating new entities)
    public LoopPop_User(String name, String email, String hobby, String favoriteMusic, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.hobby = hobby;
        this.favoriteMusic = favoriteMusic;
        this.dob = dob;
    }

    // Default constructor (required by JPA)
    public LoopPop_User() {
    }

    // Getters and Setters for all fields (required by JPA)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getFavoriteMusic() {
        return favoriteMusic;
    }

    public void setFavoriteMusic(String favoriteMusic) {
        this.favoriteMusic = favoriteMusic;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    // Calculate and return age based on 'dob' field
    public Integer getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    // Override toString method to provide a string representation of the object
    @Override
    public String toString() {
        return "LoopPop_User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", hobby='" + hobby + '\'' +
                ", favoriteMusic='" + favoriteMusic + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}

//this is the getter, setter and constructor, basically simpan data spaya Layer lain bsa pake.