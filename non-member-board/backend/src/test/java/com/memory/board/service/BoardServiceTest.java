package com.memory.board.service;

import com.memory.board.dto.BoardDTO;
import com.memory.board.dto.BoardSearchDTO;
import com.memory.board.entity.Board;
import com.memory.board.entity.enums.DeleteStatus;
import com.memory.board.repository.BoardRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
class BoardServiceTest {

    private final BoardService boardService;
    private final BoardRepository boardRepository;
    private final EntityManager em;

    @Autowired
    public BoardServiceTest(BoardService boardService, BoardRepository boardRepository, EntityManager em) {
        this.boardService = boardService;
        this.boardRepository = boardRepository;
        this.em = em;
    }

    @Test
    @DisplayName("게시판 목록 조회 - 검색조건X")
    void selectBoardTest() {

        Pageable pageable = PageRequest.of(0, 5);

        //then
        Page<BoardDTO> page = boardService.selectBoardList(pageable, null);

        List<BoardDTO> boardList = page.getContent();

        //given
        BoardDTO findBoardA = boardRepository.findById(1L).orElse(new Board()).toDTO();
        BoardDTO findBoardB = boardRepository.findById(2L).orElse(new Board()).toDTO();
        BoardDTO findBoardC = boardRepository.findById(3L).orElse(new Board()).toDTO();

        assertThat(boardList)
                .extracting("nickname")
                .containsExactly("닉네임A", "닉네임B", "닉네임C", "닉네임D", "닉네임E");
    }

    @Test
    @DisplayName("게시판 목록 조회 - 검색조건O")
    void selectBoardSearchTest() {

        Pageable pageable = PageRequest.of(0, 5);
        BoardSearchDTO boardSearchDTO = new BoardSearchDTO("nickname", "A");

        //then
        Page<BoardDTO> page = boardService.selectBoardList(pageable, boardSearchDTO);

        List<BoardDTO> boardList = page.getContent();

        assertThat(boardList)
                .extracting("nickname")
                .containsExactly("닉네임A");
    }

    @Test
    @DisplayName("게시판 상세 조회")
    void boardDetailTest() {
        Long boardId = 1L;

        BoardDTO dto = boardService.boardDetail(boardId);
        assertThat(dto.getNickname()).isEqualTo("닉네임A");
        assertThat(dto.getDelYn()).isEqualTo(DeleteStatus.N);
        assertThat(dto.getContent()).isEqualTo("내용A");



    }

}