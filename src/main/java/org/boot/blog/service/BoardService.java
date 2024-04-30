package org.boot.blog.service;

import org.boot.blog.model.BoardModel;
import org.boot.blog.model.MemberModel;

import java.util.List;

public interface BoardService {

    public BoardModel boardInfo(BoardModel boardModel) throws Exception;

    public List<BoardModel> boardList() throws Exception;
}
