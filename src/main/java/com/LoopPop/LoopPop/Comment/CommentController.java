package com.LoopPop.LoopPop.Comment;

import com.LoopPop.LoopPop.LoopPop_User.LoopPop_User;
import com.LoopPop.LoopPop.LoopPop_User.LoopPop_UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;


import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/comments")
public class CommentController {

    private final CommentService commentService;
    private final LoopPop_UserService userService;

    @Autowired
    public CommentController(CommentService commentService, LoopPop_UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody CommentRequest commentRequest, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            // Return a JSON response for unauthorized access
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("error", "Unauthorized"));
        }

        LoopPop_User user = userService.findByEmail(userDetails.getUsername());
        if (user == null) {
            // Return a JSON response for user not found
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("error", "User not found"));
        }

        Comment comment = new Comment();
        comment.setContent(commentRequest.getContent());
        comment.setTag(commentRequest.getTag());
        comment.setUser(user); // Associate the comment with the retrieved user
        Comment newComment = commentService.addComment(comment, user.getId());
        return ResponseEntity.ok(newComment);
    }

    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }
}
