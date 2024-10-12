package com.abhilash.linkedin.post_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostsDto {
    private Long id;
    private String content;
    private Long userId;
    private LocalDateTime createdAt;
}
