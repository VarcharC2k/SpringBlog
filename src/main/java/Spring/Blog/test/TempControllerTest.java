package Spring.Blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

    //thmeleaf에선 /를 안붙여도 return
    @GetMapping("/temp/home")
    public String tempHome(){
        System.out.println("tempHome");
        return "home";
    }

    @GetMapping("/temp/test")
    public String tempTest(){
        System.out.println("tempHome");
        return "views/test";
    }

}
