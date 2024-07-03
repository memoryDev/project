package com.memory.board.entity;

import com.memory.board.dto.BoardDTO;
import com.memory.board.entity.enums.DeleteStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Getter
@Setter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id", nullable = true)
    private Long id;

    @Column(length = 255, nullable = false)
    private String nickname;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(length = 255, nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    @Enumerated(STRING)
    @Column(nullable = false)
    @ColumnDefault("'N'")
    private DeleteStatus delYn;

    //== dto 생성 메서드 ==//
    public BoardDTO toDTO() {
        BoardDTO dto = new BoardDTO(
                nickname,
                password,
                title,
                content,
                createdDate,
                modifiedDate,
                delYn
        );

        return dto;
    }


}
