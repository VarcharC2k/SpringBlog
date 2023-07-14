package Spring.Blog.config;
import Spring.Blog.config.auth.PrincipalDetailService;
import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration // IoC
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private PrincipalDetailService principalDetailService;

    @Bean //Ioc가 되어 Spring이 관리
    public BCryptPasswordEncoder encodePWD() {
//        String encPassword = new BCryptPasswordEncoder().encode(); //Endcode를 통하여 들어온 String 값을 해쉬로 암호화 함

        return new BCryptPasswordEncoder();
    }

    //시큐리티가 대신 로그인을 해주는 경우, password를 가로채기를 하는데 해당 password가 뭘로 해쉬가 되어서
//    회원가입이 되었는지 알아야 같은 해쉬로 암호화해서 DB에 있는 해쉬와 비교를 할 수 있음
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
//    }


    //2차 로그인 패스워드 비교 테스트 코드
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
//        auth.setUserDetailsService(principalDetailService);
//        auth.setPasswordEncoder(encodePWD());
//
//        System.out.println("패스워드 확인");
//        return auth;
//    }
//
//    @Bean
//    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .authenticationProvider(authenticationProvider())
//                .build();
//    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//        return configuration.getAuthenticationManager();
//    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable() //csrf 토큰 비활성화 (테스트 시 걸어두는게 좋음)
//                .authorizeHttpRequests()
//                .requestMatchers("/", "/auth/**", "/WEB-INF/**", "/js/**", "/css/**", "/image/**")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//
        http.csrf().disable().cors().disable()
                .httpBasic().disable() // 기본인증 기능 비활성화
                .headers().frameOptions().disable() // 자체 X-Frame-Options Deny 비활성화
                .and()
                .authorizeHttpRequests(request -> request
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers("/","/status", "/images/**", "/css/**", "/auth/**","/js/**").permitAll()
                        .anyRequest().authenticated()
                )

                .formLogin(login -> login
                        .loginPage("/auth/loginForm")
                        .loginProcessingUrl("/auth/loginProc") //스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인 해줌
                        .defaultSuccessUrl("/", true)
//                        .failureUrl("/")
                        .permitAll()
                );
//                .logout(Customizer.withDefaults());

        return http.build();
    }


    //비밀번호 3차 비교 테스트 코드
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
//
//        System.out.println("비밀번호 매칭 확인");
//        return auth.build();
//    }


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