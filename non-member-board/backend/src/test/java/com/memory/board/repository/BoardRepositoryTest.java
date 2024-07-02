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

        //when
        BoardDTO boardA = new BoardDTO("작성자A", "123456", "게시글 제목A", "게시글 내용A", LocalDateTime.now(), null, DeleteStatus.N);
        BoardDTO boardB = new BoardDTO("작성자B", "123456", "게시글 제목B", "게시글 내용B", LocalDateTime.now(), null, DeleteStatus.N);
        BoardDTO boardC = new BoardDTO("작성자C", "123456", "게시글 제목C", "게시글 내용C", LocalDateTime.now(), null, DeleteStatus.N);

        em.persist(boardA.toEntity());
        em.persist(boardB.toEntity());
        em.persist(boardC.toEntity());

        em.flush();
        em.clear();

        //then
        List<Board> boardList = boardRepository.findAll();

        //given
        Board findBoardA = boardRepository.findById(1L).orElse(new Board());
        Board findBoardB = boardRepository.findById(2L).orElse(new Board());
        Board findBoardC = boardRepository.findById(3L).orElse(new Board());

        Assertions.assertThat(boardList.size()).isEqualTo(3);
        Assertions.assertThat(boardList).contains(findBoardA, findBoardB, findBoardC);

        log.debug("{}", "===== 게시판 목록 조회 end =====");
    }

}