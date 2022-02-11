package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberservice;
    MemoryMemberRepository memberRepository;

    @BeforeEach //실행하기전에
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberservice = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore(); //돌릴때마다 새롭게 비워주기
    }


    @Test
    void 회원가입() {
        //given (이런 상황이 주어져서 - 어떤 데이터 기반)
        Member member = new Member();
        member.setName("hello");

        //when(뭔가 주어졌을떄 - 어떤 것을 검증)
        Long saveId = memberservice.join(member);

        //then(결과가 어떤것이 나와야함)
        Member findMember = memberservice.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");


        //when
        memberservice.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberservice.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*try{
            memberservice.join(member2);
            fail();
        }catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}