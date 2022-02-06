package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>(); //저장할 메모리 저장(key는 id고 값은 Member로)
    private static long sequence =0L; //0,1,2 같이 key값을 생성해주는것

   @Override
    public Member save(Member member) {
       member.setId(++sequence); //시스템이 id 만들어줌
       store.put(member.getId(),member); //저장해줌
        return member; //저장한 결과 반환
    }

    @Override
    public Optional<Member> findById(Long id) { //id를 store에서 꺼내기
        return Optional.ofNullable(store.get(id)); //null이어도 반환 가능하게 해줌
    }

    @Override
    public Optional<Member> findByName(String name) {

        return store.values().stream() //루프를 돌림
                .filter(member -> member.getName().equals(name)) //member에서 가져온 이름이 변수로 가져온 name이랑 같으면 filter되고
                .findAny(); //찾으면 반환됨(끝까지 돌렸는데 없으면 null반환(optional)
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //store에 있는 member들 반환(hash에서 <key,value>)
    }

    public void clearStore(){
       store.clear();
    }
}
