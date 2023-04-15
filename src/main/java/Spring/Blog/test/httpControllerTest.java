package Spring.Blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//RestController >> 사용자가 요청한 항목에 대해 Data를 응답해줌
//Controller >> 사용자의 요청에 HTML 파일로 응답해줌

@RestController
public class httpControllerTest {

    private static final String TAG = "HttpControllerTest : ";

    @GetMapping("/http/lombok")
    public String lombokTest(){
//        Member m = new Member(1,"ssar","1234","ssal@nate.com");
//        lombok의 builder 패턴으로 다음과 같이 생성자 생성 가능
        //builder 패턴을 쓰면 생성자의 순서를 지키지 않아도 됨
        Member m = Member.builder().username("ssar").password("1234").email("ssar@nate.com").build();
        System.out.println(TAG+"getter : " + m.getUsername());
        m.setUsername("cos");
        System.out.println(TAG+"setter : " + m.getUsername());
        return "lombok test 완료";
    }

    //인터넷 브라우저 요청은 무조건 get 요청을 할 수 밖에 없다
    //http://localhost:8080/http/get
    @GetMapping("/http/get")
    public String getTest(Member m) {
        return "get 요청 : "+ m.getId() + "," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
    }

    //http://localhost:8080/http/post
    @PostMapping("/http/post")
    public String postTest(@RequestBody Member m) {
        return "post 요청 : "+ m.getId() + "," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
//        return "post 요청 : " + text;
    }

    //http://localhost:8080/http/put
    @PutMapping("/http/put")
    public String putTest(@RequestBody Member m) {
        return "put 요청 : " + m.getId() + "," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
    }

    //http://localhost:8080/http/delete
    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "delete 요청";
    }

}
