package Spring.Blog.config.auth;

import Spring.Blog.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

//스프링 시큐리티가 로그인 요청을 가로채고 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
//스프링 스큐리티의 고유한 세션 저장소에 저장을 해준다.
public class PrincipalDetail implements UserDetails {
    private User user; //콤포지션 (객체를 품고 있는 것)

    public PrincipalDetail(User user) {
        this.user = user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    //계정이 만료되지 않았는지 리턴 (true : 만료 안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 잠겨있지 않는지 리턴한다 (true : 잠기지 앟음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

//비밀번호가 만료되지 않았는지 리턴한다 (true : 만료안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

//    계정이 활성화(사용가능)인지 리턴한다 (true : 활성화)
    @Override
    public boolean isEnabled() {
        return false;
    }

    //계정이 가지고 있는 권한 목록을 리턴한다. (권한이 여러개 있을 수 있어서 루프를 돌아야 하지만
//    현재 예시에서 한가지 권한만 존재하기 때문에 따로 로프를 돌리지 않는다)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collectors = new ArrayList<>();
        collectors.add(() -> {
            return "ROLE_" + user.getRole();
        });

        return collectors;
    }
}
