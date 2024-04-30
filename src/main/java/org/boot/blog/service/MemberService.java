package org.boot.blog.service;

import org.boot.blog.model.MemberModel;

import java.util.List;

public interface MemberService {

    public List<MemberModel> listMembers() throws Exception;
    public int addMember(MemberModel MemberModel) throws Exception;
    public int removeMember(String id) throws Exception;
    public MemberModel login(MemberModel memberVO) throws Exception;
}