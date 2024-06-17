package com.LoopPop.LoopPop.Comment;

import com.LoopPop.LoopPop.LoopPop_User.LoopPop_User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByContent(String content);
    List<Comment> findByTag(String tag);
    // Other query methods as needed
}
