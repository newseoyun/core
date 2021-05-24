package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        @Autowired(required = false) // 자동주입 대상이 없으면 메서드 자체가 호출되지 않음.
        public void setNoNean1(Member noNean1) {
            System.out.println("noNean1 = " + noNean1);
        }

        @Autowired
        public void setNoNean2(@Nullable Member noNean2) {
            System.out.println("noNean2 = " + noNean2);
        }

        @Autowired
        public void setNoNean3(Optional<Member> noNean3) {
            System.out.println("noNean3 = " + noNean3);
        }

    }
}
