package com.abhilash.linkedin.post_service.controller;

import com.abhilash.linkedin.post_service.service.PostLikeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class PostLikesController {

    private final PostLikeService postLikeService;
    @PostMapping("/{postId}")
    public ResponseEntity<Void> likePost(@PathVariable Long postId, HttpServletRequest servletRequest){
         postLikeService.likePost(postId,1L);
         return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> unLikePost(@PathVariable Long postId, HttpServletRequest servletRequest){
        postLikeService.unLikePost(postId,1L);
        return ResponseEntity.noContent().build();
    }

}
