package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    // @Autowired 시 타입 매칭이 먼저고 그 다음 파라미터 이름으로도 매칭을 해준다.
    // 빈 중복 시 private final DiscountPolicy rateDiscountPolicy; 하면 rateDiscountPolicy 주입해줌.
    // 지금은 RateDiscountPolicy 에 직접만든 Qualifier > MainDiscountPolicy 어노테이션을 붙여준 상태.

/*
    Lombok @RequiredArgsConstructor 적용: final 필드를 모아서 생성자를 자동으로 만들어준다.

    // 생성자 주입을 사용할 것. 수정자 주입 필요시 같이 사용가능. 생성자가 하나이기 때문에 @Autowired 생략가능
    // 아래 코드를 롬복이 생성해줌.
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // 생성자 주입 시 직접만든 어노테이션 적용하기
    public OrderServiceImpl(MemberRepository memberRepository,
                            @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // 수정자 주입
    @Autowired
    public DiscountPolicy setDiscountPolicy(@MainDiscountPolicy DiscountPolicy discountPolicy) {
        return discountPolicy;
    }
*/

    public OrderServiceImpl(MemberRepository memberRepository,
                            @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

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
