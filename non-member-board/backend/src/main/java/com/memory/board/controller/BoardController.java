package com.memory.board.controller;

import com.memory.board.dto.BoardDTO;
import com.memory.board.dto.BoardSaveDTO;
import com.memory.board.dto.BoardSearchDTO;
import com.memory.board.dto.DeleteBoardDTO;
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
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/board")
    public ResponseEntity<Page<BoardDTO>> getBoardList(@RequestBody(required = false) BoardSearchDTO searchDTO,
                                                       @PageableDefault(size = 10) Pageable pageable ) {

        Page<BoardDTO> dtoList = boardService.selectBoardList(pageable, searchDTO);

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/board/{boardId}")
    public ResponseEntity<BoardDTO> getBoard(@PathVariable("boardId") Long boardId) {

        if (boardId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        BoardDTO boardDTO = boardService.boardDetail(boardId);

        if (boardDTO.getId() == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(boardDTO, HttpStatus.OK);
    }

    @DeleteMapping("/board/{boardId}")
    public ResponseEntity<String> deleteBoard(@PathVariable("boardId") Long boardId, @RequestBody DeleteBoardDTO boardDTO) {

        System.out.println(boardDTO);

        log.info("deleteBoard start");

        if (boardId == null) {
            log.info("deleteBoard boardId is null");
            return new ResponseEntity<>("잘못된 요청입니다", HttpStatus.BAD_REQUEST);
        }

        if (ObjectUtils.isEmpty(boardDTO)) {
            log.info("deleteBoard boardDTO is null");
            return new ResponseEntity<>("잘못된 요청입니다", HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.hasText(boardDTO.getPassword()) == false) {
            log.info("deleteBoard password is null");

            return new ResponseEntity<>("비밀번호를 입력해주세요.", HttpStatus.BAD_REQUEST);
        }

        Boolean isDelete = boardService.deleteBoard(boardId, boardDTO);
        if (!isDelete) {
            log.info("deleteBoard isDelete = {}", isDelete);
            return new ResponseEntity<>("비밀번호를 확인해주세요.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/board/save")
    public ResponseEntity<Object> saveBoard(@RequestBody BoardSaveDTO boardSaveDTO) {

        if (boardSaveDTO == null) {
            log.info("BoardController.saveBoard boardSaveDTO is null");
            return new ResponseEntity<>("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
        }

        // 유효성 체크
        Boolean isValidation = boardValidationCheck(boardSaveDTO);
        if (!isValidation) {
            log.info("BoardController.saveBoard.isValidation = {}", isValidation);
            return new ResponseEntity<>("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
        }

        Long savedId = boardService.saveBoard(boardSaveDTO);

        // 저장된 ID확인
        if (savedId == null || savedId == 0L) {
            log.info("BoardController.saveBoard savedId = {}", savedId);
            return new ResponseEntity<>("저장중 오류가 발생하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(savedId, HttpStatus.OK);
    }

    private Boolean boardValidationCheck (BoardSaveDTO boardSaveDTO) {

        if (!StringUtils.hasText(boardSaveDTO.getNickname())) {
            return false;
        }

        if (!StringUtils.hasText(boardSaveDTO.getPassword())) {
            return false;
        }

        if (!StringUtils.hasText(boardSaveDTO.getTitle())) {
            return false;
        }

        if (!StringUtils.hasText(boardSaveDTO.getContent())) {
            return false;
        }

        return true;
    }



}
