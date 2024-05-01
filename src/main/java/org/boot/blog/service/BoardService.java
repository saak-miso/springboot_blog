package org.boot.blog.service;

import org.boot.blog.model.BoardModel;
import org.boot.blog.model.MemberModel;

import java.util.List;

public interface BoardService {

    public BoardModel boardInfo(BoardModel boardModel) throws Exception;

    public int boardCount(BoardModel boardModel) throws Exception;

    public List<BoardModel> boardList(BoardModel boardModel, int offset, int limitRow) throws Exception;

    int insertBoard(BoardModel boardModel) throws Exception;

    int updateBoard(BoardModel boardModel) throws Exception;

    int deleteBoard(BoardModel boardModel) throws Exception;
}
