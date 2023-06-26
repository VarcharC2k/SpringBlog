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

    @Bean
    BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                    .requestMatchers("/auth/**","/WEB-INF/**")
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