package org.boot.blog.service;

import java.util.List;

import org.boot.blog.dao.MemberDAO;
import org.boot.blog.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("memberService")
@Transactional(propagation = Propagation.REQUIRED)
public class MemberServiceImpl implements MemberService {

    @Autowired
    @Qualifier("memberDAO")
    private MemberDAO memberDAO;

    @Override
    public List<MemberVO> listMembers() throws Exception {

        System.out.println("2 @@@@@@@@@@");

        List<MemberVO> membersList = null;
        membersList = memberDAO.selectAllMemberList();
        return membersList;
    }

    @Override
    public int addMember(MemberVO member) throws Exception {
        return memberDAO.insertMember(member);
    }

    @Override
    public int removeMember(String id) throws Exception {
        return memberDAO.deleteMember(id);
    }

    public MemberVO login(MemberVO memberVO) throws Exception {
        return memberDAO.loginById(memberVO);
    }
}