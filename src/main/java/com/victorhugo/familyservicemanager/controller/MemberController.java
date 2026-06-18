package com.victorhugo.familyservicemanager.controller;


import com.victorhugo.familyservicemanager.model.Member;
import com.victorhugo.familyservicemanager.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    //injection Dependency

    private final MemberService memberService;

    //Constructor
    public MemberController(MemberService memberService){
        this.memberService = memberService;

    }

    //Endpoints

    //Get all member
    @GetMapping
    public List<Member> getMembers(){
        return memberService.getMembers();
    }

    //Create a new member
    @PostMapping
    public Member createMember(@RequestBody Member member){
        return memberService.createMember(member);
    }

    //Create new members
    @PostMapping("/batch")
    public List<Member> createMembers(@RequestBody List<Member> members){
        return memberService.createMembers(members);
    }

    //Delete member by id
    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Long id){
        memberService.deleteMember(id);
    }

    //Delete members by id
    @DeleteMapping("/batch")
    public void deleteMembers(@RequestBody List<Long> ids){
         memberService.deleteMembers(ids);
    }

    //Update a member
    @PutMapping("/{id}")
    public Member updateMember(@PathVariable Long id, @RequestBody Member member){
        return memberService.updateMember(id, member);
    }







}
