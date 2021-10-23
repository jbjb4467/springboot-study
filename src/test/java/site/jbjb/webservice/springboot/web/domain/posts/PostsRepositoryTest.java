package site.jbjb.webservice.springboot.web.domain.posts;


import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import site.jbjb.webservice.springboot.domain.posts.Posts;
import site.jbjb.webservice.springboot.domain.posts.PostsRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;
    
    // Junit 에서 단위 테스트가 끝날 때마다 수행되는 메소드
    // 테스트 간 데이터 침범 방지르 위해 사용
    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void get_post() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";
        
        // 테이블 posts 에 insert/update 쿼리를 실행
        // id 값이 있다면 update, 없다면 insert
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("jbjb4467@gmail.com")
                .build());
        
        //when
        // 테이블 posts 에 있는 모든 데이터를 조회해오는 메소드
        List<Posts> postsList = postsRepository.findAll();
        
        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
        
    }
}
