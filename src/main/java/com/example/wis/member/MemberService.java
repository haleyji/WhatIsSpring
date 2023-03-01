package com.example.wis.member;

public interface MemberService {

    void signup(Member member);

    Member getById(Long memberId);
}
