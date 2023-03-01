package com.example.wis.member;

public interface MemberService {

    void save(Member member);

    Member findById(Long memberId);
}
