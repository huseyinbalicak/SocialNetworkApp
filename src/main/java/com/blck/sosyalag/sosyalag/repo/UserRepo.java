package com.blck.sosyalag.sosyalag.repo;

import com.blck.sosyalag.sosyalag.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
