package site.jbjb.webservice.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.jbjb.webservice.springboot.domain.posts.Posts;

// Posts 의 Entity 클래스와 거의 유사하지만 새로 생성
// Entity 클래스는 절대로 Request/Response 클래스로 사용해서는 안 됨
// Entity 를 기준으로 테이블이 생성되고, 스키마가 변경되기 때문
// View Layer 와 DB Layer 의 역할 분리를 꼭 해줘야 함
// 여러 테이블을 조인해야 할 경우도 있음

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {

        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
