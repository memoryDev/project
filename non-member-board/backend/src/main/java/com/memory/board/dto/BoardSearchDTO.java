package com.memory.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardSearchDTO {
    private String option;  /* 검색 타입 */
    private String keyword; /* 검색명 */
}
