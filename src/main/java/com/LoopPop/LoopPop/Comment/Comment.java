package com.LoopPop.LoopPop.Comment;

import com.LoopPop.LoopPop.LoopPop_User.LoopPop_User;
import jakarta.persistence.*;

import java.time.LocalDate;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
public class Comment {

    private static final Logger logger = LoggerFactory.getLogger(Comment.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private String tag;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private LoopPop_User user;

    // Add logging statements
    @PostLoad
    @PostPersist
    @PostUpdate
    private void logFields() {
        logger.info("id: {}", id);
        logger.info("content: {}", content);
        logger.info("tag: {}", tag);
        logger.info("user: {}", user);
    }

    // Getters and setters

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
