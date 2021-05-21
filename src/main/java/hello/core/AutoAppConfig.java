package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        /*basePackages = "hello.core.member",
        basePackageClasses = AutoAppConfig.class,
         디폴트 값은 이 설정파일의 위치.
         메인 메소드의 @SpringBootApplication 여기 안에 @ComponentScan 이 들어있기 때문에
         스프링부트 애플리케이션을 생성하면 자동으로 디폴트 경로가 지정되서 등록이 됨. */
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
