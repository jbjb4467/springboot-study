package site.jbjb.webservice.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// MyBatis 의 Dao == JPA 의 Repository
// JpaRepository<Entity 클래스, PK 타입>을 상속하면 기본적인 CRUD 메소드가 자동 생성
// Entity 클래스와 기본 Entity Repository 는 함께 위치해야 함
// Entity 클래스는 기본 Repository 없이 제대로 역할을 할 수 없음
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
