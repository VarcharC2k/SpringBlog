package Spring.Blog.config.auth;

import Spring.Blog.model.User;
import Spring.Blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

//    스프링이 로그인 요청을 가로챌 때, username, password라는 2개의 변수를 가로채는데
//    password 부분 처리는 알아서 하기 때문에, Username이 DB에 있는지만 확인해해주면 됨
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principal = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : " + username);
                });
        return new PrincipalDetail(principal); //시큐리티 세션에 유저 정보가 저장 됨
    }
}