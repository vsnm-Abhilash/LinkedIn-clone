package com.abhilash.linkedin.post_service.controller;

import com.abhilash.linkedin.post_service.auth.UserContextHolder;
import com.abhilash.linkedin.post_service.dto.PostsCreateRequestDto;
import com.abhilash.linkedin.post_service.dto.PostsDto;
import com.abhilash.linkedin.post_service.entity.Post;
import com.abhilash.linkedin.post_service.service.PostsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class PostController {

    private final PostsService postsService;
    @PostMapping
    public ResponseEntity<PostsDto> createPost(@RequestBody PostsCreateRequestDto postsCreateRequestDto){
        PostsDto createdPost=postsService.createPost(postsCreateRequestDto, UserContextHolder.getCurrentUserId());
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }


    @GetMapping("/{postId}")
    public ResponseEntity<PostsDto> getPost(@PathVariable Long postId,HttpServletRequest servletRequest){
        PostsDto postsDto=postsService.getPost(postId);
        return ResponseEntity.ok(postsDto);
    }

    @GetMapping("/users/{userId}/allPosts")
    public ResponseEntity<List<PostsDto>> getAllPostsOfUser(@PathVariable Long userId){
        List<PostsDto> posts=postsService.getAllPostsOfUser(userId);
        return ResponseEntity.ok(posts);
    }
}
