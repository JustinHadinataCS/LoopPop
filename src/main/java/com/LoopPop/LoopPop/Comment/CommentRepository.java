package com.LoopPop.LoopPop.Comment;

import com.LoopPop.LoopPop.LoopPop_User.LoopPop_User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

// Define an interface that extends JpaRepository, which provides CRUD operations for Comment entities
public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Method to find comments by their content
    List<Comment> findByContent(String content);

    // Method to find comments by their tag
    List<Comment> findByTag(String tag);

    // Other query methods can be added here as needed for specific query requirements
}
