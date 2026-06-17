package com.victorhugo.familyservicemanager.service;

import com.victorhugo.familyservicemanager.model.Member;
import com.victorhugo.familyservicemanager.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    //dependency injection
    private MemberRepository memberRepository;

    //Constructor
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }


    //Methods and logic

    //Get all Members
    public List<Member> getMembers(){
        return memberRepository.findAll();
    }

    //Create a new member
    public Member createMember(Member member){
        return memberRepository.save(member);

    }

    //Create new Members
    public List<Member> createMembers(List<Member> members){
        return memberRepository.saveAll(members);
    }

    //Delete member by id
    public void deleteMember(Long id){
        memberRepository.deleteById(id);
    }

    //Delete members by ids
    public void deleteMembers(List<Long> ids){
         memberRepository.deleteAllByIdInBatch(ids);
    }

    //Update a member by id
    public Member updateMember(Long id, Member member){
        Member existingMember = memberRepository.findById(id).orElseThrow();
        existingMember.setName(member.getName());

        return memberRepository.save(existingMember);
    }

}
