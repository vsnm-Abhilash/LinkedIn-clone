package com.abhilash.linkedin.post_service.service;

import com.abhilash.linkedin.post_service.auth.UserContextHolder;
import com.abhilash.linkedin.post_service.clients.ConnectionClient;
import com.abhilash.linkedin.post_service.dto.PersonDto;
import com.abhilash.linkedin.post_service.dto.PostsCreateRequestDto;
import com.abhilash.linkedin.post_service.dto.PostsDto;
import com.abhilash.linkedin.post_service.entity.Post;
import com.abhilash.linkedin.post_service.exception.ResourceNotFoundException;
import com.abhilash.linkedin.post_service.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostsService {

    private final PostsRepository postsRepository;
    private final ModelMapper mapper;
    private final ConnectionClient connectionClient;

    public PostsDto createPost(PostsCreateRequestDto postsCreateRequestDto,  Long userId) {
        Post post= mapper.map(postsCreateRequestDto,Post.class);
        post.setUserId(userId);

        Post savedPost=postsRepository.save(post);
        return mapper.map(savedPost,PostsDto.class);
    }

    public PostsDto getPost(Long postId) {
        List<PersonDto> firstConnections=connectionClient.getMyFirstDegreeConnections();
        Post post= postsRepository.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("Post not found with Id "+postId)
        );
        return mapper.map(post,PostsDto.class);

    }

    public List<PostsDto> getAllPostsOfUser(Long userId) {
        List<Post> posts=postsRepository.findByUserId(userId);
        return posts.stream()
                .map(post->mapper.map(post,PostsDto.class))
                .collect(Collectors.toList());
    }
}
