package Spring.Blog.controller.api;

import Spring.Blog.config.auth.PrincipalDetail;
import Spring.Blog.dto.ResponseDto;
import Spring.Blog.model.RoleType;
import Spring.Blog.model.User;
import Spring.Blog.service.UserService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    //회원가입의 경우 인증이 필요 없기때문에 auth경로로 설정
    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) {
        System.out.println("UserApiController : save 호출됨");
        //실제로 DB에 insert를 하고 아래에서 Retrun이 되면 됨
//        int result = userService.join(user);
        userService.join(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); //200 = 성공적으로 통신했다는 약어, OK를 날릴경우 200을 날림
        //1은 데이터베이스에서 리턴된 결과값으로 넣을 예정
        //자바오브젝트를 JSON으로 변환하여 리턴
    }

    //전통적인 방식의 로그인
//    @PostMapping("/api/user/login")
//    public ResponseDto<Integer> login(@RequestBody User user) {
//        System.out.println("UserApiController : login 호출됨");
//        User principal = userService.login(user); //principal = 접근 주체
//
//        if (principal != null) {
//            session.setAttribute("principal", principal);
//        }
//        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//    }

    @PutMapping("/userUpdate")
    public ResponseDto<Integer> update(@RequestBody User user) {
        userService.updateUser(user);
        // 여기서 트랜잭션이 종료 >> DB 값은 변경이 되지만 Session의 값은 변경되지 않는 상태이기 때문에 세션값을 변경해야 함
        //세션 등록

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication); // 세션에 만들어진 authentication 등록

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
