package com.victorhugo.familyservicemanager.repository;

import com.victorhugo.familyservicemanager.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, Long> {
}
