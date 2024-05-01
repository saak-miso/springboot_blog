package org.boot.blog.service;

import java.util.List;

import org.boot.blog.persistence.MemberRepository;
import org.boot.blog.model.MemberModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("memberService")
@Transactional(propagation = Propagation.REQUIRED)
public class MemberServiceImpl implements MemberService {

    @Autowired
    @Qualifier("memberRepository")
    private MemberRepository memberRepository;

    @Override
    public List<MemberModel> listMembers() throws Exception {

        List<MemberModel> membersList = null;
        membersList = memberRepository.selectAllMemberList();
        return membersList;
    }

    @Override
    public int addMember(MemberModel member) throws Exception {
        return memberRepository.insertMember(member);
    }

    @Override
    public int removeMember(String id) throws Exception {
        return memberRepository.deleteMember(id);
    }

    public MemberModel login(MemberModel memberModel) throws Exception {
        return memberRepository.loginById(memberModel);
    }
}