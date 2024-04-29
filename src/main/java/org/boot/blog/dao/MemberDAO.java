package org.boot.blog.dao;

import org.apache.ibatis.annotations.Mapper;
import org.boot.blog.model.MemberModel;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

// Spring에서 Mapper 클래스

@Mapper
@Repository("memberDAO")
public interface MemberDAO {
    public List<MemberModel> selectAllMemberList() throws DataAccessException;
    public int insertMember(MemberModel memberVO) throws DataAccessException ;
    public int deleteMember(String id) throws DataAccessException;
    public MemberModel loginById(MemberModel memberVO) throws DataAccessException;
}
