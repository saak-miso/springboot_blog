package org.boot.blog.service;

import org.boot.blog.dao.BoardDAO;

import org.boot.blog.model.BoardModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("boardService")
@Transactional(propagation = Propagation.REQUIRED)
public class BoardServiceImpl implements BoardService {

    @Autowired
    @Qualifier("boardDAO")
    private BoardDAO boardDAO;

    @Override
    public BoardModel boardInfo(BoardModel boardModel) throws Exception {
        return boardDAO.selectBoardInfo(boardModel);
    }

    @Override
    public List<BoardModel> boardList() throws Exception {

        List<BoardModel> boardList = null;
        boardList = boardDAO.selectBoardList();
        return boardList;
    }

}