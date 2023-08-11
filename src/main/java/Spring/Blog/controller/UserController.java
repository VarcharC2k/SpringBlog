package Spring.Blog.controller;

import Spring.Blog.model.KakaoProfile;
import Spring.Blog.model.OAuthToken;
import Spring.Blog.model.User;
import Spring.Blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLOutput;
import java.util.UUID;


//인증을 위한 auth 추가
//인증이 안된 사용자들이 출입할 수 있는 경로 >> /auth/**허용
//그냥 주소가 / 이면 index.jsp 허용
//static 이하에 있는 리소스 파일 허용
@Controller
public class UserController {

    @Value("${cos.key}")
    private String cosKey;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private UserService userService;

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
    public String kakaoCallback(String code) { //@ResponseBody는 data를 리턴해주는 컨트롤러 함수로 만들어 줌

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

        //Gson, Json Simple, ObjectMapper 라이브러리 사용 가능 이중 objectMapper 사용
        ObjectMapper obMapper = new ObjectMapper();

        OAuthToken oAuthToken = null;
        try {
            oAuthToken = obMapper.readValue(response.getBody(), OAuthToken.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println(oAuthToken.getAccess_token());

        RestTemplate pfrt = new RestTemplate();

        //HttpHeader 오브젝트 생성
        HttpHeaders pfHeaders = new HttpHeaders();
        pfHeaders.add("Authorization","Bearer "+ oAuthToken.getAccess_token());
        pfHeaders.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(pfHeaders);

        //Http 요청하기 - Post 방식으로 - Response 변수의 응답을 받음
        ResponseEntity<String> pfResponse = pfrt.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST,
                kakaoProfileRequest, String.class);

        ObjectMapper pfobMapper = new ObjectMapper();

        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = pfobMapper.readValue(pfResponse.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //user 오브젝트 : username, password, email
        System.out.println("카카오 아이디 : " + kakaoProfile.getId());
        System.out.println("카카오 이메일 : " + kakaoProfile.getKakao_account().getEmail());

        //현재 프로젝트의 user에 맞도록 데이터를 입력해 주어야 함
        System.out.println("카카오 유저네임 : "+kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId());
        System.out.println("이메일 : " + kakaoProfile.getKakao_account().getEmail());
//        UUID tempPassword = UUID.randomUUID(); // 패스워드를 위해여 랜덤 값을 생성
        //UUID 란 중복되지 않는 어떤 특정 값을 만들어내는 알고리즘
        //즉, 비밀번호로 사용할 수 없음 >> 생성될때마다 값이 달라지기 때문
        System.out.println("패스워드 : " + cosKey);

        //유저정보를 담아 blog 프로젝트의 회원가입을 시켜줌
        //가입자인지 비가입인지 체크 필요

        User kakaoUser = User.builder()
                .username(kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId())
                .password(cosKey)
                .email(kakaoProfile.getKakao_account().getEmail()).build();

        //가입 유저 확인
        User originUser = userService.findUser(kakaoUser.getUsername());

        //미가입자면 회원 가입
        if(originUser.getUsername() == null){
            System.out.println("기존 회원이 아닙니다.");
            userService.join(kakaoUser);
        }

        //회원인 경우 로그인 처리
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
        SecurityContextHolder.getContext().setAuthentication(authentication); // 세션에 만들어진 authentication 등록
        System.out.println("로그인 처리 완료");

        return "redirect:/";
    }
}
