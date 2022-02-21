package com.blck.sosyalag.sosyalag.service;

import com.blck.sosyalag.sosyalag.model.Post;
import com.blck.sosyalag.sosyalag.repo.PostRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepo postRepository;

    public PostService(PostRepo postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    public void save(Post post) {
        postRepository.save(post);
    }


}
