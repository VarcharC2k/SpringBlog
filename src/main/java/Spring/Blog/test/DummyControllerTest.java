package Spring.Blog.test;

import Spring.Blog.model.RoleType;
import Spring.Blog.model.User;
import Spring.Blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyControllerTest {

    @Autowired //DI : 의존성 주입
    private UserRepository userRepository;

    @PostMapping("/dummy/join")
//    public String join(String username, String password, String email){
    //Model 객체로 바로 받을수도 있음
    public String join(User user){
        System.out.println("useranme : " + user.getUsername());
        System.out.println("password : " + user.getPassword());
        System.out.println("email : " + user.getEmail());

        user.setRole(RoleType.USER);
        userRepository.save(user);

        return "회원가입이 완료되었습니다.";
    }
}
