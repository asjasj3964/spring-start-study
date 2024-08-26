package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;
    @BeforeEach // 동작하기 전에 넣어준다.
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }
    @Test
    void 회원가입() {
        // given 이러한 상황이 주어져서
        Member member = new Member();
        member.setName("hello");
        // when 이러한 기능을 실행했을 때
        Long saveId = memberService.join(member);
        // then 이러한 결과가 나온다.
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예약() {
        // given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // member2를 join 할 때 IllegalStateException이 터져야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다. ");
        /*try{
            memberService.join(member2);
            // 여기까지 진행되면 fail
            fail();
        } catch(IllegalStateException e){ // member2 join이 터지면 실행된다.
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다. ");
        }*/
        // then


    }

    @Test
    void findOne() {
    }
}