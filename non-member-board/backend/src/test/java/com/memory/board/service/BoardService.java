package com.memory.board.service;

import com.memory.board.dto.BoardDTO;
import com.memory.board.entity.Board;
import com.memory.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;


    public List<Board> selectBoardList() {
        return boardRepository.findAll();
    }

}
