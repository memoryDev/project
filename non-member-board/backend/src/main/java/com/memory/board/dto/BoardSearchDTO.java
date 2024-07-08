package com.memory.board.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardSearchDTO {
    private String option;  /* 검색 타입 */
    private String keyword; /* 검색명 */
}
