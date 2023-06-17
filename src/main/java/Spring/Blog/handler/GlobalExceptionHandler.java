package Spring.Blog.handler;

import Spring.Blog.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@ControllerAdvice //모든 Exception이 발생한 경우 해당 클래스로 들어오게 됨
@RestController
public class GlobalExceptionHandler {

//    특정한 Exception만 받는 경우
//    @ExceptionHandler(value = IllegalArgumentException.class) //IllegalArgumentException이 들어오는 경우 해당 동작 수행
//    public String handleArgumentException(IllegalArgumentException e) {
//        return "<h1>" + e.getMessage() + "</h1>";
//    }

    @ExceptionHandler(value = Exception.class)
    public ResponseDto<String > allException(Exception e) {
        return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
    }
}
