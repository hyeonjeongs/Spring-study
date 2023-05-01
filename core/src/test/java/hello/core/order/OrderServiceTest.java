package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder(){
        //given
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);

        //when
        memberService.join(member); // 회원저장
        Order order = orderService.createOrder(memberId, "itemA", 10000); // 회원등급에 따라 할인 금액 달라짐

        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
