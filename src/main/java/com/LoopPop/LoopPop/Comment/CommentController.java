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

// Mark this class as a REST controller, which will handle HTTP requests and return JSON responses
@RestController
// Specify the base path for all endpoints in this controller
@RequestMapping(path = "api/v1/comments")
public class CommentController {

    // Dependencies that will be injected
    private final CommentService commentService;
    private final LoopPop_UserService userService;

    // Constructor for dependency injection
    @Autowired
    public CommentController(CommentService commentService, LoopPop_UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    // Endpoint to add a new comment
    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody CommentRequest commentRequest, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            // Return a JSON response for unauthorized access if the user is not authenticated
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("error", "Unauthorized"));
        }
        // Retrieve the user based on their email (username)
        LoopPop_User user = userService.findByEmail(userDetails.getUsername());
        if (user == null) {
            // Return a JSON response for user not found if the user does not exist
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("error", "User not found"));
        }
        // Create a new Comment object and set its fields based on the request
        Comment comment = new Comment();
        comment.setContent(commentRequest.getContent());
        comment.setTag(commentRequest.getTag());
        comment.setUser(user); // Associate the comment with the retrieved user

        // Save the new comment using the comment service and return it in the response
        Comment newComment = commentService.addComment(comment, user.getId());
        return ResponseEntity.ok(newComment);
    }

    // Endpoint to retrieve all comments
    @GetMapping
    public List<Comment> getAllComments() {
        // Use the comment service to get all comments and return them
        return commentService.getAllComments();
    }
}
