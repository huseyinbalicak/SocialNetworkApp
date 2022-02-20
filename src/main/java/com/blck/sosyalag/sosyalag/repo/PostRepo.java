package com.blck.sosyalag.sosyalag.repo;

import com.blck.sosyalag.sosyalag.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

}
