package com.memory.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.memory.board.dto.BoardSearchDTO;
import com.memory.board.entity.enums.DeleteStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@Slf4j
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("게시판 목록 조회")
    void getBoardListTest() throws Exception {

        BoardSearchDTO searchDTO = new BoardSearchDTO("nickname", "a");

        mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/board")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(searchDTO))
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("게시판 상세 페이지 조회")
    void getBoardTest() throws Exception {
        Long boardId = 1L;

        mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/board/" + boardId)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("nickname").value("닉네임A"))
                .andExpect(MockMvcResultMatchers.jsonPath("password").value("passwordA"))
                .andExpect(MockMvcResultMatchers.jsonPath("title").value("제목A"))
                .andExpect(MockMvcResultMatchers.jsonPath("content").value("내용A"))
                .andExpect(MockMvcResultMatchers.jsonPath("delYn").value(DeleteStatus.N.toString()));
    }

}