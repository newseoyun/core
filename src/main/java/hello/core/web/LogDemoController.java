package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;

    /*
    private final ObjectProvider<MyLogger> myLoggerProvider;
    // MyLogger 의 scope 가 request 이므로  http 요청이 있을 때 싱글턴 빈을 생성하므로,
    // 기동 시엔 http 요청이 있을 수 없으므로 state 오류남.
    // 그래서 ObjectProvider 로 감싸줌 (호출 할 때까지 빈 생성 지연 기능)
    // 프록시 객체를 생성해서 주입되도록 하는 방식을 사용하면 ObjectProvider 안써도 되고 더 깔끔함

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURL().toString();
        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        Thread.sleep(1000);

        logDemoService.logic("testId");
        return "OK";
    }

    */

    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURL().toString();

        System.out.println("myLogger.getClass() >>>" + myLogger.getClass());
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        Thread.sleep(1000);

        logDemoService.logic("testId");
        return "OK";
    }

}
