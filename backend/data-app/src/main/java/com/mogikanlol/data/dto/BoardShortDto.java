package com.mogikanlol.data.dto;

import com.mogikanlol.data.domain.Board;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BoardShortDto {

    private String id;

    private String title;

    private Board.BoardGenre genre;

}
