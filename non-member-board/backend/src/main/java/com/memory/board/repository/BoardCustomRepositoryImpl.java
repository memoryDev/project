package com.memory.board.repository;

import com.memory.board.dto.BoardDTO;
import com.memory.board.dto.BoardSearchDTO;
import com.memory.board.entity.Board;
import com.memory.board.entity.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Repository
public class BoardCustomRepositoryImpl implements BoardCustomRepository{

    private final JPAQueryFactory queryFactory;
    private final QBoard board;

    public BoardCustomRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
        this.board = QBoard.board;
    }

    @Override
    public Page<BoardDTO> findBoardList(BoardSearchDTO searchDTO, Pageable pageable) {

        QBoard board = QBoard.board;

        List<BoardDTO> content = queryFactory
                .select(Projections.constructor(BoardDTO.class,
                        board.nickname,
                        board.password,
                        board.title,
                        board.content,
                        board.createdDate,
                        board.modifiedDate,
                        board.delYn
                ))
                .from(board)
                .where(
                        containsNickname(searchDTO),
                        containsTitle(searchDTO)
                )
                .offset(pageable.getOffset()) //페이지 번호
                .limit(pageable.getPageSize()) //페이지 사이즈
                .fetch();

        return new PageImpl<>(content, pageable, content.size());
    }

    private BooleanExpression containsNickname(BoardSearchDTO searchDTO) {
        // null check
        if (searchDTO == null) {
            return null;
        }

        if (!searchDTO.getOption().equals("nickname")) {
            return null;
        }

        //빈값 check
        if (!StringUtils.hasText(searchDTO.getKeyword())) {
            return null;
        }

        return board.nickname.contains(searchDTO.getKeyword());
    }

    private BooleanExpression containsTitle(BoardSearchDTO searchDTO) {
        // null check
        if (searchDTO == null) {
            return null;
        }

        if (!searchDTO.getOption().equals("title")) {
            return null;
        }

        //빈값 check
        if (!StringUtils.hasText(searchDTO.getKeyword())) {
            return null;
        }

        return board.title.contains(searchDTO.getKeyword());
    }
}
