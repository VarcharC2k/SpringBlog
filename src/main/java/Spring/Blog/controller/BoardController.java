package Spring.Blog.controller;

import Spring.Blog.config.auth.PrincipalDetail;
import Spring.Blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    //로직 구현을 위하여 임시로 비활성화
//    @GetMapping({"","/"})
//    public String index(@AuthenticationPrincipal PrincipalDetail principal){
//        System.out.println("로그인 사용자 아이디 : "+principal.getUsername());
//        return "index";
//    }

    @Autowired
    private BoardService boardService;

    @GetMapping({"", "/"})
    public String index(Model model) {
        model.addAttribute("boards", boardService.boardList());
        return "index"; //return시 viewResolver 작동 >> 해당 페이지로 Model의 정보를 들고 이동
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

}
