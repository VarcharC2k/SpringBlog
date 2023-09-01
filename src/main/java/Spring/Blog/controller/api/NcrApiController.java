package Spring.Blog.controller.api;

import Spring.Blog.dto.ResponseDto;
import Spring.Blog.model.NcrData;
import Spring.Blog.service.NcrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NcrApiController {

    @Autowired
    private NcrService ncrService;

    @PutMapping("/ncr/insert")
    public void ncrInsert(@RequestBody NcrData ncrData) {

    }
}
