package Spring.Blog.controller.api;

import Spring.Blog.dto.ResponseDto;
import Spring.Blog.model.NcrData;
import Spring.Blog.model.User;
import Spring.Blog.service.NcrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class NcrApiController {

    @Autowired
    private NcrService ncrService;

    @PostMapping("/ncr/insert")
    public ResponseDto<Integer> save(@RequestBody NcrData ncrData) {
        System.out.println("ncr API Test");
        String ncr = ncrData.getRoot().getContainerName();
        System.out.println(ncr);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); //200 = 성공적으로 통신했다는 약어, OK를 날릴경우 200을 날림
    }

    @GetMapping("/ncr/check")
    public String ncrCheck(){
        System.out.println("check");
        return "/";
    }
}