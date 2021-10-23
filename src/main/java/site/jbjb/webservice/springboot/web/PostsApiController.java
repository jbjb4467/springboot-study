package site.jbjb.webservice.springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.jbjb.webservice.springboot.service.posts.PostsService;
import site.jbjb.webservice.springboot.web.dto.PostsResponseDto;
import site.jbjb.webservice.springboot.web.dto.PostsSaveRequestDto;
import site.jbjb.webservice.springboot.web.dto.PostsUpdateRequestDto;

// 왜 @Autowired 가 없는가?
// 스프링에서 Bean 을 주입받는 방식
// 1. @Autowired 2. setter 3. 생성자
// 이 중 3. 생성자로 주입받는 방식을 가장 권장함. 1은 권장하지 않음
// @RequiredArgsConstructor 를 통해 final 이 선언된 모든 필드를 인자값으로 하는 생성자를 롬복 어노테이션이 대신 생성
@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id) {
        return postsService.findById(id);
    }
}
