package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 회원을 저장소에 저장
    Optional<Member> findById(Long id); // 저장소에서 회원을 id로 찾는다.
    Optional<Member> findByName(String name); // 저장소에서 회원을 name으로 찾는다.
    List<Member> findAll(); // 지금까지 저장된 모든 회원 리스트를 반환한다.
}
