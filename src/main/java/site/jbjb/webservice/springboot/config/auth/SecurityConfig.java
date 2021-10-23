package site.jbjb.webservice.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import site.jbjb.webservice.springboot.domain.user.Role;

@RequiredArgsConstructor
// Spring Security 설정 활성화
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // h2-console 화면을 사용하기 위해 해당 옵션 disable
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    // URL 별 권한 관리 설정 옵션. authorizeRequests 가 선언되어야 antMatches 옵션 사용 가능
                    .authorizeRequests()
                    // 권한 관리 대상 지정 옵션.
                    // URL, HTTP 메소드별 관리 가능
                    // "/" 등 지정된 URL 들은 permitAll() 옵션을 통해 전체 열람 권한 지정
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    // 설정된 값 이외의 URL 설정
                    // authenticated() 로 나머지 URL 들은 모두 인증된 사용자, 즉 로그인 사용자만 허용
                    .anyRequest().authenticated()
                .and()
                    // 로그아웃 기능 설정 진입점
                    .logout()
                        // 로그아웃 성공 시 "/" 주소로 이동
                        .logoutSuccessUrl("/")
                .and()
                    // OAuth2 로그인 기능 설정 진입점
                    .oauth2Login()
                        // OAuth2 로그인 성공 후 사용자 정보 가져올 때 설정
                        .userInfoEndpoint()
                            // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체 등록
                            // 리소스 서버(소셜 서비스)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시
                            .userService(customOAuth2UserService);
    }
}
