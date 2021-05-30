package hello.core.autowired;

import hello.core.discount.DiscountPolicy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllBeanTest {

    @Test
    void findAllBean() {
        new AnnotationConfigApplicationContext();
    }

    static class DiscoundService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        public DiscoundService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
        }
    }
}
