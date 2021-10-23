package site.jbjb.webservice.springboot.web.dto;

import lombok.Getter;
import site.jbjb.webservice.springboot.domain.posts.Posts;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    // Entity 의 필드 중 일부만 사용하므로 생성자로 Entity 를 받아 필드에 값을 넣음
    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
