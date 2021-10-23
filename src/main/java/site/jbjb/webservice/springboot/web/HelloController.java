package site.jbjb.webservice.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.jbjb.webservice.springboot.web.dto.HelloResponseDto;

// 컨트롤러를 JSON 을 반환하는 컨트롤러로 만들어줌
// @ResponseBody 를 메소드마다 선언하던 것을 한 번에 해결해주는 것
@RestController
public class HelloController {

    // HTTP Method Get 요청을 받을 수 있는 API 생성
    // @RequestMapping(method = RequestMethod.GET) 과 같은 것
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    // RequestParam: 외부에서 API 로 넘긴 파라미터를 가져오는 어노테이션
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}
