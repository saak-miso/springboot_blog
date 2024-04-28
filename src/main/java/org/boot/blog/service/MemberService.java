package org.boot.blog.service;

import org.boot.blog.vo.MemberVO;

import java.util.List;

// persistence로 이동

public interface MemberService {

    public List<MemberVO> listMembers() throws Exception;
    public int addMember(MemberVO memberVO) throws Exception;
    public int removeMember(String id) throws Exception;
    public MemberVO login(MemberVO memberVO) throws Exception;
}