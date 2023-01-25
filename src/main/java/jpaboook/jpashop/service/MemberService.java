package jpaboook.jpashop.service;

import jpaboook.jpashop.domain.Member;
import jpaboook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // public 붙은 메소드에 붙여줌
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @Transactional
    public Long join( Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    // was에 여러 개가 떠서 충분히 동시에 요청이 가능함.
    // 만약 MemberA라는 두명이 동시에 요청하는 경우 둘 다 이 메소드를 통과할 수 있으므로,
    // 실제 개발 단계에서는 더 방어해야함 --> 이런 멀티스레드 고려하여 DB에 유니크 제약조건을 걸어버리기
    private void validateDuplicateMember(Member member) {
        // 중복이면 EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    // 회원 전체 조회
    private List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }


}
