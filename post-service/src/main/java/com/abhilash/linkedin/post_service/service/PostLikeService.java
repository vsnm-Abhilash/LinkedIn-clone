package com.abhilash.linkedin.post_service.service;

import com.abhilash.linkedin.post_service.entity.PostLike;
import com.abhilash.linkedin.post_service.exception.BadRequestException;
import com.abhilash.linkedin.post_service.exception.ResourceNotFoundException;
import com.abhilash.linkedin.post_service.repository.PostLikesRepository;
import com.abhilash.linkedin.post_service.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeService {

    private final PostLikesRepository postLikesRepository;
    private final PostsRepository postsRepository;

    public void likePost(Long postId,Long userId) {
        boolean exists=postsRepository.existsById(postId);
        if(!exists) throw new ResourceNotFoundException("Post not found with Id "+postId);
        boolean alreadyLiked=postLikesRepository.existsByUserIdAndPostId(userId,postId);
        if(alreadyLiked) throw new BadRequestException( "Cannot Like the same post again .");

        PostLike postLike=new PostLike();
        postLike.setPostId(postId);
        postLike.setUserId(userId);
        postLikesRepository.save(postLike);
    }

    public void unLikePost(Long postId, Long userId) {
        boolean exists=postsRepository.existsById(postId);
        if(!exists) throw new ResourceNotFoundException("Post not found with Id "+postId);
        boolean alreadyLiked=postLikesRepository.existsByUserIdAndPostId(userId,postId);
        if(!alreadyLiked) throw new BadRequestException( "Cannot unlike the post again which is not liked.");
        postLikesRepository.deleteByUserIdAndPostId(userId,postId);
    }
}
