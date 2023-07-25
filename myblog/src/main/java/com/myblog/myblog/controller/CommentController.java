package com.myblog.myblog.controller;

import com.myblog.myblog.payload.CommentDto;
import com.myblog.myblog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //http://localhost:8080/api/posts/1/comments
    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable("postId") long postId, @RequestBody CommentDto commentDto){

        CommentDto dto = commentService.createComment(postId,commentDto);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable("postId") long postId){

        List<CommentDto> commentsByPostId = commentService.getCommentsByPostId(postId);


        return commentsByPostId;
    }

    @GetMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("postId") long postId, @PathVariable("commentId") long commentId){

        CommentDto commentById = commentService.getCommentById(postId, commentId);

        return new ResponseEntity<>(commentById,HttpStatus.OK);
    }

    @PutMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("postId") long postId,
                                                    @PathVariable("commentId") long commentId,
                                                    @RequestBody CommentDto commentDto){


        CommentDto dto = commentService.updateComment(postId,commentId,commentDto);

        return new ResponseEntity<>(dto,HttpStatus.OK);
    }


    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable("postId") long postId,@PathVariable("commentId") long commentId){
        commentService.deleteComment(postId,commentId);

        return new ResponseEntity<>("Comment is deleted successfully",HttpStatus.OK);
    }

}
