package org.boot.blog.dao;

import org.boot.blog.model.BoardModel;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("boardDAO")
public interface BoardDAO {

    public BoardModel selectBoardInfo(BoardModel boardModel) throws DataAccessException;

    public List<BoardModel> selectBoardList() throws DataAccessException;
}
