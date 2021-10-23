package site.jbjb.webservice.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 클래스 내 모든 필드의 Getter 메소드를 자동생성
@Getter
// 기본 생성자 자동 추가. public Posts() {} 와 같은 효과
@NoArgsConstructor
// 테이블과 링크될 클래스임을 나타냄. 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 테이블 이름을 매칭
// SalesManager.java -> sales_manager table
@Entity
// Entity 클래스에는 Setter 메소드를 만들지 않음
// 해당 클래스의 인스턴스 값이 언제 어디서 변해야 하는지 코드상으로 명확하게 구분할 수 없기 때문
// 필드의 값 변경이 필요하면 목적과 의도를 명확히 나타내는 메소드를 추가
public class Posts {

    // 해당 테이블의 PK 필드
    @Id
    // PK의 생성 규칙
    // 스프링부트 2.0부터는 GenerationType.IDENTITY 옵션을 추가해야 auto increment 가 됨
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 테이블의 칼럼. 굳이 선언하지 않아도 해당 클래스의 필드는 모두 칼럼이 됨
    // 기본값 외에 추가로 변경이 필요한 옵션이 있을 때 사용
    // 문자열은 VARCHAR(255)가 기본값인데, 사이즈를 500으로 늘리거나
    @Column(length = 500, nullable = false)
    private String title;

    // 타입을 TEXT 로 변경할 수 있음
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    // 해당 클래스의 빌더 패턴 클래스를 생성
    // 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
