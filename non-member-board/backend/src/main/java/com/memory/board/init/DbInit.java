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
        Thread.sleep(10);
        BoardDTO dtoG = new BoardDTO("닉네임G", "passwordF", "제목F", "내용F", LocalDateTime.now(), null, DeleteStatus.N);
        Thread.sleep(10);
        BoardDTO dtoH = new BoardDTO("닉네임H", "passwordF", "제목F", "내용F", LocalDateTime.now(), null, DeleteStatus.N);
        Thread.sleep(10);
        BoardDTO dtoI = new BoardDTO("닉네임I", "passwordF", "제목F", "내용F", LocalDateTime.now(), null, DeleteStatus.N);
        Thread.sleep(10);
        BoardDTO dtoJ = new BoardDTO("닉네임J", "passwordF", "제목F", "내용F", LocalDateTime.now(), null, DeleteStatus.N);
        Thread.sleep(10);
        BoardDTO dtoK = new BoardDTO("닉네임K", "passwordF", "제목F", "내용F", LocalDateTime.now(), null, DeleteStatus.N);
        Thread.sleep(10);
        BoardDTO dtoL = new BoardDTO("닉네임L", "passwordF", "제목F", "내용F", LocalDateTime.now(), null, DeleteStatus.N);
        Thread.sleep(10);
        BoardDTO dtoM = new BoardDTO("닉네임M", "passwordF", "제목F", "내용F", LocalDateTime.now(), null, DeleteStatus.N);
        Thread.sleep(10);
        BoardDTO dtoN = new BoardDTO("닉네임N", "passwordF", "제목F", "내용F", LocalDateTime.now(), null, DeleteStatus.N);
        Thread.sleep(10);
        BoardDTO dtoO = new BoardDTO("닉네임O", "passwordF", "제목F", "내용F", LocalDateTime.now(), null, DeleteStatus.N);

        boardRepository.save(dtoA.toEntity());
        boardRepository.save(dtoB.toEntity());
        boardRepository.save(dtoC.toEntity());
        boardRepository.save(dtoD.toEntity());
        boardRepository.save(dtoE.toEntity());
        boardRepository.save(dtoF.toEntity());
        boardRepository.save(dtoG.toEntity());
        boardRepository.save(dtoH.toEntity());
        boardRepository.save(dtoI.toEntity());
        boardRepository.save(dtoJ.toEntity());
        boardRepository.save(dtoK.toEntity());
        boardRepository.save(dtoL.toEntity());
        boardRepository.save(dtoM.toEntity());
        boardRepository.save(dtoN.toEntity());
        boardRepository.save(dtoO.toEntity());
    }
}
