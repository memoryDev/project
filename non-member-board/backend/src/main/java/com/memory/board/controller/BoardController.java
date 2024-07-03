package com.memory.board.controller;

import com.memory.board.dto.BoardDTO;
import com.memory.board.dto.BoardSearchDTO;
import com.memory.board.entity.Board;
import com.memory.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board")
    public ResponseEntity<Page<BoardDTO>> getBoardList(@RequestBody(required = false) BoardSearchDTO searchDTO,
                                                       @PageableDefault(size = 5, page = 0) Pageable pageable ) {

        System.out.println("===== pageable =====");
        System.out.println(pageable);

        Page<BoardDTO> dtoList = boardService.selectBoardList(pageable, searchDTO);

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }
}
