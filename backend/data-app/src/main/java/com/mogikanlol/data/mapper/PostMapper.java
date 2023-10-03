package com.mogikanlol.data.mapper;

import com.mogikanlol.data.domain.Post;
import com.mogikanlol.data.dto.PostDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostMapper {

    public PostDto map(Post entity) {
        return new PostDto()
                .setId(entity.getId())
                .setContent(entity.getContent());
    }

    public List<PostDto> map(List<Post> posts) {
        return posts.stream()
                .map(this::map)
                .toList();
    }
}
