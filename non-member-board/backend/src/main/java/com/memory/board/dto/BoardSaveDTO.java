package com.memory.board.dto;

import com.memory.board.entity.Board;
import com.memory.board.entity.enums.DeleteStatus;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class BoardSaveDTO {

    private String nickname;
    private String password;
    private String title;
    private String content;

    //== entity 생성 메서드 ==//
    public Board toEntity() {
        Board board = new Board();
        board.setNickname(nickname);
        board.setPassword(password);
        board.setTitle(title);
        board.setContent(content);
        board.setCreatedDate(LocalDateTime.now());
        board.setDelYn(DeleteStatus.N);

        return board;
    }
}
