package com.memory.board.service;

import com.memory.board.dto.BoardDTO;
import com.memory.board.dto.BoardSearchDTO;
import com.memory.board.dto.DeleteBoardDTO;
import com.memory.board.entity.Board;
import com.memory.board.entity.enums.DeleteStatus;
import com.memory.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;

    public Page<BoardDTO> selectBoardList(Pageable pageable, BoardSearchDTO searchDTO) {
        return boardRepository.findBoardList(searchDTO, pageable);
    }

    public BoardDTO boardDetail(Long boardId) {
        return boardRepository.findBoardByBoardIdAndDelYn(boardId);
    }

    public Boolean deleteBoard(Long boardId, DeleteBoardDTO boardDTO) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);

        // 조회 결과 여부
        if (!optionalBoard.isPresent()) {
            return false;
        }

        Board board = optionalBoard.get();

        //1. password check
        if (!board.getPassword().equals(boardDTO.getPassword())) {
            return false;
        }

        // 게시물 삭제처리
        board.setDelYn(DeleteStatus.Y);
        return true;

    }
}
