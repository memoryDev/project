package com.memory.board.dto;

import com.memory.board.entity.Board;
import com.memory.board.entity.enums.DeleteStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardDTO {

    private String nickname;            /* 작성자 닉네임 */
    private String password;            /* 게시글 비밀번호 */
    private String title;               /* 게시글 제목 */
    private String content;             /* 게시글 내용*/
    private LocalDateTime createdDate;  /* 게시글 작성일 */
    private LocalDateTime modifiedDate; /* 게시글 수정일 */
    private DeleteStatus delYn;         /* 게시글 삭제여부 */

    public BoardDTO(String nickname, String password, String title, String content, LocalDateTime createdDate, LocalDateTime modifiedDate, DeleteStatus delYn) {
        this.nickname = nickname;
        this.password = password;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.delYn = delYn;
    }

    //== entity 생성 메서드 ==//
    public Board toEntity() {
        Board board = new Board();
        board.setNickname(nickname);
        board.setPassword(password);
        board.setTitle(title);
        board.setContent(content);
        board.setCreatedDate(createdDate);
        board.setDelYn(delYn);

        return board;
    }


}
