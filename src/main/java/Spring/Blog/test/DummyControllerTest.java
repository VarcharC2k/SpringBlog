package Spring.Blog.test;

import Spring.Blog.model.RoleType;
import Spring.Blog.model.User;
import Spring.Blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired //DI : 의존성 주입
    private UserRepository userRepository;

    @GetMapping("/dummy/user/{id}")
    public User Detail(@PathVariable int id){

        //Optional의 역할 : Db에서 개체 검색 후 Null이 리턴된 경우
        //해당 객체를 한번 더 감싸서 Null인지 아닌지 판단 후 리턴하는 역할
//        User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
//            @Override
//            public User get() {
//                return new User();
//            }
//        });

//        Optional과 같은 기능을 다음으로 구현 가능함

//        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
//            @Override
//            public IllegalArgumentException get() {
//                return new IllegalArgumentException("해당 유저는 없습니다. id :"+id);
//            }
//        });

        //위 코드를 람다식으로 변경

        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저는 없습니다. id :"+id));

        //스프링 부트에서 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
        //user 오브젝트를 json으로 변환하여 브라우저에게 던져줌
        return user;
    }

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
