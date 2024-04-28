package org.boot.blog.dao;

import org.apache.ibatis.annotations.Mapper;
import org.boot.blog.vo.MemberVO;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

// Spring에서 Mapper 클래스

@Mapper
@Repository("memberDAO")
public interface MemberDAO {
    public List<MemberVO> selectAllMemberList() throws DataAccessException;
    public int insertMember(MemberVO memberVO) throws DataAccessException ;
    public int deleteMember(String id) throws DataAccessException;
    public MemberVO loginById(MemberVO memberVO) throws DataAccessException;
}
