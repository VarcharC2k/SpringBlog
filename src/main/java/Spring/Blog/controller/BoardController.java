package Spring.Blog.controller;

import Spring.Blog.config.auth.PrincipalDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    //로직 구현을 위하여 임시로 비활성화
//    @GetMapping({"","/"})
//    public String index(@AuthenticationPrincipal PrincipalDetail principal){
//        System.out.println("로그인 사용자 아이디 : "+principal.getUsername());
//        return "index";
//    }

    @GetMapping({"", "/"})
    public String index() {
        return "index";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }
//    @GetMapping("/home")
//    public String goHome() {
//        return "content/home";
//    }

}
