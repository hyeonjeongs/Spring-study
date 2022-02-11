package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.apache.catalina.Store;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //메소드 끝날때마다 호출되는 함수라는 것
    public void afterEach(){
        repository.clearStore();

    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring"); //이름설정해줌

        repository.save(member); //리포지토리에 저장
        Member result = repository.findById(member.getId()).get();//멤버가 정확히 저장됐는지 확인
        //Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result);//member가 result랑 같은가
    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        Member result = repository.findByName("spring1").get(); //findByName으로 제대로 찾아졌는가
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring3");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2); //member 2개 맞는지 확인
    }

}
