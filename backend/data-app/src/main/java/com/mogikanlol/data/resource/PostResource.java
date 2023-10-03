package com.mogikanlol.data.resource;

import com.mogikanlol.data.domain.Post;
import com.mogikanlol.data.dto.NewPostRequest;
import com.mogikanlol.data.dto.PostDto;
import com.mogikanlol.data.dto.PostPatchDto;
import com.mogikanlol.data.mapper.PostMapper;
import com.mogikanlol.data.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostResource {

    private final PostService postService;
    private final PostMapper postMapper;

    @PostMapping
    public PostDto create(@RequestBody @Valid NewPostRequest request) {
        Post post = postService.create(request);
        return postMapper.map(post);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable("id") Long id) {
        return postService.delete(id);
    }

    @PatchMapping("/{id}")
    public PostDto update(@PathVariable("id") Long id, @RequestBody PostPatchDto postDto) {
        Post post = postService.update(id, postDto);
        return postMapper.map(post);
    }
}
