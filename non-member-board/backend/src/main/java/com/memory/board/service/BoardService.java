package com.memory.board.service;

import com.memory.board.entity.Board;
import com.memory.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Page<Board> selectBoardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

}
