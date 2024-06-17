package com.LoopPop.LoopPop.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.LoopPop.LoopPop.LoopPop_User.LoopPop_UserRepository;
import com.LoopPop.LoopPop.LoopPop_User.LoopPop_User;



@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private LoopPop_UserRepository userRepository;

    public Comment addComment(Comment comment, Long userId) {
        LoopPop_User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        comment.setUser(user);
        return commentRepository.save(comment);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
}