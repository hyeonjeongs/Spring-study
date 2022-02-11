package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //회원이 저장됨
    Optional<Member> findById(Long id); //id 찾아와줌
    Optional<Member> findByName(String name); //name 찾아와줌
    List<Member> findAll(); //저장된 모든 정보 찾아와줌

}
