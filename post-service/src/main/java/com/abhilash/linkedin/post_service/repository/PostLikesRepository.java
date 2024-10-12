package com.abhilash.linkedin.post_service.repository;

import com.abhilash.linkedin.post_service.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PostLikesRepository extends JpaRepository<PostLike,Long> {
    boolean existsByUserIdAndPostId(Long userId,Long postId);

    @Transactional
    void deleteByUserIdAndPostId(Long userId, Long postId);
}
