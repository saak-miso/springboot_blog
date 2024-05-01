package org.boot.blog.persistence;

import org.boot.blog.model.BoardModel;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("boardRepository")
public interface BoardRepository {

    public BoardModel selectBoardInfo(BoardModel boardModel) throws DataAccessException;

    public int selectBoardCount(BoardModel boardModel) throws DataAccessException;

    public List<BoardModel> selectBoardList(BoardModel boardModel, int offset, int limitRow) throws DataAccessException;

    int insertBoard(BoardModel boardModel) throws Exception;

    int updateBoard(BoardModel boardModel) throws Exception;

    int deleteBoard(BoardModel boardModel) throws Exception;
}
