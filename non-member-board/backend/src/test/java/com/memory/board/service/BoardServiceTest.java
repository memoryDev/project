package com.memory.board.service;

import com.memory.board.dto.BoardDTO;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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
    @DisplayName("게시판 목록 조회")
    void selectBoardTest() {
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
        List<Board> boardList = boardService.selectBoardList();

        //given
        Board findBoardA = boardRepository.findById(1L).orElse(new Board());
        Board findBoardB = boardRepository.findById(2L).orElse(new Board());
        Board findBoardC = boardRepository.findById(3L).orElse(new Board());

        Assertions.assertThat(boardList.size()).isEqualTo(3);
        Assertions.assertThat(boardList).contains(findBoardA, findBoardB, findBoardC);
    }

}