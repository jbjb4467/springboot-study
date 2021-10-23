package site.jbjb.webservice.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 이미 생성된 사용자인지 여부를 email 로 판단하기 위한 메소드
    Optional<User> findByEmail(String email);
}
