package site.jbjb.webservice.springboot.web;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// 테스트를 진행할 때 JUnit 에 내장된 실행자 외에 다른 실행자 실행
// SpringRunner 라는 스프링 실행자 사용. 즉, 스프링부트 테스트와 JUnit 사이의 연결자 역할
@RunWith(SpringRunner.class)
// Web(Spring MVC)에 집중할 수 있는 어노테이션.
// @Controller, @ControllerAdvice 등을 사용할 수 있고, @Service, @Component, @Repository 등은 사용할 수 없음
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    // 스프링이 관리하는 빈 주입
    @Autowired
    // 웹 API 테스트 시 사용. 스프링 MVC 테스트의 시작점
    // HTTP GET, POST 등에 대한 API 테스트를 할 수 있음
    private MockMvc mvc;

    @Test
    public void return_hello() throws Exception {
        String hello = "hello";

        // MockMvc 를 통해 /hello 주소로 HTTP GET 요청
        // 체이닝을 지원해서 여러 검증 기능을 이어서 선언할 수 있음
        mvc.perform(get("/hello"))
                // mvc perform 의 결과 중 http header 의 status 를 검증
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void return_helloDto() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }

}
