package Spring.Blog.test;

import lombok.*;

//@Getter
//@Setter
@Data //Getter Setter 동시 완성
//@AllArgsConstructor //모든 필드를 다 쓰는 생성자
@NoArgsConstructor //빈생성자
//@RequiredArgsConstructor //final 이 붙은 인자에 대하여만 쓰는 생성자 사용
@Builder
public class Member {
    private int id;
    private String username;
    private String password;
    private String email;

    public Member(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }


}
