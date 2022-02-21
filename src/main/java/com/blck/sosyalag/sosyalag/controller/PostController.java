package com.blck.sosyalag.sosyalag.controller;

import com.blck.sosyalag.sosyalag.model.Comment;
import com.blck.sosyalag.sosyalag.model.Post;
import com.blck.sosyalag.sosyalag.repo.CommentRepo;
import com.blck.sosyalag.sosyalag.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;

@Controller
public class PostController {


    private  final PostService postService;
    private  final CommentRepo commentRepository;


    public PostController(PostService postService, CommentRepo commentRepository) {
        this.postService = postService;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "post/list";
    }

    @GetMapping("/post/{id}")
    public String read(@PathVariable Long id, Model model) {
        Optional<Post> post = postService.findById(id);
        if( post.isPresent() ) {
            Post currentPost = post.get();
            Comment comment = new Comment();
            comment.setPost(currentPost);
            model.addAttribute("comment", comment);
            model.addAttribute("post", currentPost);
            model.addAttribute("success", model.containsAttribute("success"));
            return "post/view";

        } else {
            return "redirect:/ ";
        }
    }

    @GetMapping("/post/submit")
    public String newPostForm(Model model){
        model.addAttribute("post", new Post());
        return "post/submit";
    }

    @PostMapping("/post/submit")
    public String createPost(Post post, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if( bindingResult.hasErrors()){
            model.addAttribute("post", post);
            return "post/submit";
        } else {
            postService.save(post);
            redirectAttributes
                    .addAttribute("id", post.getId())
                    .addFlashAttribute("success", true);
            return "redirect:/post/{id}";

        }
    }

    @PostMapping("/post/comments")
    public String addComment(Comment comment, BindingResult bindingResult){

        commentRepository.save(comment);

        return "redirect:/post/" + comment.getPost().getId();
    }

}