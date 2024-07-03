package com.memory.board.repository;

import com.memory.board.dto.BoardDTO;
import com.memory.board.entity.Board;
import com.memory.board.entity.enums.DeleteStatus;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
class BoardRepositoryTest {

    private final BoardRepository boardRepository;
    private final EntityManager em;


    @Autowired
    public BoardRepositoryTest(BoardRepository boardRepository, EntityManager em) {
        this.boardRepository = boardRepository;
        this.em = em;
    }

    @Test
    @DisplayName("게시판 목록 조회")
    void selectBoardTest() {
        log.debug("{}", "===== 게시판 목록 조회 start =====");

        //then
        List<Board> boardList = boardRepository.findAll();

        //given
        Board findBoardA = boardRepository.findById(1L).orElse(new Board());
        Board findBoardB = boardRepository.findById(2L).orElse(new Board());
        Board findBoardC = boardRepository.findById(3L).orElse(new Board());

        assertThat(boardList.size()).isEqualTo(15);
        assertThat(boardList).contains(findBoardA, findBoardB, findBoardC);

        log.debug("{}", "===== 게시판 목록 조회 end =====");
    }

    @Test
    @DisplayName("게시판 상세 페이지 조회")
    void getBoardTest() throws Exception {

        BoardDTO dto = boardRepository.findBoardByBoardIdAndDelYn(1L);
//        log.info("board.id={}", dto.getId());

        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getContent()).isEqualTo("내용A");
        assertThat(dto.getNickname()).isEqualTo("닉네임A");
        assertThat(dto.getDelYn()).isEqualTo(DeleteStatus.N);
    }

}