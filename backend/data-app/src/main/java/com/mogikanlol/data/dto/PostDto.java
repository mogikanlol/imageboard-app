package com.mogikanlol.data.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PostDto {

    private Long id;

    private String content;
}
