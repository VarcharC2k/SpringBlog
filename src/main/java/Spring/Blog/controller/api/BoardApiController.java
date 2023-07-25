package Spring.Blog.controller.api;

import Spring.Blog.config.auth.PrincipalDetail;
import Spring.Blog.dto.ResponseDto;
import Spring.Blog.model.Board;
import Spring.Blog.model.User;
import Spring.Blog.service.BoardService;
import Spring.Blog.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

    //Board 생성시 User 정보가 필요하므로 User 넘기기
    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){

        boardService.write(board,principal.getUser()); //principal에 저장되어 있는 유저정보 넘기기
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteByid(@PathVariable int id){
        boardService.boardDelete(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
}
