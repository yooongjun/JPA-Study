package jpaboook.jpashop.service;

import jpaboook.jpashop.domain.Member;
import jpaboook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    // @Transactional은 test에 있으면 기본적으로 Rollback
    @Test
    public void 회원가입() throws Exception {
        //given ~게 주어졌을 때
        Member member = new Member();
        member.setName("kim");

        //when ~게 하면
        Long savedId = memberService.join(member);

        //then ~게 나와야해
        em.flush();
        assertEquals(member, memberRepository.findOne(savedId));

    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim1");

        Member member2 = new Member();
        member2.setName("kim1");

        //when
        memberService.join(member1);
//        try {
        memberService.join(member1);
//        } catch (IllegalStateException e) {
//            return;
//        }
        //then
        // fail: 코드가 오드가 여기 오면 안 됨.
        fail("예외가 발생해야 한다.");

    }

}