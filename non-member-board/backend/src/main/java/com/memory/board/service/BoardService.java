package com.memory.board.service;

import com.memory.board.dto.BoardDTO;
import com.memory.board.dto.BoardSearchDTO;
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

    public Page<BoardDTO> selectBoardList(Pageable pageable, BoardSearchDTO searchDTO) {
        return boardRepository.findBoardList(searchDTO, pageable);
    }

    public BoardDTO boardDetail(Long boardId) {
        return boardRepository.findBoardByBoardIdAndDelYn(boardId);
    }

}
