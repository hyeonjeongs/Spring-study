package hello.hellospring.service;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberservice = new MemberService();
    @Test
    void 회원가입() {
        //given (이런 상황이 주어져서 - 어떤 데이터 기반)
        Member member = new Member();
        member.setName("hello");

        //when(뭔가 주어졌을떄 - 어떤 것을 검증)
        Long saveId = memberservice.join(member);

        //then(결과가 어떤것이 나와야함)
        Member findMember = memberservice.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("hello");


        //when
        Long saveId = memberservice.join(member1);


        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}