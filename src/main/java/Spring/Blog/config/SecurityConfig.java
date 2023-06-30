package Spring.Blog.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

@Configuration // IoC
@EnableWebSecurity
public class SecurityConfig {

    @Bean //Ioc가 되어 Spring이 관리
    public BCryptPasswordEncoder encodePWD() {
//        String encPassword = new BCryptPasswordEncoder().encode(); //Endcode를 통하여 들어온 String 값을 해쉬로 암호화 함

        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //csrf 토큰 비활성화 (테스트 시 걸어두는게 좋음)
                .authorizeHttpRequests()
                    .requestMatchers("/","/auth/**","/WEB-INF/**","/js/**","/css/**","/image/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                    .loginPage("/auth/loginForm")
                    .permitAll();
//
//        http
//                .sessionManagement()
//                .invalidSessionUrl("/auth/loginForm");

        return http.build();
    }

//   @Bean
//    public void configure(WebSecurity web) throws Exception {
//        web.httpFirewall(defualtHttpFirewall());
//    }
//
//    @Bean
//    public HttpFirewall defualtHttpFirewall() {
//        return new DefaultHttpFirewall();
//    }

}