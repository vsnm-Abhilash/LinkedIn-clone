package com.abhilash.linkedin.user_service.repsitory;

import com.abhilash.linkedin.user_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

}
