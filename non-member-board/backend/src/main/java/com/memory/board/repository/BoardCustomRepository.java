package com.memory.board.repository;

import com.memory.board.dto.BoardDTO;
import com.memory.board.dto.BoardSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BoardCustomRepository {
    Page<BoardDTO> findBoardList(BoardSearchDTO searchDTO, Pageable pageable);
}
