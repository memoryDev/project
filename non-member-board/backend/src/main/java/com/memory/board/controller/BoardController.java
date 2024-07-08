package com.memory.board.controller;

import com.memory.board.dto.BoardDTO;
import com.memory.board.dto.BoardSearchDTO;
import com.memory.board.entity.Board;
import com.memory.board.entity.enums.DeleteStatus;
import com.memory.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/board")
    public ResponseEntity<Page<BoardDTO>> getBoardList(@RequestBody(required = false) BoardSearchDTO searchDTO,
                                                       @PageableDefault(size = 10) Pageable pageable ) {

        System.out.println(searchDTO);

        Page<BoardDTO> dtoList = boardService.selectBoardList(pageable, searchDTO);

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/board/{boardId}")
    public ResponseEntity<BoardDTO> getBoard(@PathVariable("boardId") Long boardId) {

        if (boardId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        BoardDTO boardDTO = boardService.boardDetail(boardId);

        return new ResponseEntity<>(boardDTO, HttpStatus.OK);
    }
}
