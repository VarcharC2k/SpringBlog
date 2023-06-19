package Spring.Blog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Timestamp;

//User 클래스가 자동으로 mysql에 Table화
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
//@DynamicInsert //인서트시 null인 필드를 제거
public class User {

    @Id
    //프로젝트에서 연결된 DB의 넘버링 전략을 따름
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,length = 30,unique = true) //Null값 들어올 수 없음, 최대 30자, 중복값 제거
    private String username;

    @Column(nullable = false,length = 100)//Hash 변환을 위하여 넉넉하게 줌
    private String password;

    @Column(nullable = false,length = 50)
    private String email;

//    @ColumnDefault("'user'") //Default를 User로 설정, 문자열인것을 알리기 위해 '를 붙여주어야 함

    //DB에는 RoleType이 없기때문에 Enum의 타입을 알려주어야 함
    @Enumerated(EnumType.STRING)
    private RoleType role; //역할의 경우 Enum을 사용하는것이 좋음

    @CreationTimestamp //자동으로 시간을 입력
    private Timestamp createDate;

}
