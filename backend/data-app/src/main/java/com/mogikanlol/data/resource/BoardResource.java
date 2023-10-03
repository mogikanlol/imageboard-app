package com.mogikanlol.data.resource;

import com.mogikanlol.data.domain.Board;
import com.mogikanlol.data.dto.BoardDto;
import com.mogikanlol.data.dto.BoardShortDto;
import com.mogikanlol.data.mapper.BoardMapper;
import com.mogikanlol.data.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/boards")
public class BoardResource {

    private final BoardService boardService;
    private final BoardMapper boardMapper;

    @GetMapping
    public List<BoardShortDto> getAll() {
        return boardService.getAll().stream()
                .map(boardMapper::mapToShort)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BoardDto getById(@PathVariable("id") String id) {
        Board board = boardService.getById(id);
        return boardMapper.map(board);
    }
}
