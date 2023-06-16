package Spring.Blog.controller.api;

import Spring.Blog.dto.ResponseDto;
import Spring.Blog.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user){
        System.out.println("UserApiController : save 호출됨");
        //실제로 DB에 insert를 하고 아래에서 Retrun이 되면 됨
        return new ResponseDto<Integer>(HttpStatus.OK,1); //200 = 성공적으로 통신했다는 약어, OK를 날릴경우 200을 날림
        //1은 데이터베이스에서 리턴된 결과값으로 넣을 예정
        //자바오브젝트를 JSON으로 변환하여 리턴
    }
}
