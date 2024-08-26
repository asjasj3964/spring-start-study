package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// @Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    // @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository; // memberRepository를 외부에서 넣어준다.
    }
    // 회원가입
    public Long join (Member member){
        // 같은 이름의 중복 회원은 저장할 수 없다.
        vaildateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member); // 회원 검증 후 통과하면 저장
        return member.getId();
    }

    private void vaildateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m->{
                    throw new IllegalStateException("이미 존재하는 회원입니다. ");
                });
    }

    // 전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
