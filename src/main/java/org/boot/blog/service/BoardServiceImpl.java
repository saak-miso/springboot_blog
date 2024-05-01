package org.boot.blog.service;

import org.boot.blog.persistence.BoardRepository;

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
    @Qualifier("boardRepository")
    private BoardRepository boardRepository;

    @Override
    public BoardModel boardInfo(BoardModel boardModel) throws Exception {
        return boardRepository.selectBoardInfo(boardModel);
    }

    @Override
    public int boardCount(BoardModel boardModel) throws Exception {
        return boardRepository.selectBoardCount(boardModel);
    }

    @Override
    public List<BoardModel> boardList(BoardModel boardModel, int offset, int limitRow) throws Exception {
        return boardRepository.selectBoardList(boardModel, offset, limitRow);
    }

    @Override
    public int insertBoard(BoardModel boardModel) throws Exception {
        return boardRepository.insertBoard(boardModel);
    }

    @Override
    public int updateBoard(BoardModel boardModel) throws Exception {
        return boardRepository.updateBoard(boardModel);
    }

    @Override
    public int deleteBoard(BoardModel boardModel) throws Exception {
        return boardRepository.deleteBoard(boardModel);
    }
}