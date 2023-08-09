package Spring.Blog.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;



//인증을 위한 auth 추가
//인증이 안된 사용자들이 출입할 수 있는 경로 >> /auth/**허용
//그냥 주소가 / 이면 index.jsp 허용
//static 이하에 있는 리소스 파일 허용
@Controller
public class UserController {

    @GetMapping("/auth/joinForm")
    public String joinForm() {

        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm() {

        return "user/loginForm";
    }

    @GetMapping("user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }

    @GetMapping("auth/kakao/callback")
    public @ResponseBody String kakaoCallback(String code) { //@ResponseBody는 data를 리턴해주는 컨트롤러 함수로 만들어 줌

        // Post 방식으로 key-value 타입의 데이터를 요청을 해야함 (카카오 쪽으로)
        // 과거 자바에서 HttpsURLConnection을 사용하여 요청하였지만, SpringBoot에서는 RestTemplate 라이브러리를 제공
        //Retrofit2(안드로이드에서 많이 사용) 혹은 OkHttp 라이브러리로도 가능

        RestTemplate rt = new RestTemplate();

        //HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        //HttpBody 오브젝트 생성
        //원래는 바디에 넣을 데이터는 다 변수로 담아야 하지만, 테스트를 위해서 직접 박아넣음
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "6bb73ba206e135a83cd0d4243fb79ce7");
        params.add("redirect_uri","http://localhost:8000/auth/kakao/callback");
        params.add("code", code);

        //HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        //exchange 함수가 HttpEntity를 받도록 되어있기 때문에 해당 오브젝트로 넣어줌
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        //Http 요청하기 - Post 방식으로 - Response 변수의 응답을 받음
        ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST,
                kakaoTokenRequest, String.class);

        return "카카오 인증 완료 : 토큰에 대한 응답 : " +  response;
    }
}
