package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    // @Autowired 시 빈이 중복될 경우 파라미터 이름으로도 매핑을 해준다.
    // private final DiscountPolicy rateDiscountPolicy; 하면 rateDiscountPolicy 주입해줌.


/*
    Lombok @RequiredArgsConstructor 적용: final 필드를 모아서 생성자를 자동으로 만들어준다.

    // 생성자 주입을 사용할 것. 수정자 주입 필요시 같이 사용가능. 생성자가 하나이기 때문에 @Autowired 생략가능
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
