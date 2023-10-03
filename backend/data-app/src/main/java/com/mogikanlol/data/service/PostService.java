package com.mogikanlol.data.service;

import com.mogikanlol.data.domain.Post;
import com.mogikanlol.data.domain.Thread;
import com.mogikanlol.data.dto.NewPostRequest;
import com.mogikanlol.data.dto.PostPatchDto;
import com.mogikanlol.data.repository.PostRepository;
import com.mogikanlol.data.repository.ThreadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final ThreadRepository threadRepository;
    private final PostRepository postRepository;

    public Post create(NewPostRequest request) {
        Thread thread = threadRepository.findById(request.getThreadId())
                .orElseThrow(() -> new ThreadNotFoundException(request.getThreadId()));

        Post post = new Post()
                .setThread(thread)
                .setContent(request.getContent());

        return postRepository.save(post);
    }

    public Long delete(Long id) {
        postRepository.deleteById(id);
        return id;
    }

    public Post update(Long id, PostPatchDto postDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));

        post.setContent(postDto.getContent());

        return postRepository.save(post);
    }
}
