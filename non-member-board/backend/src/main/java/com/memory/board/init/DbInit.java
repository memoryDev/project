package com.memory.board.init;

import com.memory.board.dto.BoardDTO;
import com.memory.board.entity.enums.DeleteStatus;
import com.memory.board.repository.BoardRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class DbInit {

    private final BoardRepository boardRepository;

    /**
     * 초기 데이터 설정
     */
    @PostConstruct
    private void init() throws InterruptedException {
        BoardDTO dtoA = new BoardDTO("닉네임A", "passwordA", "제목A", "내용A", LocalDateTime.now(), null, DeleteStatus.N);
        Thread.sleep(10);
        BoardDTO dtoB = new BoardDTO("닉네임B", "passwordB", "제목B", "내용B", LocalDateTime.now(), null, DeleteStatus.N);
        Thread.sleep(10);
        BoardDTO dtoC = new BoardDTO("닉네임C", "passwordC", "제목C", "내용C", LocalDateTime.now(), null, DeleteStatus.N);
        Thread.sleep(10);
        BoardDTO dtoD = new BoardDTO("닉네임D", "passwordD", "제목D", "내용D", LocalDateTime.now(), null, DeleteStatus.N);
        Thread.sleep(10);
        BoardDTO dtoE = new BoardDTO("닉네임E", "passwordE", "제목E", "내용E", LocalDateTime.now(), null, DeleteStatus.N);
        Thread.sleep(10);
        BoardDTO dtoF = new BoardDTO("닉네임F", "passwordF", "제목F", "내용F", LocalDateTime.now(), null, DeleteStatus.N);

        boardRepository.save(dtoA.toEntity());
        boardRepository.save(dtoB.toEntity());
        boardRepository.save(dtoC.toEntity());
        boardRepository.save(dtoD.toEntity());
        boardRepository.save(dtoE.toEntity());
        boardRepository.save(dtoF.toEntity());
    }
}
