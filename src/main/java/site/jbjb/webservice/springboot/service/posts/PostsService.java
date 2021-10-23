package site.jbjb.webservice.springboot.service.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.jbjb.webservice.springboot.domain.posts.Posts;
import site.jbjb.webservice.springboot.domain.posts.PostsRepository;
import site.jbjb.webservice.springboot.web.dto.PostsResponseDto;
import site.jbjb.webservice.springboot.web.dto.PostsSaveRequestDto;
import site.jbjb.webservice.springboot.web.dto.PostsUpdateRequestDto;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    // db 에 쿼리를 날리지 않아도 되는 것은 JPA 의 영속성 컨테스트 때문
    // 영속성 컨테스트란 엔티티를 영구 저장하는 환경
    // JPA 의 EntityManager 가 활성화된 상태(Spring Data Jpa 의 기본 옵션)로 트랜잭션 안에서 DB 의 데이터를 가져온다면
    // 이 데이터는 영속성 컨테스트가 유지된 상태. 이 상태에서 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경을 반영
    // 즉, Entity 객체의 값만 변경하면 Update 쿼리를 날릴 필요가 없음(dirty checking)
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "해당 게시글이 없습니다. id="+ id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
}
