package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// @Repository
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member>store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member); // id를 지정해서 저장한다.
        return member;
    }
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) // getName()과 name이 같은 것을 찾아 필터링한다.
                .findAny(); // 하나라도 찾으면 반환, 없으면 null 반환
    }
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // member들 반환
    }
    public void clearStore(){
        store.clear(); // store를 비워준다.
    }
}

/*
정형화된 패텬
: @Controller(외부 요청을 받음) / @Service(비지니스 로직을 만듦) / @Repository(데이터 저장)
*/
