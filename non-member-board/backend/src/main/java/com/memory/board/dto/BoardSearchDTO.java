package com.memory.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardSearchDTO {
    private String option;  /* 검색 타입 */
    private String keyword; /* 검색명 */
}
