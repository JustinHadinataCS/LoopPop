package com.LoopPop.LoopPop.Comment;

import com.LoopPop.LoopPop.LoopPop_User.LoopPop_User;
import jakarta.persistence.*;

import java.time.LocalDate;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Mark this class as a JPA entity (a class that is mapped to a database table)
@Entity
public class Comment {

    // Define a logger for this class
    private static final Logger logger = LoggerFactory.getLogger(Comment.class);

    // Specify the primary key of the entity and define the strategy for generating the primary key value
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Define fields to map to columns in the comment table
    private String content;
    private String tag;

    // Define a many-to-one relationship between Comment and LoopPop_User
    // Each comment is associated with one user, but a user can have many comments
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Specify the foreign key column and make it non-nullable
    private LoopPop_User user;

    // Define a method to log the fields of the entity
    // This method will be called after the entity is loaded, persisted, or updated
    @PostLoad
    @PostPersist
    @PostUpdate
    private void logFields() {
        logger.info("id: {}", id);         // Log the id field
        logger.info("content: {}", content); // Log the content field
        logger.info("tag: {}", tag);       // Log the tag field
        logger.info("user: {}", user);     // Log the user field
    }

    // Getters and setters for the fields

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public LoopPop_User getUser() {
        return user;
    }

    public void setUser(LoopPop_User user) {
        this.user = user;
    }
}
