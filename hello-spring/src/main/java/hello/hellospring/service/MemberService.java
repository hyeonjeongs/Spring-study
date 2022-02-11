package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService {
    
    private final MemberRepository memberRepository;


    @Autowired
    public MemberService(MemberRepository memberRepository) { //Memberservice를 외부에서 넣어주도록설정

        this.memberRepository = memberRepository;
    }

    /**
     *회원가입
     */
    public Long join(Member member){
        //같은 이름이 있는 중복회원 x
        validateDuplicateMember(member);

        memberRepository.save(member);//중복회원 검증후 저장
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {//null이 아니고 값이 있으면 동작하는것
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
