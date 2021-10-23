package site.jbjb.webservice.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 스프링부트의 자동설정, 스프링 Bean 읽기, 생성 모두 자동으로 설정
// 이 위치부터 설정을 읽기 때문에 항상 프로젝트의 최상단에 있어야 함
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // 내장 WAS 실행
        SpringApplication.run(Application.class, args);
    }
}
